package com.zhao.esayui.domain.page;

public class UserPage {
	private int page;
	private int rows;
	private int pageSize;
	private int pageIndex;
	private String username;
	private String createtimeStart;
	private String createtimeEnd;
	private String updatetimeStart;
	private String updatetimeEnd;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		pageIndex=(page-1)*rows;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.pageSize=rows;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatetimeStart() {
		return createtimeStart;
	}
	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart = createtimeStart;
	}
	public String getCreatetimeEnd() {
		return createtimeEnd;
	}
	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}
	public String getUpdatetimeStart() {
		return updatetimeStart;
	}
	public void setUpdatetimeStart(String updatetimeStart) {
		this.updatetimeStart = updatetimeStart;
	}
	public String getUpdatetimeEnd() {
		return updatetimeEnd;
	}
	public void setUpdatetimeEnd(String updatetimeEnd) {
		this.updatetimeEnd = updatetimeEnd;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	
	
}
