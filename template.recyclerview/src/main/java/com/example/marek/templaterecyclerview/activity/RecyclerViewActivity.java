package com.example.marek.templaterecyclerview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.marek.templaterecyclerview.R;
import com.example.marek.templaterecyclerview.adapter.MyRecyclerAdapter;
import com.example.marek.templaterecyclerview.itemanimator.SlideInFromLeftItemAnimator;
import com.example.marek.templaterecyclerview.model.ViewModel;
import com.example.marek.templaterecyclerview.view.DetailView;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.UUID;

/**
 * Created by Marek on 2015-09-15.
 */
public class RecyclerViewActivity extends AppCompatActivity implements MyRecyclerAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerAdapter mAdapter;
    private Timer timer = new Timer();
    private Stack<ViewModel> items;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setupActionBar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(this, 3);
        //mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onItemClick(View view) {
        Log.v("RecyclerViewActivity", "onItemClick");
        String uuid = UUID.randomUUID().toString();
        DetailView.launch(RecyclerViewActivity.this, mRecyclerView, view.findViewById(R.id.item), uuid);
    }

    public Transition inflateTransition(int id) {
        return TransitionInflater.from(this).inflateTransition(id);
    }
    private class AddItemTask implements Runnable {

        ViewModel item;
        public AddItemTask(ViewModel item) {
            this.item= item;
        }

        @Override
        public void run() {
            mAdapter.add(item);
        }
    }
    private void fillData() {
        items = getItems();
        mAdapter = new MyRecyclerAdapter(new ArrayList<ViewModel>(0), this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new SlideInFromLeftItemAnimator(mRecyclerView));

        //ANIMATIONS
        Handler handler = new Handler(Looper.getMainLooper());
        int i = 200;
        while(!items.isEmpty()) {
            i+=200;
            handler.postDelayed(new AddItemTask(items.pop()), i);
        }
    }

    /* TEST DATA FOR RECYCLERVIEW */
    private Stack<ViewModel> getItems() {
        Stack<ViewModel> result = new Stack<>();
        for(int i=25; i>0;--i) {
            ViewModel model = new ViewModel("model"+i, false);
            result.push(model);
        }
        return result;
    }

    @Override
    public void onStart() {
        super.onStart();
        fillData();
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
