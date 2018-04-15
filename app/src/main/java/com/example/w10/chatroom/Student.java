package com.example.w10.chatroom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Student extends AppCompatActivity {
    String name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        final EditText editText=(EditText) findViewById(R.id.editText);
       final EditText editText2=(EditText) findViewById(R.id.editText2);

        Button button=(Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editText.getText().toString();
                password= editText2.getText().toString();
getToken();

                if(name.isEmpty() || password.isEmpty())
                    Toast.makeText(Student.this,"Plz fill the required parameters",Toast.LENGTH_LONG).show();
                else
                {
                    Intent i=new Intent(Student.this,StudentMain.class);
                    i.putExtra("roll_no",name);
                    startActivity(i);
                }

            }
        });


    }

    public void getToken()
    {
        String app_server_url = "http://192.168.137.205/fcmtest1/fcm_insert.php";
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
        Toast.makeText(Student.this,token+"y",Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fcm_token", token);
                params.put("user_name",name);
                //params.put("email",em);
                return params;
            }
        };
          MySingleton.getmInstance(Student.this).addToRequestque(stringRequest);
        //Toast.makeText(StudentMain.this,"You have Registered Successfully...",Toast.LENGTH_LONG).show();
    }
}
