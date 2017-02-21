package com.brioal.cman2.passchange;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.interfaces.OnPassChangResultListener;
import com.brioal.cman2.interfaces.OnPassChangeDoneClickListener;
import com.brioal.cman2.interfaces.OnPassChangeFailedClickListner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassActivityChange extends BaseActivity implements OnPassChangResultListener, OnPassChangeDoneClickListener, OnPassChangeFailedClickListner {

    @BindView(R.id.change_pass_btn_back)
    ImageButton mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pass_change);
        ButterKnife.bind(this);
        showPassChangeFragment();
        initView();
    }

    private void initView() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //显示修改密码界面
    private void showPassChangeFragment() {
        if (ChangeFragment.getInstance().isAdded()) {
            getSupportFragmentManager().beginTransaction().show(ChangeFragment.getInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.change_pass_container, ChangeFragment.getInstance()).commit();
        }
        ChangeFragment.getInstance().setPassChangResultListener(this);
    }

    //显示密码修改失败界面
    private void showPassChangeFailedFragment() {
        if (ChangeFailedFragment.getInstance().isAdded()) {
            getSupportFragmentManager().beginTransaction().show(ChangeFailedFragment.getInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.change_pass_container, ChangeFailedFragment.getInstance()).commit();
        }
        ChangeFailedFragment.getInstance().setFailedClickListner(this);
    }

    //显示密码修改成功界面
    private void showPassChangeDoneFragment() {
        if (ChangeDoneFragment.getInstance().isAdded()) {
            getSupportFragmentManager().beginTransaction().show(ChangeDoneFragment.getInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.change_pass_container, ChangeDoneFragment.getInstance()).commit();
        }
        ChangeDoneFragment.getInstance().setPassDoneListener(this);
    }

    public static void enterPassChangeActivity(Context context) {
        Intent intent = new Intent(context, PassActivityChange.class);
        context.startActivity(intent);
    }

    @Override
    public void success() {
        showPassChangeDoneFragment();
    }

    @Override
    public void failed() {
        showPassChangeFailedFragment();
    }

    @Override
    public void reChange() {
        showPassChangeFragment();
    }

    @Override
    public void backMine() {
        finish();
    }

    @Override
    public void back() {
        finish();
    }
}
