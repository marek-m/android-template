package project.template.com.template.template_shared_element.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Stack;
import java.util.Timer;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import project.template.com.template.R;
import project.template.com.template.template_recyclerview.itemanimator.SlideInFromLeftItemAnimator;
import project.template.com.template.template_shared_element.adapter.MyRecyclerAdapter;
import project.template.com.template.template_shared_element.model.ViewModel;
import project.template.com.template.template_shared_element.utils.RecyclerItemClickListener;

/**
 * Created by Marek on 2015-09-15.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerAdapter mAdapter;
    private Timer timer = new Timer();
    private Stack<ViewModel> items;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        setupActionBar();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());

        fillData();

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
        mAdapter = new MyRecyclerAdapter(items, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new SlideInFromLeftItemAnimator(mRecyclerView));

//        //ANIMATIONS
        Handler handler = new Handler(Looper.getMainLooper());
        int i = 200;
        while(!items.isEmpty()) {
            i+=200;
            handler.postDelayed(new AddItemTask(items.pop()), i);
        }

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                View imageView = view.findViewById(R.id.shared_image);
                View textView = view.findViewById(R.id.shared_text);
                animateIntent(imageView, textView);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    /* TEST DATA FOR RECYCLERVIEW */
    private Stack<ViewModel> getItems() {
        Stack<ViewModel> result = new Stack<>();
        for(int i=10; i>0;--i) {
            ViewModel model = new ViewModel("model"+i, false);
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


    public void animateIntent(View imgView, View textView) {

        Intent intent = new Intent(this, DetailsActivity.class);
        // Pass data object in the bundle and populate details activity.

        //FOR MULTIPLE TRANSITIONS AT THE SAME TIME
        Pair<View, String> p1 = Pair.create(imgView, getString(R.string.shared_image_transition));
        Pair<View, String> p2 = Pair.create(textView, getString(R.string.shared_text_transition));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);

        startActivity(intent, options.toBundle());

    }

}
