package com.brioal.cman2.feedback;

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
import com.brioal.cman2.feedback.contract.FeedBackContract;
import com.brioal.cman2.feedback.presenter.FeedBackPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends BaseActivity implements FeedBackContract.View {

    @BindView(R.id.act_feedback_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.act_feedback_et)
    EditText mEt;
    @BindView(R.id.act_feedback_btn_commit)
    TextView mBtnCommit;
    @BindView(R.id.act_feedback_btn_back_another)
    TextView mBtnBackAnother;

    private FeedBackContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_feed_back);
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new FeedBackPresenterImpl(this);
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
        mBtnBackAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //反馈内容编辑
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
        //反馈内容提交
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.commit(mEt.getText().toString());
            }
        });
    }


    public static void enterFeedBackActivity(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showCommiting() {
        showLoadingDiaog("正在提交反馈");
    }

    @Override
    public void showCommitDone() {
        hideLoadDialog();
        showToast("提交反馈成功");
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    @Override
    public void showCommitFailed() {
        hideLoadDialog();
        showToast("提交反馈失败,请稍候再试");
    }
}
