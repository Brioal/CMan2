package com.brioal.cman2.devicedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.devie_detail_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.device_detail_btn_close)
    AppCompatCheckBox mBtnClose;
    @BindView(R.id.device_detail_tv_tmp)
    TextView mTvTmp;
    @BindView(R.id.device_detail_tv_hum)
    TextView mTvHum;
    @BindView(R.id.device_detail_tv_bc)
    TextView mTvBc;
    @BindView(R.id.device_detail_tv_auto)
    TextView mTvAuto;
    @BindView(R.id.device_detail_tv_value_tmp)
    TextView mTvValueTmp;
    @BindView(R.id.device_detail_tv_value_hum)
    TextView mTvValueHum;
    @BindView(R.id.device_detail_tv_value_sme)
    TextView mTvValueSme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_detail);
        ButterKnife.bind(this);
        initData();
        initView();
        initPresenter();
    }

    private void initData() {

    }

    private void initPresenter() {

    }

    private void initView() {

    }

    public static void enterDeviceDetail(Context context) {
        Intent intent = new Intent(context, DeviceDetailActivity.class);
        context.startActivity(intent);
    }
}
