package com.example.efi2018rplrus2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.UserViewHolder>  {
    private ArrayList<ModelSepeda> dataList;
    View viewku;

    public AdapterBarang(ArrayList<ModelSepeda> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterBarang.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.adapter_barang, parent, false);
        return new AdapterBarang.UserViewHolder(viewku);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBarang.UserViewHolder holder, final int position) {
        holder.txtmerk.setText(dataList.get(position).getMerk());
        holder.txtwarna.setText(dataList.get(position).getWarna());
        holder.txthargasewa.setText(dataList.get(position).getHargasewa());
//        holder.cardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in = new Intent(holder.itemView.getContext(), Detail.class);
//                in.putExtra("id", dataList.get(position).getId());
//                in.putExtra("kodesepeda", dataList.get(position).getKodesepeda());
//                in.putExtra("merk", dataList.get(position).getMerk());
//                in.putExtra("jenis", dataList.get(position).getJenis());
//                in.putExtra("warna", dataList.get(position).getWarna());
//                in.putExtra("gambar", dataList.get(position).getGambar());
//                in.putExtra("hargasewa", dataList.get(position).getHargasewa());
//                holder.itemView.getContext().startActivity(in);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView  txtmerk, txtwarna, txthargasewa;
        CardView cardview;
        ImageView image;

        UserViewHolder(View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardku2);
            txtmerk = itemView.findViewById(R.id.txtmerk);
            txtwarna = itemView.findViewById(R.id.txtwarna);
            txthargasewa = itemView.findViewById(R.id.txthargasewa);
        }
    }

}
