package com.kursova.dan_kursova.restapiModel;

import org.springframework.stereotype.Repository;

@Repository
public class UserModel {
  //adding fields
	private int id;
	private String userId;
	private String ownedGames;
	private String totalPlayTimeHrs;
  //adding super constructor
  public UserModel() {
  super();
  // TODO Auto-generated constructor stub
  }

  //adding parameterized constructor
  public UserModel(int id,String userId,String ownedGames,String totalPlayTimeHrs) {
	super();
	this.id = id;
	this.userId = userId;
	this.ownedGames = ownedGames;
	this.totalPlayTimeHrs = totalPlayTimeHrs;
  }

  //adding getters and setters
	public int getId() {
	   return id;
	}
	public void setId(int id) {
	   this.id = id;
	}
	public String getUserId() {
		return userId;    
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOwnedGames() {
		return ownedGames;
	}
	public void setOwnedGames(String ownedGames) {
		this.ownedGames = ownedGames;
	}
	public String getTotalPlayTimeHrs() {
		return totalPlayTimeHrs;
	}
	public void setTotalPlayTimeHrs(String totalPlayTimeHrs) {
		this.totalPlayTimeHrs = totalPlayTimeHrs;
	}

	//adding to toString() method
	@Override
	public String toString() {
		return "Game [id=" + id + ", userId=" + userId + ", owned games=" + ownedGames +", total playtime"+ totalPlayTimeHrs +"]";
	}
}
