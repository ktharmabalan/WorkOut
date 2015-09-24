package ca.codemake.workout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    // Logcat Tag
    static final String TAG = "DBAdapter";

    // Database Version
    static final int DB_VERSION = 1;

    // Database Name
    static final String DB_NAME = "WORKOUT.db";

    // Table creating queries
    static final String CREATE_TABLE_MEAL = "CREATE TABLE meals (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, mealname STRING NOT NULL UNIQUE, calories INTEGER NOT NULL DEFAULT 0, margintop INTEGER NOT NULL DEFAULT 0);";
    static final String CREATE_TABLE_MEAL_ENTRY = "CREATE TABLE mealentries (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, foodname STRING NOT NULL UNIQUE, calories INTEGER NOT NULL DEFAULT 0, servingsize STRING NOT NULL, margintop INTEGER NOT NULL DEFAULT 0);";
    static final String CREATE_TABLE = "";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    // Helper class to create and manage the database
    private static class DatabaseHelper extends SQLiteOpenHelper {
        // Constructor
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // Create database if not present
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_MEAL);
                db.execSQL(CREATE_TABLE_MEAL_ENTRY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Upgrade database if not present
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS mealentries");
            db.execSQL("DROP TABLE IF EXISTS meals");
            onCreate(db);
        }
    }

    // Constructor
    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    // Open database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Close database
    public void close() {
        DBHelper.close();
    }

    public Cursor getAllMeals() {
        return db.rawQuery("SELECT mealname, calories, margintop from meals", null);
    }

    public long newMeal(String id, String mealname, int calories, int margintop) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("_id", id);
        initialValues.put("mealname", mealname);
        initialValues.put("calories", calories);
        initialValues.put("margintop", margintop);
        return db.insert("meals", null, initialValues);
    }

    public boolean deleteTimetableById(String timetableid){
        return db.delete("timetables", "_id="+timetableid, null) > 0;
    }

    public boolean updateTableslot(long id, String course, String room)
    {
        ContentValues args = new ContentValues();
        args.put("coursecode", course);
        args.put("room", room);
        return db.update("tableslots", args, "_id" + "=" + id, null) > 0;
    }

}
