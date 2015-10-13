package ca.codemake.workout;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.codemake.workout.database.WorkoutDbHelper;

public class ViewPagerActivity extends AppCompatActivity {
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if(position == 1)
                    closeOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



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
            if(args.getString(ARG_OBJECT) == "Workout") {
                rootView = inflater.inflate(R.layout.activity_workout_menu, container, false);
                setHasOptionsMenu(true);
            } else {
                rootView = inflater.inflate(R.layout.activity_nutrition_calculator, container, false);
                setHasOptionsMenu(true);
            }
            return rootView;
        }
    }

    public static class NutritionCalculatorFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for the fragment
            return inflater.inflate(R.layout.activity_nutrition_calculator, container, false);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        if(tabLayout.getSelectedTabPosition() == 0) {
            inflater.inflate(R.menu.menu_nutrition_calculator_activity_actions, menu);
        } else {
            inflater.inflate(R.menu.menu_create_routine_activity, menu);
        }

        return super.onPrepareOptionsMenu(menu);
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
