package com.example.bsfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class books extends Fragment  {
    List<Item> items = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setInitialData();
        return inflater.inflate(R.layout.fragment_books, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.booksList);
        DataAdapter adapter = new DataAdapter(getActivity(), items);
        //добавление notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemsBook = dataSnapshot.getChildren();
                ArrayList<Item> books = new ArrayList<Item>();
                for(DataSnapshot book : itemsBook ){
                    Item parsedBook = (Item)book.getValue();
                    if (parsedBook != null) {
                        books.add(parsedBook);
                    }
                }
                Log.d("Data", books.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }



/*
    private void setInitialData(){
        items.add(new Item ("Происхождение", "Дэн Браун", "детективы", "1500", R.drawable.braun));
        items.add(new Item ("Убийство в Восточном экспрессе", "Агата Кристи", "детективы", "1700", R.drawable.kristi));
        items.add(new Item ("Мастер и Маргарита", "Михаил Булгаков", "классика", "1000", R.drawable.master_i_margarita));
        items.add(new Item ("Война и мир", "Лев Толстой", "классика", "900", R.drawable.voina_i_mir));
        items.add(new Item ("Кот в сапогах", "Шарль Перро", "сказки", "700", R.drawable.kot_v_sapogah));
        items.add(new Item ("Золотой ключик", "Алексей Толстой", "сказки", "500", R.drawable.zolotoy_kluch));
        items.add(new Item ("И грянул гром", "Рэй Бредбери", "фантастика", "1200", R.drawable.i_grynul_grom));
        items.add(new Item ("Танатонавты", "Бернар Вербер", "фантастика", "1000", R.drawable.tanotonavti));

    }*/
}
