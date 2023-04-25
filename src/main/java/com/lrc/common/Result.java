package com.lrc.common;

/**
 * 统一返回前端结果类
 */
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    /**
     * 无参的成功返回
     * @return
     */
    public static Result success(){
        Result result = new Result<>();
        result.setCode(ResultCode.SUCCESS.code);
        result.setMsg(ResultCode.SUCCESS.msg);
        return result;
    }

    /**
     * 带参的成功返回
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T data){
        Result result = new Result<>(data);
        result.setCode(ResultCode.SUCCESS.code);
        result.setMsg(ResultCode.SUCCESS.msg);
        return result;
    }

    /**
     * 无参的失败返回
     * @return
     */
    public static Result error(){
        Result result = new Result<>();
        result.setCode(ResultCode.ERROR.code);
        result.setMsg(ResultCode.ERROR.msg);
        return result;
    }

    /**
     * 带错误提示的失败返回
     * @param code
     * @param msg
     * @return
     */
    public static Result error(String code,String msg){
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
