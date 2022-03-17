package com.example.fab_room_view_binding;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KontakDAO {
    @Query("SELECT * FROM kontak")
    List<Kontak> getAll(); //harus pake list karena akan di konversi menjadi cursor

    @Insert
    void insertAll(Kontak kontak); //tanpa id (karena id otomatis)

    @Update
    void update(Kontak kontak); //dengan id

    @Delete
    void delete(Kontak kontak); //dengan id
}
