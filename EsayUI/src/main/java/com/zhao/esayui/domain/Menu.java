package com.zhao.esayui.domain;
/**
 * <p>菜单bean</p>
 * @author henry.zhao
 * @version v1.0 2015年11月19日 上午9:31:27
 */
public class Menu {
	private String	id  ;    
	private String 	pid  ; 
	private String text; 
	private String url; 
	private String seq ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}
