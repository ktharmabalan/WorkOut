package ca.codemake.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.nutrition.NutritionCalculatorActivity;
import ca.codemake.workout.workout.CreateRoutineActivity;
import ca.codemake.workout.workout.WorkoutInputActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private WorkoutDbHelper db;
    private final static String TAG = "MainActivity";

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));

/*        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        setContentView(R.layout.activity_main);

//        db = new WorkoutDbHelper(getApplicationContext());
//        setUpButtons();

    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            Fragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            if (position == 0) {
                args.putString(ViewPagerFragment.ARG_OBJECT, "Nutrition");
            } else {
                args.putString(ViewPagerFragment.ARG_OBJECT, "Workout");
            }
            fragment.setArguments(args);
            return fragment;
        }

        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
            if(position == 0)
                return "Nutrition";
            else
                return "Workout";
        }

    }

    public static class ViewPagerFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle args = getArguments();

//            View rootView = inflater.inflate(R.layout.viewpager_object, container, false);
//            ((TextView) rootView.findViewById(R.id.text1)).setText(args.getString(ARG_OBJECT));

            View rootView;
            if(args.getString(ARG_OBJECT) == "Workout")
                rootView = inflater.inflate(R.layout.activity_workout_menu, container, false);
            else
                rootView = inflater.inflate(R.layout.activity_nutrition_calculator, container, false);
            return rootView;
        }
    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void setUpButtons() {
        Button b = (Button) this.findViewById(R.id.btn_begin_workout);
        b.setOnClickListener(this);

        b = (Button) this.findViewById(R.id.btn_nutrition_calculator);
        b.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_begin_workout) {
//            Toast.makeText(this, "Begin Workout", Toast.LENGTH_SHORT).show();
            if(db.getConfiguration("CHOSEN_ROUTINE").getCount() == 0) {
                Intent i = new Intent(getApplicationContext(), CreateRoutineActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(getApplicationContext(), WorkoutInputActivity.class);
                startActivity(i);
            }
        } else if(b.getId() == R.id.btn_nutrition_calculator) {
//            Toast.makeText(this, "Nutrition Calculator", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), NutritionCalculatorActivity.class);
            startActivity(i);
        }
    }

/*    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        db.close();
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        db.open();
    }*/
}