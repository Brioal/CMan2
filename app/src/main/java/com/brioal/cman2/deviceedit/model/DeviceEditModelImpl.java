package com.brioal.cman2.deviceedit.model;

import com.brioal.cman2.deviceedit.contract.DeviceEditContract;
import com.brioal.cman2.interfaces.OnNormalOperationListener;
import com.brioal.cman2.util.Netutil;
import com.socks.library.KLog;

import java.io.IOException;

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

public class DeviceEditModelImpl implements DeviceEditContract.Model {
    private final String changeNameUrl = Netutil.getHostUrl()+"cman/changename.php";

    @Override
    public void changeDeviceName(String deviceID, String name, final OnNormalOperationListener listener) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("deviceid", deviceID)
                .add("name", name)
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(changeNameUrl)
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
                KLog.e("修改名称结果:", content);
                if (content != null && content.equals("Done")) {
                    listener.success("");
                    return;
                }
                listener.failed("修改名称失败");
            }
        });
    }
}