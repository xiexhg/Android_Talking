package com.example.xie.talking;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by xie on 2015/11/12.
 */
public class RemoteMessageHandle {
    public static Msg hanleMessage(String jsonString,String name){
        try {
            JSONObject json =  new JSONObject(jsonString);
            String text = (String)json.get("text");
            int code = (int)json.get("code");
            Msg msg;

            switch (code){
                case 100000:
                    msg = new Msg(text,name,code);
                    return msg;
                case 200000:
                    String list = (String)json.get("url");
                    msg = new MsgLink(text,name,code,list);
                    return msg;
                case 302000:
                    JSONArray jsonNewArray = json.getJSONArray("list");
                    msg = new MsgNews(text,name,code,jsonNewArray);
                    return msg;
                case 308000:
                    JSONArray jsonMenuArray = json.getJSONArray("list");
                    msg = new MsgMenu(text,name,code,jsonMenuArray);
                    return msg;
                default:
                    return null;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void messageHandle(Msg msg){
        switch (msg.getType()){
            case MsgType.NEWSMSG:
                    List<MsgNews.Item> item = ((MsgNews)msg).getList();
                    Log.i("talking",String.format("msgnews size:%d",item.size()));
                    MsgNews.Item news = item.get(0);
                    if(news.icon_url!=""){
                        //DownloadImageAsyncTask task = new DownloadImageAsyncTask(node, index,activity);
                        //task.execute(news.icon_url);
                    }
                    return;
            case MsgType.COOKMSG:
                    List<MsgMenu.Item> menuItem = ((MsgMenu)msg).getList();
                    Log.i("talking",String.format("menuItem size:%d",menuItem.size()));
                    MsgMenu.Item menu = menuItem.get(0);
                    if(menu.icon_url!=""){
                        Log.i("talking","messageHandle msg is at:"+msg);
                        DownloadImageAsyncTask task = new DownloadImageAsyncTask(msg);
                        task.execute(menu.icon_url);
                    }
                    return;
            default:
                    return;
        }

    }

}
