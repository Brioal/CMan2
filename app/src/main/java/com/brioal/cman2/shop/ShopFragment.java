package com.brioal.cman2.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseFragment;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/16.
 */

public class ShopFragment extends BaseFragment {
    private static ShopFragment sFragment;

    public static ShopFragment getInstance() {
        if (sFragment == null) {
            sFragment = new ShopFragment();
        }
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fra_shop, container, false);
    }
}
