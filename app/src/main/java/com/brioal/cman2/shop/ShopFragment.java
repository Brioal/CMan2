package com.brioal.cman2.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class ShopFragment extends BaseFragment {
    private static ShopFragment sFragment;
    @BindView(R.id.layout_shequn)
    RelativeLayout mLayoutShequn;
    @BindView(R.id.layout_shancheng)
    RelativeLayout mLayoutShancheng;

    public static ShopFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ShopFragment();
        }
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutShancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("跳转商城");
            }
        });
        mLayoutShequn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("跳转社群");
            }
        });
    }
}
