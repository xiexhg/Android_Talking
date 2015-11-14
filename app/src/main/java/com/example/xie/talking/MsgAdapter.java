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

    public ViewHolder initViewHolder(View view,ViewHolder viewHolder,Msg msg){
        int msgtype = msg.getType();
        viewHolder.msgContent = (TextView) view.findViewById(R.id.msg);
        viewHolder.usrName = (TextView) view.findViewById(R.id.username);
        if(msgtype==MsgType.NEWSMSG){
            //MsgNews msgNews = (MsgNews)msg;
            //MsgNews.News news = msgNews.getList().get(0);
            viewHolder.list.content=(TextView) view.findViewById(R.id.message_name);
            viewHolder.list.taital=(TextView) view.findViewById(R.id.message_content);
            viewHolder.list.icon=(ImageView) view.findViewById(R.id.message_icon);

        } else if(msgtype==MsgType.COOKMSG){
            viewHolder.list.content=(TextView) view.findViewById(R.id.message_name);
            viewHolder.list.taital=(TextView) view.findViewById(R.id.message_content);
            viewHolder.list.icon=(ImageView) view.findViewById(R.id.message_icon);
        }
        return viewHolder;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = msg_list.get(position);
        View view;
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            if(msg.getType()==0){
                view = mInflater.inflate(R.layout.right_msg, null);
            } else{
                view = mInflater.inflate(R.layout.left_msg, null);
            }
            viewHolder=initViewHolder(view,viewHolder,msg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        System.out.printf(msg.getContent());
        Log.i("talking", String.format("type:%d content:%s name:%s", msg.getType(), msg.getContent(),msg.getUserName()));
        viewHolder.msgContent.setText(msg.getContent());
        viewHolder.usrName.setText(msg.getUserName());
        if(msg.getType() == MsgType.NEWSMSG){
            MsgNews.News news = ((MsgNews)msg).getList().get(0);
            viewHolder.list.taital.setText(news.article);
            viewHolder.list.content.setText(news.source);
            if(news.news_icon!=null)
                viewHolder.list.icon.setImageBitmap(news.news_icon);
        }

        return view;
    }
    class ViewHolder {
        TextView msgContent;
        TextView usrName;
        ExtraList list = new ExtraList() ;
    }
    class ExtraList{
        TextView taital;
        TextView content;
        ImageView icon;
    }
}

