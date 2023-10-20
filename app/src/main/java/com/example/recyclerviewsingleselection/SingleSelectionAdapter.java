package com.example.recyclerviewsingleselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SingleSelectionAdapter extends RecyclerView.Adapter<SingleSelectionAdapter.SingleSelectionViewHolder> {

    private List<Contact> contactList;

    public SingleSelectionAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public SingleSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new SingleSelectionViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SingleSelectionViewHolder holder, int position) {
        var contact = contactList.get(position);
        holder.bind(contact);
    }

    public class SingleSelectionViewHolder extends RecyclerView.ViewHolder {

        public SingleSelectionViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Contact contact) {
            TextView name = itemView.findViewById(R.id.text_view_name);
            TextView number = itemView.findViewById(R.id.text_view_number);

            name.setText(contact.name);
            number.setText(contact.number);
        }
    }
}

