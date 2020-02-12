package com.example.room20.Db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room20.R;

import java.util.List;
import java.util.zip.Inflater;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.myViewHolder> {


   private  LayoutInflater layoutInflater ;
    private List<Note> mData;

    public NoteAdapter(Context contex) {
       layoutInflater = LayoutInflater.from(contex);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.card_list_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        if(mData != null){
            holder.note_title.setText(mData.get(position).getTitle());
            holder.note_text.setText(mData.get(position).getDesc());
        }
        else{
            holder.note_title.setText("No Note Yet");
            holder.note_title.setText("No Note Yet");
        }

    }

    public void setNote(List<Note> data){
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if( mData != null){
            return mData.size();
        }
        else { return 0;}
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView note_title;
        TextView note_text;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            note_title = itemView.findViewById(R.id.card_note_title);
            note_text = itemView.findViewById(R.id.card_note_body);
        }
    }
}
