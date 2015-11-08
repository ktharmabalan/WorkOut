package ca.codemake.workout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import ca.codemake.workout.database.WorkoutContract.NutritionFact;
import ca.codemake.workout.database.WorkoutContract.NutritionFactEntry;
import ca.codemake.workout.database.WorkoutContract.Routine;
import ca.codemake.workout.database.WorkoutContract.Workout;

// Helper Class
public class WorkoutDbHelper extends SQLiteOpenHelper {

    private static WorkoutDbHelper workoutDbHelperInstance;
    private static final String TAG = "WorkoutDbHelper";
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
    private static final String DEFAULT_ONE = " DEFAULT 1";

    /* CREATE TABLES */
    /* Table Meals */
    private static final String SQL_CREATE_MEALS = CREATE_TABLE + Meal.TABLE_NAME + " (" +
            Meal._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Meal.COLUMN_NAME_MEAL_NAME + TEXT_TYPE + UNIQUE_CONST + NOT_NULL + COMMA_SEP +
            Meal.COLUMN_NAME_VISIBLE + INTEGER_TYPE + DEFAULT_ONE + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_CALORIES + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO +
            ");";

    /* Table Foods */
    private static final String SQL_CREATE_FOODS = CREATE_TABLE + Food.TABLE_NAME + " (" +
            Food._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            Food.COLUMN_NAME_FOOD_NAME + TEXT_TYPE + UNIQUE_CONST + NOT_NULL + COMMA_SEP +
            WorkoutContract.COLUMN_NAME_CALORIES + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO + COMMA_SEP +
            Food.COLUMN_NAME_SERVING_SIZE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            Food.COLUMN_NAME_DESCRIPTION + TEXT_TYPE
            + ");";

    /* Table MealEntries */
    private static final String SQL_CREATE_MEAL_ENTRIES = CREATE_TABLE + MealEntry.TABLE_NAME + " (" +
            MealEntry._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            MealEntry.COLUMN_NAME_MEAL_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            MealEntry.COLUMN_NAME_FOOD_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP  +
            MealEntry.COLUMN_NAME_NUMBER_OF_SERVINGS + REAL_TYPE + NOT_NULL + DEFAULT_ONE + COMMA_SEP +
            MealEntry.COLUMN_NAME_DATE + TEXT_TYPE + NOT_NULL
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
            Routine.COLUMN_NAME_ACTIVE + INTEGER_TYPE + NOT_NULL + DEFAULT_ZERO + COMMA_SEP +
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

    public static final String SQL_CREATE_NUTRITION_FACT = CREATE_TABLE + NutritionFact.TABLE_NAME + " (" +
            NutritionFact._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            NutritionFact.COLUMN_NAME_TITLE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            NutritionFact.COLUMN_NAME_AMOUNT + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
            NutritionFact.COLUMN_NAME_DAILY_VALUE + REAL_TYPE + DEFAULT_ZERO + COMMA_SEP +
            NutritionFact.COLUMN_NAME_VISIBLE + INTEGER_TYPE + DEFAULT_ONE + COMMA_SEP +
            NutritionFact.COLUMN_NAME_VISIBLE_AMOUNT + INTEGER_TYPE + DEFAULT_ONE + COMMA_SEP +
            NutritionFact.COLUMN_NAME_VISIBLE_DV + INTEGER_TYPE + DEFAULT_ONE
            + ");";

    public static final String SQL_CREATE_NUTRITION_FACT_ENTRY = CREATE_TABLE + NutritionFactEntry.TABLE_NAME + " (" +
            NutritionFactEntry._ID + AUTO_PRIMARY_KEY + COMMA_SEP +
            NutritionFactEntry.COLUMN_NAME_FOOD_ID + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
            NutritionFactEntry.COLUMN_NAME_NUTRITION_FACT_ID + INTEGER_TYPE + NOT_NULL
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
    private static final String SQL_DELETE_NUTRITION_FACT = DROP_TABLE + NutritionFact.TABLE_NAME;
    private static final String SQL_DELETE_NUTRITION_FACT_ENTRY = DROP_TABLE + NutritionFactEntry.TABLE_NAME;

    public static synchronized WorkoutDbHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (workoutDbHelperInstance == null) {
            workoutDbHelperInstance = new WorkoutDbHelper(context.getApplicationContext());
        }
        return workoutDbHelperInstance;
    }

    private WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Constructor");
//        db = open("WorkoutDbHelper");
        db = getWritableDatabase();
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
        db.execSQL(SQL_CREATE_NUTRITION_FACT);
        db.execSQL(SQL_CREATE_NUTRITION_FACT_ENTRY);

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
        db.execSQL(SQL_DELETE_NUTRITION_FACT);
        db.execSQL(SQL_DELETE_NUTRITION_FACT_ENTRY);

        Log.d(TAG, "Database Upgraded");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

//    // Open database
//    public SQLiteDatabase open(String caller) throws SQLException {
//        if(db == null) {
//            db = getWritableDatabase();
//            Log.d(TAG, "Db Opened by " + caller);
//        }
//        return db;
////        return getWritableDatabase();
//    }
//
//    // Close database
//    public void close(String caller) {
//        if(db != null) {
//            Log.d(TAG, "Db Closed by " + caller);
//            db.close();
//        }
////        this.close();
//    }

    public boolean isOpen() {
        return db.isOpen();
    }

    /*
    * INSERT METHODS
    * */
    public long newMeal(String mealname, int calories, boolean visible) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Meal.COLUMN_NAME_MEAL_NAME, mealname);
        contentValues.put(WorkoutContract.COLUMN_NAME_CALORIES, calories);
        contentValues.put(Meal.COLUMN_NAME_VISIBLE, visible);
        return db.insert(Meal.TABLE_NAME, null, contentValues);
    }

    public long newFood(String food_name, int calories, String serving_size, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_NAME_FOOD_NAME, food_name);
        contentValues.put(WorkoutContract.COLUMN_NAME_CALORIES, calories);
        contentValues.put(Food.COLUMN_NAME_SERVING_SIZE, serving_size);
        contentValues.put(Food.COLUMN_NAME_DESCRIPTION, description);
        return db.insert(Food.TABLE_NAME, null, contentValues);
    }

    public long newMealEntry(long meal_id, long food_id, double number_of_servings, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MealEntry.COLUMN_NAME_MEAL_ID, meal_id);
        contentValues.put(MealEntry.COLUMN_NAME_FOOD_ID, food_id);
        contentValues.put(MealEntry.COLUMN_NAME_NUMBER_OF_SERVINGS, number_of_servings);
        contentValues.put(MealEntry.COLUMN_NAME_DATE, date);
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

    public long newRoutine(String routine_name, Boolean active) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Routine.COLUMN_NAME_ROUTINE_NAME, routine_name);
        contentValues.put(Routine.COLUMN_NAME_ACTIVE, active);
        return db.insert(Routine.TABLE_NAME, null, contentValues);
    }

//    public long newRoutine(String routine_name, String start_date) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Routine.COLUMN_NAME_ROUTINE_NAME, routine_name);
//        contentValues.put(Routine.COLUMN_NAME_START_DATE, start_date);
//        return db.insert(Routine.TABLE_NAME, null, contentValues);
//    }

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

    public long newNutritionFact(String title, double amount, double daily_value, boolean visible, boolean visible_amount, boolean visible_dv) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NutritionFact.COLUMN_NAME_TITLE, title);
        contentValues.put(NutritionFact.COLUMN_NAME_AMOUNT, amount);
        contentValues.put(NutritionFact.COLUMN_NAME_DAILY_VALUE, daily_value);
        contentValues.put(NutritionFact.COLUMN_NAME_VISIBLE, visible);
        contentValues.put(NutritionFact.COLUMN_NAME_VISIBLE_AMOUNT, visible_amount);
        contentValues.put(NutritionFact.COLUMN_NAME_VISIBLE_DV, visible_dv);
        return db.insert(NutritionFact.TABLE_NAME, null, contentValues);
    }

    public long newNutritionFactEntry(long food_id, long nutrition_fact_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NutritionFactEntry.COLUMN_NAME_FOOD_ID, food_id);
        contentValues.put(NutritionFactEntry.COLUMN_NAME_NUTRITION_FACT_ID, nutrition_fact_id);
        return db.insert(NutritionFactEntry.TABLE_NAME, null, contentValues);
    }

    /*
    * GET METHODS
    * */
    public Cursor getAllNutritionFacts() {
        return db.query(NutritionFact.TABLE_NAME, new String[]{NutritionFact.COLUMN_NAME_TITLE, NutritionFact.COLUMN_NAME_AMOUNT, NutritionFact.COLUMN_NAME_DAILY_VALUE, NutritionFact.COLUMN_NAME_VISIBLE, NutritionFact.COLUMN_NAME_VISIBLE_AMOUNT, NutritionFact.COLUMN_NAME_VISIBLE_DV},
                null, null, null, null, null, null);
    }

    public Cursor getAllMeals() {
        return db.rawQuery("SELECT " + Meal.COLUMN_NAME_MEAL_NAME + ", " + Meal.COLUMN_NAME_VISIBLE + ", " + Meal._ID + " FROM " + Meal.TABLE_NAME, null);
    }

    public Cursor getAllEntriesForId(long id) {
        return db.rawQuery("SELECT " + Food.COLUMN_NAME_FOOD_NAME + " FROM " + Food.TABLE_NAME + " LEFT OUTER JOIN " + MealEntry.TABLE_NAME + " ON " + Food.TABLE_NAME + "." + Food._ID + "=" + MealEntry.TABLE_NAME + "." + MealEntry.COLUMN_NAME_FOOD_ID + " WHERE " + id + "=" + MealEntry.COLUMN_NAME_MEAL_ID, null);
    }

    // Get all meal entries
    public Cursor getAllMealEntries() {
        return db.rawQuery("SELECT foods._id as 'food id', foods.food_name, foods.calories, meals._id as 'meals id', meals.meal_name, meal_entries._id as 'meal_entry_id'," +
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

    public Cursor getConfiguration(String key) {
        return db.rawQuery("SELECT " + Configuration.COLUMN_NAME_VALUE + " FROM " + Configuration.TABLE_NAME, null);
    }

    public boolean emptyRoutine() {
        Cursor cursor = db.rawQuery("SELECT COUNT(" + Routine._ID + ") FROM " + Routine.TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.d(TAG, String.valueOf((cursor.getInt(0) == 0)));
            return (cursor.getInt(0) == 0);
        }
        return false;
    }

    public Cursor getRoutineById(Long id) {
        return db.rawQuery("SELECT " + Routine._ID + " as routine_id, " + Routine.COLUMN_NAME_ROUTINE_NAME + " as routine_name FROM " + Routine.TABLE_NAME + " WHERE " + Routine._ID + "=" + id, null);
    }

    public Cursor getRoutines() {
        return db.rawQuery("SELECT * FROM routines", null);
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
        newMeal("Breakfast", 0, true);
        newMeal("Brunch", 0, false);
        newMeal("Lunch", 0, true);
        newMeal("Dinner", 0, true);
        newMeal("Snack", 0, false);

        newFood("Eggs", 100, "200g", "");
        newFood("Bacon", 150, "200g", "");
        newFood("Sausage", 130, "200g", "");
        newFood("Toast", 50, "200g", "");
        newFood("Ham", 75, "200g", "");
        newFood("Pancake", 250, "200g", "");

        newMealEntry(1, 1, 2.5, "now");
        newMealEntry(1, 2, 2.5, "now");
        newMealEntry(2, 3, 2.5, "now");
        newMealEntry(3, 4, 2.5, "now");
        newMealEntry(4, 5, 2.5, "now");
        newMealEntry(5, 1, 2.5, "now");
        newMealEntry(4, 1, 2.5, "now");
        newMealEntry(1, 6, 2.5, "now");

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

//        newRoutine("First Routine", "Oct-2/2015");
//        newRoutine("Second Routine", "Oct-2/2015");
//        newRoutine("Third Routine", "Oct-3/2015");

        newRoutine("First Routine", true);
        newRoutine("Second Routine", false);
        newRoutine("Third Routine", false);

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

        newNutritionFact("Fat", 0, 0.0, true, true, true);
        newNutritionFact("Saturated Fat", 0, 0.0, true, true, true);
        newNutritionFact("Trans Fat", 0, 0.0, true, true, true);
        newNutritionFact("Polyunsaturated Fat", 0, 0.0, false, true, true);
        newNutritionFact("Monounsaturated Fat", 0, 0.0, false, true, true);
        newNutritionFact("Cholesterol", 0, 0.0, true, true, true);
        newNutritionFact("Sodium", 0, 0.0, true, true, true);
        newNutritionFact("Potassium", 0, 0.0, false, true, true);
        newNutritionFact("Carbohydrate", 0, 0.0, true, true, true);
        newNutritionFact("Fibre", 0, 0.0, true, true, true);
        newNutritionFact("Sugar", 0, 0.0, true, true, true);
        newNutritionFact("Protein", 0, 0.0, true, true, true);
        newNutritionFact("Vitamin A", 0, 0.0, true, true, true);
        newNutritionFact("Vitamin C", 0, 0.0, true, true, true);
        newNutritionFact("Calcium", 0, 0.0, true, true, true);
        newNutritionFact("Iron", 0, 0.0, true, true, true);
        newNutritionFact("Thiamine", 0, 0.0, false, true, true);
        newNutritionFact("Riboflavin", 0, 0.0, false, true, true);
        newNutritionFact("Vitamin D", 0, 0.0, false, true, true);
    }
}
