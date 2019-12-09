package com.edicoding.picodiploma.rongsokinuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //merubah ke activity lain
                Intent gogetstarted = new Intent(SplashAct.this,RegisterAct.class);
                startActivity(gogetstarted);
                finish();
            }
        }, 1000); //1000 millis = 1 dtk

    }
}
