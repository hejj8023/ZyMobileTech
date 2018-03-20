package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.ndebuger.GlobalConst;

/**
 * Created by example on 2018/3/20.
 */

public class CommonConnManager {
    protected final Context mContext;
    protected final Handler mH;

    public CommonConnManager(Context context, Handler handler) {
        this.mContext = context;
        this.mH = handler;
    }

    protected void notifyUIByMsgSend(String msgStr) {
        Message message = Message.obtain();
        message.what = GlobalConst.UPDATE_SEND_MSG;
        message.obj = msgStr;
        mH.sendMessage(message);
    }

    protected void notifyUIByMsgRec(String msgStr) {
        Message message = Message.obtain();
        message.what = GlobalConst.UPDATE_RECE_MSG;
        message.obj = msgStr;
        mH.sendMessage(message);
    }
    protected void notifyUIByMsgConnClient(String msgStr) {
        Message message = Message.obtain();
        message.what = GlobalConst.UPDATE_CONNECT_CLIENT;
        message.obj = msgStr;
        mH.sendMessage(message);
    }
}
