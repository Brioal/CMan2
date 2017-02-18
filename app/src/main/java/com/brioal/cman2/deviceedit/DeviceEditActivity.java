package com.brioal.cman2.deviceedit;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.deviceedit.contract.DeviceEditContract;
import com.brioal.cman2.deviceedit.presenter.DeviceEditPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceEditActivity extends BaseActivity implements DeviceEditContract.View {

    @BindView(R.id.changename_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.changename_et_name)
    EditText mEtName;
    @BindView(R.id.changename_tv_save)
    TextView mTvSave;

    private DeviceEditPresenterImpl mPresenter;
    private String mDeviceID = "";
    private AlertDialog mLoadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_edit);
        ButterKnife.bind(this);
        initData();
        initView();
        initPresenter();
    }

    private void initData() {
        mDeviceID = getIntent().getStringExtra("DeviceID");
    }

    private void initPresenter() {
        mPresenter = new DeviceEditPresenterImpl(this);
        mPresenter.start();
    }

    private void initView() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvSave.setEnabled(false);
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = mEtName.getText().toString();
                mTvSave.setEnabled(!content.isEmpty());
            }
        });
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.change(mDeviceID, mEtName.getText().toString());
            }
        });
    }

    @Override
    public void showChanging() {
        //显示正在保存更改
        mLoadingDialog = new AlertDialog.Builder(mContext).create();
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
        Window window = mLoadingDialog.getWindow();
        window.setContentView(R.layout.layout_loading);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        window.setLayout(display.getWidth() / 2, display.getWidth() / 2);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final ImageView img = (ImageView) window.findViewById(R.id.loading_iv_img);
        TextView msg = (TextView) window.findViewById(R.id.loading_tv_msg);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotating);
        animation.setDuration(900);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        msg.setText("正在保存");
        img.startAnimation(animation);
    }

    @Override
    public void showChangingDone() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        //显示更改已经完成
        showToast("更改完成,返回设备列表");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(Activity.RESULT_OK);
                finish();
            }
        }, 1500);
    }

    @Override
    public void showChangingFailed() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        //显示保存名称失败
        showToast("名称修改失败,请稍候重试");
    }

}
