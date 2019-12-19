package com.edicoding.picodiploma.rongsokinuser.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edicoding.picodiploma.rongsokinuser.DataSetFire;
import com.edicoding.picodiploma.rongsokinuser.DetailActivity;
import com.edicoding.picodiploma.rongsokinuser.Edit.EditActivity;
import com.edicoding.picodiploma.rongsokinuser.R;
import com.edicoding.picodiploma.rongsokinuser.input.FirebaseViewHolder;
import com.edicoding.picodiploma.rongsokinuser.input.InputActivity;
import com.edicoding.picodiploma.rongsokinuser.input.KatagoriActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DataSetFire> arrayList;
    private FirebaseRecyclerOptions<DataSetFire> options;
    private FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder> adapter;
    private DatabaseReference databasereference,daa;
    FloatingActionButton btninput;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        btninput = v.findViewById(R.id.input);
        recyclerView = v.findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        arrayList = new ArrayList<DataSetFire>();
        databasereference = FirebaseDatabase.getInstance().getReference("Berita");
        databasereference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<DataSetFire>().setQuery(databasereference,DataSetFire.class).build();

        adapter = new FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DataSetFire model) {

                holder.nama.setText(model.getCategory());
                holder.katagori.setText(model.getProfilname());
                Picasso.get().load(model.getImage()).into(holder.image);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Intent = new Intent(getActivity(), DetailActivity.class);
                        Intent.putExtra("date",model.getDate());
                        Intent.putExtra("category", model.getCategory());
                        Intent.putExtra("image",model.getImage());
                        Intent.putExtra("prace",model.getPrice());
                        Intent.putExtra("time",model.getTime());
                        Intent.putExtra("deskripsi",model.getDescription());
                        Intent.putExtra("profilemail",model.getProfilemail());
                        Intent.putExtra("pid",model.getPid());
                        startActivity(Intent);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.dialog_view);
                        dialog.setTitle("Pilih Aksi");
                        dialog.show();
                        Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                        Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);
                        editButton.setEnabled(false);
                        delButton.setEnabled(false);
                        if(model.getProfilname().equals(currentUser.getDisplayName()) && model.getProfilemail().equals(currentUser.getEmail())){
                            editButton.setEnabled(true);
                            delButton.setEnabled(true);
                        }else {
                            editButton.setEnabled(false);
                            delButton.setEnabled(false);
                        }
                        editButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        Intent Intent = new Intent(getActivity(), EditActivity.class);
                                        Intent.putExtra("date",model.getDate());
                                        Intent.putExtra("category", model.getCategory());
                                        Intent.putExtra("image",model.getImage());
                                        Intent.putExtra("prace",model.getPrice());
                                        Intent.putExtra("time",model.getTime());
                                        Intent.putExtra("deskripsi",model.getDescription());
                                        Intent.putExtra("profilemail",model.getProfilemail());
                                        Intent.putExtra("pid",model.getPid());
                                        startActivity(Intent);
                                    }
                                }
                        );

                        delButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        daa = FirebaseDatabase.getInstance().getReference()
                                                .child("Berita").child(model.getPid());
                                        daa.removeValue();
                                        dialog.dismiss();
                                    }
                                }
                        );
                        return false;
                    }
                });

            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup ViewGroup, int  i) {
                return new FirebaseViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.row,ViewGroup,false));
            }
        };
        btninput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInput = new Intent(getActivity(), KatagoriActivity.class);
                startActivity(intentInput);
            }
        });
        recyclerView.setAdapter(adapter);
        return v;
    }

}