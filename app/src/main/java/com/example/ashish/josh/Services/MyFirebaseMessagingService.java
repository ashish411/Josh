package com.example.ashish.josh.Services;

import android.util.Log;

import com.example.ashish.josh.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Ashish on 20-03-2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(Constants.SERVICE_TAG,"From: "+remoteMessage.getFrom());
        Log.d(Constants.SERVICE_TAG,"Message body: "+remoteMessage.getNotification().getBody());
    }
}
