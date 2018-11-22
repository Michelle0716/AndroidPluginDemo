package com.ctrip.standard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @author michelle on 2018/3/7.
 * 用来作为宿主行为通知插件执行行为的接口类
 */

public interface AppInterface {
    void setContentView(int layoutResID);

    <T extends View> T findViewById(int id);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onDestroy();

    void onPause();

    void onSaveInstanceState(Bundle outState);

    /**
     * 需要宿主app注入给插件app上下文
     */
    void attach(Activity activity);
}
