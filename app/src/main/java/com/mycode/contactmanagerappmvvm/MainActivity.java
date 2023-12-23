
// MVVM design Pattern with ROOM Database
// ROOM Database
// contact_id   contact_name   Contact_email

package com.mycode.contactmanagerappmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.mycode.contactmanagerappmvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    //Data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    //Adapter
    private MyAdapter adapter;

    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data binding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(handlers);

        //RecyclerView
        RecyclerView recyclerView =  mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        //Database
        contactDatabase =  ContactDatabase.getDbInstance(this);

        //View Model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        // inserting a new  contact (testing)
       // Contacts c1  =  new Contacts("jack","jack123@gmail.com");
       // viewModel.addContacts(c1);


        //loading tha data from database;
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                contactsArrayList.clear();
                for (Contacts c:contacts){
                    Log.v("TAGY",c.getName());
                    contactsArrayList.add(c);
                }
                adapter.notifyDataSetChanged();
            }
        });


        //Adapter
        adapter = new MyAdapter(contactsArrayList);

        recyclerView.setAdapter(adapter);

        //swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //swap the item to the left;
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContacts(c);
            }

        }).attachToRecyclerView(recyclerView);
    }
}