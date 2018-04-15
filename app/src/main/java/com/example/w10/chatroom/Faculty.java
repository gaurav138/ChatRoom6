package com.example.w10.chatroom;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Faculty extends AppCompatActivity {
String name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        final EditText editText=(EditText) findViewById(R.id.editText);
        final EditText editText2=(EditText) findViewById(R.id.editText2);

        Button button=(Button) findViewById(R.id.button3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editText.getText().toString();
                password= editText2.getText().toString();


                if(name.isEmpty() || password.isEmpty())
                    Toast.makeText(Faculty.this,"Plz fill the required parameters",Toast.LENGTH_LONG).show();
                else
                {
                    Intent i=new Intent(Faculty.this,FacultyMain.class);
                    startActivity(i);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                final String username = editText.getText().toString();
                final String password = editText2.getText().toString();


                if (username.equals("g") && password.equals("1")) {
                    Intent intent = new Intent(Faculty.this, FacultyMain.class);
                    Faculty.this.startActivity(intent);



                }
                else {


                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {


                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {

                                    String name = jsonResponse.getString("name");
                                    String username = jsonResponse.getString("username");
                                    String email=jsonResponse.getString("email");

                                    Intent intent = new Intent(Faculty.this, FacultyMain5.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    intent.putExtra("name", name);
                                    Faculty.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Faculty.this);
                                    builder.setMessage("Login Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest1 loginRequest1 = new LoginRequest1(username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Faculty.this);
                    queue.add(loginRequest1);
                }
            }

        });


    }
}


