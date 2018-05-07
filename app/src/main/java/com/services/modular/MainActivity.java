package com.services.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.services.modular.push.GeTuiPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GeTuiPushManager.getInstance().initInMainActivity(this);
    }
}
