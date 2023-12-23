package com.mycode.contactmanagerappmvvm;

import static com.mycode.contactmanagerappmvvm.BR.contact;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mycode.contactmanagerappmvvm.databinding.ContactListBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contactsArrayList;

    public MyAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Creating new View Holder for items in recyclerView;
        ContactListBinding contactListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list,
                parent,false
        );

        return new ContactViewHolder(contactListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        //called by recyclerView when it needs to display or updates an item
        // at a specific position in the list or grid;
        Contacts curr = contactsArrayList.get(position);
        holder.contactListBinding.setContact(curr);

    }

    @Override
    public int getItemCount() {
        //determines the total number of items in the dataset that will be displayed in the recyclerview
        if (contactsArrayList!=null){
            return contactsArrayList.size();
        }else{
            return 0;
        }
    }

    public void setContactsArrayList(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;

        //inform the associated RecyclerView that the underlying
        //dataset has changed ,and the recyclerView should refresh
        //its view to reflect these changes;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactListBinding contactListBinding;

        public ContactViewHolder( ContactListBinding contactListBinding) {
            super(contactListBinding.getRoot());
            this.contactListBinding = contactListBinding;
        }
    }
}
