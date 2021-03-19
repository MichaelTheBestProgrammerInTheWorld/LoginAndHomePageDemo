package com.michaelmagdy.loginandhomepagedemo.viewmodel;

import androidx.lifecycle.ViewModel;

import com.michaelmagdy.loginandhomepagedemo.model.Repository;

public class LoginViewModel extends ViewModel {

    Repository repository;

    public LoginViewModel() {

        repository = new Repository();
    }

    public void onLogin(String emailStr, String passwordStr, Repository.LoginCallbacks loginCallbacks){

        repository.loginRequest(emailStr, passwordStr, loginCallbacks);
    }
}
