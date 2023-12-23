package com.mycode.contactmanagerappmvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {


    public abstract  contactDAO getContactDAO();

    //singleton pattern
    private static ContactDatabase DbInstance;
    public static synchronized ContactDatabase getDbInstance(Context context){

        if (DbInstance==null){
            DbInstance= Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contacts_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return DbInstance;
    }

}
