package com.example.fab_room_view_binding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fab_room_view_binding.databinding.RecyclerviewLayoutBinding;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CardViewHolder> {
    private List<Kontak> kontaks;
    private Context context;

    public RvAdapter(List<Kontak> siswas, Context context) {
        this.kontaks = siswas;
        this.context = context;
    }

    public List<Kontak> getListKontak() {
        return kontaks;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvAdapter.CardViewHolder(
                RecyclerviewLayoutBinding.inflate( LayoutInflater.from(parent.getContext() ),
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        final String
                nama = getListKontak().get(position).getNama(),
                telepon = getListKontak().get(position).getTelepon(),
                tipe = getListKontak().get(position).getTipe();

        final int id = getListKontak().get(position).getId();

        holder.binding.tvNama.setText(nama);
        holder.binding.tvTelepon.setText(telepon);
        holder.binding.tvTipe.setText(tipe);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putInt("b_id", id);
                b.putString("b_nama", nama);
                b.putString("b_telepon", telepon);
                b.putString("b_kontak", tipe);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtras(b);

                //context.startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ((Activity) context).startActivityForResult(intent, 1, b);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return kontaks.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewLayoutBinding binding;

        public CardViewHolder(@NonNull RecyclerviewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
