package com.example.giuakylan2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.UserDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editAddress;
    private Button btnAddUser;
    private RecyclerView rcvUser;


    private UserAdapter userAdapter;
    private List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniUi();
        userAdapter = new UserAdapter();
        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
        );
        rcvUser.setLayoutManager(linearLayoutManager);

        rcvUser.setAdapter(userAdapter);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser ();
            }
        });
    }


    private void iniUi(){
        editUsername = findViewById(R.id.userMail);
        editAddress = findViewById(R.id.userAddress);
        btnAddUser = findViewById(R.id.button);
        rcvUser = findViewById(R.id.rcv_user);
    }
    private void addUser() {
        String strUsername = editUsername.getText().toString().trim();
        String strAddress = editAddress.getText().toString().trim();
        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return;
        }
        User user = new User(strUsername, strAddress);
        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "them thanh cong", Toast.LENGTH_SHORT).show();

        editUsername.setText("");
        editAddress.setText("");
        hideSoftKeyboard();

        mListUser = UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(mListUser);
    }
    public void hideSoftKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
}