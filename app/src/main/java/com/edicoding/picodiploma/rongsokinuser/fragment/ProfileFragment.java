package com.edicoding.picodiploma.rongsokinuser.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.edicoding.picodiploma.rongsokinuser.Login_Act;
import com.edicoding.picodiploma.rongsokinuser.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends NavHostFragment {

    FirebaseAuth fAuth;

    Button btnExit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        btnExit = v.findViewById(R.id.btn_exit);
        fAuth = FirebaseAuth.getInstance();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fAuth.signOut();
                
                Intent goexit = new Intent(getActivity(), Login_Act.class);
                startActivity(goexit);
            }
        });

        return v;
    }
}
