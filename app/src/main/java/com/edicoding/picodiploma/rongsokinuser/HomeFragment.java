package com.edicoding.picodiploma.rongsokinuser;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    EditText editTextnama;
    Button buttontambah;
    Spinner spinnertambah;

    DatabaseReference databasetambah;
    //T.2
    ListView listViewTambah;

    List<Tambah> tambahList;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        databasetambah = FirebaseDatabase.getInstance().getReference("Tambah");

        editTextnama = v.findViewById(R.id.editTextnama);
        buttontambah = v.findViewById(R.id.buttontambah);
        spinnertambah = v.findViewById(R.id.spinnerjenis);
        //T.2
        listViewTambah = v.findViewById(R.id.listViewTambah);
        tambahList = new ArrayList<>();

        buttontambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });

        listViewTambah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Tambah tambah = tambahList.get(position);

                showUpdateDialog(tambah.getTambahid(),tambah.getTambahNama());
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        databasetambah.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tambahList.clear();

                for (DataSnapshot tambahSnapshot : dataSnapshot.getChildren()){
                    Tambah tambah = tambahSnapshot.getValue(Tambah.class);

                    tambahList.add(tambah);
                }

                Tambahlist adapter = new Tambahlist(getActivity(), tambahList);
                listViewTambah.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    } //T.2

    //T.3 UPDATE
    private void showUpdateDialog(final String artistId, String artistName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_list, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextNama = (EditText) dialogView.findViewById(R.id.editTextNama);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Spinner spinnerJenisUpdate = (Spinner) dialogView.findViewById(R.id.spinnerJenisUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Updating List - "+artistName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama= editTextNama.getText().toString().trim();
                String jenis = spinnerJenisUpdate.getSelectedItem().toString();

                if (TextUtils.isEmpty(nama)){
                    editTextNama.setError("Name required");
                    return;
                }
                updateArtist(artistId, nama, jenis);

                alertDialog.dismiss();
            }
        });
        //T.4 DELETE
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArtist(artistId);
            }
        });


    }
    //T.4 DELETE
    private boolean deleteArtist(String artistId) {
        DatabaseReference drArtist = FirebaseDatabase.getInstance().getReference("Tambah").child(artistId);

        drArtist.removeValue();

        Toast.makeText(getActivity(), "Data Dihapus", Toast.LENGTH_LONG).show();

        return false;
    }

    //T.3 UPDATE
    private boolean updateArtist(String id, String nama, String jenis){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tambah").child(id);

        Tambah tambah = new Tambah(id, nama, jenis);

        databaseReference.setValue(tambah);
        Toast.makeText(getActivity(),"Perubahan Data Sukses", Toast.LENGTH_LONG).show();

        return true;
    }

    //T.1 TAMBAH
    private  void  tambah (){
        String nama = editTextnama.getText().toString().trim();
        String jenis = spinnertambah.getSelectedItem().toString();

        if (!TextUtils.isEmpty(nama)){

            String id =  databasetambah.push().getKey();
            Tambah tambah = new Tambah(id, nama, jenis);

            databasetambah.child(id).setValue(tambah);

            Toast.makeText(getActivity(),"Data Ditambahkan", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),"You should enter a name", Toast.LENGTH_LONG).show();

        }
    }

}
