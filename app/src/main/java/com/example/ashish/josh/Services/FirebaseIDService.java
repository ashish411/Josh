package com.example.ashish.josh.Services;

import android.util.Log;

import com.example.ashish.josh.Constants;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Ashish on 20-03-2018.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(Constants.SERVICE_TAG,"Refreshed token: "+refreshToken);

        sendRegistrationToServer(refreshToken);
    }

    private void sendRegistrationToServer(String refreshToken) {

    }
}
