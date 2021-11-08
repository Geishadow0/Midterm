package com.school.midterm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {
    //Database Name
    public static final String DATABASE_NAME = "logindb";
    //Database Version
    public static final int DATABASE_VERSION = 1;
    //TABLE NAME
    public static final String TABLE_USERS = "users";
    //TABLE USERSCOLUMNS
    //ID COLUMN @primarykey
    public static final String KEY_ID = "id";
    //COLUMN user name
    public static final String KEY_USER_NAME = "username";
    //COLUMN email
    public static final String KEY_EMAIL = "email";
    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //sql for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT "
            + " ) ";

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create contact values to insert
        ContentValues values = new ContentValues();

        //put username in @values
        values.put(KEY_USER_NAME, user.userName);

        //put email in @values
        values.put(KEY_EMAIL, user.email);

        //put password in @values
        values.put(KEY_PASSWORD, user.password);

        //insert row
        db.insert(TABLE_USERS, null, values);
    }
    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_USERS, //Selecting table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{user.userName},//Where clause
                null, null, null );

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //match bot passwords check thery are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that username then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("recycle") Cursor cursor = db.query(TABLE_USERS, //Selecting table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null );

        //if cursor has value then in user database there is user associated with this given email so return true
        return cursor != null && cursor.moveToFirst() && cursor.getCount() > 0;
    }
}
