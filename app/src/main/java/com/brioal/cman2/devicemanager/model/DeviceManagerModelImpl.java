package com.brioal.cman2.devicemanager.model;

import com.brioal.cman2.bean.DeviceBean;
import com.brioal.cman2.interfaces.OnDeviceLoadListener;
import com.brioal.cman2.interfaces.OnNormalOperationListener;
import com.brioal.cman2.devicemanager.contract.DeviceManagerContract;
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
 * Created by Brioal on 2017/02/17
 */

public class DeviceManagerModelImpl implements DeviceManagerContract.Model {
    private final String Device_List_Url = "http://192.168.3.27/cman/devicelist.php";
    private final String Change_Name_Url = "http://192.168.3.27/cman/deletedevice.php";

    @Override
    public void loadDeviceList(final OnDeviceLoadListener listener) {
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
    public void deleteDevice(DeviceBean bean, final OnNormalOperationListener listener) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("deviceid", bean.getDeviceID())
                .build();
        Request request = new Request.Builder()
                .url(Change_Name_Url)
                .post(body)
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
                KLog.e(content);
                if (listener == null) {
                    return;
                }
                if (content.equals("Done")) {
                    listener.success("删除设备成功");
                    return;
                }
                listener.failed("删除设备失败");
            }
        });
    }
}