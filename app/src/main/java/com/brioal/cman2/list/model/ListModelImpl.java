package com.brioal.cman2.list.model;

import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceLoadListener;
import com.brioal.cman2.interfaces.OnNormalOperationListener;
import com.brioal.cman2.list.contract.ListContract;
import com.socks.library.KLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Brioal on 2017/02/18
 */

public class ListModelImpl implements ListContract.Model {
    private final String Device_List_Url = "http://192.168.3.27/cman/devicelist.php";
    private final String Device_Statue_Url = "http://192.168.3.27/cman/changename.php";

    @Override
    public void loadDeviceList(final OnDeviceLoadListener listener) {
        //加载所有的设备列表
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Device_List_Url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener == null) {
                    return;
                }
                listener.failed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                KLog.json(content);
                List<DeviceBean> list = new ArrayList<DeviceBean>();
                try {
                    JSONArray array = new JSONArray(content);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String type = object.getString("type");
                        String deviceId = object.getString("deviceid");
                        String name = object.getString("name");
                        boolean online = object.getBoolean("online");
                        DeviceBean bean = new DeviceBean(type, deviceId, name, online);
                        list.add(bean);
                    }
                    if (listener == null) {
                        return;
                    }
                    if (list.size() == 0) {
                        listener.failed("暂无数据");
                        return;
                    }
                    listener.success(list);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void refreshDeviceStatue(DeviceBean bean, final OnNormalOperationListener listener) {
        //判断设备是否在线
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("deviceid", bean.getDeviceID())
                .add("name", bean.getName())
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(Device_Statue_Url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener == null) {
                    return;
                }
                listener.failed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener == null) {
                    return;
                }
                String content = response.body().string();
                KLog.e("当前设备状态:", content);
                if (content != null && content.equals("Done")) {
                    listener.success("");
                    return;
                }
                listener.failed("当前设备不可用");
            }
        });
    }
}