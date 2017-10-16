package com.citch.one.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.citch.one.R;

public class AuthActivity extends AppCompatActivity {

    private final static String AUTH_FRAGMENT_TAG = "auth_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_container, new AuthFragment(), AUTH_FRAGMENT_TAG)
                .commit();
    }
}
