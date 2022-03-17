package com.example.fab_room_view_binding;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Kontak.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract KontakDAO kontakDAO();
}