package com.example.viewsanduielement.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.viewsanduielement.Expense;

import java.util.ArrayList;
import java.util.List;

public class DatabaseExpenses extends SQLiteOpenHelper {
    private static final String dbName = "dbExpense";
    private static final int dbVersion = 1;
    public static final String tblExpense = "tblExpense";
    public static final String colId = "id";
    public static final String colExpName = "exp_name";
    public static final String colExpDate = "exp_date";
    public static final String colExpValue = "exp_value";
    public static final String colExpQty = "exp_qty";
    public static final String colExpTotal = "exp_total";
    public static final String colExpImg = "exp_img";
    public static final String colExpDesc = "exp_desc";
    public static final String colExpType = "exp_type";

    public static final String createTable = "CREATE TABLE " + tblExpense + "(" + colId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            colExpName + " TEXT, " + colExpDate + " TEXT, " + colExpValue + " TEXT, " + colExpTotal + " TEXT, " +
            colExpImg + " TEXT, " + colExpDesc + " TEXT, " + colExpType + " TEXT, " + colExpQty + " TEXT)";

    public DatabaseExpenses(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tblExpense);
        onCreate(sqLiteDatabase);
    }

    public int fnInsertExpense(Expense expense) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colExpName, expense.getStrExpName());
        values.put(colExpDate, expense.getStrExpDate());
        values.put(colExpValue, expense.getStrExpValue());
        values.put(colExpQty, expense.getStrExpQty());
        values.put(colExpTotal, expense.getStrExpTotal());
        return (int) db.insert(tblExpense, null, values);
    }

    public List<Expense> fnGetAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String strSelect = "SELECT * FROM " + tblExpense;
        Cursor cursor = getReadableDatabase().rawQuery(strSelect, null);
        if (cursor.moveToFirst()) {
            do {
                String expName = cursor.getString(cursor.getColumnIndex(colExpName));
                String expDate = cursor.getString(cursor.getColumnIndex(colExpDate));
                float expValue = cursor.getFloat(cursor.getColumnIndex(colExpValue));
                int expQty = cursor.getInt(cursor.getColumnIndex(colExpQty));
                float expTotal = cursor.getFloat(cursor.getColumnIndex(colExpTotal));
                Expense expense = new Expense(expName, expDate, expValue, expQty);
                expenses.add(expense);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return expenses;
    }
}