package com.brioal.cman2.qa.contract;

import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class QAContract {
    public interface View {
        void showCommiting();//显示正在提交

        void showCommitingDone();//显示提交成功

        void showCommitingFailed(String errorMsg);//显示提交失败
    }

    public interface Presenter {
        void start();

        void commitBack(String msg);//提交反馈
    }

    public interface Model {
        //提交反馈
        void commitBack(String msg, OnNormalOperationListener listener);
    }


}