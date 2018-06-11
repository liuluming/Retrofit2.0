package com.example.snt1206.mylife;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.lang.ref.SoftReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tao.j on 16-08-22.
 */
public abstract class HttpResponseCallback<T extends BaseResponse> implements Callback<T>{

    private SoftReference<Context> mContext;
    private final String TAG;

    public HttpResponseCallback(Context context, String tag) {
        mContext = new SoftReference<>(context);
        if (tag.length() > 23) {
            TAG = tag.substring(0, 22);
        } else {
            TAG = tag;
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            BaseResponse baseResponse = response.body();
            if(baseResponse != null && baseResponse.getErrorId() == 0){
                onNormalResponse((T) baseResponse);
            }else if(baseResponse != null){
                onErrorResponse(baseResponse);
            }else{
                //ToastUtils.showLongToast(mContext.get(), "The Response body is null.");
            }
        }else{
            int errorCode = response.code();
            okhttp3.ResponseBody errorBody = response.errorBody();
            try {
                //ToastUtils.showShortToast(mContext.get(), "Error code:"+errorCode+", error body:"+errorBody.string());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                onErrorResponseHandled();
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d(TAG, "onFailure "+t.getMessage());
        //ToastUtils.showLongToast(mContext.get(), "Network exception:"+t.getMessage());
        onErrorResponseHandled();
    }

    private void onErrorResponse(BaseResponse response){
        handleErrorResponse(response);
        onErrorResponseHandled();
    }

    protected void handleErrorResponse(BaseResponse response){
        int errorId = response.getErrorId();
        String errorMsg = response.getErrorMsg();
        String errorField = response.getErrorField();
        //token is invalid, let user login again.
        if(errorId == 11){
            //ToastUtils.showShortToast(mContext.get(), R.string.token_is_invalid);
            stopXmppService();
            deleteCacheData();
            //UserDataManager.getInstance().clearUserInfo();
            Intent intent = new Intent();
            //intent.setClass(mContext.get(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
            return;
        }

        if (mContext.get() != null) {
            //ToastUtils.showShortToast(mContext.get(), "["+errorId+"]["+errorMsg+"]["+errorField+"]");
        }

    }
    /*
    * delete homes of current user in sqlite
    * */
    private void deleteCacheData() {
       // String uid = UserDataManager.getInstance().getUserId();
        //int homeCount = mContext.get().getContentResolver().delete(LifeDataContract.HomeEntry.CONTENT_URI, LifeDataContract.HomeEntry.COLUMN_UID + " = ?", new String[]{uid});
        //Log.d(TAG, "deleteCacheData home: " + homeCount);
    }

    private void stopXmppService(){
        Log.d(TAG,"stop xmpp service Context = "+mContext.get());
        Intent intent = new Intent();
       // intent.setClass(mContext.get(), NotificationService.class);
        mContext.get().stopService(intent);
    }

    public abstract void onNormalResponse(T t);

    public abstract void onErrorResponseHandled();

}
