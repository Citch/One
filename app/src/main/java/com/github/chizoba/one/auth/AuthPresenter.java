package com.github.chizoba.one.auth;

/**
 * Created by Chizoba on 10/12/2017.
 */

public class AuthPresenter implements AuthContract.UserActionsListener {

    private AuthContract.View mAuthView;


    public AuthPresenter(AuthContract.View mAuthView) {
        this.mAuthView = mAuthView;
    }

}
