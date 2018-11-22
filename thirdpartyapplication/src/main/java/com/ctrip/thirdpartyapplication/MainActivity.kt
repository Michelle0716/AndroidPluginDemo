package com.ctrip.thirdpartyapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

/**
 * Created by centerm on 2018/11/21.
 * 打包的时候，把apk放在宿主app的asset中
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 这里不能直接使用findViewById，因为该方法需要一个上下文，
         * 而thirdPartyApp是插件，是没有被安装的，是没有上下文的
         * 所以，需要重新findViewById，让宿主app来实现
         */
        var img = findViewById<ImageView>(R.id.img)
        img.setOnClickListener({
            if(that!=null){
                Toast.makeText(that, "点击啦，来自宿主app的点击", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "点击啦,没有宿主", Toast.LENGTH_SHORT).show()

            }
        })
    }
}