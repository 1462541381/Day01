package com.example.day01_zuoye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.Serializable;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private TextView title;
    private Toolbar toolbar;
    private Banner banner;
    private TextView content;
    private Info info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
    }

    private void initData() {
        info = (Info) getIntent().getSerializableExtra("info");
        title.setText(info.getTitle());
        content.setText(info.getContent());
        List<String> imgs = info.getImgs();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new ImageLoad())
                .setImages(imgs)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(2000)
                .start();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        banner = (Banner) findViewById(R.id.banner);
        content = (TextView) findViewById(R.id.content);
    }
}
