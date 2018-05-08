package com.services.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.services.modular.channel.VasDollyManager;
import com.services.modular.push.GeTuiPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GeTuiPushManager.getInstance().initInMainActivity(this);
        String channel = VasDollyManager.getInstance().getChannel();
        Toast.makeText(this, channel, Toast.LENGTH_LONG).show();
    }
}
