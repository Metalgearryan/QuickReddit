package com.example.quickreddit.Account;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickreddit.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private ProgressBar mProgressBar;
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Started");

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        mPassword = (EditText) findViewById(R.id.input_password);
        mUsername = (EditText) findViewById(R.id.input_username);
        mProgressBar = (ProgressBar) findViewById(R.id.LoginRequestLoadingProgressBar);
        mProgressBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "onClick: Attempting to log in.");
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                if(!username.equals("") && !password.equals("")){
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
