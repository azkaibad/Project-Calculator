package com.example.calculator;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SharedPreferenceAdapter extends RecyclerView.Adapter<SharedPreferenceAdapter.ExampleViewHolder> {
    private final ArrayList<Hasil> aExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView aTextViewNomor1;
        public TextView aTextViewOperasi;
        public TextView aTextViewNomor2;
        public TextView aTextViewHasil;

        @SuppressLint("CutPasteId")
        public ExampleViewHolder(View itemView) {
            super(itemView);
            aTextViewNomor1 = itemView.findViewById(R.id.textview_nomor1);
            aTextViewOperasi = itemView.findViewById(R.id.textview_operasi);
            aTextViewNomor2 = itemView.findViewById(R.id.textview_nomor2);
            aTextViewHasil = itemView.findViewById(R.id.textview_hasil);
        }
    }

    public SharedPreferenceAdapter(ArrayList<Hasil> exampleList) {
        aExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Hasil currentItem = aExampleList.get(position);

        holder.aTextViewNomor1.setText(currentItem.getNomor1());
        holder.aTextViewNomor2.setText(currentItem.getNomor2());
        holder.aTextViewOperasi.setText(currentItem.getOperasi());
        holder.aTextViewHasil.setText(currentItem.getHasil());
    }

    @Override
    public int getItemCount() {
        return aExampleList.size();
    }
}