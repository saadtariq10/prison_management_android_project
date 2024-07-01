package com.example.prison.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.prison.model.Inmate;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelperInmate extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prisonDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_INMATE = "inmate";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_MARITAL_STATUS = "marital_status";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CRIME = "crime";
    private static final String COLUMN_COMPLEXION = "complexion";
    private static final String COLUMN_EYE_COLOR = "eye_color";
    private static final String COLUMN_SENTENCE_START_DATE = "sentence_start_date";
    private static final String COLUMN_SENTENCE_END_DATE = "sentence_end_date";

    public SqliteHelperInmate(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INMATE_TABLE = "CREATE TABLE " + TABLE_INMATE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FULL_NAME + " TEXT,"
                + COLUMN_DOB + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_MARITAL_STATUS + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_CRIME + " TEXT,"
                + COLUMN_COMPLEXION + " TEXT,"
                + COLUMN_EYE_COLOR + " TEXT,"
                + COLUMN_SENTENCE_START_DATE + " TEXT,"
                + COLUMN_SENTENCE_END_DATE + " TEXT" + ")";
        db.execSQL(CREATE_INMATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INMATE);
        onCreate(db);
    }

    public void addInmate(Inmate inmate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, inmate.getFullName());
        values.put(COLUMN_DOB, inmate.getDob());
        values.put(COLUMN_GENDER, inmate.getGender());
        values.put(COLUMN_MARITAL_STATUS, inmate.getMaritalStatus());
        values.put(COLUMN_ADDRESS, inmate.getAddress());
        values.put(COLUMN_CRIME, inmate.getCrime());
        values.put(COLUMN_COMPLEXION, inmate.getComplexion());
        values.put(COLUMN_EYE_COLOR, inmate.getEyeColor());
        values.put(COLUMN_SENTENCE_START_DATE, inmate.getSentenceStartDate());
        values.put(COLUMN_SENTENCE_END_DATE, inmate.getSentenceEndDate());

        db.insert(TABLE_INMATE, null, values);
        db.close();
    }

    public List<Inmate> getAllInmates() {
        List<Inmate> inmates = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_INMATE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Inmate inmate = new Inmate();
                inmate.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                inmate.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)));
                inmate.setDob(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOB)));
                inmate.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
                inmate.setMaritalStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MARITAL_STATUS)));
                inmate.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)));
                inmate.setCrime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CRIME)));
                inmate.setComplexion(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPLEXION)));
                inmate.setEyeColor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EYE_COLOR)));
                inmate.setSentenceStartDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENTENCE_START_DATE)));
                inmate.setSentenceEndDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENTENCE_END_DATE)));

                inmates.add(inmate);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return inmates;
    }
}
