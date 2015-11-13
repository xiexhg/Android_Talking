package com.example.xie.talking;

import org.json.JSONObject;

/**
 * Created by xie on 2015/11/13.
 */
public class MsgLink extends Msg{
    private String url;
    public MsgLink(String content,String user_name,int type, String url) {
        super(content,user_name,type);
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
}
