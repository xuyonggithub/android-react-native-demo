package com.shengjing.industry.industrydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shengjing.industry.industrydemo.R;
import com.shengjing.industry.industrydemo.adapter.RecordRecycleAdapter;
import com.shengjing.industry.industrydemo.bean.Record;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/10/17.
 */

public class ListFragment extends Fragment{

    @BindView(R.id.rv_record)
    RecyclerView mRvRecord;

    private RecordRecycleAdapter mAdapter;
    ArrayList<Record> list = new ArrayList<Record>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        list.add(new Record("133907", "2016.10.20 14:20", true));
        list.add(new Record("133907", "2016.10.20 14:20", false));
        list.add(new Record("133907", "2016.10.20 14:20", true));
        list.add(new Record("133907", "2016.10.20 14:20", true));
        list.add(new Record("133907", "2016.10.20 14:20", false));
        list.add(new Record("133907", "2016.10.20 14:20", false));
        list.add(new Record("133907", "2016.10.20 14:20", false));
        list.add(new Record("133907", "2016.10.20 14:20", false));
        mAdapter = new RecordRecycleAdapter(list);
        mRvRecord.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRvRecord.setAdapter(mAdapter);
    }
}
