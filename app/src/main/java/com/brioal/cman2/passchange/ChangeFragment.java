package com.brioal.cman2.passchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;
import com.brioal.cman2.interfaces.OnPassChangResultListener;
import com.brioal.cman2.passchange.contract.PassChangeContract;
import com.brioal.cman2.passchange.presenter.PassChangePresenterImpl;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class ChangeFragment extends BaseFragment implements PassChangeContract.View {
    @BindView(R.id.change_pass_et_old_pass)
    EditText mEtOldPass;
    @BindView(R.id.change_pass_et_new_pass)
    EditText mEtNewPass;
    @BindView(R.id.change_pass_et_new_pass_config)
    EditText mEtNewPassConfig;
    @BindView(R.id.change_pass_btn_commit)
    TextView mBtnCommit;
    private OnPassChangResultListener mPassChangResultListener;
    private View mRootView;
    private PassChangeContract.Presenter mPresenter;
    private static ChangeFragment sFragment;

    public static ChangeFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ChangeFragment();
        }
        return sFragment;
    }

    public void setPassChangResultListener(OnPassChangResultListener passChangResultListener) {
        mPassChangResultListener = passChangResultListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_change_pass_changing, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new PassChangePresenterImpl(this);
        mPresenter.start();
    }

    private void initView() {
        final String oldPass = mEtOldPass.getText().toString().trim();
        final String newPass = mEtNewPass.getText().toString().trim();
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                check();
            }
        };
        mEtOldPass.addTextChangedListener(watcher);
        mEtNewPass.addTextChangedListener(watcher);
        mEtNewPassConfig.addTextChangedListener(watcher);
        //提交
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.change(oldPass, newPass);
            }
        });
    }

    private void check() {
        //格式检验
        final String oldPass = mEtOldPass.getText().toString().trim();
        final String newPass = mEtNewPass.getText().toString().trim();
        String newPassAgain = mEtNewPassConfig.getText().toString().trim();
        mBtnCommit.setEnabled(!oldPass.isEmpty() && newPass.equals(newPassAgain) && !newPass.isEmpty());
    }


    @Override
    public void showChanging() {
        showLoadingDiaog("正在修改");
    }

    @Override
    public void showChangeSuccess() {
        KLog.e("修改成功");
        if (mPassChangResultListener == null) {
            return;
        }
        hideLoadDialog();
        mPassChangResultListener.success();
    }

    @Override
    public void showChangeFailed() {
        KLog.e("修改失败");
        if (mPassChangResultListener == null) {
            return;
        }
        hideLoadDialog();
        mPassChangResultListener.failed();
    }
}
