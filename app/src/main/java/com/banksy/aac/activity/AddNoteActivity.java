package com.banksy.aac.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.banksy.aac.R;
import com.banksy.aac.view_model.AddNoteViewModel;
import com.banksy.aac.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity {

    @BindView(R.id.et_note_title)
    EditText title;
    @BindView(R.id.et_note_desc)
    EditText desc;
    @BindView(R.id.button)
    Button btn;

    private AddNoteViewModel addNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        addNoteViewModel = ViewModelProviders.of(this).get(AddNoteViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNoteViewModel.addNote(new Note(title.getText().toString(), desc.getText().toString()));
                finish();
            }
        });
    }
}
