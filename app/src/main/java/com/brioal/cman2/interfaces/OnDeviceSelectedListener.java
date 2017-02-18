package com.brioal.cman2.interfaces;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/17.
 */

public interface OnDeviceSelectedListener {
    void selected(int position);//选中的时候触发

    void longClick(int position);//长按时候触发
}
