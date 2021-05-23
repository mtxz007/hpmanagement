package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Page {
	private int pageSize;			//ÿҳ������
	private int pageCount;			//ҳ������
	private int count;				//��������
	private int currentPage;		//��ǰҳ
	private ArrayList<HashMap<String,Object>> list = null;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public ArrayList<HashMap<String, Object>> getList() {
		return list;
	}
	public void setList(ArrayList<HashMap<String, Object>> list) {
		this.list = list;
	}
}
