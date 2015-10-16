package ca.codemake.workout;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.nutrition.NutritionCalculatorFragment;
import ca.codemake.workout.workout.WorkoutInputFragment;

public class MainActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    private WorkoutDbHelper db;

    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = WorkoutDbHelper.getInstance(getApplicationContext());
        setUpAppBar();

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);

        // Set initial fragment
        fragmentManager = getSupportFragmentManager();

//        loadWorkout();
        fragment = new Sample();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        createNavigationDrawer();
    }

    private void setUpAppBar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void createNavigationDrawer() {
        initNavigationView();
        initDrawerLayout();
    }

    protected void createNavigationDrawer(int layout) {
        createNavigationDrawer();
        setContent(layout);
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            // Called when a drawer has settled in a completely closed state.
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle(title);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            // Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle(title);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.getMenu().getItem(0).setChecked(true);

//      Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
//                if (menuItem.isChecked())
//                    menuItem.setChecked(false);
//                else
//                    menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.workout:
                        checkDrawerItem(menuItem);
                        fragment = new WorkoutInputFragment();
                        break;
                    case R.id.nutrition:
                        checkDrawerItem(menuItem);
                        fragment = new NutritionCalculatorFragment();
                        break;
                    case R.id.routines:
                        checkDrawerItem(menuItem);
                        break;
                    case R.id.setting:
                        checkDrawerItem(menuItem);
                        break;
                    default:
                        break;
                }

                if(fragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }
                return true;
            }
        });
    }

    private void loadWorkout() {
        fragment = new WorkoutInputFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    private void setContent(int layout) {
        frameLayout.addView(getLayoutInflater().inflate(layout, null));
    }

    // Swaps fragments in the main content view
    private void checkDrawerItem(MenuItem menuItem) {
        setTitle(menuItem.getTitle());
        menuItem.setChecked(true);
    }

    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    protected void onPause() {
        super.onPause();
//        db.close("MainActivity");
        db = WorkoutDbHelper.getInstance(getApplicationContext());
    }

    protected void onResume() {
        super.onResume();
//        db.open("MainActivity");
    }
}