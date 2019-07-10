package com.example.android.restaurantmanagement;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;
import java.util.HashMap;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.restaurantmanagement.R;

import android.content.Intent;
import org.json.JSONException;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public Activity getActivity(){
        return this;
    }

    public void onBtnLoginClicked(View view) {
        EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        final TextView invalidLogin = findViewById(R.id.invalid);

        Map<String, String> message = new HashMap<>();
        message.put("username", username);
        message.put("password", password);

        JSONObject jsonMessage = new JSONObject(message);

        // 4. Sending json message to Server
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "http://plataformas.herokuapp.com/authenticate",
                jsonMessage,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO
                        try {
                            String message = response.getString("message");
                            if(message.equals("Authorized")) {
                                Intent contactIntent = new Intent(getActivity(),RestaurantsActivity.class);
                                contactIntent.putExtra("username", response.getString("username"));
                                contactIntent.putExtra("admin", response.getString("admin"));
                                startActivity(contactIntent);
                                invalidLogin.setText("");
                            }
                            else {
                                invalidLogin.setText("Invalid Login");
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                            showMessage(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        if( error instanceof  AuthFailureError ){
                            invalidLogin.setText("Invalid Login");
                        }
                        else {
                            showMessage(error.getMessage());
                        }
                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}
