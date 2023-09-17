package com.rick.myschool2.util;

import lombok.Data;


/**
 * 结果类
 * @param <T> 泛型
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    protected static <T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return  result;
    }

    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return  result;
    }


    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    /*操作成功*/
    public static <T> Result<T> ok(T data){
        Result<T> result = build(data);
        return  build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 操作失败
     */
    public static<T> Result<T> fail(){
        return Result.fail(null);
    }
    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }



    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }
    public boolean isOk() {
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }



}
