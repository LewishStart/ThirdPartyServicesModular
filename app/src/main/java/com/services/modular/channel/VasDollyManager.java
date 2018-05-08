package com.services.modular.channel;

import com.leon.channel.helper.BuildConfig;
import com.leon.channel.helper.ChannelReaderUtil;
import com.services.modular.MyApplication;

/**
 * author: sundong
 * created at: 2018/5/8 15:35
 * class:VasDollyManager
 * desc:
 */

public class VasDollyManager {
    private String channel;

    private VasDollyManager() {
        channel = ChannelReaderUtil.getChannel(MyApplication.getInstance());
    }

    private static class SingletonHolder {
        private static VasDollyManager INSTANCE = new VasDollyManager();
    }

    public static VasDollyManager getInstance() {
        return VasDollyManager.SingletonHolder.INSTANCE;
    }

    /**
     * 得到渠道名
     */
    public String getChannel() {
        if (channel != null && !channel.isEmpty()) {
            return channel;
        }
        channel = ChannelReaderUtil.getChannel(MyApplication.getInstance());

        return channel==null?"":channel;
    }
}
