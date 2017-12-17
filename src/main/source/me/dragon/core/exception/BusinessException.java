package me.dragon.core.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常描述
     */
    private String message;

    /**
     * 构造异常
     *
     * @param code    异常码
     * @param message 异常描述
     */
    public BusinessException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造异常
     *
     * @param code    异常码
     * @param message 异常描述
     */
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}