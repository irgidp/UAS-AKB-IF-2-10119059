package com.example.uas_akb_if_2_10119059.main.menu.diary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_akb_if_2_10119059.R;
import com.example.uas_akb_if_2_10119059.database.SQLite;
import com.example.uas_akb_if_2_10119059.editDiary.EditDiaryActivity;
import com.example.uas_akb_if_2_10119059.model.Diary;

import java.util.ArrayList;
import java.util.List;

/*
        NIM : 10119059
        Nama : Irgi Dwiputra
        Kelas : IF-2/VI
        Tanggal : Selasa, 9 Agustus 2022
        Membuat class ListViewAdapter untuk membuat list dari data
*/
public class ListViewAdapter extends BaseAdapter {
    private List<Diary> listDiary;
    private Context context;
    private TextView judul, date, month, year, kategori, isi;
    private ImageView opt;
    private Button editButton, deleteButton;
    private SQLite helper;


    public ListViewAdapter(ArrayList<Diary> listDiary, Context context) {
        this.listDiary = listDiary;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDiary.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.diary_card,null);


        judul = view.findViewById(R.id.cardJudul);
        date = view.findViewById(R.id.cardDate);
        month = view.findViewById(R.id.cardMonth);
        year = view.findViewById(R.id.cardYear);
        kategori = view.findViewById(R.id.cardKategori);
        isi = view.findViewById(R.id.cardIsi);
        opt = view.findViewById(R.id.options);
        helper = new SQLite(view.getContext());

        judul.setText(listDiary.get(position).getJudul());
        date.setText(listDiary.get(position).getDate());
        month.setText(listDiary.get(position).getMonth());
        kategori.setText(listDiary.get(position).getKategori());
        isi.setText(listDiary.get(position).getIsi());
        String id = listDiary.get(position).getId();

        opt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(view.getContext(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menuDelete:
                                Toast.makeText(view.getContext(),"Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
                                helper.deteleData(id);
                                listDiary.remove(position);
                                notifyDataSetChanged();
                                return true;
                            case R.id.menuUpdate:
                                Intent intent = new Intent(context, EditDiaryActivity.class);
                                intent.putExtra("Id", listDiary.get(position).getId());
                                intent.putExtra("Judul", listDiary.get(position).getJudul());
                                intent.putExtra("Kategori", listDiary.get(position).getKategori());
                                intent.putExtra("Isi", listDiary.get(position).getIsi());
                                intent.putExtra("Date", listDiary.get(position).getDate());
                                intent.putExtra("Month", listDiary.get(position).getMonth());
                                intent.putExtra("Year", listDiary.get(position).getYear());

                                context.startActivity(intent);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.menu);
                popup.show();
            }

        });
        return view;
    }


}
