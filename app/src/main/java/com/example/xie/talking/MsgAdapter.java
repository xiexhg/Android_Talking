package com.example.xie.talking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xie on 2015/11/9.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {
    private int resourceId;
   /* private static final int leftresourceId  = R.layout.left_msg;
    private static final int rightresourceId  = R.layout.right_msg;*/
   /* public int getResourceId(int Type)
    {
        if(Type==Msg.TYPE_RECEIVED)
            return leftresourceId;
        else
            return rightresourceId;
    }*/
    public MsgAdapter(Context context,int resourceId,List<Msg> objects) {
        super(context,resourceId,objects);
        this.resourceId = resourceId;
        Log.i("talking", "init MsgAdapter");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftMsgContent = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.leftUsrName = (TextView) view.findViewById(R.id.left_username);
            viewHolder.leftLayOut = (RelativeLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightMsgContent = (TextView) view.findViewById(R.id.right_msg);
            viewHolder.rightUsrName = (TextView) view.findViewById(R.id.right_username);
            viewHolder.rightLayOut = (RelativeLayout) view.findViewById(R.id.right_layout);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        System.out.printf(msg.getContent());
        Log.i("talking", String.format("type:%d content:%s name:%s", msg.getType(), msg.getContent(),msg.getUserName()));
        if(msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.leftLayOut.setVisibility(view.VISIBLE);
            viewHolder.rightLayOut.setVisibility(view.GONE);
            viewHolder.leftMsgContent.setText(msg.getContent());
            viewHolder.leftUsrName.setText(msg.getUserName());

        } else  if(msg.getType() == Msg.TYPE_SEND){
            viewHolder.rightLayOut.setVisibility(view.VISIBLE);
            viewHolder.leftLayOut.setVisibility(view.GONE);
            viewHolder.rightMsgContent.setText(msg.getContent());
            viewHolder.rightUsrName.setText(msg.getUserName());
        }

        return view;
    }
    class ViewHolder {
        RelativeLayout layOut;
        TextView msgContent;
        TextView usrName;
    }
}

