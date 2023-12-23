package com.mycode.contactmanagerappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.mycode.contactmanagerappmvvm.databinding.ActivityAddNewContactsBinding;

public class AddNewContacts extends AppCompatActivity {
    private ActivityAddNewContactsBinding binding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contacts);
        contacts = new Contacts();
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_new_contacts
        );
        //my view model;
        MyViewModel myViewModel= new ViewModelProvider(this)
                .get(MyViewModel.class);

        handler = new AddNewContactClickHandler(contacts,this,myViewModel);

        binding.setAddContact(contacts);
        binding.setClickHandler(handler);

    }
}