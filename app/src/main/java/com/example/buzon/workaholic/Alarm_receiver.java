package com.example.buzon.workaholic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by buzon on 7/29/2017.
 */

public class Alarm_receiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){

        Log.e("We are in the receiver.", "Yay!");

        //Fetch extra strings from the intent
        String get_your_string = intent.getExtras().getString("extra");
        Log.e("What is the key? ", get_your_string);

        //CREATE AN INTENT TO THE RINGTONE SERVICE
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //pass the extra string from Main Activity to the Ringtone Playing Service
        service_intent.putExtra("extra",get_your_string);
        //start the Ringtone service
        context.startService(service_intent);
    }
}
