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
    private ListView ChartListView;
    private ChartAdapter adapter;
    private List<ChartItem> ChartList = new ArrayList<ChartItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initItems();
        Log.i("talking", "init initItems");
        adapter = new ChartAdapter(MainActivity.this,R.layout.chart_item,ChartList);

        ChartListView =(ListView) findViewById(R.id.chart_list_view);
        ChartListView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        //Log.i("talking",System.out.printf("clicked id %d",v.getId()).toString());
        if(v.getId()==R.id.send){
           /* Log.i("talking","send clicked");
            String content = inputText.getText().toString();
            if(!"".equals(content)){
                Msg msg = new Msg(content,send_name,MsgType.SENDMSG);
                msgList.add(msg);
                adapter.notifyDataSetChanged();

            }*/
        }
    }

    private void initItems(){
        ChartItem h1 = new ChartItem("lili","nihaoa",R.drawable.mini_avatar_shadow);
        ChartItem h2 = new ChartItem("woli","nihaoa ddd",R.drawable.mini_avatar_shadow);
        ChartList.add(h1);
        ChartList.add(h2);
    }

}
