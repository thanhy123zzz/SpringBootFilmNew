package com.filmnew.Enity;

import java.util.List;

public class film {
private String page;
private List<results> results;
private int total_pages;
private int total_results;
public List<results> getResults() {
	return results;
}

public void setResults(List<results> results) {
	this.results = results;
}

public int getTotal_pages() {
	return total_pages;
}

public void setTotal_pages(int total_pages) {
	this.total_pages = total_pages;
}

public int getTotal_results() {
	return total_results;
}

public void setTotal_results(int total_results) {
	this.total_results = total_results;
}

public String getPage() {
	return page;
}

public void setPage(String page) {
	this.page = page;
}

@Override
public String toString() {
	return "film [page=" + page + ", results=" + results + ", total_pages=" + total_pages + ", total_results="
			+ total_results + "]";
}

}
