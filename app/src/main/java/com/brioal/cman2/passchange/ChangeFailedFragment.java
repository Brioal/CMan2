package com.brioal.cman2.passchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;
import com.brioal.cman2.interfaces.OnPassChangeFailedClickListner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class ChangeFailedFragment extends BaseFragment {
    @BindView(R.id.change_pass_failed_btn_back)
    TextView mBtnBack;
    private static ChangeFailedFragment sFragment;


    private OnPassChangeFailedClickListner mFailedClickListner;
    private View mRootView;

    public static ChangeFailedFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ChangeFailedFragment();
        }
        return sFragment;
    }

    public void setFailedClickListner(OnPassChangeFailedClickListner failedClickListner) {
        mFailedClickListner = failedClickListner;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_change_pass_change_failed, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        if (mFailedClickListner == null) {
            return;
        }
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFailedClickListner.back();
            }
        });
    }
}
