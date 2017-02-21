package com.brioal.cman2.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.act_about_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.act_about_tv_version)
    TextView mTvVersion;
    @BindView(R.id.act_about_tv_rate)
    TextView mTvRate;
    @BindView(R.id.act_about_tv_desc)
    TextView mTvDesc;
    @BindView(R.id.act_about_tv_complaint)
    TextView mTvComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_about);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //返回
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //评分
        mTvRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("跳转评分");
            }
        });
        //介绍
        mTvDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncationDescActivity.enterFuncationDescActivity(mContext);
            }
        });
        //投诉
        mTvComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComplaintActivity.enterComplantActivity(mContext);
            }
        });
        //其他原因
    }

    public static void enterAboutActiivty(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
}
