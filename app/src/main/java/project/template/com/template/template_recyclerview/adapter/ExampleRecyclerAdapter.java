package project.template.com.template.template_recyclerview.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.template.com.template.R;

/**
 * Created by Marek on 2015-08-24.
 */
public class ExampleRecyclerAdapter extends CursorRecyclerAdapter<ExampleRecyclerAdapter.ViewHolder>  {

    private final Activity context;
    private OnItemClickListener mItemClickListener;

    public ExampleRecyclerAdapter(Cursor c, Activity activity) {
        super(c);
        this.context = activity;
    }

    /* Bind data to objects */
    @Override
    public void onBindViewHolder(ExampleRecyclerAdapter.ViewHolder holder, Cursor cursor) {

        //TODO get column idexes
        holder.id = cursor.getInt(0);
        holder.text1.setText(cursor.getString(1));
    }

    @Override
    public ExampleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.cardview_example_item, parent, false);
        return new ViewHolder(sView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView text1;
        int id;

        public ViewHolder(View row) {
            super(row);
            text1 = (TextView) row.findViewById(R.id.text1);
            row.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, id);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemLongClick(v, getPosition(), getItemId(), getItemViewType());
            }
            return true;
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position, long id, int viewType);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
