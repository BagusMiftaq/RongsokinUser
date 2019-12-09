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

public class RegisterAct extends AppCompatActivity {
    TextView textreg;
    EditText ins_username, ins_email, ins_password;
    Button btn_sign_in;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textreg = findViewById(R.id.textreg);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        ins_username = findViewById(R.id.ins_username);
        ins_email = findViewById(R.id.ins_email);
        ins_password = findViewById(R.id.ins_password);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeAct.class));
            finish();
        }

        textreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gologin = new Intent(RegisterAct.this, Login_Act.class);
                startActivity(gologin);
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ins_email.getText().toString().trim();
                String password = ins_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    ins_email.setError("Harap isi email dengan benar");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    ins_password.setError("password nya yang bener heh");
                    return;
                }
                if (password.length()<6){
                    ins_password.setError("Password harus lebih dari 6 digit");
                    return;
                }

                //register user ke firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterAct.this, "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), SuksesAct.class));
                            } else {
                                Toast.makeText(RegisterAct.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });

    }
}
