package com.brioal.cman2.languagechange;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageChangeActivity extends BaseActivity {


    @BindView(R.id.language_btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.language_rg)
    RadioGroup mLanguageRg;
    @BindView(R.id.change_pass_done_btn_back)
    TextView mBtnCommit;
    @BindView(R.id.language_tv_simplechinese)
    RadioButton mTvSimplechinese;
    @BindView(R.id.language_tv_traditional_chinese)
    RadioButton mTvTraditionalChinese;
    @BindView(R.id.language_tv_english)
    RadioButton mTvEnglish;
    private int mCurrentLanguage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_language_change);
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    private void initPresenter() {

    }

    private void initView() {
        //显示
        mCurrentLanguage = 0;
        // TODO: 2017/2/21 获取当前的语言
        int id = -1;
        switch (mCurrentLanguage) {
            case 0:
                id = R.id.language_tv_simplechinese;
                break;
            case 1:
                id = R.id.language_tv_traditional_chinese;
                break;
            case 2:
                id = R.id.language_tv_english;
                break;
        }
        mLanguageRg.check(id);
        mLanguageRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int index = -1;
                switch (checkedId) {
                    case R.id.language_tv_simplechinese:
                        mTvSimplechinese.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.ic_language_select), null);
                        mTvTraditionalChinese.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        mTvEnglish.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        index = 0;
                        break;
                    case R.id.language_tv_traditional_chinese:
                        mTvSimplechinese.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        mTvTraditionalChinese.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.ic_language_select), null);
                        mTvEnglish.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        index = 1;
                        break;
                    case R.id.language_tv_english:
                        mTvSimplechinese.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        mTvTraditionalChinese.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        mTvEnglish.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.ic_language_select), null);
                        index = 2;
                        break;
                }
                mBtnCommit.setEnabled(mCurrentLanguage != index);
            }
        });
        //提交修改
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDiaog("正在切换");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoadDialog();
                        showToast("语言切换不成功");
                    }
                }, 2000);
            }
        });
        //返回
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void enterLanguageActivity(Context context) {
        Intent intent = new Intent(context, LanguageChangeActivity.class);
        context.startActivity(intent);
    }


}
