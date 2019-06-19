package com.example.bsfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewBookActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    private EditText name;
    private EditText author;
    private EditText bookGanre;
    private EditText price;
    private EditText imageBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        name = (EditText)findViewById(R.id.name);
        author = (EditText)findViewById(R.id.author);
        bookGanre = (EditText)findViewById(R.id.bookGanre);
        price = (EditText)findViewById(R.id.price);
        imageBook = (EditText)findViewById(R.id.imageBook);

        findViewById(R.id.add_new_book).setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_new_book)
        {
            writeNewBook(name.getText().toString(), name.getText().toString(), author.getText().toString(), bookGanre.getText().toString(), price.getText().toString(), imageBook.getText().toString());
        }
    }
    public void writeNewBook(String bookId,String name, String author, String bookGanre, String price, String imageBook){
        Item book = new Item(name, author, bookGanre, price, imageBook);
        myRef.child("books").child(bookId).setValue(book);
    }
}
