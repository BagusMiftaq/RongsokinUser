package com.edicoding.picodiploma.rongsokinuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashAct extends AppCompatActivity {

    Animation app_splash, btt;
    ImageView logo_app;
    TextView titlecuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this,R.anim.btt);

        logo_app = findViewById(R.id.logo_app);
        titlecuy = findViewById(R.id.titlecuy);

        //animasi
        logo_app.startAnimation(app_splash);
        titlecuy.startAnimation(btt);

        //timer untuk 2 detik
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //merubah ke activity lain
                Intent gogetstarted = new Intent(SplashAct.this,RegisterAct.class);
                startActivity(gogetstarted);
                finish();
            }
        }, 2000); //1000 millis = 1 dtk

    }
}
