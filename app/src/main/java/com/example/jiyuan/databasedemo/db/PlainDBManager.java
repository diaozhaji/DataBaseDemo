package com.example.jiyuan.databasedemo.db;

import android.content.Context;
import com.example.jiyuan.databasedemo.model.PlainPerson;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class PlainDBManager {
    private PlainDBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public PlainDBManager(Context context) {
        mDBHelper = new PlainDBHelper(context);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void addPersonData(PlainPerson person) {
        try {
            // 开启事务
            mDB.beginTransaction();

            // 执行插入语句
            final String sql = "INSERT INTO person VALUES(NULL,?,?)";
            Object[] objects = new Object[] { person.getName(), person.getAddress() };
            mDB.execSQL(sql, objects);

            // 设置事务完成成功
            mDB.setTransactionSuccessful();
        } finally {
            // 关闭事务
            mDB.endTransaction();
        }
    }

    public boolean addPersonList(List<PlainPerson> list) {
        try {
            // 开启事务
            mDB.beginTransaction();

            // 执行插入语句
            for (PlainPerson person : list) {
                Object[] objects = new Object[] { person.getName(), person.getAddress() };
                final String sql = "INSERT INTO person VALUES(NULL,?,?)";
                mDB.execSQL(sql, objects);
            }

            // 设置事务完成成功
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            return false;
        } finally {
            // 关闭事务
            mDB.endTransaction();
        }
        return true;
    }

    /**
     * 拿到数据库中所有的Person并放入集合中
     */
    public List<PlainPerson> getPersonListData() {
        List<PlainPerson> listData = new ArrayList<>();
        Cursor c = getAllPersonInfo();
        while (c.moveToNext()) {
            PlainPerson person = new PlainPerson();
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setAddress(c.getString(c.getColumnIndex("address")));
            listData.add(person);
        }
        c.close();
        return listData;
    }

    private Cursor getAllPersonInfo() {
        return mDB.rawQuery("SELECT * FROM person", null);
    }

    /**
     * 关闭  database；
     */
    public void closeDB() {
        mDB.close();
    }

    /**
     * 删除数据库
     */
    public Boolean deleteDatabase() {
        return mDBHelper.onDelete();
    }
}
