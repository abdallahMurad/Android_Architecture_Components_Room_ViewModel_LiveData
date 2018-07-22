package com.banksy.aac.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.banksy.aac.database.NoteDatabase;
import com.banksy.aac.model.Note;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteDatabase noteDatabase;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = NoteDatabase.getDatabase(this.getApplication());
    }

    public void addNote(Note note) {

        new AddAsyncTask(noteDatabase).execute(note);
    }

    private class AddAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDatabase noteDatabase;

        public AddAsyncTask(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDatabase.getNoteModelDao().insertNote(notes[0]);
            return null;
        }
    }
}
