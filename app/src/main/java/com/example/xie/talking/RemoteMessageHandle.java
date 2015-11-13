package com.example.xie.talking;

import org.json.JSONObject;

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
            String list;
            switch (code){
                case 100000:
                    msg = new Msg(text,name,code);
                    return msg;
                case 200000:
                    list = (String)json.get("url");
                    msg = new MsgLink(text,name,code,list);
                    return msg;
                case 302000:
                    list = (String)json.get("list");
                    msg = new MsgNews(text,name,code,list);
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


}
