package com.example.xie.talking;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xie on 2015/11/14.
 */
public class MsgMenu extends Msg {
    public List<Menu> list = new ArrayList<Menu>();
    public MsgMenu(String content,String user_name,int type,JSONArray jsonArray){
        super(content,user_name,type);
        initMsgMenu(jsonArray);
    }
    public List<Menu> getList(){
        return list;
    }
    private void initMsgMenu(JSONArray jsonArray){
        try {
            JSONArray json =  jsonArray;
            int length = 2;
            if(json.length() < 2)
                length = json.length();

            for(int i=0; i<length;i++){
                Menu menu = new Menu();
                menu.name = json.getJSONObject(i).getString("name");
                Log.i("talking", menu.name);
                menu.info = json.getJSONObject(i).getString("info");
                menu.detail_url = json.getJSONObject(i).getString("detailurl");
                //news.news_icon = json.getJSONObject(1).getString("news_icon");
                //URL icon_url = new URL(json.getJSONObject(i).getString("icon"));
                menu.icon_url=json.getJSONObject(i).getString("icon");

                list.add(menu);
                Log.i("talking", String.format("initMsgNews++++#### i:%d len:%d list len:%d", i, length,list.size()));
            }
        }  catch (Exception e)
        {
            return;
            //e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getMsgList() {
        //return super.getMsgList();
        return list;
    }

    public void setListIcon(int index,Bitmap bitmap){
        Menu news =list.get(index);
        news.icon = bitmap;
        list.set(index,news);
    }
    public class Menu{
        String name;
        String info;
        String icon_url;
        Bitmap icon;
        String detail_url;
    }
}
