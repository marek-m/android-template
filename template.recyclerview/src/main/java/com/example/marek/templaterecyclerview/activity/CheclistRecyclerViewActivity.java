package com.example.marek.templaterecyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.marek.templaterecyclerview.R;
import com.example.marek.templaterecyclerview.adapter.ChecklistRecyclerAdapter;
import com.example.marek.templaterecyclerview.model.ViewModel;

import java.util.Stack;
import java.util.Timer;


/**
 * Created by Marek on 2015-09-15.
 */
public class CheclistRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChecklistRecyclerAdapter mAdapter;
    private Timer timer = new Timer();
    private Stack<ViewModel> items;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setupActionBar();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        fillData();

    }

    private void fillData() {
        items = getItems();
        mAdapter = new ChecklistRecyclerAdapter(items, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /* TEST DATA FOR RECYCLERVIEW */
    private Stack<ViewModel> getItems() {
        Stack<ViewModel> result = new Stack<>();
        for(int i=0; i<10;i++) {
            ViewModel model = new ViewModel("model"+i, i%2==0);
            result.push(model);
        }
        return result;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // action bar menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_template_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // action bar menu behaviour
        switch (item.getItemId()) {
            case android.R.id.home:
                // TODO
                return true;

            case R.id.menu_refresh:
                // TODO
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setHomeButtonEnabled(true);
    }

}
