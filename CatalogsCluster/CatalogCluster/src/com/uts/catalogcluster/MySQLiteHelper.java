package com.uts.catalogcluster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_USERS = "user";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAMA = "nama";
  public static final String COLUMN_KTP = "ktp";
  public static final String COLUMN_ALAMAT = "alamat";
  public static final String COLUMN_HP = "hp";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_USER = "user";
  public static final String COLUMN_PASS = "pass";

  private static final String DATABASE_NAME = "user.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_USERS + "(" 
	  + COLUMN_ID + " integer primary key autoincrement, " 
	  + COLUMN_NAMA + " text not null, " 
	  + COLUMN_KTP + " text not null, " 
	  + COLUMN_ALAMAT + " text not null, " 
	  + COLUMN_HP + " text not null, " 
	  + COLUMN_EMAIL + " text not null, " 
	  + COLUMN_USER + " text not null, " 
	  + COLUMN_PASS + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    onCreate(db);
  }

} 