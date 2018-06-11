package com.example.snt1206.mylife;


import java.util.List;

/**
 * Created by tao.j on 2016/8/21.
 */
public class LoginResponse extends BaseResponse {
    String uid;
    String access_token;
    String hubbleToken;
    String username;
    String email;
    String avatarUrl;
    //List<Home> homeList;

    public String getUid() {
        return uid;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getHubbleToken() {
        return hubbleToken;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }



    @Override
    public String toString() {
        return "LoginResponse{" +
                super.toString() +
                "uid='" + uid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", hubbleToken='" + hubbleToken + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
