package com.edicoding.picodiploma.rongsokinuser.Tambah;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.edicoding.picodiploma.rongsokinuser.R;

import java.util.List;

public class Tambahlist extends ArrayAdapter <Tambah> {

    private Activity context;
    private List <Tambah> tambahList;

    public  Tambahlist (Activity context, List<Tambah> tambahList){
        super(context, R.layout.list_layout, tambahList);
        this.context = context;
        this.tambahList = tambahList;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewJenis = (TextView) listViewItem.findViewById(R.id.textViewJenis);

        Tambah tambah = tambahList.get(position);

        textViewName.setText(tambah.getTambahNama());
        textViewJenis.setText(tambah.getTambahJenis());

        return listViewItem;

    }
}