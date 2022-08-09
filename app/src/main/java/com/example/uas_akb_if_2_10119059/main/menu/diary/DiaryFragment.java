package com.example.uas_akb_if_2_10119059.main.menu.diary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uas_akb_if_2_10119059.R;
import com.example.uas_akb_if_2_10119059.createDiary.CreateDiaryActivity;
import com.example.uas_akb_if_2_10119059.database.SQLite;
import com.example.uas_akb_if_2_10119059.model.Diary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*
        NIM : 10119059
        Nama : Irgi Dwiputra
        Kelas : IF-2/VI
        Tanggal : Selasa, 9 Agustus 2022
        Membuat class diaryfragment untuk menampilkan data
*/
public class DiaryFragment extends Fragment {

    /* Deklarasi variable */
    private FloatingActionButton addButton;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private ArrayList<Diary> listTask = new ArrayList<>();
    private SQLite helper;

    @Nullable
    @Override
    /*Fungsi membuat view dan menampilkan data*/
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_diary, container, false);

        addButton = root.findViewById(R.id.addButton);
        listView = root.findViewById(R.id.listView);

        helper = new SQLite(this.getActivity());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateDiaryActivity.class));
            }
        });

        showData();

        return root;
    }

    /*Fungsi menampilkan data*/
    public void showData() {
        listTask.clear();
        Cursor res = helper.getAllData();
        while (res.moveToNext()) {
            String id = res.getString(0);
            String judul = res.getString(1);
            String kategori = res.getString(2);
            String isi = res.getString(3);
            String date = res.getString(4);
            String month = res.getString(5);
            String year = res.getString(6);

            listTask.add(new Diary(id, judul, kategori, isi, date, month, year));
        }

        listViewAdapter = new ListViewAdapter(listTask, getActivity());
        listView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();
    }

}