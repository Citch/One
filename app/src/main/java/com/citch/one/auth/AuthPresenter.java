package com.citch.one.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/*
 * Created by Chizoba on 10/12/2017.
 */

class AuthPresenter implements AuthContract.UserActionsListener {

    private AuthContract.View mAuthView;

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;

    AuthPresenter(final AuthContract.View mAuthView) {
        this.mAuthView = mAuthView;

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook login callback manager
        this.mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // Display success toast message
                        mAuthView.displayToast("Login Successful!");

                        // Handle Firebase user registration with retrieved Facebook access token
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // Display cancel toast message
                        mAuthView.displayToast("Login was Cancelled!");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // Display error toast message
                        mAuthView.displayToast("An error occurred!");
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        // Display progress bar
        mAuthView.displayProgress(true);

        // Firebase sign in with Facebook credentials
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            mAuthView.displayToast("Authentication Successful!");
                            mAuthView.onAuthSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            mAuthView.displayToast("Authentication failed.");
                        }
                        // Hide progress bar
                        mAuthView.displayProgress(false);
                    }
                });
    }

    @Override
    public void initiateFacebookAuth() {
        // Details asked of Facebook user
        final ArrayList<String> fbPermissions = new ArrayList<>();
        fbPermissions.add("email");
        fbPermissions.add("public_profile");

        // Login user with requested permissions
        LoginManager.getInstance().logInWithReadPermissions((Fragment) mAuthView, fbPermissions);
    }

    @Override
    public void onFacebookAuthActivityResult(int requestCode, int resultCode, Intent data) {
        // Facebook login returned response
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
