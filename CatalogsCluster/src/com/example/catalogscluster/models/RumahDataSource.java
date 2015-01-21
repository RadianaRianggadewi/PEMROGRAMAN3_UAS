package com.example.catalogscluster.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RumahDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private String[] allColumns = { 
			dbHelper.COLUMN_ID, 
			dbHelper.COLUMN_NAMARUMAH, 
			dbHelper.COLUMN_STOK, 
			dbHelper.COLUMN_DESKRIPSI
		  };
	
	public RumahDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public void insertData(String nama, String ktp, String alamat) {

		  ContentValues cv = new ContentValues();
		  cv.put(MySQLiteHelper.COLUMN_NAMARUMAH, nama);
		  cv.put(MySQLiteHelper.COLUMN_STOK, ktp);
		  cv.put(MySQLiteHelper.COLUMN_DESKRIPSI, alamat);
		  database.insert(MySQLiteHelper.TABLE_RUMAH, null, cv);
	}
	
	public Cursor readEntry() {
		String[] allColumns = new String[] {
				dbHelper.COLUMN_ID, 
				dbHelper.COLUMN_NAMARUMAH, 
				dbHelper.COLUMN_STOK
		};
		
		Cursor c = database.query(dbHelper.TABLE_RUMAH, allColumns, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Rumah create(String nama, String ktp, String alamat) {
		ContentValues value = new ContentValues();
		value.put(MySQLiteHelper.COLUMN_NAMARUMAH, nama);
		value.put(MySQLiteHelper.COLUMN_STOK, ktp);
		value.put(MySQLiteHelper.COLUMN_DESKRIPSI, alamat);
		
		long insertId = database.insert(MySQLiteHelper.TABLE_RUMAH, null, value);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_RUMAH, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		null, null, null);
		cursor.moveToFirst();
		Rumah newUser = cursorToString(cursor);
		cursor.close();
		return newUser;
	}
	
	private Rumah cursorToString(Cursor cursor) {
	    Rumah rumah = new Rumah();
	    rumah.setNama(cursor.getString(0));
	    rumah.setStok(cursor.getString(1));
	    rumah.setDesc(cursor.getString(2));
	    return rumah;
	  }
}
