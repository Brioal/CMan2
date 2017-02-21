package com.brioal.cman2.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.complaint.OtherResonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintActivity extends BaseActivity {

    @BindView(R.id.complaint_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.complaint_tv_renshen)
    TextView mTvRenshen;
    @BindView(R.id.complaint_tv_zhishi)
    TextView mTvZhishi;
    @BindView(R.id.complaint_tv_other)
    TextView mTvOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_compla);
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
        //人身
        mTvRenshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("人身权被侵犯");
            }
        });
        //知识产权
        mTvZhishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("只是产权");
            }
        });
        //其他原因
        mTvOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherResonActivity.enterResonActivity(mContext);
            }
        });
    }


    //进入投诉界面
    public static void enterComplantActivity(Context context) {
        Intent intent = new Intent(context, ComplaintActivity.class);
        context.startActivity(intent);
    }
}
