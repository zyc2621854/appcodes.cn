package com.example.dustinchen.othello_chen_deshun;

/**
 * Created by dustinchen on 10/11/15.
 */
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class Sound extends Service {
    private MediaPlayer mp;


    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.dididi);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
        stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //boolean playing = intent.getBooleanExtra("playing", false);
        //if (playing) {
        if(!mp.isPlaying()){
            mp.start();
        }
        return START_STICKY;
//        //} else {
//            mp.pause();
//        }
        //return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}