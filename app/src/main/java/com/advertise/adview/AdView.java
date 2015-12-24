package com.advertise.adview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.advertise.adview.core.Constants;
import com.bumptech.glide.Glide;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * Created by Алексей on 13.11.2015.
 */
public class AdView extends ImageView {
    private String API_KEY;
    private String image_url;
    private Context context;
    private String image_onclick_url;


    public AdView(Context context, String api_key) {
        super(context);
        this.API_KEY = api_key;
        this.context = context;
        this.setWillNotDraw(false);
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AdView,
                0, 0);

        API_KEY = a.getString(R.styleable.AdView_api);
        Toast.makeText(context, API_KEY, Toast.LENGTH_SHORT).show();

        new GetImageUrl().execute();
    }


    public class GetImageUrl extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) { // create HttpClient
            try {
                HttpClient httpclient = new DefaultHttpClient();
                String url = Constants.Api.BASE_URL + API_KEY + Constants.Api.ARG_OS;

                // make GET request to the given URL
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

                String json = EntityUtils.toString(httpResponse.getEntity());
                JSONObject object = new JSONObject(json);
                image_url = object.getString("img_src");
                image_onclick_url = object.getString("url");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Glide.with(context)
                    .load(image_url)
                    .into(AdView.this);
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ev = event.getAction();
        switch (ev){
            case MotionEvent.ACTION_UP:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(image_onclick_url));
                context.startActivity(browserIntent);
                break;
        }
        return true;
    }
}
