package com.example.tom.androidtraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;

/**
 * Created by Tom on 10/22/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATA_VERSION = 1;
    private static final String DATABASE_NAME = "person.db";

    private static final String TABLE_PERSON = "person";

    public static final String COLUMN_PERSONID = "id";
    public static final String COLUMN_PERSONNAME = "name";
    public static final String COLUMN_PERSONEMAIL = "email";
    public static final String COLUMN_PERSONADDRESS = "address";
    public static final String COLUMN_PERSONCITY = "city";

    private Person [] personData;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PERSON + "(" +
                COLUMN_PERSONID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_PERSONNAME + " TEXT " +
                COLUMN_PERSONEMAIL + " TEXT " +
                COLUMN_PERSONADDRESS + " TEXT " +
                COLUMN_PERSONCITY + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PERSON);
        onCreate(db);
    }

    public void addPerson(String name, String email, String address, String city){

        //inserting column name and value
        ContentValues values = new ContentValues();

        values.put(COLUMN_PERSONNAME, name);
        values.put(COLUMN_PERSONEMAIL, email);
        values.put(COLUMN_PERSONADDRESS, address);
        values.put(COLUMN_PERSONCITY, city);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PERSON, null, values);

        db.close();
    }

    public Person [] getPeople (){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PERSON + ";";

        Cursor c = db.rawQuery(query, null);

        int numPeople = c.getCount();

        if (numPeople >= 1) {

            personData = new Person [numPeople];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                personData[i] = new Person (c.getString(c.getColumnIndex("address")),
                        c.getString(c.getColumnIndex("city")),
                        c.getString(c.getColumnIndex("email")),
                        c.getString(c.getColumnIndex("name"))
                        );

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return personData;
    }
}
