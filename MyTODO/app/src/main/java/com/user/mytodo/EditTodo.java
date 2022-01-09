package com.user.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTodo extends AppCompatActivity {

    //initialize objects
    private EditText editTitle, editDesc;
    private Button btnEdit;
    private DBHandler dbHandler;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        context = this;
        dbHandler = new DBHandler(context);
        //connect with xml using ids
        editTitle = findViewById(R.id.editTitleInput);
        editDesc = findViewById(R.id.editDescInput);
        btnEdit = findViewById(R.id.btnEdit);


        final String id = getIntent().getStringExtra("id"); //get the value which passed from the alert box
        Todo todo = dbHandler.getSingleTodo(Integer.parseInt(id)); //get relevant todoData from the database

        editTitle.setText(todo.getTitle()); //assign previously entered data
        editDesc.setText(todo.getDescription());

        btnEdit.setOnClickListener(new View.OnClickListener() {//Edit button click event
            @Override
            public void onClick(View view) {
                String titleTxt = editTitle.getText().toString();//assign the user entered new data
                String decText = editDesc.getText().toString();
                updateDate = System.currentTimeMillis();

                //create new todoObject and pass the data to the model class
                Todo toDo = new Todo(Integer.parseInt(id), titleTxt,decText,updateDate,0);
                int state = dbHandler.updateSingleTodo(toDo); //get the update status

                if (state > 0 ){
                    Toast.makeText(context,"ToDo Updated successfully", Toast.LENGTH_SHORT);
                }
                startActivity(new Intent(context, MainActivity.class));//redirect to the main activity
            }
        });
    }
}