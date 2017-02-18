package com.brioal.cman2.devicemanager;

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
import com.brioal.cman2.interfaces.OnDeviceSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/17.
 */

public class DevieManagerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<DeviceBean> mList = new ArrayList<>();
    private int mSelectIndex = -1;//选中的Item
    private OnDeviceSelectedListener mSelectedListener;

    public DevieManagerAdapter(Context context) {
        mContext = context;
    }

    public void showList(List<DeviceBean> list) {
        mList.clear();
        mList.addAll(list);
    }

    public void setSelectedListener(OnDeviceSelectedListener selectedListener) {
        mSelectedListener = selectedListener;
    }

    public void deleteDevice(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        int index = mSelectIndex;
        mSelectIndex = -1;
        notifyItemChanged(index);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_device_manager, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindView(mList, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //设备管理ViewHolder
    class DeviceItemViewHolder extends BaseViewHolder {

        @BindView(R.id.item_device_manager_tv_name)
        TextView mTvName;
        @BindView(R.id.item_device_manager_tv_delete)
        TextView mTvDelete;

        View itemView;

        public DeviceItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.itemView = itemView;
        }

        @Override
        public void bindView(List list, final int position) {
            DeviceBean bean = (DeviceBean) list.get(position);
            if (bean == null) {
                return;
            }
            mTvName.setText(bean.getName() + "");
            if (mSelectIndex == position) {
                //当前已经选中
                mTvName.setTextColor(Color.WHITE);
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
                mTvDelete.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mSelectedListener == null) {
                            return true;
                        }
                        mSelectedListener.longClick(position);
                        return true;
                    }
                });
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mSelectedListener == null) {
                            return true;
                        }
                        mSelectedListener.longClick(position);
                        return true;
                    }
                });
            } else {
                //当前未选中
                itemView.setBackgroundColor(Color.WHITE);
                mTvName.setTextColor(mContext.getResources().getColor(R.color.colorTv_deviceName_black));
                itemView.setOnLongClickListener(null);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectIndex != -1) {
                        //更新上一个的状态
                        int lastIndex = mSelectIndex;
                        notifyItemChanged(lastIndex);
                    }
                    //更新当前的状态
                    mSelectIndex = position;
                    notifyItemChanged(position);
                    if (mSelectedListener == null) {
                        return;
                    }
                    mSelectedListener.selected(position);
                }
            });
        }

    }
}
