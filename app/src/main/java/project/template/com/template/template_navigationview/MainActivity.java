package project.template.com.template.template_navigationview;

/**
 * Created by Marek on 2015-09-15.
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import project.template.com.template.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        setupActionBar();
        setupDrawer(savedInstanceState);
        bindData();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // open or close the drawer if home button is pressed
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // action bar menu behaviour
        switch (item.getItemId()) {
            case android.R.id.home:
                // TODO
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfiguration) {
        super.onConfigurationChanged(newConfiguration);
        mDrawerToggle.onConfigurationChanged(newConfiguration);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }


    private void setupActionBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
    }


    private void setupDrawer(Bundle savedInstanceState) {
        // reference
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_example_layout);
        mNavigationView = (NavigationView) findViewById(R.id.activity_example_navigation);

        // navigation
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

        // toggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // show initial fragment
        if (savedInstanceState == null) {
            MenuItem item = mNavigationView.getMenu().findItem(R.id.menu_example1);
            selectDrawerItem(item);
        }
    }


    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.menu_example1:
            case R.id.menu_example2:
            case R.id.menu_example3:
                fragment = null;
                break;

            case R.id.menu_example4:
            case R.id.menu_example5:
            case R.id.menu_example6:
            case R.id.menu_example7:
                intent = null;
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_example_container, fragment).commitAllowingStateLoss();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        if (intent != null) {
            startActivity(intent);
        }

        mDrawerLayout.closeDrawers();
    }


    private void bindData() {
        // reference
        TextView titleTextView = (TextView) findViewById(R.id.navigation_header_title);

        // title
        titleTextView.setText("Hello world");
    }
}
