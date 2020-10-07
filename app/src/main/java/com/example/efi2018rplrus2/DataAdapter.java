package com.example.efi2018rplrus2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> {

    private ArrayList<Model> dataList;
    View viewku;

    public DataAdapter(ArrayList<Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.adapterrv, parent, false);
        return new UserViewHolder(viewku);

    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        holder.tvemail.setText(dataList.get(position).getEmail());
        holder.tvnama.setText(dataList.get(position).getNama());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), Detail.class);
                in.putExtra("id", dataList.get(position).getId());
                in.putExtra("nama", dataList.get(position).getNama());
                in.putExtra("username", dataList.get(position).getEmail());
                in.putExtra("nohp", dataList.get(position).getNohp());
                in.putExtra("noktp", dataList.get(position).getAlamat());
                in.putExtra("alamat", dataList.get(position).getNoktp());
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvemail, tvnama;
        CardView cardview;

        UserViewHolder(View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardku);
            tvemail = itemView.findViewById(R.id.tvemail);
            tvnama = itemView.findViewById(R.id.tvnama);
        }
    }

}