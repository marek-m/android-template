package com.example.marek.templaterecyclerview.view;

import android.content.Context;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.marek.templaterecyclerview.R;
import com.example.marek.templaterecyclerview.activity.RecyclerViewActivity;


public class DetailView extends RelativeLayout {
    public View transition_element;

    public DetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        transition_element = findViewById(R.id.transition_element);
    }

    public static void launch(RecyclerViewActivity activity, final View container, View iv, String transitionName) {

        final DetailView v = (DetailView) activity.getLayoutInflater().inflate(R.layout.view_detail, container, false);

        //same transition name
        iv.setTransitionName(transitionName);
        v.transition_element.setTransitionName(transitionName);


        Transition shared = activity.inflateTransition(android.R.transition.move);
        shared.addTarget(transitionName);
        shared.setDuration(2000);


        Transition fade = activity.inflateTransition(android.R.transition.fade);
        fade.excludeTarget(transitionName, true);
        fade.setDuration(2000);


        final TransitionSet set = new TransitionSet()
                .addTransition(fade)
                .addTransition(shared);

        Scene scene = new Scene(container, v);

        TransitionManager.go(scene, set);
    }

}
