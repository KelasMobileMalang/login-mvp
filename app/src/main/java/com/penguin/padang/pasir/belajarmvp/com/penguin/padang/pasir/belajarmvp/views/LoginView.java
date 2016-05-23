package com.penguin.padang.pasir.belajarmvp.com.penguin.padang.pasir.belajarmvp.views;

/**
 * Created by jowy on 5/23/16.
 */
public interface LoginView {

    /**
     * Show Validation if got Error
     */
    void showValidationError();

    /**
     * Show a message when Login success/ authenticated
     */
    void loginSuccess();

    /**
     * Show an error message when login not authenticated
     */
    void loginError();
}
