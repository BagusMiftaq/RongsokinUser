package com.edicoding.picodiploma.rongsokinuser.input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.edicoding.picodiploma.rongsokinuser.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class KatagoriActivity extends AppCompatActivity
{
    private ImageView botolplastik, kardus,besi,kaca;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katagori);


        botolplastik = (ImageView) findViewById(R.id.k_botolplastik);
        kardus = (ImageView) findViewById(R.id.k_kaca);
        kaca = (ImageView) findViewById(R.id.k_kaca);
        besi = (ImageView) findViewById(R.id.k_besi);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        botolplastik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(KatagoriActivity.this, InputActivity.class);
                intent.putExtra("category", "botol plastik");
                intent.putExtra("profilname",currentUser.getDisplayName());
                intent.putExtra("profilemail",currentUser.getEmail());
                startActivity(intent);
            }
        });


        kardus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(KatagoriActivity.this, InputActivity.class);
                intent.putExtra("category", "kardus");
                intent.putExtra("profilname",currentUser.getDisplayName());
                intent.putExtra("profilemail",currentUser.getEmail());
                startActivity(intent);
            }
        });

        besi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KatagoriActivity.this, InputActivity.class);
                intent.putExtra("category", "besi");
                intent.putExtra("profilname",currentUser.getDisplayName());
                intent.putExtra("profilemail",currentUser.getEmail());
                startActivity(intent);
            }
        });

        kaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KatagoriActivity.this, InputActivity.class);
                intent.putExtra("category", "kaca");
                intent.putExtra("profilname",currentUser.getDisplayName());
                intent.putExtra("profilemail",currentUser.getEmail());
                startActivity(intent);
            }
        });
    }
}
