package com.brioal.cman2.deviceedit.presenter;

import android.os.Handler;

import com.brioal.cman2.deviceedit.contract.DeviceEditContract;
import com.brioal.cman2.deviceedit.model.DeviceEditModelImpl;
import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * Created by Brioal on 2017/02/17
 */

public class DeviceEditPresenterImpl implements DeviceEditContract.Presenter {
    private DeviceEditContract.View mView;
    private DeviceEditContract.Model mModel;
    private Handler mHandler = new Handler();

    public DeviceEditPresenterImpl(DeviceEditContract.View view) {
        mView = view;
        mModel = new DeviceEditModelImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void change(String deviceID, String newName) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mView.showChanging();
            }
        });
        mModel.changeDeviceName(deviceID, newName, new OnNormalOperationListener() {
            @Override
            public void success(String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showChangingDone();
                    }
                });
            }

            @Override
            public void failed(String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showChangingFailed();
                    }
                });
            }
        });
    }

}