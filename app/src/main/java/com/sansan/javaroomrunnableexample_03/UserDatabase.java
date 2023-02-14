package com.sansan.javaroomrunnableexample_03;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
