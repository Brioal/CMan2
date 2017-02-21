package com.brioal.cman2.complaint.contract;

import com.brioal.cman2.interfaces.OnNormalOperationListener;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/21.
 */

public class ComplaintContract {
    public interface View {
        void showCommiting();//显示正在提交

        void showCommitDone();//显示提交成功

        void showCommitFailed(String errorMsg);//显示提交失败
    }

    public interface Presenter {
        void start();//默认的开始

        void commitMsg(String msg);//提交原因
    }

    public interface Model {
        void commmitMsg(String msg, OnNormalOperationListener listener);
    }


}