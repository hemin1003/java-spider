package com.gs.spider.model.async;

/**
 * PingMsg
 */
public class PingMsg extends InfoMsg {
    public PingMsg(String clientId) {
        super(clientId);
        this.setType(MsgType.PING);
    }
}
