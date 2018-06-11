package com.example.snt1206.mylife;

/**
 * Created by tao.j on 2016/8/21.
 */
public class BaseResponse {
    int error_id;
    String error_msg;
    String error_field;

    public int getErrorId() {
        return error_id;
    }
    public String getErrorMsg() {
        return error_msg;
    }
    public String getErrorField()
    {
        return error_field;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "error_id=" + error_id +
                ", error_msg='" + error_msg + '\'' +
                ", error_field='" + error_field + '\'' +
                '}';
    }
}
