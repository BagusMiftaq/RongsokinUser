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

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sign_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                                    startActivity(new Intent(getApplicationContext(), HomeAct.class));
                                } else {
                                    Toast.makeText(Login_Act.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            }


        });
        textlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterAct.class));
            }
        });
    }
}