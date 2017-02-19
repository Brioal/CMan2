package com.brioal.cman2.passchange;

import android.os.Bundle;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.passchange.contract.PassChangeContract;

public class PassChangeActivity extends BaseActivity implements PassChangeContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pass_change);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadingDone() {

    }

    @Override
    public void showLoadingFailed(String errorMsg) {

    }

    @Override
    public void showChangeSuccess() {

    }

    @Override
    public void showChangeFailed() {

    }
}
