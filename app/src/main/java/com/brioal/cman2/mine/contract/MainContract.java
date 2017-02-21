package com.brioal.cman2.mine.contract;

import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class MainContract {
    public interface View {
        void showCommiting();//显示正在提交

        void showCommitDone();//显示提交完成

        void showCommitFailed(String errorMsg);//显示提交失败

    }

    public interface Presenter {
        void start();//默认的开始

        void commitFeedBack(String msg);//提交反馈

    }

    public interface Model {
        void commitFeedBack(String msg, OnNormalOperationListener listener);
    }


}