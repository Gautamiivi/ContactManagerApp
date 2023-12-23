package com.mycode.contactmanagerappmvvm;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.logging.Handler;

public class MainActivityClickHandlers {
    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public  void onFABClicked(View view){
        Intent i = new Intent(view.getContext(),AddNewContacts.class);
        context.startActivity(i);
    }


}
