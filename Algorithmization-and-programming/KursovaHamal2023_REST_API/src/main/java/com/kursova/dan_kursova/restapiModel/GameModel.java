package com.kursova.dan_kursova.restapiModel;

import org.springframework.stereotype.Repository;

import com.kursova.dan_kursova.gamesDataHash;

@Repository
public class GameModel {
  //adding fields
	private int id;
	private String name;
	private String developer;

  //adding super constructor
  public GameModel() {
  super();
  // TODO Auto-generated constructor stub
  }

  //adding parameterized constructor
  public GameModel(int id,String name,String developer) {
	super();
	this.id = id;
	this.name = name;
	this.developer = developer;
  }

  //adding getters and setters
	public int getId() {
	   return id;
	}
	public void setId(int id) {
	   this.id = id;
	}
	public String getName() {
		return name;    
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}



	//adding to toString() method
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", developer=" + developer + "]";
	}
}
