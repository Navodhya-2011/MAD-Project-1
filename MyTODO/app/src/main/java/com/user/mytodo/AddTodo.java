package com.user.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    //initialize objects
    private EditText title,desc;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //connect with xml using ids
        title = findViewById(R.id.titleInput);
        desc = findViewById(R.id.descInput);
        add = findViewById(R.id.btnSubmit);
    }
}