package com.ProjectBackend.model.asset;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AddAssetInfo {

	private String name;
	private Map<String, Object> params;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
