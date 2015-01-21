package com.example.catalogscluster.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDataSource {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { 
									dbHelper.COLUMN_ID, 
									dbHelper.COLUMN_NAMA, 
									dbHelper.COLUMN_KTP, 
									dbHelper.COLUMN_ALAMAT, 
									dbHelper.COLUMN_HP, 
									dbHelper.COLUMN_EMAIL, 
									dbHelper.COLUMN_USER, 
									dbHelper.COLUMN_PASS
								  };
	
	public UserDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public void deleteData(String id) {
		database.delete(dbHelper.TABLE_USERS, dbHelper.COLUMN_USER + "=?", new String[]{id}); 
	}
	
	public String getSinlgeEntry(String userName) {
		Cursor cursor=database.query(dbHelper.TABLE_USERS, null, dbHelper.TABLE_USERS+"=?", new String[]{userName}, null, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PASS));
        cursor.close();
        return password;                
    }
	
	public String getUsername(String userName) {
		Cursor cursor=database.query(dbHelper.TABLE_USERS, null, dbHelper.TABLE_USERS+"=?", new String[]{userName}, null, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String user= cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_USER));
        cursor.close();
        return user;                
    }
	
	public Cursor getData(String userName) {
		Cursor cursor=database.query(dbHelper.TABLE_USERS, null, dbHelper.COLUMN_USER+"=?", new String[]{userName}, null, null, null, null);

        if (cursor != null) {
        	cursor.moveToFirst();
		}
        //String user= cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_USER));
        //cursor.close();
        return cursor;                
    }
	
	 
	public Cursor readEntry() {
		String[] allColumns = new String[] {
				dbHelper.COLUMN_ID, 
				dbHelper.COLUMN_NAMA, 
				dbHelper.COLUMN_USER
		};
		
		Cursor c = database.query(dbHelper.TABLE_USERS, allColumns, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public User create(String nama, String ktp, String alamat, String hp, String email, String user, String pass) {
		ContentValues value = new ContentValues();
		value.put(MySQLiteHelper.COLUMN_NAMA, nama);
		value.put(MySQLiteHelper.COLUMN_KTP, ktp);
		value.put(MySQLiteHelper.COLUMN_ALAMAT, alamat);
		value.put(MySQLiteHelper.COLUMN_HP, hp);
		value.put(MySQLiteHelper.COLUMN_EMAIL, email);
		value.put(MySQLiteHelper.COLUMN_USER, user);
		value.put(MySQLiteHelper.COLUMN_PASS, pass);
		
		long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null, value);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		null, null, null);
		cursor.moveToFirst();
		User newUser = cursorToString(cursor);
		cursor.close();
		return newUser;
	}
	
	private User cursorToString(Cursor cursor) {
	    User user = new User();
	    user.setNama(cursor.getString(0));
	    user.setKtp(cursor.getLong(1));
	    user.setAlamat(cursor.getString(2));
	    user.setHP(cursor.getLong(3));
	    user.setEmail(cursor.getString(4));
	    user.setUser(cursor.getString(5));
	    user.setPass(cursor.getString(6));
	    return user;
	  }

}
