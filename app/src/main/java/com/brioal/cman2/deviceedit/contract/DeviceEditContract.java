package com.brioal.cman2.deviceedit.contract;

import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * 设备名称修改的Contract
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/17.
 */

public class DeviceEditContract {

    public interface View {
        void showChanging();//显示正在修改

        void showChangingDone();//显示修改完成

        void showChangingFailed();//显示修改失败
    }

    public interface Presenter {
        void start();//开始

        void change(String deviceID, String newName);//修改名称
    }

    public interface Model {
        void changeDeviceName(String deviceID , String name , OnNormalOperationListener listener);
    }


}