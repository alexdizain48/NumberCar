package com.alex.numbercar;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private ImageView splashImg;
    boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splashImg = (ImageView)findViewById(R.id.splashImg);

        Animation animStartLogo = AnimationUtils.loadAnimation(this, R.anim.animation_opacity);
        splashImg.startAnimation(animStartLogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ContextCompat.startActivity(
                        SplashScreen.this,
                        new Intent(SplashScreen.this, MainActivity.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                SplashScreen.this,
                                splashImg,
                                getString(R.string.trasition_splash_icon)).toBundle());
                isExit = true;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isExit) {
            finish();
        }
    }
}
