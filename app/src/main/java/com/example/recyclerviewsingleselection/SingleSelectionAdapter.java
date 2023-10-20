package com.example.recyclerviewsingleselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SingleSelectionAdapter extends RecyclerView.Adapter<SingleSelectionAdapter.SingleSelectionViewHolder> {

    private List<Contact> contactList;
    private int selectedPositon = -1;

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

        ConstraintLayout container;
        ImageView checkedIcon;
        TextView name;
        TextView number;

        public SingleSelectionViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.constraint_layout_container);
            checkedIcon = itemView.findViewById(R.id.image_view_icon_check);
            name = itemView.findViewById(R.id.text_view_name);
            number = itemView.findViewById(R.id.text_view_number);
        }

        public void bind(Contact contact) {
            var adapterPosition = getAdapterPosition();

            if (selectedPositon == -1 || adapterPosition != selectedPositon) {
                checkedIcon.setVisibility(View.GONE);
            } else {
                checkedIcon.setVisibility(View.VISIBLE);
            }

            name.setText(contact.name);
            number.setText(contact.number);

            container.setOnClickListener(view -> {
                checkedIcon.setVisibility(View.VISIBLE);

                var adapterPositionOnClick = getAdapterPosition();
                if (selectedPositon != adapterPositionOnClick) {
                    if (selectedPositon != -1) {
                        notifyItemChanged(selectedPositon);
                    }
                    selectedPositon = adapterPositionOnClick;
                }
            });

        }
    }

    public Contact getSelection() {
        if (selectedPositon != -1) {
            return contactList.get(selectedPositon);
        }
        return null;
    }
}

