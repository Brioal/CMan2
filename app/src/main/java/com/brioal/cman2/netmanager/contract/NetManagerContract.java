package com.brioal.cman2.netmanager.contract;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class NetManagerContract {
    public interface View {
        void showChanging();//显示正在更改

        void showChangindDone();//显示更改完成

        void showChangingFailed(String errorMsg);//显示更改失败
    }

    public interface Presenter {
        void start();///默认开始

    }

    public interface Model {
    }


}