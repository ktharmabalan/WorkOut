package ca.codemake.workout;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class DrawerBaseActivity extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected ListView drawerList;
    protected String[] drawerItems;
    protected ActionBarDrawerToggle drawerToggle;
    protected CharSequence title;
    protected CharSequence drawerTitle;

    protected void onCreateDrawer(int layout) {
        drawerItems = getResources().getStringArray(R.array.drawer_items);

        title = drawerTitle = getTitle();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerItems));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            // Called when a drawer has settled in a completely closed state.
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            // Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

        setContent(layout);
    }

    private void setContent(int layout) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        View v = getLayoutInflater().inflate(layout, null);
        frameLayout.addView(v);
    }

    // Swaps fragments in the main content view
    private void selectItem(int position) {
//        // Create a new fragment and specify the fragment to show based on position
//        Fragment fragment = new Fragment();
//        Bundle args = new Bundle();
//        args.putInt("POSITION", position);
//        fragment.setArguments(args);
//
//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.content_frame, fragment)
//                .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(drawerItems[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    public void setTitle(CharSequence title) {
        this.title = title;
        getSupportActionBar().setTitle(title);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    // Called whenever we call invalidateOptionsMenu()
    public boolean onPrepareOptionsMenu(Menu menu){
        // If the navigation drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
//        menu.findItem(R.id.action_calendar).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

}
