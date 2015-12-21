package com.example.xie.talking;

import android.graphics.Bitmap;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by xie on 2015/12/21.
 */
public class ChartItem {
    private String chart_name;
    private String last_msg;
    private int head;
    public ChartItem(String name,String msg,int head_name){
        chart_name = name;
        last_msg = msg;
        head = head_name;
    }

    public String getChartName(){
        return chart_name;
    }
    public String getLastMsg(){
        return last_msg;
    }
    public int getHead(){
        return head;
    }

}
