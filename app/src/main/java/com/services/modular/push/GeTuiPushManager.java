package com.services.modular.push;

import android.app.Activity;

import com.igexin.sdk.PushManager;

/**
 * author: sundong
 * created at: 2018/5/7 18:07
 * class:GeTuiPushManager
 * desc:
 */

public class GeTuiPushManager {
    private GeTuiPushManager() {

    }

    private static class SingletonHolder {
        private static GeTuiPushManager INSTANCE = new GeTuiPushManager();
    }

    public static GeTuiPushManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initInMainActivity(Activity activity){
        PushManager.getInstance().initialize(activity, GeTuiPushService.class);
        PushManager.getInstance().registerPushIntentService(activity, PushIntentService.class);

    }
}
