package ca.codemake.workout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ca.codemake.workout.database.WorkoutContract.*;

public class WorkoutDbAdapter {
    // Logcat Tag
    static final String TAG = "WorkoutDbAdapter";

    final Context context;
    WorkoutDbHelper workoutDbHelper;
    SQLiteDatabase db;

    // Constructor
    public WorkoutDbAdapter(Context context) {
        this.context = context;
        workoutDbHelper = new WorkoutDbHelper(context);
    }

    // Open database
    public WorkoutDbAdapter open() throws SQLException {
        db = workoutDbHelper.getWritableDatabase();
        Log.d(TAG, "DB OPEN");
        return this;
    }

    // Close database
    public void close() {
        Log.d(TAG, "DB CLOSE");
        db.close();
    }

    public Cursor getAllMeals() {
        return db.rawQuery("SELECT " + Meal.COLUMN_NAME_MEAL_NAME + ", " + Meal._ID + " from " + Meal.TABLE_NAME, null);
    }

    public Cursor getAllEntriesForId(long id) {
        return db.rawQuery("SELECT " + Food.COLUMN_NAME_FOOD_NAME + " FROM " + Food.TABLE_NAME + " LEFT OUTER JOIN " + MealEntry.TABLE_NAME + " ON " +Food.TABLE_NAME + "." + Food._ID + "=" + MealEntry.TABLE_NAME + "." + MealEntry.COLUMN_NAME_FOOD_ID + " WHERE " + id + "=" + MealEntry.COLUMN_NAME_MEAL_ID, null);
    }

    public Cursor getAllFoods() {
        return db.rawQuery("SELECT " + Food.COLUMN_NAME_FOOD_NAME + ", " + Food._ID + " from " + Food.TABLE_NAME, null);
    }

    public long newMeal(String mealname, int calories, int margintop) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Meal.COLUMN_NAME_MEAL_NAME, mealname);
        initialValues.put(WorkoutContract.COLUMN_NAME_CALORIES, calories);
        initialValues.put(WorkoutContract.COLUMN_NAME_MARGIN_TOP, margintop);
        return db.insert(Meal.TABLE_NAME, null, initialValues);
    }

    public long newFood(String food_name, int calories, int margintop) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Food.COLUMN_NAME_FOOD_NAME, food_name);
        initialValues.put(WorkoutContract.COLUMN_NAME_CALORIES, calories);
        initialValues.put(WorkoutContract.COLUMN_NAME_MARGIN_TOP, margintop);
        return db.insert(Food.TABLE_NAME, null, initialValues);
    }

    public long newMealEntry(long meal_id, long food_id){
        ContentValues initialValues = new ContentValues();
        initialValues.put(MealEntry.COLUMN_NAME_MEAL_ID, meal_id);
        initialValues.put(MealEntry.COLUMN_NAME_FOOD_ID, food_id);
        return db.insert(MealEntry.TABLE_NAME, null, initialValues);
    }

    public boolean deleteTimetableById(String timetableid){
        return db.delete("timetables", "_id=" + timetableid, null) > 0;
    }


    public boolean updateTableslot(long id, String course, String room)
    {
        ContentValues args = new ContentValues();
        args.put("coursecode", course);
        args.put("room", room);
        return db.update("tableslots", args, "_id" + "=" + id, null) > 0;
    }
}
