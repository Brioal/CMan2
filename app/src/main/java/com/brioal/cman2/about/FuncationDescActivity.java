package com.brioal.cman2.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuncationDescActivity extends BaseActivity {

    @BindView(R.id.funcation_desc_btn_back)
    ImageButton mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_funcation_desc);
        ButterKnife.bind(this);
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

    public static void enterFuncationDescActivity(Context context) {
        Intent intent = new Intent(context, FuncationDescActivity.class);
        context.startActivity(intent);
    }
}
