package com.sansan.javaroomrunnableexample_03;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uId;

    @ColumnInfo(name = "userName")
    public String userName;

    @ColumnInfo(name = "userAge")
    public String userAge;

}
