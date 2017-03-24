package com.example.davidlevitsky.nougatencryptiontest;

/**
 * Created by davidlevitsky on 1/31/17.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.app.Notification.VISIBILITY_PUBLIC;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.app.Notification.VISIBILITY_PUBLIC;

/**
 * Created by davidlevitsky on 1/26/17.
 */

public class LockedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
        notificationIntent.setData(Uri.parse("http://www.wgn.com"));
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle("LOCKED RECEIVER")
                .setContentText("DEVICE IS IN DIRECT BOOT MODE!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVisibility(VISIBILITY_PUBLIC)
                .addAction(android.R.drawable.ic_menu_view, "View details", contentIntent)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(10, notification);

        Log.d("DIRECT BOOT", "received boot completed broadcast receiver... starting settings");
        Toast.makeText(context, "DIRECT BOOT", Toast.LENGTH_LONG).show();
        //Intent myIntent = new Intent(context, MainActivity.class);
        //myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(myIntent);

    }
}
