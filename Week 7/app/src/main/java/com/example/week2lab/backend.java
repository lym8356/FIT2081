package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.week2lab.provider.Item;
import com.example.week2lab.provider.ItemViewModel;

import java.util.ArrayList;

public class backend extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter = new MyRecyclerAdapter();

    private ItemViewModel mItemViewModel;
    //ArrayList<Item> data = new ArrayList<Item>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend);

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        String st =  getIntent().getExtras().getString("key");
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
//        data = gson.fromJson(st,type);

        recyclerView.setAdapter(adapter);

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mItemViewModel.getAllItems().observe(this, newData -> {
            adapter.setItems(newData);
            adapter.notifyDataSetChanged();
        });


//        adapter = new MyRecyclerAdapter();
//        adapter.setData(data);
//        recyclerView.setAdapter(adapter);

    }




}
