package com.brioal.cman2.passchange.contract;

import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/19.
 */

public class PassChangeContract {
    public interface View {
        void showLoading();//显示正在加载

        void showLoadingDone();//显示加载完成

        void showLoadingFailed(String errorMsg);//显示加载失败

        void showChangeSuccess();//显示修改成功

        void showChangeFailed();//显示修改失败

    }

    public interface Presenter {
        void start();//开始

        void change(String oldPass, String newPass);
    }

    public interface Model {
        void changePass(String oldPass, String newPass, OnNormalOperationListener onNormalOperationListener);
    }


}