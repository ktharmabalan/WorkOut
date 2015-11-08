package ca.codemake.workout.database;

import android.provider.BaseColumns;

public final class WorkoutContract {

    // Columns
    public static final String COLUMN_NAME_CALORIES = "calories";

    // To prevent instantiating the contract class,
    // give it an empty constructor
    public WorkoutContract() {}

    // Inner class that defines the table contents
    public static abstract class Meal implements BaseColumns {
        public static final String TABLE_NAME = "meals";
        public static final String COLUMN_NAME_MEAL_NAME = "meal_name";
        public static final String COLUMN_NAME_VISIBLE = "visible";
    }

    public static abstract class Food implements BaseColumns {
        public static final String TABLE_NAME = "foods";
        public static final String COLUMN_NAME_FOOD_NAME = "food_name";
        public static final String COLUMN_NAME_SERVING_SIZE = "serving_size";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

    public static abstract class MealEntry implements BaseColumns {
        public static final String TABLE_NAME = "meal_entries";
        public static final String COLUMN_NAME_MEAL_ID = "meal_id";
        public static final String COLUMN_NAME_FOOD_ID = "food_id";
        public static final String COLUMN_NAME_NUMBER_OF_SERVINGS = "number_of_servings";
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
        public static final String COLUMN_NAME_ACTIVE = "active";
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

    public static abstract class NutritionFact implements BaseColumns {
        public static final String TABLE_NAME = "nutrition_facts";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DAILY_VALUE = "daily_value";
        public static final String COLUMN_NAME_AMOUNT = "amount";

        public static final String COLUMN_NAME_VISIBLE = "visible";
        public static final String COLUMN_NAME_VISIBLE_DV = "visible_dv";
        public static final String COLUMN_NAME_VISIBLE_AMOUNT = "visible_amount";
    }

    public static abstract class NutritionFactEntry implements BaseColumns {
        public static final String TABLE_NAME = "nutrition_facts_entry";
        public static final String COLUMN_NAME_NUTRITION_FACT_ID = "nutrition_fact_id";
        public static final String COLUMN_NAME_FOOD_ID = "food_id";
    }
}
