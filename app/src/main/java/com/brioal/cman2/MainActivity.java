package com.brioal.cman2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.brioal.bottomtab.BottomTabLayout;
import com.brioal.bottomtab.OnTabSelectedListener;
import com.brioal.bottomtab.TabEntity;
import com.brioal.cman2.base.BaseActivity;
import com.brioal.cman2.base.BaseFragment;
import com.brioal.cman2.list.ListFragment;
import com.brioal.cman2.mine.MineFragment;
import com.brioal.cman2.shop.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_bottomLayout)
    BottomTabLayout mBottomLayout;

    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.CMTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomLayout();
        changeFragment(ListFragment.getInstance());

    }

    private void initBottomLayout() {
        List<TabEntity> mList = new ArrayList<>();
        mList.add(new TabEntity("设备列表", R.mipmap.ic_tab_list_normal, R.mipmap.ic_tab_list_selected));
        mList.add(new TabEntity("在线商城", R.mipmap.ic_tab_shop_normal, R.mipmap.ic_tab_shop_selected));
        mList.add(new TabEntity("个人中心", R.mipmap.ic_tab_mine_normal, R.mipmap.ic_tab_mine_selected));
        mBottomLayout.setTextColorNormal(Color.BLACK);
        mBottomLayout.setTextColorSelected(getResources().getColor(R.color.colorAccent));
        mBottomLayout.setList(mList);
        mBottomLayout.setListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(int position) {
                BaseFragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = ListFragment.getInstance();
                        break;
                    case 1:
                        fragment = ShopFragment.getInstance();
                        break;
                    case 2:
                        fragment = MineFragment.getInstance();
                        break;
                    default:
                        fragment = ListFragment.getInstance();
                }
                changeFragment(fragment);
            }
        });
    }

    private void changeFragment(BaseFragment fragment) {
        if (mCurrentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(mCurrentFragment).commit();
        }
        if (fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragment).commit();
        }
        mCurrentFragment = fragment;
    }
}
