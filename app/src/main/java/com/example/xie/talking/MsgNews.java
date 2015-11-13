package com.example.xie.talking;

import android.graphics.Bitmap;

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
    private List<News> list;
    public MsgNews(String content,String user_name,int type,String list){
        super(content,user_name,type);
        initMsgNews(list);
    }
    public List<News> getList(){
        return list;
    }
    private void initMsgNews(String list){
        try {
            JSONArray json =  new JSONArray(list);
            int length = 2;
            if(json.length() < 2)
                length = json.length();

            for(int i=0; i<length;i++){
                News news = new News();
                news.article = json.getJSONObject(1).getString("article");
                news.source = json.getJSONObject(1).getString("source");
                news.detail_url = json.getJSONObject(1).getString("detail_url");
                //news.news_icon = json.getJSONObject(1).getString("news_icon");
                URL icon_url = new URL(json.getJSONObject(1).getString("icon"));

            }
        }  catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setListIcon(int index,Bitmap bitmap){
        News news =list.get(index);
        news.news_icon = bitmap;
        list.set(index,news);
    }
    class News{
        String article;
        String source;
        Bitmap news_icon;
        String detail_url;
    }
}
