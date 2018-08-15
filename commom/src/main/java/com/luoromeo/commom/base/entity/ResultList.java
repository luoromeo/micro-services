package com.luoromeo.commom.base.entity;

import java.io.Serializable;
import java.util.List;

public class ResultList<T> implements Serializable {
    /**
     * 成功的消息代码
     */
    public static final int CODE_SUCCESS = 10000;

    /**
     * 失败的消息代码
     */
    public static final int CODE_FAILURE = 10001;

    /**
     * 系统异常代码
     */
    public static final int CODE_EXCEPTION = 10002;

    /**
     * 找不到相关数据代码
     */
    public static final int CODE_DATA_NOT_FOUND = 10003;

    /**
     * 请求被拒绝代码
     */
    public static final int CODE_REFUSE = 10004;

    /**
     * 结果代码
     */
    private int code;

    /**
     * 结果消息
     */
    private String msg;

    /**
     * 返回结果携带的数据
     */
    private Object data;

    /**
     * @description 成功
     * @author luozhanghua
     * @date 2018-04-12 14:52:25
     * @param msg
     * @return
     */
    public static <T> ResultList<T> success(String msg) {
        ResultList success = new ResultList();
        success.setCode(CODE_SUCCESS);
        success.setMsg(msg);
        return success;
    }

    /**
     * @description 成功
     * @author luozhanghua
     * @date 2018-04-12 14:52:42
     * @param msg 返回消息
     * @param data 返回数据
     * @return
     */
    public static <T> ResultList<T> success(String msg, List<T> data) {
        ResultList<T> success = new ResultList<>();
        success.setCode(CODE_SUCCESS);
        success.setMsg(msg);
        success.setData(data);
        return success;
    }

    /**
     * @description 失败
     * @author luozhanghua
     * @date 2018-04-12 14:53:31
     * @param msg 失败返回消息
     * @return
     */
    public static <T> ResultList<T> failure(String msg) {
        ResultList<T> failure = new ResultList<T>();
        failure.setCode(CODE_FAILURE);
        failure.setMsg(msg);
        return failure;
    }

    /**
     * @description 自定义返回结果
     * @author luozhanghua
     * @date 2018-04-12 14:53:54
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static <T> ResultList<T> resultInfo(int code, String msg, List<T> data) {
        ResultList<T> result = new ResultList<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
