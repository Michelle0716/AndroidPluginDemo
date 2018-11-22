package com.ctrip.pluginapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ctrip.standard.AppInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author michelle on 2018/3/7.
 *
 * 这个类用来作为宿主和插件的桥梁
 *
 *
 */

public class ProxyActivity extends Activity {
    /**
     * 要跳转的activity的name
     */
    private String className = "";
    private AppInterface appInterface = null;
    private String TAG="ProxyActivity.class";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * step1：得到插件app的activity的className
         */
        className = getIntent().getStringExtra("className");
        /**
         * step2：通过反射拿到class，
         * 但不能用以下方式
         * classLoader.loadClass(className)
         * Class.forName(className)
         * 因为插件app没有被安装！
         * 这里我们调用我们重写过多classLoader
         */
        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor();
            Object instance = constructor.newInstance();

            appInterface = (AppInterface) instance;
            appInterface.attach(this);
            Bundle bundle = new Bundle();
            appInterface.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG,"异常"+e.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Log.e(TAG,"异常2"+e.getMessage());

        } catch (InstantiationException e) {
            e.printStackTrace();
            Log.e(TAG,"异常3"+e.getMessage());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e(TAG,"异常4"+e.getMessage());

        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.e(TAG,"异常5"+e.getMessage());

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(appInterface!=null){
            appInterface.onStart();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(appInterface!=null){
            appInterface.onResume();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(appInterface!=null){
            appInterface.onDestroy();

        }
    }

    @Override
    public ClassLoader getClassLoader() {
        //不用系统的ClassLoader，用dexClassLoader加载
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        //不用系统的resources，自己实现一个resources
        return PluginManager.getInstance().getResources();
    }

}
