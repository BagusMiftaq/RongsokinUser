package com.edicoding.picodiploma.rongsokinuser.input;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edicoding.picodiploma.rongsokinuser.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {
    public TextView katagori,nama;
    public CircularImageView image;


    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        nama = itemView.findViewById(R.id.namab);
        katagori = itemView.findViewById(R.id.urlb);
        image = (CircularImageView) itemView.findViewById(R.id.imv);
    }
}
