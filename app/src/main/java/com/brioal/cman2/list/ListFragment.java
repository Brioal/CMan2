package com.brioal.cman2.list;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;
import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.devicedetail.DeviceDetailActivity;
import com.brioal.cman2.interfaces.OnDeviceEnterListener;
import com.brioal.cman2.list.contract.ListContract;
import com.brioal.cman2.list.presenter.ListPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class ListFragment extends BaseFragment implements ListContract.View {
    private static ListFragment sFragment;
    @BindView(R.id.list_tv_count)
    TextView mTvCount;
    @BindView(R.id.list_tv_statue)
    TextView mTvStatue;
    @BindView(R.id.list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.list_tv_add)
    TextView mTvAdd;
    private ImageView mIvLoading;
    @BindView(R.id.list_layout)
    PtrFrameLayout mLayout;

    public static ListFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ListFragment();
        }
        return sFragment;
    }

    private View mRootView;
    private boolean isRefreshing = false;
    private ListPresenterImpl mPresenter;
    private ListAdapter mAdapter;
    private AlertDialog mLoadingDialog;
    private TextView mLoadTvMsg;
    private ImageView mLoadImg;

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
        initView();
        initRefreshLayout();
        initPresenter();
    }

    private void initView() {
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/2/18 添加设备
                showToast("添加设备");
            }
        });
        showDeviceCount(0);
    }

    //初始化刷新部分
    private void initRefreshLayout() {
        mIvLoading = (ImageView) mLayout.findViewById(R.id.list_iv_loading);
        mLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRefreshing = true;
                //刷新显示
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotating);
                animation.setDuration(900);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (isRefreshing) {
                            mIvLoading.startAnimation(animation);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mIvLoading.startAnimation(animation);
                //加载数据
                mPresenter.refresh();
            }
        });
    }

    //初始化Presenter
    private void initPresenter() {
        mPresenter = new ListPresenterImpl(this);
        mPresenter.start();
    }


    @Override
    public void showLoading() {
        mLayout.autoRefresh();
    }

    @Override
    public void showLoadingDone() {
        if (mLayout.isRefreshing()) {
            mLayout.refreshComplete();
        }
        isRefreshing = false;
    }

    @Override
    public void showLoadingFailed(String error) {
        showLoadingDone();
        showToast(error);
    }

    @Override
    public void showDevice(List<DeviceBean> list) {
        mAdapter = new ListAdapter(mContext);
        mAdapter.showList(list);
        mAdapter.setEnterListener(new OnDeviceEnterListener() {
            @Override
            public void enter(DeviceBean bean) {
                mPresenter.connect(bean);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showDeviceCount(int deviceCount) {
        SpannableString ss = new SpannableString(deviceCount + " 个智能设备");
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvCount.setText(ss);
    }

    //显示连接设备,并且更新进度
    @Override
    public void showConnecting(String msg) {
        if (mLoadingDialog != null && mLoadTvMsg != null) {
            mLoadTvMsg.setText(msg);
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
        } else {
            //重新创建Dialog
            mLoadingDialog = new AlertDialog.Builder(mContext).create();
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.show();
            Window window = mLoadingDialog.getWindow();
            window.setContentView(R.layout.layout_loading);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager windowManager = window.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            window.setLayout(display.getWidth() - 100, display.getWidth() / 2);
            mLoadTvMsg = (TextView) window.findViewById(R.id.loading_tv_msg);
            mLoadTvMsg.setText(msg);
            mLoadImg = (ImageView) window.findViewById(R.id.loading_iv_img);
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotating);
        animation.setDuration(900);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mLoadingDialog.isShowing()) {
                    mLoadImg.startAnimation(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLoadImg.startAnimation(animation);
    }

    @Override
    public void showConnectFailed(String msg) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        showToast(msg);
    }

    @Override
    public void showConnectDone() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        DeviceDetailActivity.enterDeviceDetail(mContext);
    }
}
