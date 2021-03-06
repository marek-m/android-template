package com.example.marek.templaterecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.marek.templaterecyclerview.R;
import com.example.marek.templaterecyclerview.model.ViewModel;

import java.util.List;


/**
 * Created by Marek on 2015-09-16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {


    private static final String TAG = MyRecyclerAdapter.class.getSimpleName();
    private List<ViewModel> items;
    private int lastPosition = -1;
    private Context context;
    private OnItemClickListener mItemClickListener;

    public MyRecyclerAdapter(List<ViewModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_example_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewModel item = items.get(position);
        holder.text.setText(item.getText());
        holder.itemView.setTag(item);

        //setAnimation(holder.itemView, position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mItemClickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    synchronized public void add(ViewModel item) {
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void remove(ViewModel item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text;
        int id;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.v(TAG, "onClick");
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v);
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view);
    }

    /* ANIMATION */

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}