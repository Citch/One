package com.citch.one.auth;

/*
 * Created by Chizoba on 10/12/2017.
 */

import android.content.Intent;

interface AuthContract {

    // used for presenter to view(activity/fragment) communication
    interface View {
        // Interface method to display toast message
        void displayToast(String message);

        // Interface method to display progress
        void displayProgress(boolean isDisplay);

        // Interface method to notify for successful authentication
        void onAuthSuccess();
    }

    // used for view(activity/fragment) to presenter communication
    interface UserActionsListener {
        // Interface method to begin Facebook Auth process
        void initiateFacebookAuth();

        // Interface method to notify for Facebook Auth activity result
        void onFacebookAuthActivityResult(int requestCode, int resultCode, Intent data);
    }
}
