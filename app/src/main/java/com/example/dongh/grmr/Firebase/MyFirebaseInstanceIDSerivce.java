package com.example.dongh.grmr.Firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDSerivce extends FirebaseInstanceIdService{
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    // 토큰 재생성
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "token = " + token);
    }
}
