package com.advertise.adview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.advertise.adview.core.Constants;
import com.bumptech.glide.Glide;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class AdView extends LinearLayout {
    private String API_KEY;
    private String image_url;
    private Context context;
    private String image_onclick_url;
    private int type;


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
        API_KEY = a.getString(R.styleable.AdView_key);
        type = a.getInteger(R.styleable.AdView_size, 1);
        if (API_KEY.length()>5) {
            Toast.makeText(context, "Loading started ; size = " + type, Toast.LENGTH_SHORT).show();
            initComponent(context);
        }else{
            Toast.makeText(context, "Something wrong with advertise api key!", Toast.LENGTH_SHORT).show();
        }
    }


    public class GetImageUrl extends AsyncTask<Void, Void, Void> {

        ImageView viewToLoad;

        public GetImageUrl(ImageView viewToLoad){
            this.viewToLoad = viewToLoad;
        }


        String resultJSON = "";
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String url = Constants.Api.BASE_URL + API_KEY;
                HttpClient httpclient = new DefaultHttpClient();
                switch (type) {
                    case Constants.Attr.BOTTOMLINE:
                        url += "?width=320&height=50";
                        break;
                    case Constants.Attr.TOPLINE:
                        url += "?width=320&height=50";
                        break;
                    case Constants.Attr.FULLSCREEN:
                        url += "?width=320&height=480";
                        break;
                }
                url += Constants.Api.ARG_OS;
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
                String json = EntityUtils.toString(httpResponse.getEntity());
                resultJSON = json;
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
            if (resultJSON.length() > 0 && viewToLoad !=null) {
                Toast.makeText(context, resultJSON + " ; size = " + type, Toast.LENGTH_SHORT).show();
                Glide.with(context)
                        .load(image_url)
                        .into(viewToLoad);
            }
            else {
                Toast.makeText(context, "result empty; size = " + type, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ev = event.getAction();
        switch (ev) {
            case MotionEvent.ACTION_UP:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(image_onclick_url));
                context.startActivity(browserIntent);
                break;
        }
        return true;
    }




    private void initComponent(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.banner, null, false);
        ImageButton hideView = (ImageButton)view.findViewById(R.id.close);
        hideView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(GONE);
            }
        });
        ImageView img = (ImageView)view.findViewById(R.id.banner_img);
        new GetImageUrl(img).execute();
        this.addView(view);

    }
}
