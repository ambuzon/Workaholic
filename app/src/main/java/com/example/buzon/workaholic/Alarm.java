package com.example.buzon.workaholic;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.buzon.workaholic.R;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {
//to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        this.context = this;

        //Initialize our alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Initializes our timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        //Initialize our text update box
        update_text = (TextView) findViewById(R.id.update_text);

        //create an instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        //Initialize start button
        Button alarm_on = (Button) findViewById(R.id.alarmon);

        //create an intent to the Alarm Receiver class
         final Intent my_intent = new Intent(this.context, Alarm_receiver.class);


        alarm_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());

                int hour = alarm_timepicker.getCurrentHour();
                int minute = alarm_timepicker.getCurrentMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (hour > 12){
                    hour_string = String.valueOf( hour - 12);
                }
                if(minute < 10){
                    minute_string = "0" + String.valueOf(minute);
                }

                set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);

                my_intent.putExtra("extra", "alarm on");

                //create a pending intent that delays the intent
                //until the intent
                pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set te alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

            }
        });

        //Initialize the stop button
        final Button alarm_off = (Button) findViewById(R.id.alarmoff);

        //create an onClick listener to stop the alarm or undo an alarm set
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_alarm_text("Alarm off!");
                alarm_manager.cancel(pending_intent);
                alarm_off.equals(1);
                //put extra string into my_intent
                //tells the alarm to turn off
                my_intent.putExtra("extra", "alarm off");

                sendBroadcast(my_intent);
            }
        });


/*
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                //get int values of hour and minute
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();
                //convert the int values to strings
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                //method that changes textview
                set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);
            }
        });*/

    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }
}
