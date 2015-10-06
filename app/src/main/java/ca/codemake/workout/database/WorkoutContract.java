package ca.codemake.workout.database;

import android.provider.BaseColumns;

public final class WorkoutContract {

    // Columns
    public static final String COLUMN_NAME_CALORIES = "calories";
    public static final String COLUMN_NAME_MARGIN_TOP = "margin_top";

    // To prevent instantiating the contract class,
    // give it an empty constructor
    public WorkoutContract() {}

    // Inner class that defines the table contents
    public static abstract class Meal implements BaseColumns {
        public static final String TABLE_NAME = "meals";
        public static final String COLUMN_NAME_MEAL_NAME = "meal_name";
    }

    public static abstract class Food implements BaseColumns {
        public static final String TABLE_NAME = "foods";
        public static final String COLUMN_NAME_FOOD_NAME = "food_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";

        public static final String COLUMN_NAME_SERVING_SIZE = "serving_size";

//        public static final String COLUMN_NAME_PER = "per";
        public static final String COLUMN_NAME_FAT = "fat";
        public static final String COLUMN_NAME_FAT_DV = "fat_dv";
        public static final String COLUMN_NAME_SATURATED_FAT = "saturated_fat";
//        public static final String COLUMN_NAME_SATURATED_FAT_DV = "saturated_fat_dv";
        public static final String COLUMN_NAME_TRANS_FAT = "trans_fat";
//        public static final String COLUMN_NAME_TRANS_FAT_DV = "trans_fat_dv";
        public static final String COLUMN_NAME_SATURATED_AND_TRANS_FAT_DV = "saturated_and_trans_fat";
        public static final String COLUMN_NAME_CHOLESTEROL = "cholesterol";
//        public static final String COLUMN_NAME_CHOLESTEROL_DV = "cholesterol_dv";
        public static final String COLUMN_NAME_SODIUM = "sodium";
        public static final String COLUMN_NAME_SODIUM_DV = "sodium_dv";
        public static final String COLUMN_NAME_CARBOHYDRATE = "carbohydrate";
        public static final String COLUMN_NAME_CARBOHYDRATE_DV = "carbohydrate_dv";
        public static final String COLUMN_NAME_FIBRE = "fibre";
        public static final String COLUMN_NAME_FIBRE_DV = "fibre_dv";
        public static final String COLUMN_NAME_SUGARS = "sugars";
//        public static final String COLUMN_NAME_SUGARS_DV = "sugars_dv";
        public static final String COLUMN_NAME_PROTEIN = "protein";
//        public static final String COLUMN_NAME_PROTEIN_DV = "protein_dv";
        public static final String COLUMN_NAME_VITAMIN_A = "vitamin_a";
        public static final String COLUMN_NAME_VITAMIN_A_DV = "vitamin_a_dv";
        public static final String COLUMN_NAME_VITAMIN_C = "vitamin_c";
        public static final String COLUMN_NAME_VITAMIN_C_DV = "vitamin_c_dv";
        public static final String COLUMN_NAME_CALCIUM = "calcium";
        public static final String COLUMN_NAME_CALCIUM_DV = "calcium_dv";
        public static final String COLUMN_NAME_IRON = "iron";
        public static final String COLUMN_NAME_IRON_DV = "iron_dv";
    }

    public static abstract class MealEntry implements BaseColumns {
        public static final String TABLE_NAME = "meal_entries";
        public static final String COLUMN_NAME_MEAL_ID = "meal_id";
        public static final String COLUMN_NAME_FOOD_ID = "food_id";
        public static final String COLUMN_NAME_DATE = "date";
    }

    public static abstract class BodyGroup implements BaseColumns {
        public static final String TABLE_NAME = "body_groups";
        public static final String COLUMN_NAME_BODY_GROUP_NAME = "body_group_name";
    }

    public static abstract class Exercise implements BaseColumns {
        public static final String TABLE_NAME = "exercises";
        public static final String COLUMN_NAME_EXERCISE_NAME = "exercise_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

    public static abstract class Routine implements BaseColumns {
        public static final String TABLE_NAME = "routines";
        public static final String COLUMN_NAME_ROUTINE_NAME = "routine_name";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";
    }

    public static abstract class Workout implements BaseColumns {
        public static final String TABLE_NAME = "workouts";
        public static final String COLUMN_NAME_WORKOUT_NAME = "workout_name";
        public static final String COLUMN_NAME_ROUTINE_ID = "routine_id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_MARGIN_TOP = "margin_top";
    }

    public static abstract class ExerciseBodyGroup implements BaseColumns {
        public static final String TABLE_NAME = "exercise_body_groups";
        public static final String COLUMN_NAME_EXERCISE_ID = "exercise_id";
        public static final String COLUMN_NAME_BODY_GROUP_ID = "body_group_id";
    }

    public static abstract class ExerciseEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercise_entries";
        public static final String COLUMN_NAME_WORKOUT_ID = "workout_id";
        public static final String COLUMN_NAME_EXERCISE_ID = "exercise_id";
        public static final String COLUMN_NAME_SETS = "sets";
    }

    public static abstract class Configuration implements BaseColumns {
        public static final String TABLE_NAME = "configurations";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}
