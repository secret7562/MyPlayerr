package com.example.myplayerr;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;

    public  MainActivity(){
        super();
    }
    public void  onDestory(){
        if(player!=null){
            player.release();
        }
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.videoView);
        int id = getResources().getIdentifier("test.mp4","raw",this.getPackageName());
        final String path = "android.resource://" + this.getPackageName() + "/" + id;
        Log.d("URI PATH", path);
        videoView.setVideoURI(Uri.parse(path));

        player = MediaPlayer.create(this,R.raw.abc);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
            }
        });
        try {
            player.prepareAsync();
        }catch (Exception e){

        }
    }

    public void start(View view){
        if(player!=null){

            if(player.isPlaying() == false){
                player.start();
            }
        }
    }

    public void pause(View view){
        if(player!=null){
            Log.d("MP PAUSE","MP PAUSE");
            if(player.isPlaying() == false){
                player.pause();
            }
        }
    }

    public void stop(View view){
        if(player!=null){
            if(player.isPlaying() == false){
                player.stop();
                player.prepareAsync();
            }
        }
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }
}