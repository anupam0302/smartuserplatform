package com.smartuserplatform.dto;

import java.util.List;

public class PageResponse<T> {
	
	private List<T> content;
	private int page;
	private int size;
	private long totalElement;
	
	public PageResponse(List<T> content, int page, int size, long totalElement) {
		super();
		this.content = content;
		this.page = page;
		this.size = size;
		this.totalElement = totalElement;
	}
	
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}
}
