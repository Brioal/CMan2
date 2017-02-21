package com.brioal.cman2.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brioal.cman2.R;
import com.brioal.cman2.about.AboutActivity;
import com.brioal.cman2.base.BaseFragment;
import com.brioal.cman2.devicemanager.DeviceManagerActivity;
import com.brioal.cman2.feedback.FeedBackActivity;
import com.brioal.cman2.languagechange.LanguageChangeActivity;
import com.brioal.cman2.passchange.PassActivityChange;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class MineFragment extends BaseFragment {
    private static MineFragment sFragment;
    @BindView(R.id.mine_tv_deviceManager)
    Button mTvDevManager;
    @BindView(R.id.mine_tv_pass_change)
    Button mTvPassChange;
    @BindView(R.id.mine_tv_feedback)
    Button mTvFeedback;
    @BindView(R.id.mine_tv_about)
    Button mTvAbout;
    @BindView(R.id.mine_tv_language)
    Button mTvLanguage;
    @BindView(R.id.mine_tv_update)
    Button mTvUpdate;

    public static MineFragment getInstance() {
        if (sFragment == null) {
            sFragment = new MineFragment();
        }
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        //进入设备管理界面
        mTvDevManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DeviceManagerActivity.class);
                mContext.startActivity(intent);
            }
        });
        //进入密码修改界面
        mTvPassChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassActivityChange.enterPassChangeActivity(mContext);
            }
        });
        //进入反馈界面
        mTvFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBackActivity.enterFeedBackActivity(mContext);
            }
        });
        //关于界面
        mTvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.enterAboutActiivty(mContext);
            }
        });
        //语言切换
        mTvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageChangeActivity.enterLanguageActivity(mContext);
            }
        });
        //更新
        mTvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/2/21
            }
        });

    }
}
