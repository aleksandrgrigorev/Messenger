package com.grigorev.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";

    private EditText editTextEmail;
    private Button buttonResetPassword;

    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initViews();
        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observeViewModel();

        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editTextEmail.setText(email);

        buttonResetPassword.setOnClickListener(view -> {
            String email1 = editTextEmail.getText().toString().trim();
            viewModel.resetPassword(email1);
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        errorMessage,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        viewModel.isSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                Toast.makeText(
                        ResetPasswordActivity.this,
                        R.string.reset_link_sent,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
    }

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}