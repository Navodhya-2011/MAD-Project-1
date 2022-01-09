package com.user.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    //initialize objects
    private EditText title,desc;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //connect with xml using ids
        title = findViewById(R.id.titleInput);
        desc = findViewById(R.id.descInput);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Context context = this;

        dbHandler = new DBHandler(context); //create database handler object

        btnSubmit.setOnClickListener(view -> {
            String userTitle = title.getText().toString();//get and assign user input title
            String userDesc = desc.getText().toString();//get and assign user input description
            long started = System.currentTimeMillis();//get and assign current date and time from the system

            Todo todo = new Todo(userTitle,userDesc,started,0);//pass the data to the model class
            dbHandler.addTodo(todo); //pass the data to the database handler

            startActivity(new Intent(context, MainActivity.class)); //redirect to the main activity
        });
    }
}