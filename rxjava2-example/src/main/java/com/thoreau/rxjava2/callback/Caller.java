package com.thoreau.rxjava2.callback;

/**
 * 2018/6/1 下午10:30.
 *
 * @author zhaozhou
 */
	public class Caller {
	    private CallBack callBack;

	    public Caller(CallBack callBack) {
	        this.callBack = callBack;
	    }
	    public void doSomething(){
	        System.out.println("do somethings...");
	        callBack.call();
	    }
	}
