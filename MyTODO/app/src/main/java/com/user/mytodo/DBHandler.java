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

    public void deleteTodo(int id){
        SQLiteDatabase db = getWritableDatabase(); //create writable database connection
        db.delete(TABLE_NAME, ID + " =?",new String[]{String.valueOf(id)}); //SQLite delete query
        db.close();//close database connection
    }

    public Todo getSingleTodo(int id){
        SQLiteDatabase db = getWritableDatabase();//create writable database connection
        //dSQLite query to get all data relevant field (using id)
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED,FINISHED},ID + " =?",new String[]{String.valueOf(id)},null,null,null);

        Todo toDo;
        if (cursor != null){ //if there is any data
            cursor.moveToFirst(); //set the cursor
            //create object with new values
            toDo = new Todo(
                    cursor.getInt(0),//id
                    cursor.getString(1),//title
                    cursor.getString(2),//description
                    cursor.getLong(3),//started
                    cursor.getLong(4)//finished
            );

            return toDo; //return the data
        }
        return null;
    }

    public int updateSingleTodo(Todo todo){
        SQLiteDatabase db = getWritableDatabase();//create writable database connection
        ContentValues contentValues = new ContentValues(); //create content value object
        //put the new data into the contentValue object
        contentValues.put(TITLE, todo.getTitle());
        contentValues.put(DESCRIPTION, todo.getDescription());
        contentValues.put(STARTED, todo.getStarted());
        contentValues.put(FINISHED, todo.getFinished());
        //update the database and get the status
        int status = db.update(TABLE_NAME, contentValues, ID + " =?", new String[]{String.valueOf(todo.getId())});

        db.close();
        return status; //return the status
    }
}
