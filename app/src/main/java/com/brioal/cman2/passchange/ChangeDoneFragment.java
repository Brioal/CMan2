package com.brioal.cman2.passchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;
import com.brioal.cman2.interfaces.OnPassChangeDoneClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class ChangeDoneFragment extends BaseFragment {
    @BindView(R.id.change_pass_done_btn_back)
    TextView mBtnBack;
    @BindView(R.id.change_pass_done_btn_re_change)
    TextView mBtnReChange;
    private static ChangeDoneFragment sFragment;

    public static ChangeDoneFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ChangeDoneFragment();
        }
        return sFragment;
    }

    private OnPassChangeDoneClickListener mPassDoneListener;
    private View mRootView;


    public void setPassDoneListener(OnPassChangeDoneClickListener passDoneListener) {
        mPassDoneListener = passDoneListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_change_pass_change_done, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        if (mPassDoneListener == null) {
            return;
        }
        //返回个人中心
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassDoneListener.backMine();
            }
        });
        //再次修改
        mBtnReChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassDoneListener.reChange();
            }
        });
    }
}
