package ca.codemake.workout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected FrameLayout content_frame;
    protected FloatingActionButton fab;
    protected ActionBar actionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar);

        content_frame = (FrameLayout) findViewById(R.id.content_frame);

        initAppBar();
        initFAB();
    }

    private void initAppBar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        showFAB(false);
    }

    protected void showFAB(boolean visible) {
        if(visible) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }
    }

    protected void inflateContentFrame(int contentFrame) {
        getLayoutInflater().inflate(contentFrame, content_frame, true);
    }
}
