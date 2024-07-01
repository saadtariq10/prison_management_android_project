package com.example.prison.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.prison.model.Block;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperCellBlock extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prisonDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BLOCK = "block";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BLOCK_NAME = "block_name";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_FLOOR = "floor";
    private static final String COLUMN_BUILDING = "building";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_SECURITY_LEVEL = "security_level";

    public SQLiteHelperCellBlock(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BLOCK_TABLE = "CREATE TABLE " + TABLE_BLOCK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BLOCK_NAME + " TEXT,"
                + COLUMN_CAPACITY + " TEXT,"
                + COLUMN_FLOOR + " TEXT,"
                + COLUMN_BUILDING + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_SECURITY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_BLOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOCK);
        onCreate(db);
    }

    public void addBlock(Block block) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BLOCK_NAME, block.getBlockName());
        values.put(COLUMN_CAPACITY, block.getCapacity());
        values.put(COLUMN_FLOOR, block.getFloor());
        values.put(COLUMN_BUILDING, block.getBuilding());
        values.put(COLUMN_DESCRIPTION, block.getDescription());
        values.put(COLUMN_SECURITY_LEVEL, block.getSecurityLevel());

        db.insert(TABLE_BLOCK, null, values);
        db.close();
    }

    public List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BLOCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Block block = new Block();
                block.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                block.setBlockName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BLOCK_NAME)));
                block.setCapacity(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAPACITY)));
                block.setFloor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FLOOR)));
                block.setBuilding(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BUILDING)));
                block.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                block.setSecurityLevel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SECURITY_LEVEL)));

                blocks.add(block);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return blocks;
    }
}
