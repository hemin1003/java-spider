package com.gs.spider.model.async;

/**
 * LoginMsg
 */
public class LoginMsg extends BaseMsg {

    public LoginMsg(String clientId) {
        super(clientId);
        this.setType(MsgType.LOGIN);
    }
}
