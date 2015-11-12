package com.example.xie.talking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xie on 2015/11/9.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {
    private int resourceId;
    public MsgAdapter(Context context,int textViewResourceId,List<Msg> objects) {
        super(context,textViewResourceId,objects);
        Log.i("talking", "init MsgAdapter");
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        System.out.printf("type is :%d \n",msg.getType());
        System.out.printf(msg.getContent());
        Log.i("talking", String.format("%d", msg.getType()));
        Log.i("talking",msg.getContent());
        if(msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.leftLayout.setVisibility(view.VISIBLE);
            viewHolder.rightLayout.setVisibility(view.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        } else  if(msg.getType() == Msg.TYPE_SEND){
            viewHolder.rightLayout.setVisibility(view.VISIBLE);
            viewHolder.leftLayout.setVisibility(view.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }
    class ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }
}
