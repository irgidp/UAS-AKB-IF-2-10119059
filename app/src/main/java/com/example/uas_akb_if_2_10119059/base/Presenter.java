package com.example.uas_akb_if_2_10119059.base;

/*
        NIM : 10119059
        Nama : Irgi Dwiputra
        Kelas : IF-2/VI
        Tanggal : Selasa, 9 Agustus 2022
        Membuat presenter dengan konsep MVP
*/

public interface Presenter <T extends View> {
    void onAttach(T view);
    void onDetach();
}
