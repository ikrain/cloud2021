package cn.krain.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CC
 * @data 2021/2/1 - 21:56
 */
@Data
@AllArgsConstructor     // 全参构造方法
@NoArgsConstructor      // 无参构造方法
public class CommentResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommentResult(Integer code, String message) {
        this(code,message,null);
    }
}
