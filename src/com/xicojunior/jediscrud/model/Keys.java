package com.xicojunior.jediscrud.model;

public enum Keys {

	USER_ALL("user:all"),
	USER_DATA("user:%s:data"),
	USER_IDS("user:ids");
	
	private String key;
	Keys(String key){
		this.key = key;
	}
	
	public String key(){
		return key;
	}
	
	public String formated(String... value){
		return String.format(key, value);
	}
}
