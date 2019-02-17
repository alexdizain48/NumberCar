package com.alex.numbercar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnect = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnect) {
                Toast.makeText(context, "Disconnect", Toast.LENGTH_SHORT).show();
            } /*else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }*/
        }
    }
}