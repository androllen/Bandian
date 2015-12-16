package com.cc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;


/**
 * Created by androllen on 2015/8/19.
 */
public class CCInfoDb {
    public static final String TB_INFO="tb_Info";

    public static class Cols implements BaseColumns {
        public static final String InfoID = "infoid";
        public static final String InfoName = "infoName";
        public static final String infoHeight="infoHeight";
        public static final String infoWeight="infoWeight";
        public static final String InfoIntroduction = "infoIntroduction";

    }
    int id=0;
    String InfoName;
    int infoHeight=0;
    int infoWeight=0;
    boolean infoIntroduction=false;

    public CCInfoDb(){

    }

    public CCInfoDb(int id, String mName,int mHeight,int mWeight) {
        this.id = id;
        this.InfoName = mName;
        this.infoHeight = mHeight;
        this.infoWeight = mWeight;
    }
    public String getName() {
        return this.InfoName;
    }
    public int getId() {
        return this.id;
    }
    public int getHeight() {
        return this.infoHeight;
    }
    public int getWeight() {
        return this.infoWeight;
    }
    public boolean getIntroduction() {return this.infoIntroduction;}

    public ArrayList<CCInfoDb> QueryToDo(Context context){
        ArrayList<CCInfoDb> list=new ArrayList<CCInfoDb>();
        SQLiteDatabase db = DbHelper.getInstance();

        if(db==null)
            return null;

        ContentValues values = new ContentValues();
        values.put(Cols.InfoID, getId());
        values.put(Cols.InfoName, getName());
        values.put(Cols.infoHeight, getHeight());
        values.put(Cols.infoWeight, getWeight());
        values.put(Cols.InfoIntroduction,getIntroduction());


        Cursor c = db.rawQuery("select * from " + TB_INFO , null);

        if(c==null){
            return null;
        }else{
            if(c.getCount()==0){
                if(!c.isClosed()){
                    c.close();
                }
            }
        }

        for (c.moveToFirst(); !c.isAfterLast();c.moveToNext()) {
            CCInfoDb mail = new CCInfoDb();
            mail.id = c.getInt(c.getColumnIndex(Cols.InfoID));
            mail.InfoName = c.getString(c.getColumnIndex(Cols.InfoName));
            mail.infoHeight = c.getInt(c.getColumnIndex(Cols.infoHeight));
            mail.infoWeight = c.getInt(c.getColumnIndex(Cols.infoWeight));
            mail.infoIntroduction=c.moveToPosition(c.getColumnIndex(Cols.InfoIntroduction));

            list.add(mail);
        }

        if (c != null && !c.isClosed()) {
            c.close();
        }

        return list;
    }

    public long updateToDo(Context context)
    {
        SQLiteDatabase db = DbHelper.getInstance();
        ContentValues values = new ContentValues();
        values.put(Cols.InfoID, getId());
        values.put(Cols.InfoName, getName());
        values.put(Cols.infoHeight, getHeight());
        values.put(Cols.infoWeight, getWeight());

        String strWhere = Cols.InfoID + " =? and " + Cols.InfoName + " =? and "+Cols.infoHeight+" =? and "+Cols.infoWeight;
        String[] strArgs = new String[] { String.valueOf(getId()), String.valueOf(getName()), String.valueOf(getHeight()),String.valueOf(getWeight())};

        long todo_id  =db.update(TB_INFO, values, strWhere, strArgs);
        return todo_id;
    }
    public long deleteToDo(Context context){
        SQLiteDatabase db = DbHelper.getInstance();

        String strWhere = Cols.InfoID + " =? and " + Cols.InfoName + " =? and "+Cols.infoHeight+" =? and "+Cols.infoWeight;
        String[] strArgs = new String[] { String.valueOf(getId()), String.valueOf(getName()), String.valueOf(getHeight()),String.valueOf(getWeight())};

        long todo_id = db.delete(TB_INFO, strWhere, strArgs);
        return todo_id;
    }

    public long createToDo(Context context) {
        SQLiteDatabase db = DbHelper.getInstance();

        ContentValues values = new ContentValues();
        values.put(Cols.InfoID, getId());
        values.put(Cols.InfoName, getName());
        values.put(Cols.infoHeight, getHeight());
        values.put(Cols.infoWeight, getWeight());

        long todo_id = db.insert(TB_INFO, null, values);
        return todo_id;
    }
}
