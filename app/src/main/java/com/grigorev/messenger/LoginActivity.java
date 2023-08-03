package com.grigorev.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword;
    private TextView textViewRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        buttonLogin.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            //login
        });
        textViewForgotPassword.setOnClickListener(view -> {
            // launch forgot password screen intent
        });
        textViewRegister.setOnClickListener(view -> {
            Intent intent = RegistrationActivity.newIntent(LoginActivity.this);
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewRegister = findViewById(R.id.textViewRegister);
    }
}