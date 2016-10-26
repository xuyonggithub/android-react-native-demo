package com.shengjing.industry.industrydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.shengjing.industry.industrydemo.R;
import com.shengjing.industry.industrydemo.bean.Record;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/10/19.
 */

public class RecordRecycleAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Record> mData = new ArrayList<Record>();

    public RecordRecycleAdapter(ArrayList<Record> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new RecordViewHolder(layoutInflater.inflate(R.layout.item_record, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((RecordViewHolder)holder).time.setText(mData.get(position).time);
        ((RecordViewHolder)holder).id.setText(mData.get(position).id);
        ((RecordViewHolder)holder).status.setChecked(mData.get(position).status);
        ((RecordViewHolder)holder).status.clearFocus();
        ((RecordViewHolder)holder).status.setFocusable(false);
        ((RecordViewHolder)holder).status.setFocusableInTouchMode(false);
        ((RecordViewHolder)holder).status.setClickable(false);
        ((RecordViewHolder)holder).status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setChecked(mData.get(position).status);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_record_time)
        TextView time;
        @BindView(R.id.tv_record_id)
        TextView id;
        @BindView(R.id.ct_upload_status)
        CheckBox status;

        public RecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
