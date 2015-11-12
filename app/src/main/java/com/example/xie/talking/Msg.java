package com.example.xie.talking;

/**
 * Created by xie on 2015/11/9.
 */
public class Msg {
    public  static final int TYPE_SEND =1;
    public static final int TYPE_RECEIVED =0;
    private String content;
    private String user_name;
    private int type;
    public Msg(String content,String user_name,int type){
        this.content =content;
        this.user_name = user_name;
        this.type=type;
    }
    public String getContent(){
        return content;
    }
    public String getUserName() { return user_name;}
    public int getType(){
        return type;
    }
}
