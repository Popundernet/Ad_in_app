package com.advertise.advertisesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.advertise.adview.AdView;

public class MainActivity extends AppCompatActivity {

    Button top, bottom, full;
    AdView topView, bottomView, fullView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top = (Button)findViewById(R.id.open_top);
        bottom = (Button)findViewById(R.id.open_bottom);
        full = (Button)findViewById(R.id.open_full);

        topView = (AdView)findViewById(R.id.topline);
        bottomView = (AdView)findViewById(R.id.bottomline);
        fullView = (AdView)findViewById(R.id.fullscreen);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topView.setVisibility(View.VISIBLE);
                bottomView.setVisibility(View.GONE);
                fullView.setVisibility(View.GONE);
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topView.setVisibility(View.GONE);
                bottomView.setVisibility(View.VISIBLE);
                fullView.setVisibility(View.GONE);
            }
        });

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topView.setVisibility(View.GONE);
                bottomView.setVisibility(View.GONE);
                fullView.setVisibility(View.VISIBLE);
            }
        });

    }
}
