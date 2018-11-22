package com.ctrip.pluginapplication;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 *  应用初始化
 *  @author michelle on 2018/11/21.
 *  在app启动的时候，就把asset的apk插件移到sdk的指定目录
 */

public class App extends Application {
   public static  String newPath="/plugin_test"+"/apks";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("app",newPath);
        FileUtils.getInstance(getApplicationContext()).copyAssetsToSD("apks",newPath).setFileOperateCallback(
                new FileUtils.FileOperateCallback() {
                    @Override
                    public void onSuccess () {
                        // TODO: 文件复制成功时，主线程回调
                        Log.e("file","success");
                    }

                    @Override
                    public void onFailed (String error){
                        // TODO: 文件复制失败时，主线程回调
                        Log.e("file","erro");

                    }
                });


    }






}
