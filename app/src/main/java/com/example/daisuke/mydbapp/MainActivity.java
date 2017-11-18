package com.example.daisuke.mydbapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//tableの定義
		//users (name,score)

		//open helper

		//open db
		UserOpenHelper userOpenHelper = new UserOpenHelper(this);
		SQLiteDatabase db = userOpenHelper.getWritableDatabase();

		//transaction
		try{
			db.beginTransaction();
			db.execSQL("update users set score = score + 10 " +
						"where name = 'chika'");
			db.execSQL("update users set score = score - 10 " +
					"where name = 'riko'");
			db.setTransactionSuccessful();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			db.endTransaction();
		}

		//処理
		//追加
//		ContentValues newUser = new ContentValues();
//		newUser.put(UserContract.Users.COL_NAME,"ruby");
//		newUser.put(UserContract.Users.COL_SCORE,86);
//		long newId = db.insert(
//				UserContract.Users.TABLE_NAME,
//				null,
//				newUser
//		);
//		//更新
//		ContentValues newScore = new ContentValues();
//		newScore.put(UserContract.Users.COL_SCORE,98);
//		int updatednewId = db.update(
//				UserContract.Users.TABLE_NAME,
//				newScore,
//				UserContract.Users.COL_NAME + " = ?",
//				new String[] {"chika"}
//		);
//		//deleted
//		int deletedCount = db.delete(
//				UserContract.Users.TABLE_NAME,
//				UserContract.Users.COL_NAME + " = ?",
//				new String[] {"riko"}
//		);
		Cursor c = null;
		c = db.query(
				UserContract.Users.TABLE_NAME,
				null,
				null,
				null,
				null,
				null,
				null
		);
		Log.v("DB_TEST","Count : " + c.getCount());
		while(c.moveToNext()){
			int id = c.getInt(c.getColumnIndex(UserContract.Users._ID));
			String name = c.getString(c.getColumnIndex(UserContract.Users.COL_NAME));
			int score = c.getInt(c.getColumnIndex(UserContract.Users.COL_SCORE));
			Log.v("DB_TEST","id: " + id + " name: " + name + " score: " + score);
		}
		c.close();
		//close db
		db.close();
	}
}
