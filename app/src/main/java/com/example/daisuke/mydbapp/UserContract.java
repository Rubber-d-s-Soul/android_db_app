package com.example.daisuke.mydbapp;

import android.provider.BaseColumns;

/**
 * Created by daisuke on 2017/11/18.
 */

public final class UserContract {
	public UserContract(){};

	public static abstract class Users implements BaseColumns{ //_id　アンダーバーがつく
		public static final String TABLE_NAME = "users";
		public static final String COL_NAME = "name";
		public static final String COL_SCORE = "score";
	}
}
