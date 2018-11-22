# AndroidPluginDemo
安卓插件化demo

## 找不到插件类异常
Didn't find class "com.ctrip.thirdpartyapplication.TestActivity" on path: DexPathList[[
zip file "/storage/emulated/0/plugin_test/apks/plugin.apk"]

### 解决
#### 1、确保在sd卡目录里有apk
有的手机找不到/storage/emulated/0/目录，即0就是内置存储（我的手机是这样），要看完整，可以下载一个es文件浏览器

#### 2、主题
把application的统一主题去掉，分别给每个activity配置主题。
再来一次，buildApk，导入主程序，运行主程序

## 先加载apk,在点击“到插件”

## 调用DexClassLoader
##  插件化作用
增加功能，不用重新打包，绕过商店上架审核


## 获取包信息
####        Log.i("JG",  "包名："+getPackageName());
####        Log.w("JG",  "代码路径："+getPackageCodePath());
####        Log.e("JG",  "资源路径："+getPackageResourcePath());



