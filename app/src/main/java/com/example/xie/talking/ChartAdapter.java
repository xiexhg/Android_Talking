package com.example.xie.talking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xie on 2015/12/21.
 */
public class ChartAdapter extends ArrayAdapter<ChartItem> {
    private int resourceId;
   // private List<ChartItem> item_list;
    public ChartAdapter(Context context,int resourceId,List<ChartItem> objects){
        super(context, resourceId, objects);
        this.resourceId = resourceId;
        //this.item_list = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ChartItem item = getItem(position);
        /*if(item==null)
            Log.i("talking","item error is null"+position);*/
        if(convertView!=null){
            view =convertView;
        }else {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        }
        ImageView char_head = (ImageView) view.findViewById(R.id.char_head);
        TextView char_name = (TextView) view.findViewById(R.id.chart_name);
        TextView last_msg = (TextView) view.findViewById(R.id.last_msg);
        char_head.setImageResource(item.getHead());
        char_name.setText(item.getChartName());
        last_msg.setText(item.getLastMsg());
        return view;
    }
}
