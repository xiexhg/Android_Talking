package com.example.xie.talking;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by xie on 2015/11/13.
 */
public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private int node;
    private int index;
    MainActivity activity;
    private Bitmap getImageBitmap(String url){
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
    public  DownloadImageAsyncTask(int node,int index,MainActivity activity){
        this.node = node;
        this.index = index;
        this.activity = activity;

    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Log.i("talking",String.format("doInBackground url is :%s",params[0]));
        Bitmap b = getImageBitmap(params[0]);

        return b;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Msg msg = activity.getMsgList().get(node);
        msg.setListIcon(index, bitmap);
        activity.updateMsgList(node,msg);
    }
}
