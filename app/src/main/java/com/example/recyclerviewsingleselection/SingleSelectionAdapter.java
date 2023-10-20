package com.example.recyclerviewsingleselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewsingleselection.databinding.RvItemBinding;

import java.util.List;

public class SingleSelectionAdapter extends RecyclerView.Adapter<SingleSelectionAdapter.SingleSelectionViewHolder> {

    private List<Contact> contactList;
    private int selectedPosition = -1;

    public SingleSelectionAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public SingleSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SingleSelectionViewHolder(binding);
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
        RvItemBinding binding;

        public SingleSelectionViewHolder(RvItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Contact contact) {
            var adapterPosition = getAdapterPosition();

            if (selectedPosition == -1 || adapterPosition != selectedPosition) {
                binding.imageViewIconCheck.setVisibility(View.GONE);
            } else {
                binding.imageViewIconCheck.setVisibility(View.VISIBLE);
            }

            binding.textViewName.setText(contact.name);
            binding.textViewNumber.setText(contact.number);

            binding.constraintLayoutContainer.setOnClickListener(view -> {
                binding.imageViewIconCheck.setVisibility(View.VISIBLE);

                var adapterPositionOnClick = getAdapterPosition();
                if (selectedPosition != adapterPositionOnClick) {
                    if (selectedPosition != -1) {
                        notifyItemChanged(selectedPosition);
                    }
                    selectedPosition = adapterPositionOnClick;
                }
            });

        }
    }

    public Contact getSelection() {
        if (selectedPosition != -1) {
            return contactList.get(selectedPosition);
        }
        return null;
    }
}

