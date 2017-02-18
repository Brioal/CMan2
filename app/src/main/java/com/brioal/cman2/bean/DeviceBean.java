package com.brioal.cman2.bean;

/**
 * 设备实体类
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/17.
 */

public class DeviceBean {
    private String mType;//设备的类型

    private String mDeviceID;//设备的ID

    private String mName;//设备的名称

    private boolean isOnline;//设备是否在线


    public DeviceBean() {
    }

    public DeviceBean(String type, String deviceID, String name, boolean isOnline) {
        mType = type;
        mDeviceID = deviceID;
        mName = name;
        this.isOnline = isOnline;
    }

    public String getType() {
        return mType;
    }

    public DeviceBean setType(String type) {
        mType = type;
        return this;
    }

    public String getDeviceID() {
        return mDeviceID;
    }

    public DeviceBean setDeviceID(String deviceID) {
        mDeviceID = deviceID;
        return this;
    }

    public String getName() {
        return mName;
    }

    public DeviceBean setName(String name) {
        mName = name;
        return this;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public DeviceBean setOnline(boolean online) {
        isOnline = online;
        return this;
    }
}
