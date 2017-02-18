package com.brioal.cman2.interfaces;

import com.brioal.cman2.bean.DeviceBean;

import java.util.List;

/**
 * 加载设备列表窃听器
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/17.
 */

public interface OnDeviceLoadListener {
    void success(List<DeviceBean> list);

    void failed(String msg);

}
