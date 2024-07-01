package com.example.prison.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.prison.model.Prison;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperPrison extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prisonDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRISON = "prison";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRISON_NAME = "prison_name";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_SECURITY_LEVEL = "security_level";

    public SQLiteHelperPrison(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRISON_TABLE = "CREATE TABLE " + TABLE_PRISON + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRISON_NAME + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_CAPACITY + " INTEGER,"
                + COLUMN_SECURITY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_PRISON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRISON);
        onCreate(db);
    }

    public void addPrison(Prison prison) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRISON_NAME, prison.getName());
        values.put(COLUMN_LOCATION, prison.getLocation());
        values.put(COLUMN_CAPACITY, prison.getCapacity());
        values.put(COLUMN_SECURITY_LEVEL, prison.getSecurityLevel());

        db.insert(TABLE_PRISON, null, values);
        db.close();
    }

    public List<Prison> getAllPrisons() {
        List<Prison> prisons = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRISON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Prison prison = new Prison();
                prison.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                prison.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRISON_NAME)));
                prison.setLocation(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION)));
                prison.setCapacity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAPACITY)));
                prison.setSecurityLevel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SECURITY_LEVEL)));

                prisons.add(prison);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return prisons;
    }
}
