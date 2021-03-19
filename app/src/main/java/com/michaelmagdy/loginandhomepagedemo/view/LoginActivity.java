package com.michaelmagdy.loginandhomepagedemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.michaelmagdy.loginandhomepagedemo.R;
import com.michaelmagdy.loginandhomepagedemo.model.Repository;
import com.michaelmagdy.loginandhomepagedemo.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdt, passwordEdt;
    private Button loginBtn;
    private TextView skipTxt;
    private LoginViewModel loginViewModel;
    public static final String IS_LOGGED = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = emailEdt.getText().toString();
                String passwordStr = passwordEdt.getText().toString();
                if (isInputValid(emailStr, passwordStr)){
                    callLoginApi(emailStr, passwordStr);
                }
            }
        });

        skipTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveToHomeScreen(false);
            }
        });
    }

    private void initUI() {

        emailEdt = findViewById(R.id.email_edt);
        passwordEdt = findViewById(R.id.password_edt);
        loginBtn = findViewById(R.id.login_btn);
        skipTxt = findViewById(R.id.skip_txt);
    }

    private boolean isInputValid(String emailStr, String passwordStr) {


        if (emailStr.isEmpty() || passwordStr.isEmpty()){
            Toast.makeText(this, "all fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (passwordStr.length() < 8) {
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void callLoginApi(String emailStr, String passwordStr) {

        loginViewModel.onLogin(emailStr, passwordStr, new Repository.LoginCallbacks() {
            @Override
            public void onSuccess() {
                moveToHomeScreen(true);
                Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail() {

                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToHomeScreen(boolean isLoggedIn) {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(IS_LOGGED, isLoggedIn);
        startActivity(intent);
    }


}