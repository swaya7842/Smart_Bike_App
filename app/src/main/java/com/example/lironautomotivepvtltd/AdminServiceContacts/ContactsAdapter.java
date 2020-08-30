package com.example.lironautomotivepvtltd.AdminServiceContacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lironautomotivepvtltd.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

private List<MyContacts>myContacts;
private MyClickListener myClickListener;

    public ContactsAdapter(List<MyContacts> myContacts, MyClickListener myClickListener) {
        this.myContacts = myContacts;
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contacts_recycler_layout,viewGroup,false);
        return new ContactsHolder(view,myClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {

        MyContacts contacts = myContacts.get(position);
        holder.displayName.setText(contacts.getContactName());
        holder.displayNumber.setText(contacts.getContactNumber().get(0));

    }

    @Override
    public int getItemCount() {
        return myContacts.size();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView displayName;
        private TextView displayNumber;
        private MyClickListener listener;

        public ContactsHolder(@NonNull final View itemView, MyClickListener listener) {
            super(itemView);
            this.displayName = itemView.findViewById(R.id.contact_name);
            this.displayNumber = itemView.findViewById(R.id.contact_mobile_no);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(itemView.getContext(),"OnClick...",Toast.LENGTH_SHORT).show();
            listener.onItemClick(v,getAdapterPosition());
        }
    }
}
