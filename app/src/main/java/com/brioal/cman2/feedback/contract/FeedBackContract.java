package com.brioal.cman2.feedback.contract;

import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/21.
 */

public class FeedBackContract {
    public interface View {
        void showCommiting();//显示正在提交

        void showCommitDone();//显示提交成功

        void showCommitFailed();//显示提交失败
    }

    public interface Presenter {
        void start();//默认的初始操作

        void commit(String msg);//提交反馈内容
    }

    public interface Model {
        //提交反馈内容到服务器
        void commitMsg(String msg, OnNormalOperationListener listener);
    }


}