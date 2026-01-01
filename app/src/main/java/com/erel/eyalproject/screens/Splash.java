package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;

public class Splash extends AppCompatActivity {

    private ImageView myImageView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        myImageView=(ImageView)findViewById(R.id.ivLOGO);

        Thread mSplashThread = new Thread()
        {
            @Override
            public void run () {
            try {
                synchronized (this) {
                    Animation myFadeInAnimation = AnimationUtils.loadAnimation(Splash.this, R.anim.tween);
                    wait(3000);
                }
            } catch (InterruptedException ex) {
            }
            finish();

            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
        }
        } ;
        mSplashThread.start();
    }

}