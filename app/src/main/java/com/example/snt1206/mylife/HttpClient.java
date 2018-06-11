package com.example.snt1206.mylife;

import android.util.Log;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tao.j on 2016/7/26.
 */
public class HttpClient {

    private static LifeService sLifeService;

    private static String sToken = null;
    private static String sUrl;

    private static void createService(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(12, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.i("HttpClient", "intercept....");
                Request request = chain.request();
                if(sToken != null) {
                    request = request.newBuilder()
                            .addHeader("Authorization", "key=" + LifeService.HEADER_AUTHORIZATION_KEY+";token=" + sToken)
                            .build();
                }else{
                    request = request.newBuilder()
                            .addHeader("Authorization", "key=" + LifeService.HEADER_AUTHORIZATION_KEY)
                            .build();
                }
                Response response = chain.proceed(request);
                Log.d("HttpClient", "response ="+response.toString());
                return response;
            }
        });
        builder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient client = builder.build();

        Log.d("HttpClient", "interceptor size ="+client.interceptors().size());
        List<Interceptor> interceptorList = client.interceptors();
        for (Interceptor interceptor : interceptorList){
            Log.d("HttpClient", "interceptor ="+interceptor.toString());

        }
        client.interceptors().size();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sLifeService = retrofit.create(LifeService.class);
    }

    private HttpClient(){
    }

    public static LifeService getService(){
        if(sLifeService == null){
           /* sToken = UserDataManager.getInstance().getAccessToken();
            sUrl = UserDataManager.getInstance().getServerUrl();*/
            createService();
        }
        return sLifeService;
    }


}
