package com.luoromeo.commom.base.entity;

import java.io.Serializable;

public final class Results<T> implements Serializable {
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


    public static final class Result<T> {
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
        private T data;

        public Result() {
            super();
        }

        public static <T> Result<T> newResult() {
            return new Result<T>();
        }

        public static <T> Result<T> newResult(int code, String msg, T data) {
            return new Result<T>(code, msg, data);
        }

        public Result(int code, String msg, T data) {
            super();
           this.code = code;
           this.msg = msg;
           this.data = data;
        }

        public static <T> Result<T> success(String msg) {
            Result<T> success = new Result<>();
            success.setCode(CODE_SUCCESS);
            success.setMsg(msg);
            return success;
        }

        public static <T> Result<T> success(String msg, T data) {
            Result<T> success = new Result<>();
            success.setCode(CODE_SUCCESS);
            success.setMsg(msg);
            success.setData(data);
            return success;
        }

        public static <T> Result<T> failure(String msg) {
            Result<T> success = new Result<>();
            success.setCode(CODE_FAILURE);
            success.setMsg(msg);
            return success;
        }

        public static <T> Result<T> failure(String msg, T data) {
            Result<T> success = new Result<>();
            success.setCode(CODE_FAILURE);
            success.setMsg(msg);
            success.setData(data);
            return success;
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

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public static <T> Builder<T> builder() {
            return new Builder<T>();
        }

        public static class Builder<T> {
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
            private T data;

            private Result.Builder<T> builder;

            private Builder() {
                super();
            }

            public Builder(Result.Builder<T> builder) {
                super();
                this.builder = builder;
            }

            public static <T> Builder<T> builder() {
                return new Builder<T>();
            }

            public Result.Builder<T> begin() {
                return new Result.Builder<T>(this);
            }

            public Builder<T> code(int code) {
                this.code = code;
                return this;
            }

            public Builder<T> msg(String msg) {
                this.msg = msg;
                return this;
            }

            public Builder<T> data(T data) {
                this.data = data;
                return this;
            }

            public Builder<T> success() {
                this.code = Results.CODE_SUCCESS;
                return this;
            }

            public Builder<T> failure() {
                this.code = Results.CODE_REFUSE;
                return this;
            }
        }
    }
}
