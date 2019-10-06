package com.example.wannago;
import java.util.ArrayList;
import java.util.List;

public class poll_list {
    private static poll_list sPollList;
    private List<Polls> mPolls;
    private poll_list() {
        mPolls=new ArrayList<>();
        for(int i=0;i<100;i++){
            Polls poll=new Polls();
            poll.setId(i);
            poll.setTitle("Item number "+i);
            mPolls.add(poll);
        }
    }

    public static poll_list get(){
        if(sPollList==null){
            sPollList=new poll_list();
        }
        return sPollList;
    }

    public List<Polls> getPolls() {
        return mPolls;
    }
}
