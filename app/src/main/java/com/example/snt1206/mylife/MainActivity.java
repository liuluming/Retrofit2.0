package com.example.snt1206.mylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onLoginButtonClick(View view) {
        /*String username = mAccountEdit.getText().toString().trim();
        String password =mPasswdEdit.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            //ToastUtils.showShortToast(this, R.string.account_name_or_password_is_missing);
            return;
        }*/

        final LoginRequestBody requestBody = new LoginRequestBody("123", "123");
        LifeService service = HttpClient.getService();
        Call<LoginResponse> call = service.login(requestBody);
        HttpResponseCallback<LoginResponse> responseCallback = new HttpResponseCallback<LoginResponse>(this, "") {
            @Override
            public void onNormalResponse(LoginResponse loginResponse) {
               // Log.d(TAG, "onNormalResponse " + loginResponse);
                //ToastUtils.showShortToast(LoginActivity.this, R.string.login_success);
                //saveUserInfo(loginResponse);
                //mHandler.sendEmptyMessage(MSG_LOGIN_SUCCESS);
            }

            @Override
            public void onErrorResponseHandled() {
               // Log.d(TAG, "onErrorResponseHandled ");
                //ToastUtils.showShortToast(LoginActivity.this, R.string.login_failed);
               // ProgressDialogUtils.dismissProgressDialog();
            }
        };
        call.enqueue(responseCallback);
        //ProgressDialogUtils.showProgressDialog(this, getString(R.string.logining_in));
    }
}
