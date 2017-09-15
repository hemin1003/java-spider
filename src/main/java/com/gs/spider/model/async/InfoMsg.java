package com.gs.spider.model.async;

/**
 * InfoMsg
 */
public class InfoMsg extends BaseMsg {
    private String info;

    public InfoMsg(String clientId) {
        super(clientId);
        this.setType(MsgType.INFO);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
