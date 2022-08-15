package com.filmnew.Enity;

import java.util.List;

public class videos {
private String id;
private List<results> results;

public List<results> getResults() {
	return results;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public void setResults(List<results> results) {
	this.results = results;
}

@Override
public String toString() {
	return "videos [id=" + id + ", results=" + results + "]";
}

}
