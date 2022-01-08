package com.user.mytodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}