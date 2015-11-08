package ca.codemake.workout;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import ca.codemake.workout.nutrition.FoodFragment;
import ca.codemake.workout.nutrition.MealFragment;
import ca.codemake.workout.nutrition.NutritionCalculatorFragment;
import ca.codemake.workout.workout.ExerciseFragment;
import ca.codemake.workout.workout.RoutineFragment;
import ca.codemake.workout.workout.WorkoutInputFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private static final String SELECTED_NAVIGATION = null;
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private int selectedNavigation;

    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    private boolean fabVisible;

    FloatingActionButton fab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAppBar();

        fabVisible = true;

        initFab();

        createNavigationDrawer();

        if (savedInstanceState != null) {
            selectedNavigation = savedInstanceState.getInt(SELECTED_NAVIGATION);
            Log.d(TAG, "SELECTED NAVIGATION on RESTORE SAVED INSTANCE: " + selectedNavigation);
        } else {
            selectedNavigation = 0;
        }

        setFragment();
    }

    private void initAppBar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);

//        fab.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                if (fabVisible) {
//                    if(fragment != null) {
//                        switch (fragment.getClass().getSimpleName()) {
//                            case "WorkoutInputFragment":
//
////                                break;
//                            case "NutritionCalculatorFragment":
//
////                                break;
//                            default:
////                                break;
//                        }
////                        Snackbar.make(view, fragment.getClass().getSimpleName(), Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show();
//                    }
//                }
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });
    }

    private void createNavigationDrawer() {
        initDrawerLayout();
        initNavigationView();
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);

//      Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * TODO: selected navigation item on fragment if saved bundle
         * */
        navigationView.getMenu().getItem(selectedNavigation).setChecked(true);
        setTitle(navigationView.getMenu().getItem(selectedNavigation).getTitle());
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nutrition:
                checkDrawerItem(menuItem);
                selectedNavigation = 0;
                break;
            case R.id.workout:
                checkDrawerItem(menuItem);
                selectedNavigation = 1;
                break;
            case R.id.routines:
                checkDrawerItem(menuItem);
                selectedNavigation = 2;
                break;
            case R.id.setting:
                checkDrawerItem(menuItem);
                selectedNavigation = 3;
                break;
            case R.id.nav_routine:
                checkDrawerItem(menuItem);
                selectedNavigation = 4;
                break;
            case R.id.nav_exercise:
                checkDrawerItem(menuItem);
                selectedNavigation = 5;
                break;
            case R.id.nav_meal:
                checkDrawerItem(menuItem);
                selectedNavigation = 6;
                break;
            case R.id.nav_food:
                checkDrawerItem(menuItem);
                selectedNavigation = 7;
                break;
            default:
                break;
        }

//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        setFragment();
        return true;
    }

    // Swaps fragments in the main content view
    private void checkDrawerItem(MenuItem menuItem) {
        setTitle(menuItem.getTitle());
        menuItem.setChecked(true);
    }

    private void setFragment() {
        switch (selectedNavigation) {
            case 0:
                fragment = new NutritionCalculatorFragment();
                break;
            case 1:
                fragment = new WorkoutInputFragment();
                break;
            case 2:
//                fragment = null;
////                fragment = new SettingsPreferenceFragment();
//                // Display the fragment as the main content.
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.content_frame, new SettingsPreferenceFragment())
//                        .commit();
                break;
            case 3:
//                fragment = SettingsFragment.newInstance();
                fragment = new SettingsFragment();
                break;
            case 4:
                fragment = new RoutineFragment();
                break;
            case 5:
                fragment = new ExerciseFragment();
                break;
            case 6:
                fragment = new MealFragment();
                break;
            case 7:
                fragment = new FoodFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
//            fragment.setArguments(getIntent().getExtras());

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
//            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "ON SAVE INSTANCE SELECTED NAVIGATION: " + selectedNavigation);
        outState.putInt(SELECTED_NAVIGATION, selectedNavigation);
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "ON RESTORE SELECTED NAVIGATION: " + selectedNavigation);

        selectedNavigation = savedInstanceState.getInt(SELECTED_NAVIGATION);
    }

    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public static class SettingsPreferenceFragment extends PreferenceFragment {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }
    }
}