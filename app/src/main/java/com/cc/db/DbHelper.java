package com.cc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by androllen on 2015/8/19.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbhelper.db";
    private static final int DB_VER = 1;

    private static SQLiteDatabase database;

    public static SQLiteDatabase getInstance() {
        return database;
    }

    public static void init(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DbHelper(context).getWritableDatabase();
        }
    }

    private DbHelper(Context context) {
        super(context, getDbName(), null, getDbVer());
    }

    public static String getDbName() {
        return DATABASE_NAME;
    }

    public static int getDbVer() {
        return DB_VER;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createMailTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    private static final String CREATE_TABLE_INFO = "CREATE TABLE " + CCInfoDb.TB_INFO + " ("
            + CCInfoDb.Cols.InfoID + " INTEGER PRIMARY KEY,"
            + CCInfoDb.Cols.InfoName + " TEXT,"
            + CCInfoDb.Cols.infoHeight + " integer,"
            + CCInfoDb.Cols.infoWeight + " integer" + ");";

    private void createMailTable(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INFO);
    }
}
