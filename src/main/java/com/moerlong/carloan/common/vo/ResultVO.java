package com.moerlong.carloan.common.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable{


    private Integer status;

    private String msg;

    private T data;

    public void setResult(ErrorCode err){
        this.status = err.getErrCode();
        this.msg = err.getMsg();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T    getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.status).append(",");
        sb.append(this.msg).append(",");
        if(data != null){
            sb.append(data.toString());
        }
        return sb.toString();
    }

    public boolean isSuccess() {
        if (Integer.valueOf(0).equals(status)) {
            return true;
        }
        return false;
    }

    public static <T> ResultVO<T> build(ErrorCode err){
        ResultVO<T> vo = new ResultVO<>();
        vo.setResult(err);
        vo.setData(null);
        return vo;
    }

    public static <T> ResultVO<T> build(ErrorCode err, T data){
        ResultVO<T> vo = new ResultVO<>();
        vo.setResult(err);
        vo.setData(data);
        return vo;
    }

    public static <T> ResultVO<T> build(Integer status, String errMsg, T data){
        ResultVO<T> vo = new ResultVO<>();
        vo.setStatus(status);
        vo.setMsg(errMsg);
        vo.setData(data);
        return vo;
    }

    public static <T> ResultVO<T> build(Integer status, String errMsg){
        ResultVO<T> vo = new ResultVO<>();
        vo.setStatus(status);
        vo.setMsg(errMsg);
        vo.setData(null);
        return vo;
    }
}
