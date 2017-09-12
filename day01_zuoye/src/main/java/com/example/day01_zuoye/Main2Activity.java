package com.example.day01_zuoye;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.day01_zuoye.MyAdapter.MyAdapter;
import com.example.day01_zuoye.OkHttp.OkHttpUtlis;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private TextView title;
    private Toolbar toolbar;
    private ListView lv;
    private ArrayList<Info> list;
    private MyAdapter adapter;
    private AlertDialog dialog;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initAdapter();

        initListener();
    }

    private void initListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Info info = list.get(position);
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("info",info);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        dialog.cancel();
        OkHttpUtlis.getInstance().sendGet("http://172.16.44.44:8080/Json_Type/GaoJi.json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ArrayList<Info> o = new Gson().fromJson(response.body().string(), new TypeToken<ArrayList<Info>>() {
                }.getType());
                list.addAll(o);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new MyAdapter(this,list);
        lv.setAdapter(adapter);
        dialog = new AlertDialog.Builder(Main2Activity.this)
                .setTitle("请稍等。。。")
                .create();
        dialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                initData();
            }
        },2000);




    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv = (ListView) findViewById(R.id.lv);
        setSupportActionBar(toolbar);
    }
}
