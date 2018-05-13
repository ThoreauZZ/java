package com.thoreau.rpc.protocol;

import lombok.Data;

import java.util.HashMap;

/**
 * 2018/5/12 上午1:38.
 *
 * @author zhaozhou
 */
@Data
public class RpcContext {
    private Request request;
    private Response response;
    private HashMap<String,Object> attributes = new HashMap<>();
}
