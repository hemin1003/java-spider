package com.gs.spider.model.async;

/**
 * CallbackMsg
 */
public class CallbackMsg extends BaseMsg {

    public CallbackMsg(String clientId) {
        super(clientId);
        this.setType(MsgType.CALLBACK);
    }
}
