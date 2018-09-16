package com.shenyong.aabills;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sddy.baseui.BaseActivity;

public class StartupActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        showFullScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        findViewById(R.id.ll_start_up_name).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        }, 2000);
    }
}
