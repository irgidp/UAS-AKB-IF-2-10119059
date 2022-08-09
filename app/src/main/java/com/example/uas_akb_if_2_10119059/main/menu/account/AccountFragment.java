package com.example.uas_akb_if_2_10119059.main.menu.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_akb_if_2_10119059.R;
import com.example.uas_akb_if_2_10119059.main.LoginActivity;
import com.example.uas_akb_if_2_10119059.main.MainActivity;
import com.example.uas_akb_if_2_10119059.main.menu.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
        NIM : 10119059
        Nama : Irgi Dwiputra
        Kelas : IF-2/VI
        Tanggal : Selasa, 9 Agustus 2022
        Membuat class AccountFragment untuk menampilkan data user dan tombol logout
*/

public class AccountFragment extends Fragment {
    // Inisialisasi button dan textview
    private TextView account;
    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        // Inisialisasi firebase dan firebase user
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(currentUser.getUid());

        // mengubah textview berdasarkan email user
        account = root.findViewById(R.id.tvAccount);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null){
                    account.setText(user.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // menampilkan tombol logout
        btnLogout = root.findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                Toast.makeText(getActivity(), "Berhasil Logout!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }
}