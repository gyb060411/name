package com.gy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class MainActivity extends AppCompatActivity {

    private Button mShareBut;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionMgr.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionMgr.getInstance().requestPermissions(this);
        initView();
    }

    private void initView() {
        mShareBut = findViewById(R.id.share_but);
        mShareBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
    }
    public void share() {

        UMImage image = new UMImage(MainActivity.this, "http://ww4.sinaimg.cn/large/610dc034jw1f2uyg3nvq7j20gy0p6myx.jpg");//网络图片
        image.setThumb(new UMImage(MainActivity.this, R.mipmap.ic_launcher_round));

        new ShareAction(MainActivity.this).withText("这是分享奥斯卡奖地方").withMedia(image).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
                .setCallback(umShareListener).open();

    }
    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(MainActivity.this, "开始分享", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(MainActivity.this, "分享结果", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(MainActivity.this, "开始失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(MainActivity.this, "分享取消", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}