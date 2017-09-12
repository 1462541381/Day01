package com.example.day01_zuoye.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day01_zuoye.Info;
import com.example.day01_zuoye.R;

import java.util.ArrayList;

/**
 * Created by 妞妞 on 2017/9/11.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Info> list;
    private ViewHolder holder;

    public MyAdapter(Context context, ArrayList<Info> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getContent());
        Glide.with(context.getApplicationContext()).load(list.get(position).getImgs().get(0)).into(holder.img);
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView img;
        public TextView title;
        public TextView content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.img = (ImageView) rootView.findViewById(R.id.img);
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.content = (TextView) rootView.findViewById(R.id.content);
        }

    }
}
