package com.brioal.cman2.mine;

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

public class MineFragment extends BaseFragment {
    private static MineFragment sFragment;

    public static MineFragment getInstance() {
        if (sFragment == null) {
            sFragment = new MineFragment();
        }
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fra_mine,container,false);
    }
}
