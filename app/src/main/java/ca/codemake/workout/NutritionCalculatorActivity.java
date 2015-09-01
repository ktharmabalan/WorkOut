package ca.codemake.workout;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import ca.codemake.workout.adapters.NutritionAdapter;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;
import ca.codemake.workout.models.MealEntry;

public class NutritionCalculatorActivity extends ListActivity implements View.OnClickListener {

    public NutritionAdapter nutritionAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_calculator);
        setUpButtons();
        loadData();
        setListAdapter(nutritionAdapter);
    }

    private void loadData() {
        nutritionAdapter = new NutritionAdapter(this);
/*
        ArrayList<MealEntry> mealEntries = new ArrayList<>();
        mealEntries.add(new MealEntry("Pancakes", 300));
        mealEntries.add(new MealEntry("Orange Juice", 100));

        mealEntries.add(new MealEntry("Burger", 500));
        mealEntries.add(new MealEntry("Pop", 200));

        mealEntries.add(new MealEntry("Pasta", 500));
        mealEntries.add(new MealEntry("Wine", 300));

        nutritionAdapter.addMealEntries(mealEntries);*/

        ArrayList<Item> items = new ArrayList<>();
        Meal meal = new Meal("Breakfast");
        items.add(meal);
        items.add(new MealEntry("Pancakes", 300));
        items.add(new MealEntry("Orange Juice", 100));

        meal = new Meal("Lunch");
        meal.setMarginTop(true);
        items.add(meal);
        items.add(new MealEntry("Burger", 500));
        items.add(new MealEntry("Pop", 200));

        meal = new Meal("Dinner");
        meal.setMarginTop(true);
        items.add(meal);
        items.add(new MealEntry("Pasta", 500));
        items.add(new MealEntry("Wine", 300));

        meal = new Meal("Other");
        meal.setMarginTop(true);
        items.add(meal);
        items.add(new MealEntry("Apple", 100));
        items.add(new MealEntry("Orange Juice", 200));
        items.add(new MealEntry("Cookies", 250));

        nutritionAdapter.setItems(items);

/*        Meal meal = new Meal("Breakfast");
        meal.AddMealEntry(new MealEntry("Pancakes", 300));
        meal.AddMealEntry(new MealEntry("Orange Juice", 100));
        nutritionAdapter.addMeal(meal);

        meal = new Meal("Lunch");
        meal.AddMealEntry(new MealEntry("Burger", 500));
        meal.AddMealEntry(new MealEntry("Pop", 200));
        nutritionAdapter.addMeal(meal);

        meal = new Meal("Dinner");
        meal.AddMealEntry(new MealEntry("Pasta", 500));
        meal.AddMealEntry(new MealEntry("Wine", 300));
        nutritionAdapter.addMeal(meal);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_nutrition_calculator, menu);
        return true;
    }

    public void setUpButtons() {
        Button b = (Button) this.findViewById(R.id.btn_nutrition_day);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_nutrition_day) {
            Intent i = new Intent(getApplicationContext(), AddNutritionEntryActivity.class);
            startActivity(i);
        }
    }
}
