package com.thoreau.netty.client;

import java.io.Serializable;

/**
 * 2018/5/14 下午12:30.
 *
 * @author zhaozhou
 * @date 2018/05/14
 */

public class Response implements Serializable {
    private long requestId;
    private Object data;

    public long getRequestId() {
        return requestId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }
}
