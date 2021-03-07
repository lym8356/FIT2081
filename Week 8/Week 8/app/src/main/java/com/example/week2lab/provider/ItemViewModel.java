package com.example.week2lab.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel{

    private ItemRepository mRepository;
    private LiveData<List<Item>> mAllItems;

    public ItemViewModel (@NonNull Application application) {
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
    }

    public LiveData<List<Item>> getAllItems() { return mAllItems; }

    public void insert(Item item) {mRepository.insert(item); }
    public void deleteAll() { mRepository.deleteAll();}
}
