package com.example.wannago;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wannago.dummy.DummyContent.DummyItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PastPollAdapter extends RecyclerView.Adapter<PastPollAdapter.PollHolder> {

    private final List<Polls> mValues;
    private final OnListFragmentInteractionListener mListener;

    public PastPollAdapter(List<Polls> items,OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public PollHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_polls_past, parent, false);
        return new PollHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PollHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.dest.setText(""+mValues.get(position).getDest());
        holder.price.setText(""+mValues.get(position).getPrice());
        holder.time.setText(""+mValues.get(position).getTime());
        holder.date.setText(""+mValues.get(position).getDate());
        holder.name.setText(""+mValues.get(position).getName());
        //holder.seats.setText("Seats Available :"+mValues.get(position).getSeat());



        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(mValues.get(position));
                    Toast.makeText(v.getContext(), ""+mValues.get(position).getKey() , Toast.LENGTH_SHORT).show();

                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("polls");

                    //Storing Added Users in Firebase
                    Map<String,String> Added_users=new HashMap<>();
                    Added_users.put("name",""+mValues.get(position).getName());
                    Added_users.put("user",""+mValues.get(position).getUser());
                    databaseReference.child(""+mValues.get(position).getParent()).child("Added_users").child("Person"+mValues.get(position).getKey()).setValue(Added_users);
                }
            }
        });

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
        public TextView name;
        String added_name;
        String added_user;
        public final Button btnAdd;
        public Polls mItem;

        public PollHolder(View view) {
            super(view);
            mView = view;
            dest = (TextView) view.findViewById(R.id.dest_r);
            price= (TextView) view.findViewById(R.id.price_r);
            time= (TextView) view.findViewById(R.id.time_r);
            date= (TextView) view.findViewById(R.id.date_r);
            seats=(TextView) view.findViewById(R.id.seat);
            name=(TextView) view.findViewById(R.id.seat3);
            btnAdd = view.findViewById(R.id.btnAdd);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + dest.getText() + "'";
        }
    }
}
