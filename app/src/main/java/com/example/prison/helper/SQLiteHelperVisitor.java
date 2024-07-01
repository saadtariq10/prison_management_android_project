package com.example.prison.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.prison.model.Visitor;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperVisitor extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prisonDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VISITOR = "visitor";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_INMATE_NAME = "inmate_name";
    private static final String COLUMN_RELATIONSHIP = "relationship";
    private static final String COLUMN_VISIT_DATE = "visit_date";
    private static final String COLUMN_VISIT_TIME = "visit_time";

    public SQLiteHelperVisitor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VISITOR_TABLE = "CREATE TABLE " + TABLE_VISITOR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_CONTACT + " TEXT,"
                + COLUMN_INMATE_NAME + " TEXT,"
                + COLUMN_RELATIONSHIP + " TEXT,"
                + COLUMN_VISIT_DATE + " TEXT,"
                + COLUMN_VISIT_TIME + " TEXT" + ")";
        db.execSQL(CREATE_VISITOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITOR);
        onCreate(db);
    }

    public void addVisitor(Visitor visitor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, visitor.getVisitorName());
        values.put(COLUMN_CONTACT, visitor.getContact());
        values.put(COLUMN_INMATE_NAME, visitor.getInmateName());
        values.put(COLUMN_RELATIONSHIP, visitor.getRelationship());
        values.put(COLUMN_VISIT_DATE, visitor.getVisitDate());
        values.put(COLUMN_VISIT_TIME, visitor.getVisitTime());

        db.insert(TABLE_VISITOR, null, values);
        db.close();
    }

    public List<Visitor> getAllVisitors() {
        List<Visitor> visitors = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VISITOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Visitor visitor = new Visitor();
                visitor.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                visitor.setVisitorName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                visitor.setContact(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT)));
                visitor.setInmateName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INMATE_NAME)));
                visitor.setRelationship(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RELATIONSHIP)));
                visitor.setVisitDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VISIT_DATE)));
                visitor.setVisitTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VISIT_TIME)));

                visitors.add(visitor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return visitors;
    }
}
