package com.brioal.cman2.devicemanager.contract;

import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceLoadListener;
import com.brioal.cman2.interfaces.OnNormalOperationListener;

import java.util.List;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/17.
 */

public class DeviceManagerContract {
    public interface View {
        void showLoading();//正在加载设备列表

        void showDeviceList(List<DeviceBean> list);//显示设备

        void showLoadingFailed(String msg);//下载失败

        void showDeleting();//显示正在删除

        void showDeleteDone();//显示删除成功

        void showDeleteFailed(String error);//显示删除失败

    }

    public interface Presenter {
        void start();//开始

        void deleteDevice(DeviceBean bean);//修改Bean

    }

    public interface Model {
        //加载设备列表
        void loadDeviceList(OnDeviceLoadListener listener);

        //修改设备名称
        void deleteDevice(DeviceBean bean, OnNormalOperationListener listener);
    }


}