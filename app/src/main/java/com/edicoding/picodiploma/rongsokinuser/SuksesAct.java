package com.edicoding.picodiploma.rongsokinuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class SuksesAct extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //merubah ke activity lain
                Intent gogetstarted = new Intent(SuksesAct.this,Login_Act.class);
                startActivity(gogetstarted);
                finish();
            }
        }, 2000); //1000 millis = 1 dtk
    }
}
