package com.grigorev.messenger;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordViewModel extends ViewModel {

    private FirebaseAuth auth;

    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<Boolean> isSuccess() {
        return success;
    }

    public void resetPassword(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(unused -> success.setValue(true))
                .addOnFailureListener(e -> error.setValue(e.getMessage()));
    }
}
