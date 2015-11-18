package com.example.xie.talking;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xie on 2015/11/9.
 */
public class MsgAdapter extends BaseAdapter {
    private int resourceId;
    private  List<Msg> msg_list;
    private LayoutInflater mInflater;
   /* private static final int leftresourceId  = R.layout.left_msg;
    private static final int rightresourceId  = R.layout.right_msg;*/
   /* public int getResourceId(int Type)
    {
        if(Type==Msg.TYPE_RECEIVED)
            return leftresourceId;
        else
            return rightresourceId;
    }*/

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return msg_list.get(position);
    }

    @Override
    public int getCount() {
        return msg_list.size();
    }

    public MsgAdapter(Context context,List<Msg> objects) {
        msg_list=objects;
        mInflater=LayoutInflater.from(context);
        Log.i("talking", "init MsgAdapter");
    }

    public ViewHolder initViewHolder(View view ,Msg msg){
        ViewHolder viewHolder = new ViewHolder();
        int msgtype = msg.getType();
        viewHolder.msgContent = (TextView) view.findViewById(R.id.msg);
        viewHolder.usrName = (TextView) view.findViewById(R.id.username);

        if(msgtype > MsgType.NORMALMSG){
            viewHolder.list.taital=(TextView) view.findViewById(R.id.message_name);
            viewHolder.list.content=(TextView) view.findViewById(R.id.message_content);
            viewHolder.list.icon=(ImageView) view.findViewById(R.id.message_icon);
        } else if(msgtype == MsgType.NORMALMSG){
            viewHolder.layout = (RelativeLayout)view.findViewById(R.id.extra_info);
            Log.i("talking","findViewById at"+viewHolder.layout);
        }
        return viewHolder;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = msg_list.get(position);
        View view;
        ViewHolder viewHolder;
        //if(convertView == null){
            if(msg.getType()==0){
                view = mInflater.inflate(R.layout.right_msg, null);
            } else{
                view = mInflater.inflate(R.layout.left_msg, null);
            }

            viewHolder=initViewHolder(view,msg);
            viewHolder.pos=position;
            view.setTag(viewHolder);
       /* } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }*/
        System.out.printf(msg.getContent());
        Log.i("talking", String.format("type:%d content:%s name:%s", msg.getType(), msg.getContent(), msg.getUserName()));
        viewHolder.msgContent.setText(msg.getContent());
        viewHolder.usrName.setText(msg.getUserName());
        if(msg.getType() == MsgType.COOKMSG){
            MsgMenu.Item item = (MsgMenu.Item)msg.getMsgList().get(0);
            viewHolder.list.taital.setText(item.name);
            viewHolder.list.content.setText(item.info);
            if(item.icon!=null)
                viewHolder.list.icon.setImageBitmap(item.icon);
        } else if(msg.getType() == MsgType.NEWSMSG){
            MsgNews.Item item = (MsgNews.Item)msg.getMsgList().get(0);
            viewHolder.list.taital.setText(item.article);
            viewHolder.list.content.setText(item.source);
            if(item.news_icon!=null)
                viewHolder.list.icon.setImageBitmap(item.news_icon);
        } else if(msg.getType() == MsgType.NORMALMSG) {
            Log.i("talking","layout at:"+viewHolder.layout);
            viewHolder.layout.setVisibility(View.GONE);
        }
        Log.i("talking",String.format("$$$$$****position:%d index:%d",position,viewHolder.pos));
        return view;
    }
    class ViewHolder {
        int pos ;
        TextView msgContent;
        TextView usrName;
        RelativeLayout layout;
        ExtraList list = new ExtraList() ;
    }
    class ExtraList{
        TextView taital;
        TextView content;

        ImageView icon;
    }
}



