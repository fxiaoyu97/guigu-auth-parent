package com.sangeng.system.exception;

import com.sangeng.common.result.ResultCodeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: calos
 * @create: 2023-11-18 16:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanException extends RuntimeException {

    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * 
     * @param code
     * @param message
     */
    public SanException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * 
     * @param resultCodeEnum
     */
    public SanException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "SanException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
