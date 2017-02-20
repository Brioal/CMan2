package com.brioal.cman2.languagechange.contract;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class LanguageChangeContract {
    public interface View {
        void showChanging();//显示正在更改

        void showChangingDone();//显示更改完成

        void showChangingFailed();//显示更改失败
    }

    public interface Presenter {
        void start();//初始

        void change(int languageIndex);//更改语言
    }

    public interface Model {
    }


}