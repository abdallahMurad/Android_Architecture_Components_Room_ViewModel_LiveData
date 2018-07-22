package com.banksy.aac.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.banksy.aac.model.Note;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteModelDao {
    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note WHERE id = :noteId")
    Note getNoteById(String noteId);

    @Insert(onConflict = REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);
}
