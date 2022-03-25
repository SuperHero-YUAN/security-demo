package cn.yuanj.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YuanJ
 * @date 2022/3/19 18:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    private int code;
    private String msg;
    private T data;

    public static Result success() {
        return success(SUCCESS, "操作成功", null);
    }

    public static <T> Result success(T data) {
        return success(SUCCESS, "操作成功", data);
    }

    public static Result success(String msg) {
        return success(SUCCESS, msg, null);
    }

    public static <T> Result success(String msg, T data) {
        return success(SUCCESS, msg, data);
    }

    public static Result success(int code, String msg) {
        return success(code, msg, null);
    }

    public static <T> Result success(int code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    public static Result fail() {
        return success(FAIL, "操作失败", null);
    }

    public static <T> Result fail(T data) {
        return success(FAIL, "操作失败", data);
    }

    public static Result fail(String msg) {
        return success(FAIL, msg, null);
    }

    public static <T> Result fail(String msg, T data) {
        return success(FAIL, msg, data);
    }

    public static Result fail(int code, String msg) {
        return success(code, msg, null);
    }

    public static <T> Result fail(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }
}
