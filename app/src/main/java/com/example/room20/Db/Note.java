package com.example.room20.Db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int note_id = 0;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String desc;

    public Note(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }


    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }
}
