package com.caleb.acosta.quickella;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context c;
    DatabaseHandler db;

    ContactAdapter(Context context){
        this.c = context;
        this.db = new DatabaseHandler(context, null);


    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.contact_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = db.getAllContacts().get(position);

        holder.phone.setText("+"+contact.getAreaCode()+" "+contact.getPhone());
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link link = new Link(contact);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(link.getLink()));
                v.getContext().startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(contact);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return db.getContactsCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public Button phone;
        public ImageButton delete;

        public ViewHolder(View itemView) {
            super(itemView);
            this.phone = (Button) itemView.findViewById(R.id.listContact);
            this.delete = (ImageButton)itemView.findViewById(R.id.deleteButton);
            this.linearLayout = (LinearLayout)itemView.findViewById(R.id.contactMainLayout);
        }
    }
}
