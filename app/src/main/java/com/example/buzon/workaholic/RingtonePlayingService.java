package com.example.buzon.workaholic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.buzon.workaholic.R;

/**
 * Created by buzon on 7/29/2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int start_id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //fetch the extra strong values
        String state = intent.getExtras().getString("extra");

        switch (state) {
            case "alarm on":
                start_id = 1;
                break;
            case "alarm off":
                start_id = 0;
                break;
            default:
                start_id = 0;
                break;
        }

        media_song = MediaPlayer.create(this, R.raw.alarmsample);
        media_song.start();

        return START_NOT_STICKY;
    }

    public void onDestroy() {
        Toast.makeText(this, "On Destroy Called", Toast.LENGTH_SHORT).show();
    }
}
