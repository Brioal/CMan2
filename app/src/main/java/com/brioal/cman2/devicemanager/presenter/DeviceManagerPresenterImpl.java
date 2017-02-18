package com.brioal.cman2.devicemanager.presenter;

import android.os.Handler;

import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceLoadListener;
import com.brioal.cman2.interfaces.OnNormalOperationListener;
import com.brioal.cman2.devicemanager.contract.DeviceManagerContract;
import com.brioal.cman2.devicemanager.model.DeviceManagerModelImpl;

import java.util.List;

/**
 * Created by Brioal on 2017/02/17
 */

public class DeviceManagerPresenterImpl implements DeviceManagerContract.Presenter {
    private DeviceManagerContract.Model mModel;
    private DeviceManagerContract.View mView;
    private Handler mHandler = new Handler();

    public DeviceManagerPresenterImpl(DeviceManagerContract.View view) {
        mView = view;
        mModel = new DeviceManagerModelImpl();
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
                        mView.showDeviceList(list);
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
    public void deleteDevice(DeviceBean bean) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mView.showDeleting();
            }
        });
        mModel.deleteDevice(bean, new OnNormalOperationListener() {
            @Override
            public void success(String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showDeleteDone();
                    }
                });
            }

            @Override
            public void failed(final String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showDeleteFailed(error);
                    }
                });
            }
        });
    }
}