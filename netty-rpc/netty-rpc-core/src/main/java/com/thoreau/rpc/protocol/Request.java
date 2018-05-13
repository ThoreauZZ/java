package com.thoreau.rpc.protocol;

import lombok.Data;

/**
 * 2018/5/11 下午10:28.
 *
 * @author zhaozhou
 */
@Data
public class Request {
    private long seqNum;
    private String objName;
    private String methodName;
    private Object[] args;
}
