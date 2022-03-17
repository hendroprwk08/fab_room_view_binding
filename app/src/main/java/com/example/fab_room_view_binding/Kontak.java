package com.example.fab_room_view_binding;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Kontak {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "telepon")
    private String telepon;

    @ColumnInfo(name = "tipe")
    private String tipe;

    @Ignore
    public Kontak(String nama, String telepon, String tipe) {
        this.nama = nama;
        this.telepon = telepon;
        this.tipe = tipe;
    }

    public Kontak(int id, String nama, String telepon, String tipe) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
        this.tipe = tipe;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getTipe() {
        return tipe;
    }
}