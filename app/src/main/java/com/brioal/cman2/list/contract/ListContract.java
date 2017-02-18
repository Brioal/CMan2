package com.brioal.cman2.list.contract;

import com.brioal.cman2.bean.DeviceBean;

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
        void showLoadingFailed();

        //显示设备列表
        void showDevice(List<DeviceBean> list);

    }

    public interface Presenter {
    }

    public interface Model {
    }


}