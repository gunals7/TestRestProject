package com.events.model;

public class Event {


	private String data;
	
	private int id;

	public Event(){

	}

	public Event(int id, String data){
		this.id = id;
		this.data = data;
	}

	public void setData(String data){
		this.data = data;

	}

	public String getData(){
		return data;
	}

	public void setId(int id){
		this.id = id;

	}

	public int getId(){
		return id;
	}
	@Override
	public String toString() {

		return new StringBuffer(" data : ").append(this.data).append(this.id).toString();


	}
}