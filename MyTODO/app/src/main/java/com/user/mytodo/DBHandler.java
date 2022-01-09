package com.user.mytodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "todoDB";
    private static final String TABLE_NAME = "todo";

    //column names
    private static final String  ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+ " TEXT,"
                +DESCRIPTION+ " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT" + ");"; //sql query

        /* CREATE TABLE "todo(the table name)" (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
                         started TEXT, finished TEXT) */

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);//execute the query

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        //Drop older table if existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        //create tables again
        onCreate(sqLiteDatabase);
    }

    public void addTodo(Todo todo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();//insert data method
        ContentValues contentValues = new ContentValues();//store data in the database
        contentValues.put(TITLE, todo.getTitle()); //get the title and store in the database
        contentValues.put(DESCRIPTION, todo.getDescription()); //get the description and store in the database
        contentValues.put(STARTED, todo.getStarted());
        contentValues.put(FINISHED, todo.getFinished());

        //save to data in the table
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        //close database connection
        sqLiteDatabase.close();
    }

    public int countToDo(){
        SQLiteDatabase db = getReadableDatabase(); //read the database data
        String query = "SELECT * FROM " + TABLE_NAME; //SQL query
        Cursor cursor = db.rawQuery(query, null); //execute query
        return cursor.getCount(); //return the count of records in the table
    }

    //get all todos into a list
    public List<Todo> getAllTodos() { //get all todos (return a arrayList with todoData)
        List<Todo> toDos = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME; //sql query

        Cursor cursor = db.rawQuery(query, null);//execute query

        if(cursor.moveToFirst()){//check if data is available
            do {
                //create new todoObject
                Todo toDo = new Todo();

                toDo.setId(cursor.getInt(0)); //get the id from the table using index  and assign to variable
                toDo.setTitle(cursor.getString(1));
                toDo.setDescription(cursor.getString(2));
                toDo.setStarted(cursor.getLong(3));
                toDo.setFinished(cursor.getLong(4));

                toDos.add(toDo);
            }while (cursor.moveToNext()); //move to next row
        }
        return toDos;
    }
}
