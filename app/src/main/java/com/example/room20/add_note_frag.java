package com.example.room20;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room20.Db.Note;
import com.example.room20.Db.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class add_note_frag extends Fragment {


    public add_note_frag() {
        // Required empty public constructor
    }

    EditText note_title, note_body;
    FloatingActionButton fab_save;
    private NoteViewModel noteViewModel;
    NoteAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_note_frag, container, false);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        note_title = view.findViewById(R.id.add_note_title);
        note_body = view.findViewById(R.id.add_note_body);
        fab_save = view.findViewById(R.id.fab_save);
        adapter = new NoteAdapter(getContext());


        fab_save.setOnClickListener(v -> {
            if (!note_title.getText().toString().isEmpty()) {
                Note note = new Note(note_title.getText().toString(), note_body.getText().toString());
                noteViewModel.insert(note);
                Toast.makeText(getContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                NavController controller = Navigation.findNavController(getActivity(), R.id.fragment);
                controller.navigate(R.id.action_back_to_note);

            } else {
                Toast.makeText(getContext(), "Note Title Is Empty", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


}
