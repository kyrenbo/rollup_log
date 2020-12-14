package com.ren.model;

public class LogResult<T> {

    private T data;
    private String message;
    private String code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private LogResult() {
    }

    private LogResult(T data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> LogResult<T> success(T data, String message) {
        return new LogResult<>(data, message, "0");
    }

    public static <T> LogResult<T> success(T data) {
        return new LogResult<>(data, "响应成功！！", "0");
    }

    public static <T> LogResult<T> fail(String message, String code) {
        return new LogResult<>(null, message, code);
    }

    public static <T> LogResult<T> fail(String message) {
        return new LogResult<>(null, message, "-1");
    }

}
