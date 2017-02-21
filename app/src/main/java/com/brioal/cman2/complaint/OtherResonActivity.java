package com.brioal.cman2.complaint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.complaint.contract.ComplaintContract;
import com.brioal.cman2.complaint.presenter.ComplaintPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherResonActivity extends BaseActivity implements ComplaintContract.View {

    @BindView(R.id.other_reson_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.other_reson_et)
    EditText mEt;
    @BindView(R.id.other_reson_btn_commit)
    TextView mBtnCommit;

    private ComplaintContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_other_reson);
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new ComplaintPresenterImpl(this);
        mPresenter.start();
    }

    private void initView() {
        //返回
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //文本框
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString().trim();
                mBtnCommit.setEnabled(!content.isEmpty());
            }
        });
        //提交原因
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.commitMsg(mEt.getText().toString());
            }
        });
    }

    public static void enterResonActivity(Context context) {
        Intent intent = new Intent(context, OtherResonActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showCommiting() {
        showLoadingDiaog("正在提交");
    }

    @Override
    public void showCommitDone() {
        hideLoadDialog();
        showToast("提交成功");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }

    @Override
    public void showCommitFailed(String errorMsg) {
        hideLoadDialog();
        showToast(errorMsg);
    }
}
