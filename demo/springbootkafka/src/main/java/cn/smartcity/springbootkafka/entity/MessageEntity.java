package cn.smartcity.springbootkafka.entity;

import java.util.Date;

/**
 * @author lipoGiser
 * @date 2020/1/3 14:17
 * @Version 0.1
 */
public class MessageEntity {
    private String msg;
    private Date sendTime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
