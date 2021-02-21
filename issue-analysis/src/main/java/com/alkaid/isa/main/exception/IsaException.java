package com.alkaid.isa.main.exception;

public class IsaException extends Exception {
    /**
     * 代码
     */
    protected int code;

    /**
     * 消息
     */
    protected String businessMessage;

    /**
     * 消息类型
     */
    protected MessageType type;


    public static final int ERROR = -1;

    public static final int ALERT = -2;

    public static final int WARN  = -3;

    public IsaException() {
        super();
        type = MessageType.ERROR;
    }


    public IsaException(String message) {
        super(message);
        type = MessageType.ERROR;
    }

    public IsaException(String message, Throwable e) {
        super(message, e);
        type = MessageType.ERROR;
    }
    /**
     * 异常构造器
     *
     * @param type      异常类型
     * @param code      业务代码
     * @param message   消息
     */
    public IsaException(MessageType type, int code, String message) {
        super(message);
        this.type = type;
        this.code = code;
        this.businessMessage = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBusinessMessage() {
        return businessMessage;
    }

    public void setBusinessMessage(String businessMessage) {
        this.businessMessage = businessMessage;
    }

    /**
     * 消息类型
     */
    public enum MessageType {
        ERROR,
        ALERT
    }
}
