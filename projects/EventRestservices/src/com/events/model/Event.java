package com.events.model;

public class Event {


	private String data;

	public Event(){

	}

	public Event(String data){
		this.data = data;
	}

	public void setData(String data){
		this.data = data;

	}

	public String getData(){
		return data;
	}

	@Override
	public String toString() {

		return new StringBuffer(" data : ").append(this.data).toString();


	}
}