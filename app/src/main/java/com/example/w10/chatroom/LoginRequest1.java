package com.example.w10.chatroom;

/**
 * Created by W10 on 12/10/2017.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest1 extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.137.205/facultylogin.php";
    private Map<String, String> params;

    public LoginRequest1(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}