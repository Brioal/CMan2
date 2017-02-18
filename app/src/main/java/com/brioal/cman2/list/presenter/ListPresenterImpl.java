package com.brioal.cman2.list.presenter;

import android.os.Handler;

import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceLoadListener;
import com.brioal.cman2.list.contract.ListContract;
import com.brioal.cman2.list.model.ListModelImpl;

import java.util.List;

/**
 * Created by Brioal on 2017/02/18
 */

public class ListPresenterImpl implements ListContract.Presenter {
    private ListContract.View mView;
    private ListContract.Model mModel;
    private Handler mHandler = new Handler();

    public ListPresenterImpl(ListContract.View view) {
        mView = view;
        mModel = new ListModelImpl();
    }

    @Override
    public void start() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mView.showLoading();
            }
        });
        mModel.loadDeviceList(new OnDeviceLoadListener() {
            @Override
            public void success(final List<DeviceBean> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showLoadingDone();
                        mView.showDeviceCount(list.size());
                        mView.showDevice(list);
                    }
                });
            }

            @Override
            public void failed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showLoadingFailed(msg);
                    }
                });
            }
        });
    }

    @Override
    public void refresh() {
        mModel.loadDeviceList(new OnDeviceLoadListener() {
            @Override
            public void success(final List<DeviceBean> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showLoadingDone();
                        mView.showDeviceCount(list.size());
                        mView.showDevice(list);
                    }
                });
            }

            @Override
            public void failed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showLoadingFailed("更新设备列表失败,请检查网络");
                    }
                });
            }
        });
    }
}