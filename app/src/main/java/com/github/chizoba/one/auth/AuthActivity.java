package com.github.chizoba.one.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chizoba.one.R;

public class AuthActivity extends AppCompatActivity implements AuthContract.View {

    private AuthContract.UserActionsListener mActionsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mActionsListener = new AuthPresenter(this);
    }
}
