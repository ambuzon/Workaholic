package com.example.buzon.workaholic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.buzon.workaholic.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    /** Called when the user taps the Send button */
    public void Map(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void Alarm(View view){
        Intent intent = new Intent(this, Alarm.class);
        startActivity(intent);
    }

    public void Emergency(View view){
        Intent intent = new Intent(this, Emergency.class);
        startActivity(intent);
    }

}
