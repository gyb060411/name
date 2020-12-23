package com.gy.myapplication;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public
class MyApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UMConfigure.init(this,"5fe15d17345b8b53f575e1e5"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        // QQ设置
        PlatformConfig.setQQZone("101921073", "38a314d9c8acadf3d3fe056cf908a2ad");
        PlatformConfig.setQQFileProvider("com.gy.myapplication.fileprovider");

        // 其他平台设置
        PlatformConfig.setSinaWeibo("2779483073", "3fcbcd9df63e095b8168202bae259465","http://www.sina.com");

    }
}
