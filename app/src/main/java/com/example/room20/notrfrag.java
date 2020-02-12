package com.example.room20;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.room20.Db.Note;
import com.example.room20.Db.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class notrfrag extends Fragment {


    public notrfrag() {
        // Required empty public constructor
    }

    private FloatingActionButton fab_add;
    NoteAdapter adapter;
    NoteViewModel noteViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notrfrag, container, false);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.note_recycler_view);
        adapter = new NoteAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fab_add = view.findViewById(R.id.fab_add);

        noteViewModel.getAllNote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNote(notes);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab_add.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.fragment);
            controller.navigate(R.id.action_add_note);
        });

    }
}
