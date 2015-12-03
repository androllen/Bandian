package com.cc.model;

public class PItem {

	private String mc1;
	private String mc2;
	private String mt1;
	private String mt2;
	public PItem(String c1,String c2){
		mc1=c1;
		mc2=c2;
	}
	
	public String cString(){
		return mc1;
	}
	
	public String tString(){
		return mc2;
	}	
	
	
}
