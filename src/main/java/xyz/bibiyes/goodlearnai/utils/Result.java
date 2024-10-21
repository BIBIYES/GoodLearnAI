package xyz.bibiyes.goodlearnai.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mouse Sakura
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private int code;
    private String msg;
    private Object data;

    public static Result success(String msg) {
        return new Result(200, msg, null);
    }

    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result error(String msg) {
        return new Result(500, msg, null);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }
}
