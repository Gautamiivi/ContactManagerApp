package com.mycode.contactmanagerappmvvm;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Callable;

public class AddNewContactClickHandler {
    Contacts contacts;
    Context context;
    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contacts, Context context,MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel = myViewModel;

    }

    public void onSubmitBtnClicked(View view){
        if(contacts.getName()==null||contacts.getEmail()==null){
            Toast.makeText(context,
                    "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            Intent i =new Intent(context,MainActivity.class);
            //i.putExtra("Name",contacts.getName());
            //i.putExtra("Email",contacts.getEmail());
            Contacts c = new Contacts(contacts.getName(),contacts.getEmail());
            myViewModel.addContacts(c);

            context.startActivity(i);
        }
    }


}
