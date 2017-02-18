package com.brioal.cman2.list;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.cman2.R;
import com.brioal.cman2.base.BaseViewHolder;
import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceEnterListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/18.
 */

public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<DeviceBean> mList = new ArrayList<>();
    private OnDeviceEnterListener mEnterListener;

    public ListAdapter(Context context) {
        mContext = context;
    }

    public void showList(List<DeviceBean> list) {
        mList.clear();
        mList.addAll(list);
    }

    public void setEnterListener(OnDeviceEnterListener enterListener) {
        mEnterListener = enterListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_device_list, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindView(mList, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DeviceItemViewHolder extends BaseViewHolder {
        private View mItemView;

        @BindView(R.id.item_list_tv_name)
        TextView mTvName;
        @BindView(R.id.item_list_tv_startues)
        TextView mTvStartues;


        public DeviceItemViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(List list, int position) {
            final DeviceBean bean = (DeviceBean) list.get(position);
            if (bean.isOnline()) {
                //在线
                mItemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
                mTvName.setTextColor(Color.WHITE);
                mTvStartues.setTextColor(Color.WHITE);
                mTvStartues.setText("运行");

            } else {
                //离线
                mItemView.setBackgroundColor(Color.WHITE);
                mTvName.setTextColor(mContext.getResources().getColor(R.color.device_tv_color_outline));
                mTvStartues.setTextColor(mContext.getResources().getColor(R.color.device_tv_color_outline));
                mTvStartues.setText("离线");
            }
            mTvName.setText(bean.getName() + "");
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEnterListener == null) {
                        return;
                    }
                    mEnterListener.enter(bean);
                }
            });
        }
    }
}
