package com.example.snt1206.mylife;

/**
 * Created by tao.j on 2016/8/21.
 */
public class LoginRequestBody {
    String username;
    String password;

    public LoginRequestBody(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
