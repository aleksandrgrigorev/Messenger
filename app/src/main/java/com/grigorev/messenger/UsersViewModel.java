package com.grigorev.messenger;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsersViewModel extends ViewModel {

    private FirebaseAuth auth;

    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public UsersViewModel() {
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(firebaseAuth -> user.setValue(firebaseAuth.getCurrentUser()));
    }

    public void logOut() {
        auth.signOut();
    }
}
