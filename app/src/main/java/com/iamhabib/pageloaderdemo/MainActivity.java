package com.iamhabib.pageloaderdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iamhabib.pageloader.PageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PageLoader pageLoader= (PageLoader) findViewById(R.id.pl);
        pageLoader.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageLoader.setNoConnection();
            }
        }, 5000);
        pageLoader.setOnRetry(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageLoader.startLoading();
            }
        });
    }
}
