package com.grigorev.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class UsersActivity extends AppCompatActivity {

    private static final String EXTRA_CURRENT_USER_ID = "current_id";

    private RecyclerView recyclerViewUsers;
    private UsersAdapter usersAdapter;

    private UsersViewModel viewModel;

    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        initViews();
        currentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);
        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        observeViewModel();
        usersAdapter.setOnUserClickListener(user -> {
            Intent intent = ChatActivity.newIntent(
                    UsersActivity.this,
                    currentUserId,
                    user.getId()
            );
            startActivity(intent);
        });
    }

    private void initViews() {
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        usersAdapter = new UsersAdapter();
        recyclerViewUsers.setAdapter(usersAdapter);
    }

    private void observeViewModel() {
        viewModel.getUser().observe(this, firebaseUser -> {
            if (firebaseUser == null) {
                Intent intent = LoginActivity.newIntent(UsersActivity.this);
                startActivity(intent);
                finish();
            }
        });
        viewModel.getUsers().observe(this, users -> usersAdapter.setUsers(users));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_logout) {
            viewModel.logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context, String currentUserId) {
        Intent intent = new Intent(context, UsersActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID, currentUserId);
        return intent;
    }
}