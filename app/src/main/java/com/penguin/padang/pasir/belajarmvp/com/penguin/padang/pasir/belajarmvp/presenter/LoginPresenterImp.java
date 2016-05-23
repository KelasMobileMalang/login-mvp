package com.penguin.padang.pasir.belajarmvp.com.penguin.padang.pasir.belajarmvp.presenter;

import android.text.TextUtils;

import com.penguin.padang.pasir.belajarmvp.com.penguin.padang.pasir.belajarmvp.views.LoginView;

/**
 * Created by jowy on 5/23/16.
 */
public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            loginView.showValidationError();
        } else {
            if (username.equals("admin") && password.equals("admin123")) {
                loginView.loginSuccess();
            } else {
                loginView.loginError();
            }
        }
    }
}
