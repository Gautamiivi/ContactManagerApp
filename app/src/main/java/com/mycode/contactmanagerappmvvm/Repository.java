package com.mycode.contactmanagerappmvvm;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    // the available data source
    // room database
    private final contactDAO contactDAO;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {

        ContactDatabase contactDatabase= ContactDatabase.getDbInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        //use for  background database operations;
        executor = Executors.newSingleThreadExecutor();

        //used for updating the UI;
        handler = new Handler(Looper.getMainLooper());
    }
    //methods in DAO being executed from repository
    public  void addContact(Contacts contacts){



        //Runnable : Executing Task on Separating Threads
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Execute this code Asynchronously
                //on separate thread
                contactDAO.insert(contacts);
            }
        });
    }
    public  void deleteContact(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });

    }
    public LiveData<List<Contacts>> getAllContact(){
       return contactDAO.getAllContacts();
    }

}
