package com.banksy.aac.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.banksy.aac.model.Note;

@Database(entities = Note.class, version = 2)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "notes_db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstances() {
        INSTANCE = null;
    }

    public abstract NoteModelDao getNoteModelDao();
}
