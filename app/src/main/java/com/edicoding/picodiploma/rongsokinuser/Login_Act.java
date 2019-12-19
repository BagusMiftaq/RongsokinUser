package com.edicoding.picodiploma.rongsokinuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Act extends AppCompatActivity {

    Button btn_sign_in;
    EditText ins_email, ins_password;
    TextView textlog;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        textlog = findViewById(R.id.textlog);
        ins_email = findViewById(R.id.ins_email);
        ins_password = findViewById(R.id.ins_password);
        fAuth = FirebaseAuth.getInstance();

        btn_sign_in.setOnClickListener(view -> btn_sign_in.setOnClickListener(view1 -> {
            String email = ins_email.getText().toString().trim();
            String password = ins_password.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                ins_email.setError("Harap isi email dengan benar");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                ins_password.setError("password nya yang bener heh");
                return;
            }
            if (password.length() < 6) {
                ins_password.setError("Password harus lebih dari 6 digit");
                return;
            }

            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login_Act.this, "Login Succes", Toast.LENGTH_SHORT).show();
                        checkIfEmailVerified();
                    } else {
                        Toast.makeText(Login_Act.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }));
        textlog.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterAct.class)));
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Intent intent = new Intent(Login_Act.this, HomeAct.class);
            startActivity(intent);
            finish();
            Toast.makeText(Login_Act.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(Login_Act.this, "Anda belum verifikasi email anda...", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }
}