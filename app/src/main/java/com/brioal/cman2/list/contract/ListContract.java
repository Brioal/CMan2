package com.brioal.cman2.list.contract;

import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceLoadListener;
import com.brioal.cman2.interfaces.OnNormalOperationListener;

import java.util.List;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/18.
 */

public class ListContract {
    public interface View {
        //显示正在加载
        void showLoading();

        //显示加载完成
        void showLoadingDone();

        //显示加载失败
        void showLoadingFailed(String error);

        //显示设备列表
        void showDevice(List<DeviceBean> list);

        //显示设备总数
        void showDeviceCount(int deviceCount);

        //显示正在连接
        void showConnecting(String msg);

        //显示连接失败
        void showConnectFailed(String msg);

        //显示连接成功
        void showConnectDone();

    }

    public interface Presenter {
        void start();//开始=显示刷新+数据加载

        void refresh();//刷新=数据加载

        void connect(DeviceBean bean);//连接设备

    }

    public interface Model {
        //加载设备列表
        void loadDeviceList(OnDeviceLoadListener loadListener);

        //判断指定设备当前是否在线
        void refreshDeviceStatue(DeviceBean bean, OnNormalOperationListener listener);
    }


}