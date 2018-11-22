package com.ctrip.pluginapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

/**
 * @author michelle on 2018/3/7.
 *
 *
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用插件
                click();
            }
        });
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //集成插件
                load();
            }
        });
        PluginManager.getInstance().setContext(this);
    }

    //事件绑定load
    private void load() {
        /**
         * 事先放置到SD卡根目录的plugin.apk
         * 现实场景中是有服务端下发
         */
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/plugin_test", "apks/plugin.apk");
        Log.e("load","路径"+file.toString());
        if(file.exists()){
            PluginManager.getInstance().loadPath(file.getAbsoluteFile().getPath());

        }else {
            File f=new File(App.newPath+"/plugin.apk");
            if(f.exists()){
                Log.e("load","f路径存在"+f.toString());

                PluginManager.getInstance().loadPath(App.newPath+"/plugin.apk");

            }else {
                Log.e("load","apk不存在");

            }
        }
    }

    //事件绑定click
    private void click() {
        /**
         * 点击跳往插件app的activity，一律跳转到PRoxyActivity
         */


//        Intent intent = new Intent(MainActivity.this,ProxyActivity.class);
//        intent.setComponent(new ComponentName(PluginManager.getInstance().getPackageName(),
//                PluginManager.getInstance().getEntryName()));
//        startActivity(intent);


//com.ctrip.third
// partyapplication.MainActivity
        Intent intent = new Intent(MainActivity.this,ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getEntryName());
        startActivity(intent);

    }
}