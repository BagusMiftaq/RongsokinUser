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
    private ImageView tShirts, sportsTShirts;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katagori);


        tShirts = (ImageView) findViewById(R.id.t_shirts);
        sportsTShirts = (ImageView) findViewById(R.id.sports_t_shirts);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(KatagoriActivity.this, InputActivity.class);
                intent.putExtra("category", "Hoax");
                intent.putExtra("profilname","waw");
                intent.putExtra("profilemail","waw");
                startActivity(intent);
            }
        });


        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(KatagoriActivity.this, InputActivity.class);
                intent.putExtra("category", "Bukan Hoax");
                intent.putExtra("profilname",currentUser.getDisplayName());
                intent.putExtra("profilemail",currentUser.getEmail());
                startActivity(intent);
            }
        });
    }
}
