package com.example.xie.talking;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import java.net.URLEncoder;
import org.json.JSONObject;


public class MainActivity extends Activity implements View.OnClickListener {
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private static final String send_name = "二货";
    private static final String receive_name = "智者";
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<Msg>();
    public static final int RECEIVE_MSG = 0;
    public static final int IMAGE_DONE = 1;
    /*public  static enum message_type {
        RECEIVE_MSG,
        IMAGE_DONE;
    };*/
    private static String preurl = "http://www.tuling123.com/openapi/api?key=2b9c12b2770d9bb3a9481ce66f72a449&info=";
    private Handler handler = new Handler() {
        public void  handleMessage(Message msg){
            switch (msg.what){
                case RECEIVE_MSG:
                    String respons = (String) msg.obj;
                    Log.i("talking", respons);
                    /*try {
                        JSONObject json =  new JSONObject(respons);
                        String rsp = (String)json.get("text");
                        Msg recmsg = new Msg(rsp,receive_name,Msg.TYPE_RECEIVED);
                        msgList.add(recmsg);
                        adapter.notifyDataSetChanged();
                        msgListView.smoothScrollToPosition(adapter.getCount()-1);


                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    };*/
                    Msg recmsg =RemoteMessageHandle.hanleMessage(respons,receive_name);
                    msgList.add(recmsg);
                    adapter.notifyDataSetChanged();
                    int node = msgList.size() - 1;
                    msgListView.smoothScrollToPosition(node);
                    RemoteMessageHandle.messageHandle(node,1,MainActivity.this,recmsg);
                    return;
                case IMAGE_DONE:
                    Bitmap bitmap = (Bitmap) msg.obj;

                    return;
                default:
                    return;


            }
        }
    };

    public List<Msg> getMsgList(){
        return msgList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initMsgs();
        Log.i("talking", "init initMsgs");
        adapter = new MsgAdapter(MainActivity.this,msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView =(ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Log.i("talking",System.out.printf("clicked id %d",v.getId()).toString());
        if(v.getId()==R.id.send){
            Log.i("talking","send clicked");
            String content = inputText.getText().toString();
            if(!"".equals(content)){
                Msg msg = new Msg(content,send_name,Msg.TYPE_SEND);
                msgList.add(msg);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                inputText.setText("");
                sendHttpRequestAndGetRespond(content);
            }
        }
    }

    private void sendHttpRequestAndGetRespond(final  String content){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                   // String strurl = URLEncoder.encode(preurl.concat(content), "UTF-8");
                    String strurl = preurl.concat(URLEncoder.encode(content, "UTF-8"));
                    Log.i("talking", strurl);
                    URL url = new URL(strurl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = RECEIVE_MSG;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                    //Msg recMsg =new Msg(response.toString(),Msg.TYPE_RECEIVED);
                    Log.i("talking", response.toString());
                    //msgList.add(recMsg);
                    //adapter.notifyDataSetChanged();
                } catch (Exception e){
                    Log.i("talking",e.toString());
                    e.printStackTrace();
                } finally {
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }
    public void updateMsgList(int pos,Msg msg){
        msgList.set(pos,msg);
    }
    private void initMsgs(){
        Msg msg1 = new Msg("Hello guy.",receive_name,Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello,ni hao.",send_name,Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is xie.",receive_name,Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }
}
