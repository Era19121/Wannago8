package com.example.wannago;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class past_poll_view extends Fragment implements OnListFragmentInteractionListener {

    DatabaseReference dbPolls;
    RecyclerView list;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Polls> mPolls;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public past_poll_view() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbPolls = FirebaseDatabase.getInstance().getReference("polls");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_polls_list_past, container, false);
        list = view.findViewById(R.id.list);
        mPolls = new ArrayList<>();
        // Set the adapter
        /*if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new PastPollAdapter(poll_list.get().getPolls(),this));
        }*/
        dbPolls.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    try {
                        Polls polls = postSnapshot.getValue(Polls.class);
                        final String parent = postSnapshot.getKey();


                    if (polls.getStatus() != null && polls.getStatus().equalsIgnoreCase("notactive")
                            ) {

                        postSnapshot.getRef().child("requested").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                mPolls.clear();
                                for (DataSnapshot postSnapshot1 : dataSnapshot.getChildren()) {
                                    try {
                                        //fetching values
                                        Polls requested_polls = postSnapshot1.getValue(Polls.class);
                                        String key = postSnapshot1.getKey();
                                        requested_polls.setParent(parent);
                                        requested_polls.setKey(key);
                                        mPolls.add(requested_polls);
                                    } catch (Exception e) {
                                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                if (mPolls != null && mPolls.size() > 0)
                                    list.setAdapter(new PastPollAdapter(mPolls, past_poll_view.this));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        });


                        /*//fetching requested user and name from firebase
                        try {
                            String requestName = postSnapshot.child("requested").child("name").getValue(String.class);
                            Long requestUser = postSnapshot.child("requested").child("user").getValue(Long.class);
                            if (requestName != null && requestUser != null) {
                                polls.setName(requestName);
                                polls.setUser(requestUser.toString());
                                polls.setKey(key);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        //add polls to Adapter
                        mPolls.add(polls);*/
                    }
                    }
                    catch(Exception e){
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                //commentsAdapter = new CommentsAdapter(TaskDetailsActivity.this, commentsEntities);
                //if (mPolls != null && mPolls.size() > 0)
                //   list.setAdapter(new PastPollAdapter(mPolls, past_poll_view.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }

    @Override
    public void onListFragmentInteraction(Polls item) {
        Toast.makeText(this.getActivity(), "" + item.getId(), Toast.LENGTH_SHORT).show();
    }


}
