package com.example.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import static com.example.splash.CustomeCountDownTimer.*;



public class SplashActivity extends AppCompatActivity {

    private FullScreenVideoView mVideoView;
    private TextView mTvTimer;
    private CustomeCountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       mVideoView = (FullScreenVideoView) findViewById(R.id.vv_play);
       mTvTimer = (TextView)findViewById(R.id.tv_splash);
       mVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.splash));
       mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {
               mp.start();
           }
       });
       mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
});

        timer = new CustomeCountDownTimer(5, new IcountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time+"秒");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("skip");
                mVideoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
                });

            }
        });
        //
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
