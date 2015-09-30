package ca.codemake.workout.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ca.codemake.workout.database.WorkoutContract.*;

// Helper Class
public class WorkoutDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Workout.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ", ";
    private static final String UNIQUE_CONST = " UNIQUE";
    private static final String NOT_NULL = " NOT NULL";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String DEFAULT = " DEFAULT";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String AUTO_INCREMENT = " AUTOINCREMENT";
    private static final String AUTO_PRIMARY_KEY = INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + NOT_NULL;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String DEFAULT_ZERO = " DEFAULT 0";

    // CREATE TABLES
    // Table Meals
    private static final String SQL_CREATE_MEALS = CREATE_TABLE + Meal.TABLE_NAME + " (" +
            Meal._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Meal.COLUMN_NAME_MEAL_NAME + TEXT_TYPE + UNIQUE_CONST + NOT_NULL + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_CALORIES + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_MARGIN_TOP + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO +
            ");";

    // Table Foods
    private static final String SQL_CREATE_FOODS = CREATE_TABLE + Food.TABLE_NAME + " (" +
            Food._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Food.COLUMN_NAME_FOOD_NAME + TEXT_TYPE + UNIQUE_CONST + NOT_NULL + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_CALORIES + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_MARGIN_TOP + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO
//            + COMMA_SEP +
//            Food.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
//            Food.COLUMN_NAME_SERVING_SIZE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
////            Food.COLUMN_NAME_PER + TEXT_TYPE + COMMA_SEP +
//            Food.COLUMN_NAME_FAT + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_FAT_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_SATURATED_FAT + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
////            Food.COLUMN_NAME_SATURATED_FAT_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_TRANS_FAT + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
////            Food.COLUMN_NAME_TRANS_FAT_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_SATURATED_AND_TRANS_FAT_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_CHOLESTEROL + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
////            Food.COLUMN_NAME_CHOLESTEROL_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_SODIUM + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_SODIUM_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_CARBOHYDRATE + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_CARBOHYDRATE_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_FIBRE + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_FIBRE_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_SUGARS + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
////            Food.COLUMN_NAME_SUGARS_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_PROTEIN + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
////            Food.COLUMN_NAME_PROTEIN_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_VITAMIN_A + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_VITAMIN_A_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_VITAMIN_C + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_VITAMIN_C_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_CALCIUM + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_CALCIUM_DV + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_IRON + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
//            Food.COLUMN_NAME_IRON_DV + REAL_TYPE + DEFAULT_ZERO
            + ");";

    // Table MealEntries
    private static final String SQL_CREATE_MEAL_ENTRIES = CREATE_TABLE + MealEntry.TABLE_NAME + " (" +
            MealEntry._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            MealEntry.COLUMN_NAME_MEAL_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            MealEntry.COLUMN_NAME_FOOD_ID + INTEGER_TYPE + NOT_NULL
//            + COMMA_SEP  +
//            MealEntry.COLUMN_NAME_DATE + TEXT_TYPE + NOT_NULL
            + ");";

    // DELETE
    // Table Meals
    private static final String SQL_DELETE_MEALS = DROP_TABLE + Meal.TABLE_NAME;

    // Table Foods
    private static final String SQL_DELETE_FOODS = DROP_TABLE + Food.TABLE_NAME;

    // MealEntries
    private static final String SQL_DELETE_MEAL_ENTRIES = DROP_TABLE + MealEntry.TABLE_NAME;

    public WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DbHelper", "Constructor");
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MEALS);
        db.execSQL(SQL_CREATE_FOODS);
        db.execSQL(SQL_CREATE_MEAL_ENTRIES);
        Log.d("DbHelper", "Database Created");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MEAL_ENTRIES);
        db.execSQL(SQL_DELETE_FOODS);
        db.execSQL(SQL_DELETE_MEALS);
        Log.d("DbHelper", "Database Upgraded");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion, newVersion);
    }
}
