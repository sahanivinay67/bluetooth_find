package com.example.search_bluetoothdevices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mm_admin";
    private static final int DATABASE_VERSION = 1;

    private static final String User_Table = "user_table";
    private static final String User_Id = "id";
    private static final String User_Name = "name";
    private static final String user_Number = "number";
    private static final String User_Status = "status";


    private static final String Create_Special_Scholarship_Table = "CREATE TABLE "
            + User_Table + "(" +
            User_Id + " INTEGER primary key ," +
            User_Name + " VARCHAR ," +
            user_Number + " VARCHAR, " +
            User_Status + " VARCHAR " + ")";





    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Special_Scholarship_Table);





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert_User_Data(User_Model user_model) {
        boolean creatSuccessfull = false;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(User_Id, user_model.getId());
            contentValues.put(User_Name, user_model.getName());
            contentValues.put(user_Number, user_model.getNumber());
            contentValues.put(User_Status, user_model.getStatus());

            SQLiteDatabase db = this.getWritableDatabase();
            creatSuccessfull = db.insertWithOnConflict(User_Table, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE) > 0;
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return creatSuccessfull;
    }
    public boolean is_ps_DataInserted() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(User_Table, null, null, null, null, null, null);
            return !(c != null && c.moveToFirst()) || c.getCount() == 0;
        } finally {
            assert c != null;
            c.close();
            db.close();
        }
    }
    public List<User_Model> get_User_Data() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User_Model> user_models = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *  FROM " + User_Table , null);

        int id = cursor.getColumnIndex(User_Id);
        int u_name = cursor.getColumnIndex(User_Name);
        int u_number = cursor.getColumnIndex(user_Number);
        int u_status = cursor.getColumnIndex(User_Status);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            int ID = cursor.getInt(id);
            String Name = cursor.getString(u_name);
            String Number = cursor.getString(u_number);
            int Status = cursor.getInt(u_status);


            user_models.add(new User_Model(ID, Name, Number, Status));
        }
        cursor.close();
        return user_models;
    }





}
