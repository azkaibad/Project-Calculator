package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Hasil> aExampleList;
    private RecyclerView aRecyclerView;
    private SharedPreferenceAdapter aAdapter;
    private RecyclerView.LayoutManager aLayoutManager;
    RadioGroup RadioGroup;
    RadioButton tambah, kurang, kali, bagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        RadioGroup = findViewById(R.id.RadioGroup);
        tambah = findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        kali = findViewById(R.id.kali);
        bagi = findViewById(R.id.bagi);

        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (tambah.isChecked()) {
                    Toast.makeText(MainActivity.this, "Tambah", Toast.LENGTH_SHORT).show();
                } else if (kurang.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kurang", Toast.LENGTH_SHORT).show();
                } else if (kali.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kali", Toast.LENGTH_SHORT).show();
                } else if (bagi.isChecked()) {
                    Toast.makeText(MainActivity.this, "Bagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(aExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Hasil>>() {}.getType();
        aExampleList = gson.fromJson(json, type);

        if (aExampleList == null) {
            aExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        aRecyclerView = findViewById(R.id.recyclerview);
        aRecyclerView.setHasFixedSize(true);
        aLayoutManager = new LinearLayoutManager(this);
        aAdapter = new SharedPreferenceAdapter(aExampleList);

        aRecyclerView.setLayoutManager(aLayoutManager);
        aRecyclerView.setAdapter(aAdapter);
    }

    private void setInsertButton() {
        Button buttonHitung = findViewById(R.id.button_hitung);
        buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nomor1 = findViewById(R.id.nomor1);
                EditText nomor2 = findViewById(R.id.nomor2);
                insertItem(nomor1.getText().toString(), nomor2.getText().toString());
                saveData();
            }
        });
    }

    private void insertItem(String nomor1, String nomor2) {
        int hasil = 0;
        if (tambah.isChecked()) {
            hasil = Integer.parseInt(nomor1) + Integer.parseInt(nomor2);
        } else if (kurang.isChecked()) {
            hasil = Integer.parseInt(nomor1) - Integer.parseInt(nomor2);
        } else if (kali.isChecked()) {
            hasil = Integer.parseInt(nomor1) * Integer.parseInt(nomor2);
        } else if (bagi.isChecked()) {
            hasil = Integer.parseInt(nomor1) / Integer.parseInt(nomor2);
        }

        // memunculkan operasi yang dipilih dari operasiGroup ke dalam hasil
        String operasi = "";
        if (tambah.isChecked()) {
            operasi = "+";
        } else if (kurang.isChecked()) {
            operasi = "-";
        } else if (kali.isChecked()) {
            operasi = "x";
        } else if (bagi.isChecked()) {
            operasi = ":";
        }

        aExampleList.add(new Hasil(nomor1, operasi, nomor2, String.valueOf(hasil)));
        aAdapter.notifyItemInserted(aExampleList.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        aExampleList.clear();
        aAdapter.notifyDataSetChanged();
    }

}