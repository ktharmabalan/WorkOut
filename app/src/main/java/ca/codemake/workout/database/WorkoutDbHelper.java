package ca.codemake.workout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ca.codemake.workout.database.WorkoutContract.BodyGroup;
import ca.codemake.workout.database.WorkoutContract.Configuration;
import ca.codemake.workout.database.WorkoutContract.Exercise;
import ca.codemake.workout.database.WorkoutContract.ExerciseBodyGroup;
import ca.codemake.workout.database.WorkoutContract.ExerciseEntry;
import ca.codemake.workout.database.WorkoutContract.Food;
import ca.codemake.workout.database.WorkoutContract.Meal;
import ca.codemake.workout.database.WorkoutContract.MealEntry;
import ca.codemake.workout.database.WorkoutContract.Routine;
import ca.codemake.workout.database.WorkoutContract.Workout;

// Helper Class
public class WorkoutDbHelper extends SQLiteOpenHelper {

    private static final String TAG = WorkoutDbHelper.class.getName();
    private SQLiteDatabase db;

    /* If you change the database schema, you must increment the database version */
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

    /* CREATE TABLES */
    /* Table Meals */
    private static final String SQL_CREATE_MEALS = CREATE_TABLE + Meal.TABLE_NAME + " (" +
            Meal._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Meal.COLUMN_NAME_MEAL_NAME + TEXT_TYPE + UNIQUE_CONST + NOT_NULL + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_CALORIES + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_MARGIN_TOP + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO +
            ");";

    /* Table Foods */
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

    /* Table MealEntries */
    private static final String SQL_CREATE_MEAL_ENTRIES = CREATE_TABLE + MealEntry.TABLE_NAME + " (" +
            MealEntry._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            MealEntry.COLUMN_NAME_MEAL_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            MealEntry.COLUMN_NAME_FOOD_ID + INTEGER_TYPE + NOT_NULL
//            + COMMA_SEP  +
//            MealEntry.COLUMN_NAME_DATE + TEXT_TYPE + NOT_NULL
            + ");";

    /* Table BodyGroup */
    private static final String SQL_CREATE_BODY_GROUPS = CREATE_TABLE + BodyGroup.TABLE_NAME + " (" +
            BodyGroup._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            BodyGroup.COLUMN_NAME_BODY_GROUP_NAME + TEXT_TYPE + NOT_NULL + UNIQUE_CONST
            + ");";

    /* Table Exercise */
    private static final String SQL_CREATE_EXERCISES = CREATE_TABLE + Exercise.TABLE_NAME + " (" +
            Exercise._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Exercise.COLUMN_NAME_EXERCISE_NAME + TEXT_TYPE + NOT_NULL + UNIQUE_CONST + COMMA_SEP +
            Exercise.COLUMN_NAME_DESCRIPTION + TEXT_TYPE
            + ");";

    /* Table Routine */
    private static final String SQL_CREATE_ROUTINES = CREATE_TABLE + Routine.TABLE_NAME + " (" +
            Routine._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Routine.COLUMN_NAME_ROUTINE_NAME + TEXT_TYPE + NOT_NULL + UNIQUE_CONST + COMMA_SEP +
            Routine.COLUMN_NAME_START_DATE + TEXT_TYPE + COMMA_SEP +
            Routine.COLUMN_NAME_END_DATE + TEXT_TYPE
            + ");";

    /* Table Workout */
    private static final String SQL_CREATE_WORKOUTS = CREATE_TABLE + Workout.TABLE_NAME + " (" +
            Workout._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Workout.COLUMN_NAME_WORKOUT_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            Workout.COLUMN_NAME_ROUTINE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            Workout.COLUMN_NAME_DATE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            Workout.COLUMN_NAME_MARGIN_TOP + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO
            + ");";

    /* Table ExerciseBodyGroup */
    public static final String SQL_CREATE_EXERCISE_BODY_GROUPS = CREATE_TABLE + ExerciseBodyGroup.TABLE_NAME + " (" +
            ExerciseBodyGroup._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            ExerciseBodyGroup.COLUMN_NAME_EXERCISE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            ExerciseBodyGroup.COLUMN_NAME_BODY_GROUP_ID + INTEGER_TYPE + NOT_NULL
            + ");";

    /* Table ExerciseEntry */
    public static final String SQL_CREATE_EXERCISE_ENTRIES = CREATE_TABLE + ExerciseEntry.TABLE_NAME + " (" +
            ExerciseEntry._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            ExerciseEntry.COLUMN_NAME_EXERCISE_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            ExerciseEntry.COLUMN_NAME_WORKOUT_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            ExerciseEntry.COLUMN_NAME_SETS + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO
            + ");";

    /* Table Configuration */
    public static final String SQL_CREATE_CONFIGURATIONS = CREATE_TABLE + Configuration.TABLE_NAME + " (" +
            Configuration._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Configuration.COLUMN_NAME_KEY + TEXT_TYPE + NOT_NULL + UNIQUE_CONST + COMMA_SEP +
            Configuration.COLUMN_NAME_VALUE + TEXT_TYPE + NOT_NULL
            + ");";



    // DELETE
    // Table Meals
    private static final String SQL_DELETE_MEALS = DROP_TABLE + Meal.TABLE_NAME;

    // Table Foods
    private static final String SQL_DELETE_FOODS = DROP_TABLE + Food.TABLE_NAME;

    // MealEntries
    private static final String SQL_DELETE_MEAL_ENTRIES = DROP_TABLE + MealEntry.TABLE_NAME;

    private static final String SQL_DELETE_BODY_GROUPS = DROP_TABLE + BodyGroup.TABLE_NAME;
    private static final String SQL_DELETE_EXERCISES = DROP_TABLE + Exercise.TABLE_NAME;
    private static final String SQL_DELETE_ROUTINES = DROP_TABLE + Routine.TABLE_NAME;
    private static final String SQL_DELETE_WORKOUTS = DROP_TABLE + Workout.TABLE_NAME;
    private static final String SQL_DELETE_EXERCISE_BODY_GROUPS = DROP_TABLE + ExerciseBodyGroup.TABLE_NAME;
    private static final String SQL_DELETE_EXERCISE_ENTRIES = DROP_TABLE + ExerciseEntry.TABLE_NAME;
    private static final String SQL_DELETE_CONFIGURATIONS = DROP_TABLE + Configuration.TABLE_NAME;

    public WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = open();
        Log.d(TAG, "Constructor");
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MEALS);
        db.execSQL(SQL_CREATE_FOODS);
        db.execSQL(SQL_CREATE_MEAL_ENTRIES);
        db.execSQL(SQL_CREATE_BODY_GROUPS);
        db.execSQL(SQL_CREATE_EXERCISES);
        db.execSQL(SQL_CREATE_ROUTINES);
        db.execSQL(SQL_CREATE_WORKOUTS);
        db.execSQL(SQL_CREATE_EXERCISE_BODY_GROUPS);
        db.execSQL(SQL_CREATE_EXERCISE_ENTRIES);
        db.execSQL(SQL_CREATE_CONFIGURATIONS);

        Log.d(TAG, "Database Created");

        this.db = db;
        loadInitialData();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MEAL_ENTRIES);
        db.execSQL(SQL_DELETE_FOODS);
        db.execSQL(SQL_DELETE_MEALS);
        db.execSQL(SQL_DELETE_BODY_GROUPS);
        db.execSQL(SQL_DELETE_EXERCISES);
        db.execSQL(SQL_DELETE_ROUTINES);
        db.execSQL(SQL_DELETE_WORKOUTS);
        db.execSQL(SQL_DELETE_EXERCISE_BODY_GROUPS);
        db.execSQL(SQL_DELETE_EXERCISE_ENTRIES);
        db.execSQL(SQL_DELETE_CONFIGURATIONS);

        Log.d(TAG, "Database Upgraded");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Open database
    public SQLiteDatabase open() throws SQLException {
        db = getWritableDatabase();
        Log.d(TAG, "Db Open");
        return db;
//        return getWritableDatabase();
    }

    // Close database
    public void close() {
        Log.d(TAG, "Db Close");
        db.close();
//        this.close();

    }

    public Cursor getAllMeals() {
        return db.rawQuery("SELECT " + Meal.COLUMN_NAME_MEAL_NAME + ", " + Meal._ID + " FROM " + Meal.TABLE_NAME, null);
    }

    public Cursor getAllEntriesForId(long id) {
        return db.rawQuery("SELECT " + Food.COLUMN_NAME_FOOD_NAME + " FROM " + Food.TABLE_NAME + " LEFT OUTER JOIN " + MealEntry.TABLE_NAME + " ON " + Food.TABLE_NAME + "." + Food._ID + "=" + MealEntry.TABLE_NAME + "." + MealEntry.COLUMN_NAME_FOOD_ID + " WHERE " + id + "=" + MealEntry.COLUMN_NAME_MEAL_ID, null);
    }

    // Get all meal entries
    public Cursor getAllMealEntries() {
        return db.rawQuery("SELECT foods._id as 'food id', foods.food_name, foods.calories, meals._id as 'meals id', meals.meal_name," +
                "(SELECT SUM(foods.calories) FROM foods JOIN meal_entries on meal_entries.food_id = foods._id JOIN meals as m ON m._id = meal_entries.meal_id WHERE meals._id = m._id) as 'total_calories'," +
                "(SELECT SUM(foods.calories) FROM foods JOIN meal_entries on meal_entries.food_id = foods._id JOIN meals as m ON m._id = meal_entries.meal_id) as 'all_calories'" +
                "FROM foods JOIN meal_entries on meal_entries.food_id = foods._id JOIN meals ON meals._id = meal_entries.meal_id ORDER BY meals._id ASC", null);
    }

    public Cursor getAllFoods() {
        return db.rawQuery("SELECT " + Food.COLUMN_NAME_FOOD_NAME + ", " + Food._ID + " from " + Food.TABLE_NAME, null);
    }

    public Cursor getExerciseEntries() {
        return db.rawQuery("SELECT ee." + ExerciseEntry._ID + " AS exercise_entry_id, ee." + ExerciseEntry.COLUMN_NAME_SETS + ", e." + Exercise._ID + " AS exercise_id, e." + Exercise.COLUMN_NAME_EXERCISE_NAME + ", w." + Workout._ID + " AS workout_id, w." + Workout.COLUMN_NAME_WORKOUT_NAME + ", w." + Workout.COLUMN_NAME_DATE + " AS workout_date, w." + Workout.COLUMN_NAME_MARGIN_TOP + ", r." + Routine._ID + " AS routine_id, r." + Routine.COLUMN_NAME_ROUTINE_NAME +
                " FROM " + Workout.TABLE_NAME + " AS w " +
                " JOIN " + Routine.TABLE_NAME + " AS r ON r." + Routine._ID + " = w." + Workout.COLUMN_NAME_ROUTINE_ID +
                " JOIN " + ExerciseEntry.TABLE_NAME + " AS ee ON ee." + ExerciseEntry.COLUMN_NAME_WORKOUT_ID + " = w." + Workout._ID +
                " JOIN " + Exercise.TABLE_NAME + " AS e ON e." + Exercise._ID + " = ee." + ExerciseEntry.COLUMN_NAME_EXERCISE_ID +
                " ORDER BY w." + Workout._ID + " ASC", null);
    }

    public Cursor getExerciseEntriesByWorkoutId(long id) {
        return db.rawQuery("SELECT w." + Workout.COLUMN_NAME_WORKOUT_NAME + " as workout_name, w." + Workout._ID + " as workout_id, e." + Exercise._ID + " as exercise_id, e." +
                Exercise.COLUMN_NAME_EXERCISE_NAME + " as exercise_name, ee." + ExerciseEntry._ID + " as exercise_entry_id, ee." + ExerciseEntry.COLUMN_NAME_SETS + " as sets, w." +
                Workout.COLUMN_NAME_ROUTINE_ID + " as routine_id FROM " + Workout.TABLE_NAME + " as w JOIN " + ExerciseEntry.TABLE_NAME + " as ee ON ee." + ExerciseEntry.COLUMN_NAME_WORKOUT_ID + "=w." +
                Workout._ID + " JOIN " + Exercise.TABLE_NAME + " as e ON e." + Exercise._ID + "=ee." + ExerciseEntry.COLUMN_NAME_EXERCISE_ID + " WHERE w." + Workout._ID + "=" + id, null);
    }

    public long newMeal(String mealname, int calories, int margintop) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Meal.COLUMN_NAME_MEAL_NAME, mealname);
        contentValues.put(WorkoutContract.COLUMN_NAME_CALORIES, calories);
        contentValues.put(WorkoutContract.COLUMN_NAME_MARGIN_TOP, margintop);
        return db.insert(Meal.TABLE_NAME, null, contentValues);
    }

    public long newFood(String food_name, int calories, int margintop) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_NAME_FOOD_NAME, food_name);
        contentValues.put(WorkoutContract.COLUMN_NAME_CALORIES, calories);
        contentValues.put(WorkoutContract.COLUMN_NAME_MARGIN_TOP, margintop);
        return db.insert(Food.TABLE_NAME, null, contentValues);
    }

    public long newMealEntry(long meal_id, long food_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MealEntry.COLUMN_NAME_MEAL_ID, meal_id);
        contentValues.put(MealEntry.COLUMN_NAME_FOOD_ID, food_id);
        return db.insert(MealEntry.TABLE_NAME, null, contentValues);
    }

    public long newBodyGroup(String body_group_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BodyGroup.COLUMN_NAME_BODY_GROUP_NAME, body_group_name);
        return db.insert(BodyGroup.TABLE_NAME, null, contentValues);
    }

    public long newExercise(String exerciseName, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Exercise.COLUMN_NAME_EXERCISE_NAME, exerciseName);
        contentValues.put(Exercise.COLUMN_NAME_DESCRIPTION, description);
        return db.insert(Exercise.TABLE_NAME, null, contentValues);
    }

    public long newRoutine(String routine_name, String start_date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Routine.COLUMN_NAME_ROUTINE_NAME, routine_name);
        contentValues.put(Routine.COLUMN_NAME_START_DATE, start_date);
        return db.insert(Routine.TABLE_NAME, null, contentValues);
    }

    public long newWorkout(String workout_name, long routine_id, String date, int margin_top) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Workout.COLUMN_NAME_WORKOUT_NAME, workout_name);
        contentValues.put(Workout.COLUMN_NAME_ROUTINE_ID, routine_id);
        contentValues.put(Workout.COLUMN_NAME_DATE, date);
        contentValues.put(Workout.COLUMN_NAME_MARGIN_TOP, margin_top);
        return db.insert(Workout.TABLE_NAME, null, contentValues);
    }

    public long newExerciseBodyGroup(long exercise_id, long body_group_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExerciseBodyGroup.COLUMN_NAME_EXERCISE_ID, exercise_id);
        contentValues.put(ExerciseBodyGroup.COLUMN_NAME_BODY_GROUP_ID, body_group_id);
        return db.insert(ExerciseBodyGroup.TABLE_NAME, null, contentValues);
    }

    public long newExerciseEntry(long workout_id, long exercise_id, int sets) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExerciseEntry.COLUMN_NAME_WORKOUT_ID, workout_id);
        contentValues.put(ExerciseEntry.COLUMN_NAME_EXERCISE_ID, exercise_id);
        contentValues.put(ExerciseEntry.COLUMN_NAME_SETS, sets);
        return db.insert(ExerciseEntry.TABLE_NAME, null, contentValues);
    }

    public long newConfiguration(String key, String value) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Configuration.COLUMN_NAME_KEY, key);
        contentValues.put(Configuration.COLUMN_NAME_VALUE, value);
        return db.insert(Configuration.TABLE_NAME, null, contentValues);
    }

    public Cursor getConfiguration(String key) {
        return db.rawQuery("SELECT " + Configuration.COLUMN_NAME_VALUE + " FROM " + Configuration.TABLE_NAME, null);
    }

    public boolean emptyRoutine() {
        Cursor cursor = db.rawQuery("SELECT COUNT(" + Routine._ID + ") FROM " + Routine.TABLE_NAME, null);
        if(cursor != null) {
            cursor.moveToFirst();
            Log.d(TAG, String.valueOf((cursor.getInt(0) == 0)));
            return (cursor.getInt(0) == 0);
        }
        return false;
    }

    public Cursor getRoutineById(Long id) {
        return db.rawQuery("SELECT " + Routine._ID + " as routine_id, " + Routine.COLUMN_NAME_ROUTINE_NAME + " as routine_name FROM " + Routine.TABLE_NAME + " WHERE " + Routine._ID + "=" + id, null);
    }

/*    public boolean deleteTimetableById(String timetableid){
        return db.delete("timetables", "_id=" + timetableid, null) > 0;
    }

    public boolean updateTableslot(long id, String course, String room)
    {
        ContentValues args = new ContentValues();
        args.put("coursecode", course);
        args.put("room", room);
        return db.update("tableslots", args, "_id" + "=" + id, null) > 0;
    }*/

    public void loadInitialData() {
        Log.d(TAG, "Load Initial Data");

        /* Insert nutrition data into database if there are no meal entries */
        newMeal("Breakfast", 0, 1);
        newMeal("Brunch", 0, 1);
        newMeal("Lunch", 0, 1);
        newMeal("Dinner", 0, 1);
        newMeal("Snack", 0, 1);

        newFood("Eggs", 100, 0);
        newFood("Bacon", 150, 0);
        newFood("Sausage", 130, 0);
        newFood("Toast", 50, 0);
        newFood("Ham", 75, 0);
        newFood("Pancake", 250, 0);

        newMealEntry(1, 1);
        newMealEntry(1, 2);
        newMealEntry(2, 3);
        newMealEntry(3, 4);
        newMealEntry(4, 5);
        newMealEntry(5, 1);
        newMealEntry(4, 1);
        newMealEntry(1, 6);

        /* Insert workout data */
        newBodyGroup("Arms");
        newBodyGroup("Legs");
        newBodyGroup("Chest");
        newBodyGroup("Back");
        newBodyGroup("Shoulders");
        newBodyGroup("Quads");
        newBodyGroup("Biceps");

        newExercise("Barbell Curls", "Nothing");
        newExercise("Leg Press", "Nothing");
        newExercise("Bench Press", "Nothing");
        newExercise("Rows", "Nothing");
        newExercise("Shoulder Press", "Nothing");
        newExercise("Squats", "Nothing");
        newExercise("Hammer Curls", "Nothing");
        newExercise("Calf Raise", "Nothing");

        newRoutine("First Routine", "Oct-2/2015");
        newRoutine("Second Routine", "Oct-2/2015");
        newRoutine("Third Routine", "Oct-3/2015");

        newWorkout("Leg day", 1, "Oct-2/2015", 1);
        newWorkout("Chest day", 1, "Oct-2/2015", 0);
        newWorkout("Back day", 1, "Oct-3/2015", 0);
        newWorkout("Arms day", 1, "Oct-3/2015", 0);

        newExerciseBodyGroup(1, 1);
        newExerciseBodyGroup(2, 2);
        newExerciseBodyGroup(3, 3);
        newExerciseBodyGroup(4, 4);
        newExerciseBodyGroup(5, 5);
        newExerciseBodyGroup(6, 6);
        newExerciseBodyGroup(7, 7);
        newExerciseBodyGroup(1, 7);
        newExerciseBodyGroup(2, 6);
        newExerciseBodyGroup(3, 5);

        newExerciseEntry(1, 2, 5);
        newExerciseEntry(1, 6, 4);
        newExerciseEntry(1, 8, 10);
        newExerciseEntry(4, 1, 10);
        newExerciseEntry(4, 7, 5);

        newConfiguration("CURRENT_ROUTINE", "1");
        newConfiguration("TARGET_CALORIES", String.valueOf(2000));
    }
}
