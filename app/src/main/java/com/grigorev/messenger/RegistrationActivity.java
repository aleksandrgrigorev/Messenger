package com.grigorev.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Button buttonSignUp;

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();

        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();
        buttonSignUp.setOnClickListener(view -> {
            String email = getTrimmedValue(editTextEmail);
            String password = getTrimmedValue(editTextPassword);
            String name = getTrimmedValue(editTextName);
            String lastName = getTrimmedValue(editTextLastName);
            String ageString = getTrimmedValue(editTextAge);

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || lastName.isEmpty()
                    || ageString.isEmpty()) {
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            } else {
                int age = Integer.parseInt(ageString);
                viewModel.signUp(email, password, name, lastName, age);
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(RegistrationActivity.this, errorMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        viewModel.getUser().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Intent intent = UsersActivity.newIntent(RegistrationActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }
}