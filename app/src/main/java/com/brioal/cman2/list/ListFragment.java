package com.brioal.cman2.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class ListFragment extends BaseFragment {
    private static ListFragment sFragment;
    @BindView(R.id.list_tv_count)
    TextView mTvCount;
    @BindView(R.id.list_tv_statue)
    TextView mTvStatue;
    @BindView(R.id.list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.list_tv_add)
    TextView mTvAdd;

    public static ListFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ListFragment();
        }
        return sFragment;
    }

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fra_list, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, mRootView);
    }
}
