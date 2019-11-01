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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public PastPollAdapter(List<Polls> items, OnListFragmentInteractionListener listener) {
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
        holder.dest.setText("" + mValues.get(position).getDest());
        holder.price.setText("" + mValues.get(position).getPrice());
        holder.time.setText("" + mValues.get(position).getTime());
        holder.date.setText("" + mValues.get(position).getDate());
        holder.name.setText("" + mValues.get(position).getName());
        //holder.seats.setText("Seats Available :"+mValues.get(position).getSeat());


        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(mValues.get(position));

                    final long[] count = {-1};

                    //Database reference for Creater details
                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("polls");

                    Map<String, String> creater = new HashMap<>();
                    creater.put("name", null);
                    creater.put("user", "" + mValues.get(position).getCreater_uid());
                    creater.put("poll", "" + mValues.get(position).getParent());

                    databaseReference2.child("" + mValues.get(position).getParent()).child("Added_users").child("" + mValues.get(position).getCreater_uid()).setValue(creater);

                    //Database reference for Added_users details
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("polls");

                    databaseReference.child("" + mValues.get(position).getParent()).child("Added_users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            count[0] = dataSnapshot.getChildrenCount();
                            if (count[0] > -1 && count[0] <= 3) {
                                //Storing Added Users in Firebase
                                Map<String, String> Added_users = new HashMap<>();
                                Added_users.put("name", "" + mValues.get(position).getName());
                                Added_users.put("user", "" + mValues.get(position).getUser());
                                Added_users.put("poll", "" + mValues.get(position).getParent());
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("polls");
                                databaseReference1.child("" + mValues.get(position).getParent()).child("Added_users").child("" + mValues.get(position).getUser()).setValue(Added_users);
                                Toast.makeText(v.getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(v.getContext(), "You Exceed Maximum Limit, Cannot Add More Than 3 Persons", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    /*databaseReference.child("" + mValues.get(position).getParent()).child("Added_users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            count[0] = dataSnapshot.getChildrenCount();

                            if (count[0] > -1 && count[0] < 3) {
                                //Storing Added Users in Firebase
                                Map<String, String> Added_users = new HashMap<>();
                                Added_users.put("name", "" + mValues.get(position).getName());
                                Added_users.put("user", "" + mValues.get(position).getUser());
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("polls");
                                databaseReference1.child("" + mValues.get(position).getParent()).child("Added_users").child("Person" + mValues.get(position).getKey()).setValue(Added_users);
                            } else {
                                Toast.makeText(v.getContext(), "You Exceed Maximum Limit, Cannot Add More Than 3 Persons", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/



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
        public final Button btnAdd;
        public TextView name;
        public Polls mItem;
        String added_name;
        String added_user;

        public PollHolder(View view) {
            super(view);
            mView = view;
            dest = (TextView) view.findViewById(R.id.dest_r);
            price = (TextView) view.findViewById(R.id.price_r);
            time = (TextView) view.findViewById(R.id.time_r);
            date = (TextView) view.findViewById(R.id.date_r);
            seats = (TextView) view.findViewById(R.id.seat);
            name = (TextView) view.findViewById(R.id.seat3);
            btnAdd = view.findViewById(R.id.btnAdd);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + dest.getText() + "'";
        }
    }
}
