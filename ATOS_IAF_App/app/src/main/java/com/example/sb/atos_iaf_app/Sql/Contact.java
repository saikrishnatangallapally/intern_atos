package com.example.sb.atos_iaf_app.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import java.sql.SQLClientInfoException;

/**
 * Created by Akshitha on 30-06-2017.
 */

public class Contact extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DAS_ID = "DASID";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_OTP = "OTP";
    public static final String COLUMN_CONTACT = "Contact";
    private static final String TABLE_CREATE = " create table users(DASID text primary key not null ," +
            " Name text not null,email text not null,OTP text not null ," +
            " Contact long not null); ";

    public Contact(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
        //  db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COLUMN_NAME, "Akshitha Valishetti");
        v.put(COLUMN_DAS_ID, "A12345");
        v.put(COLUMN_EMAIL, "valishetti.akshitha@atos.net");
        v.put(COLUMN_OTP, "0000");
        v.put(COLUMN_CONTACT, "9878798978");
        ContentValues v1 = new ContentValues();
        v1.put(COLUMN_NAME, "Meghana Ponnuru");
        v1.put(COLUMN_DAS_ID, "A67890");
        v1.put(COLUMN_EMAIL, "ponnuru.meghana@atos.net");
        v1.put(COLUMN_OTP, "0000");
        v1.put(COLUMN_CONTACT, "9784984789");
        db.insert(TABLE_NAME, null, v);
        // db.insert(TABLE_NAME,null,v1);
        // db.close();
    }

    public String searchUser(String user) {
        System.out.print("hiausi");
        db = this.getReadableDatabase();
        String query = " select DASID,OTP from " +  TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        System.out.print("hiausi");
        String a, b;
        b = "not found";

        if (c.moveToFirst()) {
            do {
                a = c.getString(0);
                if (a.equals(user)) {
                    b = c.getString(1);

                }
            }

            while (c.moveToNext());
        }
        return b;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        String TABLE_DROP = " Drop Table if Exists " + TABLE_NAME;
        db.execSQL(TABLE_DROP);
        onCreate(db);
        // db.close();
    }
}