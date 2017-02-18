package com.brioal.cman2.devicemanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.deviceedit.DeviceEditActivity;
import com.brioal.cman2.devicemanager.contract.DeviceManagerContract;
import com.brioal.cman2.devicemanager.presenter.DeviceManagerPresenterImpl;
import com.brioal.cman2.interfaces.OnDeviceSelectedListener;
import com.socks.library.KLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceManagerActivity extends BaseActivity implements DeviceManagerContract.View {

    @BindView(R.id.devmag_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.devmag_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.devmag_tv_change)
    TextView mTvChange;

    private AlertDialog mLoadingDialog;
    private DeviceManagerPresenterImpl mPresenter;
    private DevieManagerAdapter mAdapter;
    private int mChangeNameIndex = -1;
    private int mDeleteIndex = -1;
    private final int EDIT_NAME_REQUESTCODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_manager);
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    private void initView() {
        mTvChange.setEnabled(false);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPresenter() {
        mPresenter = new DeviceManagerPresenterImpl(this);
        mPresenter.start();
    }

    @Override
    public void showLoading() {
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
        msg.setText("正在加载");
        img.startAnimation(animation);
    }

    @Override
    public void showDeviceList(final List<DeviceBean> list) {
        //进度条小时
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        mAdapter = new DevieManagerAdapter(mContext);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.showList(list);
        mAdapter.setSelectedListener(new OnDeviceSelectedListener() {
            @Override
            public void selected(final int position) {
                mChangeNameIndex = position;
                //选中了一个设备
                mTvChange.setEnabled(true);
                mTvChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KLog.e("修改第:" + position + "设备");
                        editDeviceName(list.get(position).getDeviceID());
                    }
                });
            }

            @Override
            public void longClick(int position) {
                mDeleteIndex = position;
                //长按一个设备
                KLog.e("解除绑定第:" + position + "号设备");
                showDeleteNotice(list.get(position));
            }
        });
        mRecyclerview.setAdapter(mAdapter);
    }

    //跳转修改设备名称
    private void editDeviceName(String deviceID) {
        Intent intent = new Intent(this, DeviceEditActivity.class);
        intent.putExtra("DeviceID", deviceID);
        startActivityForResult(intent, EDIT_NAME_REQUESTCODE);
    }

    //显示解除绑定的Dilog
    private void showDeleteNotice(final DeviceBean bean) {
        mLoadingDialog = new AlertDialog.Builder(mContext).create();
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
        Window window = mLoadingDialog.getWindow();
        window.setContentView(R.layout.layout_delete_device);
        TextView name = (TextView) window.findViewById(R.id.layout_delete_tv_name);
        Button btnNo = (Button) window.findViewById(R.id.layout_delete_btn_no);
        Button btnYes = (Button) window.findViewById(R.id.layout_delete_btn_yes);
        name.setText(bean.getName() + "");
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingDialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingDialog.dismiss();
                mPresenter.deleteDevice(bean);
            }
        });
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        window.setLayout((int) (display.getWidth() * 3.0f / 4), (int) (display.getWidth() * 2.0f / 4));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void showLoadingFailed(String msg) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        showToast(msg);
    }

    @Override
    public void showDeleting() {
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
        msg.setText("正在解绑");
        img.startAnimation(animation);
    }

    @Override
    public void showDeleteDone() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }

        showToast("删除设备成功");
        if (mDeleteIndex != -1) {
            mAdapter.deleteDevice(mDeleteIndex);
            mDeleteIndex = -1;
        }
        if (mAdapter.getItemCount() == 0) {
            mTvChange.setEnabled(false);
        }

    }

    @Override
    public void showDeleteFailed(String error) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        showToast(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_NAME_REQUESTCODE && resultCode == Activity.RESULT_OK) {
            //修改成功,重新刷新
            mPresenter.start();
        }
    }
}
