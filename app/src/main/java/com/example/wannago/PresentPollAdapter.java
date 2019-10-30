package com.example.wannago;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wannago.present_poll_view.OnListFragmentInteractionListener;
import com.example.wannago.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PresentPollAdapter extends RecyclerView.Adapter<PresentPollAdapter.PollHolder> {

    private final List<Polls> mValues;
    //private final OnListFragmentInteractionListener mListener;

    public PresentPollAdapter(List<Polls> items) {
        mValues = items;
        //mListener = listener;
    }

    @Override
    public PresentPollAdapter.PollHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_polls_present, parent, false);
        return new PollHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PollHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.dest.setText(""+mValues.get(position).getDest());
        holder.price.setText(""+mValues.get(position).getPrice());
        holder.time.setText(""+mValues.get(position).getTime());
        holder.date.setText(""+mValues.get(position).getDate());
        //holder.seats.setText("Seats Available :"+mValues.get(position).getSeat());

        /*holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/

    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class PollHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView dest;
        public final TextView price;
        public final TextView time;
        public final TextView date;
        public final TextView seats;
        public Polls mItem;

        public PollHolder(View view) {
            super(view);
            mView = view;
            dest = (TextView) view.findViewById(R.id.dest_r);
            price= (TextView) view.findViewById(R.id.price_r);
            time= (TextView) view.findViewById(R.id.time_r);
            date= (TextView) view.findViewById(R.id.date_r);
            seats=(TextView) view.findViewById(R.id.seat);
        }

               @Override
        public String toString() {
            return super.toString() + " '" + dest.getText() + "'";
        }
    }
}
