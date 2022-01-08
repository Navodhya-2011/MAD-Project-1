package com.user.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditTodo extends AppCompatActivity {

    //initialize objects
    private EditText editTitle, editDesc;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        //connect with xml using ids
        editTitle = findViewById(R.id.editTitleInput);
        editDesc = findViewById(R.id.editDescInput);
        btnEdit = findViewById(R.id.btnEdit);

    }
}