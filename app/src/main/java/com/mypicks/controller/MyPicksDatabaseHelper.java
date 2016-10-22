package com.mypicks.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mypicks.model.api.ChampionDto;
import com.mypicks.model.database.Champion;

import java.util.ArrayList;

/**
 * Created by Pierre-Alain SIMON on 21/03/2016.
 * Vivoka
 * simon_p@vivoka.com
 */
public class MyPicksDatabaseHelper extends SQLiteOpenHelper {
  private static final String TAG = "MyPicksDatabaseHelper";

  private static MyPicksDatabaseHelper sInstance;
  // Database Info
  private static final String DATABASE_NAME = "myPicksDatabase";
  private static final int DATABASE_VERSION = 1;

  // Table Names
  private static final String TABLE_PREF = "preference";
  private static final String TABLE_CHAMP = "champion";

  // Champion Table Columns
  private static final String KEY_CHAMP_ID = "id";
  private static final String KEY_CHAMP_NAME = "name";
  private static final String KEY_CHAMP_TITLE = "title";
  private static final String KEY_CHAMP_KEY = "key";

  // Preference Table Columns
  private static final String KEY_PREF_ID = "id";
  private static final String KEY_PREF_ROLE = "role";
  private static final String KEY_PREF_CHAMP_ID = "champion";

  public static synchronized MyPicksDatabaseHelper getInstance(Context context) {
    if (sInstance == null) {
      sInstance = new MyPicksDatabaseHelper(context.getApplicationContext());
    }
    return sInstance;
  }

  private MyPicksDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onConfigure(SQLiteDatabase db) {
    super.onConfigure(db);
    db.setForeignKeyConstraintsEnabled(true);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_PREF_TABLE = "CREATE TABLE " + TABLE_PREF +
        "(" +
        KEY_PREF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        KEY_PREF_ROLE + " TEXT," +
        KEY_PREF_CHAMP_ID + " INTEGER REFERENCES " + TABLE_CHAMP + "," +
        "UNIQUE(" + KEY_PREF_ROLE + "," + KEY_PREF_CHAMP_ID + ")" +
        ")";

    String CREATE_CHAMPIONS_TABLE = "CREATE TABLE " + TABLE_CHAMP +
        "(" +
        KEY_CHAMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        KEY_CHAMP_NAME + " TEXT," +
        KEY_CHAMP_TITLE + " TEXT," +
        KEY_CHAMP_KEY + " TEXT" +
        ")";

    db.execSQL(CREATE_CHAMPIONS_TABLE);
    db.execSQL(CREATE_PREF_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion != newVersion) {
      // Simplest implementation is to drop all old tables and recreate them
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREF);
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAMP);
      onCreate(db);
    }
  }

  public void addAllChampions(ArrayList<ChampionDto> champions) {
    // Create and/or open the database for writing
    SQLiteDatabase db = getWritableDatabase();

    // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
    // consistency of the database.
    db.beginTransaction();
    try {
      for (ChampionDto champ : champions) {
        ContentValues values = new ContentValues();
        values.put(KEY_CHAMP_NAME, champ.getName());
        values.put(KEY_CHAMP_TITLE, champ.getTitle());
        values.put(KEY_CHAMP_KEY, champ.getKey());

        // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
        db.insertOrThrow(TABLE_CHAMP, null, values);
      }
      db.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(TAG, "Error while trying to add champions to database");
    } finally {
      db.endTransaction();
    }
  }

  public void addPref(Champion champ, String lane) {
    SQLiteDatabase db = getWritableDatabase();

    db.beginTransaction();
    try {
      int champId = findChampion(champ);
      ContentValues values = new ContentValues();
      values.put(KEY_PREF_ROLE, lane);
      values.put(KEY_PREF_CHAMP_ID, champId);

      db.insertOrThrow(TABLE_PREF, null, values);
      db.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(TAG, "Error while trying to add preference to database");
    } finally {
      db.endTransaction();
    }
  }

  private int findChampion(Champion champ) {
    SQLiteDatabase db = getWritableDatabase();
    int userId = -1;

    db.beginTransaction();
    try {
        String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
            KEY_CHAMP_ID, TABLE_CHAMP, KEY_CHAMP_KEY);
        Cursor cursor = db.rawQuery(usersSelectQuery,
            new String[]{String.valueOf(champ.getChampionKey())});
        try {
          if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
            db.setTransactionSuccessful();
          }
        } finally {
          if (cursor != null && !cursor.isClosed()) {
            cursor.close();
          }
        }
    } catch (Exception e) {
      Log.d(TAG, "Error while trying to find champion id");
    } finally {
      db.endTransaction();
    }
    return userId;
  }

  public ArrayList<Champion> getAllChampions() {
    ArrayList<Champion> posts = new ArrayList<>();

    String CHAMP_SELECT_QUERY = String.format("SELECT * FROM %s ORDER BY %s",
        TABLE_CHAMP,
        KEY_CHAMP_NAME);

    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery(CHAMP_SELECT_QUERY, null);
    try {
      if (cursor.moveToFirst()) {
        do {
          Champion newChamp = createChampionObject(
              cursor.getString(cursor.getColumnIndex(KEY_CHAMP_NAME)),
              cursor.getString(cursor.getColumnIndex(KEY_CHAMP_TITLE)),
              cursor.getString(cursor.getColumnIndex(KEY_CHAMP_KEY)));

          posts.add(newChamp);
        } while(cursor.moveToNext());
      }
    } catch (Exception e) {
      Log.d(TAG, "Error while trying to get champions from database");
    } finally {
      if (cursor != null && !cursor.isClosed()) {
        cursor.close();
      }
    }
    return posts;
  }

  public ArrayList<Champion> getRole(String role) {
    ArrayList<Champion> posts = new ArrayList<>();

    String CHAMP_SELECT_QUERY = String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s WHERE %s.%s LIKE '" + role + "'",
        TABLE_CHAMP,
        TABLE_PREF,
        TABLE_CHAMP, KEY_CHAMP_ID,
        TABLE_PREF, KEY_PREF_CHAMP_ID,
        TABLE_PREF, KEY_PREF_ROLE);

    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery(CHAMP_SELECT_QUERY, null);
    try {
      if (cursor.moveToFirst()) {
        do {
          Champion newChamp = createChampionObject(
              cursor.getString(cursor.getColumnIndex(KEY_CHAMP_NAME)),
              cursor.getString(cursor.getColumnIndex(KEY_CHAMP_TITLE)),
              cursor.getString(cursor.getColumnIndex(KEY_CHAMP_KEY)));
          posts.add(newChamp);
        } while(cursor.moveToNext());
      }
    } catch (Exception e) {
      Log.d(TAG, "Error while trying to get " + role + " pref from database");
    } finally {
      if (cursor != null && !cursor.isClosed()) {
        cursor.close();
      }
    }
    return posts;
  }

  public void deleteChampionForRole(Champion champion, String role) {
    SQLiteDatabase db = getWritableDatabase();
    db.beginTransaction();

    String CHAMP_DELETE_QUERY = String.format("%s like '%s' AND %s = %s",
        KEY_PREF_ROLE,
        role,
        KEY_PREF_CHAMP_ID,
        String.valueOf(findChampion(champion)));

    db.delete(TABLE_PREF, CHAMP_DELETE_QUERY, null);
  }


  public void deleteAllPostsAndUsers() {
    SQLiteDatabase db = getWritableDatabase();
    db.beginTransaction();
    try {
      db.delete(TABLE_PREF, null, null);
      db.delete(TABLE_CHAMP, null, null);
      db.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(TAG, "Error while trying to delete all posts and users");
    } finally {
      db.endTransaction();
    }
  }

  /**
   * Create and return a new champion object
   * @param name of the champion
   * @param title of the champion
   * @param key of the champion in rito API
   * @return Champion
   */
  private Champion createChampionObject(String name, String title, String key) {
    Champion newChamp = new Champion();
    newChamp.setChampionName(name);
    newChamp.setChampionTitle(title);
    newChamp.setChampionKey(key);
    return newChamp;
  }
}
