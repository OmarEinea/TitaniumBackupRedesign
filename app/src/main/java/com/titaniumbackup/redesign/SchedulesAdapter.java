package com.titaniumbackup.redesign;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/** Created by omar on 5/23/17. */

class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.ScheduleHolder> {
    private ArrayList<Schedule> mSchedules;

    public SchedulesAdapter(ArrayList<Schedule> schedules) {
        mSchedules = schedules;
    }

    @Override
    public SchedulesAdapter.ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ScheduleHolder holder, int position) {
        final Schedule schedule = mSchedules.get(position);
        holder.mLabel.setText(schedule.label);
        holder.mTime.setText(new SimpleDateFormat("HH:mm").format(schedule.date));
        holder.mDay.setText(new SimpleDateFormat("yyyy-MM-dd").format(schedule.date));
        holder.mSwitch.setChecked(schedule.enabled);
        holder.mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.snack(v, (((Switch)v).isChecked() ? "Enabled " : "Disabled ") + schedule.label + " Schedule", Snackbar.LENGTH_SHORT);
            }
        });
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.snack(v, "Running " + schedule.label + " Schedule...", Snackbar.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSchedules.size();
    }

    class ScheduleHolder extends RecyclerView.ViewHolder {
        private TextView mLabel, mDay, mTime;
        private Switch mSwitch;
        private Button mButton;

        ScheduleHolder(View view) {
            super(view);
            mTime = (TextView) view.findViewById(R.id.sch_time);
            mLabel = (TextView) view.findViewById(R.id.sch_label);
            mDay = (TextView) view.findViewById(R.id.sch_day);
            mSwitch = (Switch) view.findViewById(R.id.sch_switch);
            mButton = (Button) view.findViewById(R.id.sch_button);
        }
    }
}
