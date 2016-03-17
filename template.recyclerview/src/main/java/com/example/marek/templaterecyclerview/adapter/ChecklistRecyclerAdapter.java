package com.example.marek.templaterecyclerview.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marek.templaterecyclerview.R;
import com.example.marek.templaterecyclerview.model.ViewModel;

import java.util.List;


/**
 * Created by Marek on 2015-09-16.
 */
/* CHECKLIST WITH ANIMATION */
public class ChecklistRecyclerAdapter extends RecyclerView.Adapter<ChecklistRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    private List<ViewModel> items;
    private int lastPosition = -1;
    private Context context;

    public ChecklistRecyclerAdapter(List<ViewModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_checklist_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        holder.checkButton.setOnClickListener(this);
        return holder;
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        ViewModel item = items.get(position);
        holder.text.setText(item.getText());
        if(item.isChecked())
            holder.checkButton.setImageResource(R.drawable.ic_circle_check_done);

        holder.itemView.setTag(holder);
        holder.checkButton.setTag(holder);

       // setAnimation(holder.itemView, position);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public void add(ViewModel item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(ViewModel item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    private void updateCheckButton(final ViewHolder holder, boolean checked) {
        int position = holder.getAdapterPosition();
        if(checked) {
            AnimatorSet animatorSet = new AnimatorSet();

//            ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(holder.checkButton, "rotation", 0f, 360f);
//            rotationAnim.setDuration(300);
//            rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

            ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(holder.checkButton, "scaleX", 0.2f, 1f);
            bounceAnimX.setDuration(300);
            bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

            ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(holder.checkButton, "scaleY", 0.2f, 1f);
            bounceAnimY.setDuration(300);
            bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
            bounceAnimY.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    holder.checkButton.setImageResource(R.drawable.ic_circle_check_done);
                }
            });

            //animatorSet.play(rotationAnim);
            animatorSet.play(bounceAnimX).with(bounceAnimY);//.after(rotationAnim);

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //resetLikeAnimationState(holder);
                }
            });

            animatorSet.start();
            items.get(position).setChecked(true);
        } else {
            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(holder.checkButton, "rotation", 0f, 360f);
            rotationAnim.setDuration(300);
            rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);


            ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(holder.checkButton, "scaleX", 1f, 0.1f);
            bounceAnimX.setDuration(600);
            bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

            ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(holder.checkButton, "scaleY", 1f, 0.1f);
            bounceAnimY.setDuration(600);
            bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);


            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(holder.checkButton, "alpha",0);
            fadeOut.setDuration(100);

            bounceAnimX.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    holder.checkButton.setImageResource(R.drawable.ic_circle_unchecked);
                    holder.checkButton.setAlpha(1f);
                    holder.checkButton.setScaleX(1f);
                    holder.checkButton.setScaleY(1f);
                }
            });

            animatorSet.play(rotationAnim);
            animatorSet.play(bounceAnimX).with(bounceAnimY).with(fadeOut).after(rotationAnim);

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //resetLikeAnimationState(holder);
                }
            });

            animatorSet.start();
            items.get(position).setChecked(false);
        }


    }

    @Override
    public void onClick(View view) {
        Log.v("ChecklistRecyclerA", "onClick:");
        ViewHolder holder = (ViewHolder) view.getTag();
        if(view.getId() == R.id.chckBox) {
            ViewModel item = items.get(holder.getAdapterPosition());
            if(!item.isChecked()) {
                updateCheckButton(holder, true);
            } else {
                updateCheckButton(holder, false);
            }
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView checkButton;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text1);
            checkButton = (ImageView) itemView.findViewById(R.id.chckBox);
        }
    }


    /* ANIMATION */

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}