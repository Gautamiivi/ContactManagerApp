package com.mycode.contactmanagerappmvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    //if you need to use context inside ur ViewModel
    //you should use AndroidViewModel AVM
    //because it contains the application context.


    //live data
    private  Repository myRepository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }
    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myRepository.getAllContact();
        return allContacts;
    }
    public void addContacts (Contacts contacts){
        myRepository.addContact(contacts);
    }
    public void deleteContacts(Contacts contacts){
        myRepository.deleteContact(contacts);
    }
}
