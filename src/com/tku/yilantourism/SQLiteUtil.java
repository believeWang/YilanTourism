package com.tku.yilantourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteUtil extends SQLiteOpenHelper {

	public final static int DATABASE_VERSION = 1;//
	public final static String DATABASE_NAME = "SQLiteUtil.db";
	public static final String TABLE_NAME = "SQLiteUtil";
	public static final String TABLE_NAME2 = "SQLiteUtil2";
	public static final String TABLE_NAME3 = "SQLiteUtil3";
	public static final String[] COLUMN_NAMEs3 = { "_site","_time","_money" };
	public static final String[] COLUMN_NAMEs2 = { "_which", "_check" };
	public static final String[] COLUMN_NAMEs = { "_title", "_snippet",
			"_latitude", "_longtitude" };

	public SQLiteUtil(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		Create(db, TABLE_NAME);
		Create(db, TABLE_NAME2);
		Create(db, TABLE_NAME3);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Drop(db, TABLE_NAME);
		Drop(db, TABLE_NAME2);
		Drop(db, TABLE_NAME3);
		Create(db, TABLE_NAME3);
		Create(db, TABLE_NAME2);
		Create(db, TABLE_NAME);

	}

	public void reBuild(String table) {
		SQLiteDatabase db = this.getReadableDatabase();
		Drop(db, table);
		Create(db, table);
		db.close();
	}

	public void Create(SQLiteDatabase db, String table) {
		// "create table " + TABLE_NAME + " (" +
		// "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,";

		if (table.equals(TABLE_NAME)) {
			db.execSQL("Create table " + TABLE_NAME + "("
					+ "_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,"
					+ "_title VARCHAR(25) NOT NULL,"
					+ "_snippet VARCHAR(60) NOT NULL,"
					+ "_latitude INTEGER NOT NULL,"
					+ "_longtitude INTEGER NOT NULL" + ");");
		}
		if (table.equals(TABLE_NAME2)) {
			db.execSQL("Create table " + TABLE_NAME2 + "("
					+ "_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,"
					+ "_which INTEGER NOT NULL,"
					+ "_check VARCHAR(25) NOT NULL" +

					");");

		}
		if (table.equals(TABLE_NAME3)) {
			db.execSQL("Create table " + TABLE_NAME3 + "("
					+ "_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,"
					+ "_site VARCHAR(25) NOT NULL,"
					+ "_time VARCHAR(25) NOT NULL,"
					+ "_money INTEGER NOT NULL" +
					

					");");

		}

	}

	public void Drop(SQLiteDatabase db, String table) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + table); //
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		// TODO
	}

	@Override
	public synchronized void close() {
		super.close();
	}

	public void execSQL(String strSQL) {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL(strSQL);
		db.close();
	}

	//
	// return the row ID of the newly inserted row, or -1 if an error occurred
	public long Insert(String table, ContentValues cv) {
		SQLiteDatabase db = this.getReadableDatabase();
		// Log.e("i", String.valueOf(list.size()));
		long row = db.insert(table, null, cv);
		db.close();
		return row;
	}

	// constrain don't need 'where'

	// return the number of rows affected
	public long Update(String table, String constrain, ContentValues cv) {
		SQLiteDatabase db = this.getWritableDatabase();
		long row = db.update(table, cv, constrain, null);
		db.close();
		return row;
	}

	// constrain don't need 'where'
	// return the number of rows affected if a whereClause is passed in,
	// 0 otherwise. To remove all rows and get a count pass "1" as the
	// whereClause.
	public int Delete(String table, String constrain) {
		SQLiteDatabase db = this.getWritableDatabase();
		int r = db.delete(table, constrain, null);
		db.close();
		return r;
	}

	public List<Set<String>> SelectAll(String table) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * from " + table, null);

		List<Set<String>> list = new ArrayList<Set<String>>();
		String s = "aaa";
		if (cursor.moveToFirst()) {
			do {
				// Map<String, String> map = new HashMap<String, String>();
				Set<String> set = new HashSet<String>();

				s = cursor.getString(cursor.getColumnIndex("_title"));
				set.add(s);
				// Log.e(s, s);
				s = cursor.getString(cursor.getColumnIndex("_snippet"));
				set.add(s);
				// Log.e(cursor.getString(cursor.getColumnIndex("_title")), s);
				s = cursor.getString(cursor.getColumnIndex("_latitude"));
				set.add(s);
				// Log.e(cursor.getString(cursor.getColumnIndex("_title")), s);
				s = cursor.getString(cursor.getColumnIndex("_longtitude"));
				set.add(s);
				// Log.e(cursor.getString(cursor.getColumnIndex("_title")), s);
				list.add(set);
				/*
				 * Iterator it=set.iterator(); while(it.hasNext()){ String
				 * test=(String) it.next(); Log.e("iterator", test); }
				 */

				/*
				 * for(int i=0;i<set.size();i++){ Object obj[]=set.toArray();
				 * Log.e("ARRAY[]"+String.valueOf(obj[i]),
				 * String.valueOf(obj[i])); } Log.e("~~~~~~~~~", "~~~~~~~~~~~");
				 * for(int i=0;i<set.size();i++){ Object obj[]=set.toArray();
				 * Log.e("ARRAY[]"+String.valueOf(i), String.valueOf(obj[i])); }
				 */
			} while (cursor.moveToNext());

		}
		cursor.close();
		db.close();
		return list;
	}

	public Map<String, String> SelectAll2(String table) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * from " + table, null);

		Map<String, String> map = new HashMap<String, String>();
		String s1 = "aaa";
		String s2 = "aaa";
		if (cursor.moveToFirst()) {
			do {

				s1 = cursor.getString(cursor.getColumnIndex("_which"));

				// Log.e(s, s);
				s2 = cursor.getString(cursor.getColumnIndex("_check"));
				map.put(s1, s2);
				/*
				 * Iterator it=set.iterator(); while(it.hasNext()){ String
				 * test=(String) it.next(); Log.e("iterator", test); }
				 */

				/*
				 * for(int i=0;i<set.size();i++){ Object obj[]=set.toArray();
				 * Log.e("ARRAY[]"+String.valueOf(obj[i]),
				 * String.valueOf(obj[i])); } Log.e("~~~~~~~~~", "~~~~~~~~~~~");
				 * for(int i=0;i<set.size();i++){ Object obj[]=set.toArray();
				 * Log.e("ARRAY[]"+String.valueOf(i), String.valueOf(obj[i])); }
				 */
			} while (cursor.moveToNext());

		}
		cursor.close();
		db.close();
		return map;
	}
	public ArrayList<Process> SelectAll3(String table) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * from " + table, null);

		ArrayList<Process> list = new ArrayList<Process>();
		String s = "aaa";
		if (cursor.moveToFirst()) {
			do {
				// Map<String, String> map = new HashMap<String, String>();
				Process sqlPro=new Process();

				s = cursor.getString(cursor.getColumnIndex("_site"));
				sqlPro.site=s;
				// Log.e(s, s);
				s = cursor.getString(cursor.getColumnIndex("_time"));
				sqlPro.time=s;
				// Log.e(cursor.getString(cursor.getColumnIndex("_title")), s);
				s = cursor.getString(cursor.getColumnIndex("_money"));
				sqlPro.money=s;
				
				list.add(sqlPro);
				/*
				 * Iterator it=set.iterator(); while(it.hasNext()){ String
				 * test=(String) it.next(); Log.e("iterator", test); }
				 */

				/*
				 * for(int i=0;i<set.size();i++){ Object obj[]=set.toArray();
				 * Log.e("ARRAY[]"+String.valueOf(obj[i]),
				 * String.valueOf(obj[i])); } Log.e("~~~~~~~~~", "~~~~~~~~~~~");
				 * for(int i=0;i<set.size();i++){ Object obj[]=set.toArray();
				 * Log.e("ARRAY[]"+String.valueOf(i), String.valueOf(obj[i])); }
				 */
			} while (cursor.moveToNext());

		}
		cursor.close();
		db.close();
		return list;
	}
}
