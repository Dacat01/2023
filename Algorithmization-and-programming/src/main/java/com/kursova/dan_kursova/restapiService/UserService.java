package com.kursova.dan_kursova.restapiService;

import java.io.IOException;
import java.util.List;


import com.kursova.dan_kursova.restapiModel.UserModel;

public interface UserService {
  public List<UserModel> getUsers();
  public List<UserModel> getUsersBy(Integer id,String userId) throws Exception ;
  public String addUser(Integer id,String userId,String ownedGames, String totalPlayTimeHrs) throws Exception ;
  public String deleteById(Integer id) throws IOException;
}