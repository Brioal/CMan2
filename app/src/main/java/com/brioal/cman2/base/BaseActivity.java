package com.brioal.cman2.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brioal.cman2.R;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class BaseActivity extends AppCompatActivity {
    private Toast mToast;
    protected Context mContext;
    private AlertDialog mLoadingDialog;
    private TextView mLoadTvMsg;
    private ImageView mLoadImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    protected void showLoadingDiaog(String msg) {
        if (mLoadingDialog != null && mLoadTvMsg != null) {
            mLoadTvMsg.setText(msg);
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
        } else {
            //重新创建Dialog
            mLoadingDialog = new AlertDialog.Builder(mContext).create();
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.show();
            Window window = mLoadingDialog.getWindow();
            window.setContentView(R.layout.layout_loading);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager windowManager = window.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            window.setLayout(display.getWidth() - 100, display.getWidth() / 2);
            mLoadTvMsg = (TextView) window.findViewById(R.id.loading_tv_msg);
            mLoadTvMsg.setText(msg);
            mLoadImg = (ImageView) window.findViewById(R.id.loading_iv_img);
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotating);
        animation.setDuration(900);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mLoadingDialog.isShowing()) {
                    mLoadImg.startAnimation(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLoadImg.startAnimation(animation);
    }

    protected void hideLoadDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
