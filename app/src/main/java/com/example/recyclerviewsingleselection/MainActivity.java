package com.example.recyclerviewsingleselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewsingleselection.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        var view = binding.getRoot();
        setContentView(view);

        var contactList = createList();
        var adapter = new SingleSelectionAdapter(contactList);
        binding.recyclerViewContacts.setAdapter(adapter);
        binding.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        binding.buttonShowSelected.setOnClickListener(v -> {
            Toast.makeText(getBaseContext(), "selected: ", Toast.LENGTH_SHORT).show();
        });
    }

    public List<Contact> createList() {
        var list = new ArrayList<Contact>();

        var names = new ArrayList<String>();
        names.add("Boris");
        names.add("Mike");
        names.add("John");
        names.add("Karl");

        var rand = new Random();

        int n = 10;
        for (int i = 0; i < n; ++i) {
            var name = names.get(rand.nextInt(names.size()));
            var number = "+7" + (1000 + rand.nextInt(9000));
            var contact = new Contact(name, number);
            list.add(contact);
        }

        return list;
    }
}