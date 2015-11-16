package com.example.xie.talking;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by xie on 2015/11/13.
 */
public class MsgNews extends Msg {
    public List<News> list = new ArrayList<News>();
    public MsgNews(String content,String user_name,int type,JSONArray jsonArray){
        super(content,user_name,type);
        initMsgNews(jsonArray);
    }
    public List<News> getList(){
        return list;
    }
    private void initMsgNews(JSONArray jsonArray){
        try {
            JSONArray json =  jsonArray;
            int length = 2;
            if(json.length() < 2)
                length = json.length();

            for(int i=0; i<length;i++){
                News news = new News();
                news.article = json.getJSONObject(i).getString("article");
                Log.i("talking",news.article);
                news.source = json.getJSONObject(i).getString("source");
                news.detail_url = json.getJSONObject(i).getString("detailurl");
                //news.news_icon = json.getJSONObject(1).getString("news_icon");
                //URL icon_url = new URL(json.getJSONObject(i).getString("icon"));
                news.icon_url=json.getJSONObject(i).getString("icon");

                list.add(news);
                Log.i("talking", String.format("initMsgNews++++#### i:%d len:%d list len:%d", i, length,list.size()));
            }
        }  catch (Exception e)
        {
            return;
            //e.printStackTrace();
        }
    }
    @Override
    public List<News> getMsgList() {
        //return super.getMsgList();
        return list;
    }
    public void setListIcon(int index,Bitmap bitmap){
        News news =list.get(index);
        news.news_icon = bitmap;
        list.set(index,news);
    }
    public class News{
        String article;
        String source;
        String icon_url;
        Bitmap news_icon;
        String detail_url;
    }
}
