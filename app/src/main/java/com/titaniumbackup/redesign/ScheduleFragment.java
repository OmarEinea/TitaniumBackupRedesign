package com.titaniumbackup.redesign;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public ScheduleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule("Weekly Backup", new Date(117, 4, 10), true));
        schedules.add(new Schedule("Monthly Backup", new Date(), false));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.apps_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new PaddingItemDecoration());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(new SchedulesAdapter(schedules));

        return view;
    }

    public class PaddingItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            // Add top margin only for the first item to avoid double space between items
            if(parent.getChildLayoutPosition(view) == 0)
                outRect.top = (int) parent.getResources().getDimension(R.dimen.appbar_padding_top);
        }
    }
}
