package com.example.sktest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "test.db";

    // DBHelper 생성자
    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    // Person Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ExistCar(carNum TEXT)");
    }

    // Person Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Person");
        onCreate(db);
    }

    // Person Table 데이터 입력
    public void insert(String carNum) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO ExistCar VALUES('" + carNum + "')");
        db.close();
    }

    /*
    // Person Table 데이터 수정
    public void Update(String name, int age, String Addr) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Person SET age = " + age + ", ADDR = '" + Addr + "'" + " WHERE NAME = '" + name + "'");
        db.close();
    }
    // Person Table 데이터 삭제
    public void Delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE Person WHERE NAME = '" + name + "'");
        db.close();
    }
     */

    // Person Table 조회
    public boolean getResult(String carNum) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM ExistCar WHERE carNum= ? ", new String[] {carNum});
        Log.d("carNum", carNum);
        while (cursor.moveToFirst()) {
            Log.d("cursor", cursor.getString(0));
            return true;
        }

        return false;
    }
}
