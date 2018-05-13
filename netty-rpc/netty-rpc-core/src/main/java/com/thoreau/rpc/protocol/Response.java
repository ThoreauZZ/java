package com.thoreau.rpc.protocol;

import lombok.Data;

/**
 * 2018/5/12 上午1:39.
 *
 * @author zhaozhou
 */
@Data
public class Response {
    private long seqNum;
    private String objName;
    private String methodName;
    private Object result;
}
