package com.edicoding.picodiploma.rongsokinuser.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.edicoding.picodiploma.rongsokinuser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EditActivity extends AppCompatActivity {

    EditText desk,namau;
    ImageView gambar;
    Button update;
    FirebaseDatabase database;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String image = getIntent().getStringExtra("image");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String nama = getIntent().getStringExtra("prace");
        String pid = getIntent().getStringExtra("pid");
        Log.i("OUR VALUE 4",image);
        Log.i("OUR VALUE 5",deskripsi);
        Log.i("OUR VALUE 6",nama);
        Log.i("OUR VALUE",pid);

        namau = findViewById(R.id.product_price);
        desk = findViewById(R.id.product_description);
        gambar = findViewById(R.id.select_product_image);
        update = findViewById(R.id.add_new_product);

        Picasso.get().load(image).into(gambar);
        namau.setText(nama);
        desk.setText(deskripsi);

        namau.setEnabled(false);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }
    private void updateData() {
        String pid = getIntent().getStringExtra("pid");
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        myref.child("Berita").child(pid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toast();
                dataSnapshot.getRef().child("description").setValue(desk.getText().toString());
                dataSnapshot.getRef().child("price").setValue(namau.getText().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                toast2();
            }
        });
    }

    private void ValidateProductData()
    {
        String Description = desk.getText().toString();
        String Price = namau.getText().toString();


        if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Tolong Isi deskripsi...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Tolong Tulis Nama Anda...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            updateData();
        }
    }
    private void toast(){
        Toast.makeText(this, "Data sudah di update", Toast.LENGTH_SHORT).show();
    }
    private void toast2(){
        Toast.makeText(this, "Data Gagal di update", Toast.LENGTH_SHORT).show();
    }
}
