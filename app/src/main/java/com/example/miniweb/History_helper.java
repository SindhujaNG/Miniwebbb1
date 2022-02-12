package com.example.miniweb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class History_helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sites.db";
    private static final String TABLE_SITES = "sites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "url";
    private static final String COLUMN_TITLE= "title";
    public static final String COLUMN_DATE = "date";
    public History_helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_SITES + "(" +
                COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + "TEXT" + COLUMN_TITLE + "TEXT" + COLUMN_DATE + " INTEGER" +")";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITES);
        onCreate(db);
    }

    public void addurl(Website website) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, website.getUrl()); // Contact Name
        values.put(COLUMN_TITLE,website.getTitle());
        values.put(COLUMN_DATE,website.getDate());
        db.insert(TABLE_SITES, null, values);
        db.close(); // Closing database connection
    }

    public void deleteurl(String urlName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SITES + " WHERE " + COLUMN_NAME + "=\"" + urlName + "\";");

    }
public List<Website>getAllWebsite()
{
    List<Website> websitelist=new ArrayList<Website>();
    String  selectQuery = "SELECT  * FROM " + TABLE_SITES;
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
            Website website = new Website();
            website.setId(Integer.parseInt(cursor.getString(0)));
            website.setUrl(cursor.getString(1));
            website.setTitle(cursor.getString(2));
            website.setDate(cursor.getLong(3));

            // Adding website to list
            websitelist.add(website);
        } while (cursor.moveToNext());
    }

    // return contact list
    return websitelist;
}

    // code to update the single contact
    public int updateWebsite(Website website) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, website.getUrl());
        values.put(COLUMN_TITLE, website.getTitle());

        // updating row
        return db.update(TABLE_SITES, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(website.getId()) });
    }

    // Deleting single contact
    public void deleteWebsite(Website website) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SITES, COLUMN_ID + " = ?",
                new String[] { String.valueOf(website.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getWebsiteCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SITES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}



