package com.services.modular.push;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */

public class PushIntentService extends GTIntentService {

    @Override
    public void onReceiveServicePid(Context context, int i) {

    }

    @Override
    public void onReceiveClientId(Context context, String s) {

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
//        //收到透传消息
//        String appid = gtTransmitMessage.getAppid();
//        String taskid = gtTransmitMessage.getTaskId();
//        String messageid = gtTransmitMessage.getMessageId();
//        byte[] payload = gtTransmitMessage.getPayload();
//        String pkg = gtTransmitMessage.getPkgName();
//        String cid = gtTransmitMessage.getClientId();
//
//        if (payload == null) {
//            return;
//        }
//        String data = new String(payload);
//
////        if (!TextUtils.isEmpty(data)) {
////            ViseLog.i(data);
////        }
//
//        try {
//            JSONObject object = new JSONObject(data);
//            String sequenceId = object.optString("sequence_id", "");
//            String msgAscription = object.optString("msg_ascription", "");     //消息类别
//            String msgTitle = object.optString("msg_title", "");
//            String msgContent = object.optString("msg_content", "");
//            String uri = object.optString("uri", "");
//
//            String myMsgTitle = object.optString("my_title", "");
//            String myMsgContent = object.optString("my_content", "");
//            String myMsgSummary = object.optString("my_summary", "");
//            int myMsgTime = object.optInt("my_msg_time");
//            String myMsgLink = object.optString("my_msg_link", "");
//            String myMsgPicture = object.optString("my_picture", "");
//
//            String commandType = object.optString("command_type", "");
//
//            String active_id = object.optString("active_id", "");
//
//            //
//            if (!TextUtils.isEmpty(msgAscription) && "active".equals(msgAscription)) {  //用来判断设备是否还在线
//                if (TextUtils.isEmpty(active_id)){
//                    return;
//                }
//                UIHelper.sendBroadcast(MainActivity.PowerInfo_Collect_Notify);
//                ViseApi viseApi = new ViseApi.Builder(context).baseUrl(ApiHost.getHost()).build();
//
//                Map<String, String> paramsMap = new HashMap<>();
//                paramsMap.put("active_id", active_id);
//                paramsMap.put("task_id", taskid);
//                paramsMap.put("messageid", messageid);
//                paramsMap.put("myMsgTime", String.valueOf(myMsgTime));
//                paramsMap.put("sequenceId", sequenceId);
//
//                viseApi.post(ApiUrl.NORMAL_LASTACTIVITY_TIME, paramsMap, new ApiCallback<JsonObject>() {
//                    @Override
//                    public void onNext(JsonObject jsonObject) {
//
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//
//                    }
//                }, false, Constants.canCancel);
//                //
//                return;
//            }
//
//            if (!TextUtils.isEmpty(msgAscription) && "command".equals(msgAscription)) {
//                SpCache spCache = new SpCache(context);
//                spCache.put(SpKey.KEY_COMMAND_TYPE, commandType);
//                return;
//            }
//
//
//            if (!TextUtils.isEmpty(msgAscription) && "my_msg".equals(msgAscription)) {  //此为个人消息
//                if (TextUtils.isEmpty(msgTitle) || TextUtils.isEmpty(msgContent)) {
//                    return;
//                }
//
//                UserMessageService userMessageService = DbHelper.getInstance().getUserMessageService();
//                //最好做下消息的排重
//                long hasCount = userMessageService.queryBuilder()
//                        .where(UserMessageDao.Properties.Msg_id.eq(messageid)).count();
//
//                UserMessage userMessage = new UserMessage();
//                userMessage.setSequence_id(sequenceId);
//                userMessage.setMsg_id(messageid);
//                userMessage.setTask_id(taskid);
//                userMessage.setMsg_ascription(msgAscription);
//                userMessage.setMsg_title(myMsgTitle);
//                userMessage.setMsg_summary(myMsgSummary);
//                userMessage.setMsg_content(myMsgContent);
//                userMessage.setCreate_time((int) (System.currentTimeMillis() / 1000));
//                userMessage.setMsg_time(myMsgTime);
//                userMessage.setHas_read(false);
//                userMessage.setUri(uri);
//                userMessage.setMsg_link(myMsgLink);
//                userMessage.setMsg_picture(myMsgPicture);
//                if (hasCount == 0) {
//                    userMessageService.save(userMessage);
//                }
//
//                User user = UserManager.getInstance().getLoginUser();
//                //只考虑登录的情况
//                if (null != user && !TextUtils.isEmpty(user.getSession_key())) {
//                    if (!user.getSequence_id().equals(sequenceId)) {
//                        return;
//                    }
//
//                    //应用在前台 并且屏幕是亮着的时候 我们只发广播 不推送消息
//                    if (!BaseApplication.getInstance().isApplicationBroughtToBackground(context)
//                            && BaseApplication.getInstance().isScreenOn(context)) {
//                        //only send message
//                        UIHelper.sendBroadcast(MainActivity.My_MessageInfo_Notify);
//                        return;
//                    }
//                }
//            } else if (!TextUtils.isEmpty(msgAscription) && "custom".equals(msgAscription)) {  //在线客服接受到的消息
//                CustomService customService = DbHelper.getInstance().getCustomService();
//                Custom custom = customService.queryBuilder()
//                        .where(CustomDao.Properties.Sequence_id.eq(sequenceId))
//                        .unique();
//                if (custom == null) {
//                    custom = new Custom();
//                    custom.setCreate_time((int) (System.currentTimeMillis() / 1000));
//                    custom.setUnread_count(0);
//                    custom.setSequence_id(sequenceId);
//                    custom.setUpdate_time((int) (System.currentTimeMillis() / 1000));
//                    customService.save(custom);
//                }
//                int unReadCount = custom.getUnread_count();
//                unReadCount++;
//                custom.setUnread_count(unReadCount);
//                custom.setUpdate_time((int) (System.currentTimeMillis()/1000));
//                customService.update(custom);
//
//
//                User user = UserManager.getInstance().getLoginUser();
//                //只考虑登录的情况
//                if (null != user && !TextUtils.isEmpty(user.getSession_key())) {
//                    if (!user.getSequence_id().equals(sequenceId)) {
//                        return;
//                    }
//
//                    //应用在前台 并且屏幕是亮着的时候 我们只发广播 不推送消息
//                    if (!BaseApplication.getInstance().isApplicationBroughtToBackground(context)
//                            && BaseApplication.getInstance().isScreenOn(context)) {
//                        //only send message
//                        UIHelper.sendBroadcast(MainActivity.Custom_Message_Notify);
//                        return;
//                    }
//                }
//            }
//
//
//            if (!TextUtils.isEmpty(msgTitle) && !TextUtils.isEmpty(msgContent)) {  //复合这个条件 认为是透传消息
//                CXXNotification cxxNotification = new CXXNotification();
//                cxxNotification.setTitle(msgTitle);
//                cxxNotification.setTickerText(msgTitle);
//                cxxNotification.setContent(msgContent);
//                //type是为了保证同一种notify不出现多次，这里更好的做法是每次都弹出一次通知，所以用了时间做type
//                if(!TextUtils.isEmpty(msgAscription) && "custom".equals(msgAscription)) {
//                    cxxNotification.setType(1);
//                } else {
//                    cxxNotification.setType(((Long) System.currentTimeMillis()).intValue());
//                }
//
//                Uri.Builder mainUriBuilder = Uri.parse("icxx://www.caixiaoxian.com/entry").buildUpon();
//                //mainUriBuilder.appendQueryParameter("lch", lch).appendQueryParameter("pushid", pushid);
//                //mainUriBuilder.appendQueryParameter("lch", "shs");
//                cxxNotification.setUriStr(mainUriBuilder.toString());
//
//                Uri.Builder builder = Uri.parse(uri).buildUpon();
//                if (!TextUtils.isEmpty(Uri.parse(uri).getQueryParameter("url"))) {
//                    builder.appendQueryParameter("push", "true");
//                }
//                Map<String, String> extra = new HashMap<>();
//                extra.put(MainActivity.REDIRECT, builder.toString());
//
//                cxxNotification.setExtra(Utils.hashMapToJson(extra));
//
//                BaseNotificationController.getInstance(context).notify(cxxNotification);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gtNotificationMessage) {

    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gtNotificationMessage) {

    }
}
