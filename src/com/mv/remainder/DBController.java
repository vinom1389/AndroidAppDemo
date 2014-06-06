package com.mv.remainder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.lang.*;

import android.text.format.Time;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	
	  Date dt = null;
	  Time today =null;
	public DBController(Context applicationcontext) {
        super(applicationcontext, "remainderdb4.db", null, 1);
        Log.d(LOGCAT,"Created");
    }	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE remainder4 ( Id INTEGER PRIMARY KEY, Name TEXT, Phoneno TEXT, Days TEXT, Months TEXT, Years TEXT, Events TEXT, Dobs TEXT, Groups TEXT, Emails TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT,"remainder Created");
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS remainder4";
		database.execSQL(query);
        onCreate(database);
	}
	
	public void insertAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Name", queryValues.get("Name"));
		values.put("Phoneno", queryValues.get("Phoneno"));
		values.put("Days", queryValues.get("Days"));
		values.put("Months", queryValues.get("Months"));
		values.put("Years", queryValues.get("Years"));		
		values.put("Events", queryValues.get("Events"));
		values.put("Dobs", queryValues.get("Dobs"));
		values.put("Groups", queryValues.get("Groups"));
		values.put("Emails", queryValues.get("Emails"));
		database.insert("remainder4", null, values);
		database.close();
	}
	
	public int updateAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("Name", queryValues.get("Name"));
	    values.put("Phoneno", queryValues.get("Phoneno"));
	    values.put("Days", queryValues.get("Days"));
		values.put("Months", queryValues.get("Months"));
		values.put("Years", queryValues.get("Years"));	    
	    values.put("Events", queryValues.get("Events"));
	    values.put("Dobs", queryValues.get("Dobs"));
	    values.put("Groups", queryValues.get("Groups"));
	    values.put("Emails", queryValues.get("Emails"));
	    return database.update("remainder4", values, "Id" + " = ?", new String[] { queryValues.get("Id") });
	    //String updateQuery = "Update  words set txtWord='"+word+"' where txtWord='"+ oldWord +"'";
	    //Log.d(LOGCAT,updateQuery);
	    //database.rawQuery(updateQuery, null);
	    //return database.update("words", values, "txtWord  = ?", new String[] { word });
	}
	
	public void deleteAnimal(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  remainder4 where Id='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	
	public ArrayList<HashMap<String, String>> getAllremainders() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM remainder4";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("Id", cursor.getString(0));
	        	map.put("Name", cursor.getString(1));
	        	map.put("Phoneno", cursor.getString(2));
	        	map.put("Events", cursor.getString(6));
	        	map.put("Dobs", cursor.getString(7));
	        	map.put("Emails", cursor.getString(8));
	        	
	        	wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wordList;
	}

	public ArrayList<HashMap<String, String>> getcurrentremainders() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM remainder4";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("Id", cursor.getString(0));
	        	map.put("Name", cursor.getString(1));
	        	map.put("Phoneno", cursor.getString(2));
	        	map.put("Events", cursor.getString(6));
	        	map.put("Dobs", cursor.getString(7));
	        	map.put("Emails", cursor.getString(8));
	        	
	        	wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wordList;
	}
	
	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM remainder4 where Id='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {
					//HashMap<String, String> map = new HashMap<String, String>();
	        	wordList.put("Name", cursor.getString(1));
	        	wordList.put("Phoneno", cursor.getString(2));
	        	wordList.put("Events", cursor.getString(6));
	        	wordList.put("Dobs", cursor.getString(7));
	        	wordList.put("Emails", cursor.getString(8));
	        	
				   //wordList.add(map);
	        } while (cursor.moveToNext());
	    }				    
	return wordList;
	}	
	
public ArrayList<HashMap<String, String>> gettodayremainder() {
	Time today = new Time(Time.getCurrentTimezone());
	today.setToNow();
	
	dt=new Date(); 
	dt.getYear(); 
	dt.getMonth(); 
	dt.getDay();
	ArrayList<HashMap<String, String>> wordList;
	wordList = new ArrayList<HashMap<String, String>>();
	String selectQuery = "SELECT * FROM remainder4 where Days='"+today.monthDay+"' and Months='"+today.month+"'";
    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
        do {
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("Name", cursor.getString(1));
        	map.put("Phoneno", cursor.getString(2));
        	map.put("Events", cursor.getString(6));
        	
            wordList.add(map);
        } while (cursor.moveToNext());
    }
 
    // return contact list
    return wordList;
}
public HashMap<String, String> getreminfo() {
	
	Time today = new Time(Time.getCurrentTimezone());
	today.setToNow();
	
	HashMap<String, String> wordList = new HashMap<String, String>();
	SQLiteDatabase database = this.getReadableDatabase();
	String selectQuery = "SELECT * FROM remainder4 where Days='"+today.monthDay+"' and Months='"+today.month+"'";
	Cursor cursor = database.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) {
        do {
				//HashMap<String, String> map = new HashMap<String, String>();
        	wordList.put("Name", cursor.getString(1));
        	wordList.put("Phoneno", cursor.getString(2));
        	wordList.put("Events", cursor.getString(6));
        	wordList.put("Dobs", cursor.getString(7));
        	wordList.put("Emails", cursor.getString(8));
        	
			   //wordList.add(map);
        } while (cursor.moveToNext());
    }				    
return wordList;
}	
}
