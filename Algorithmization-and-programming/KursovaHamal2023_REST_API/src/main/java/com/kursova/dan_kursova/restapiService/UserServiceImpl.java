package com.kursova.dan_kursova.restapiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.kursova.dan_kursova.usersDataHash;
import com.kursova.dan_kursova.restapiModel.UserModel;

@Service
public class UserServiceImpl implements UserService{
   //storing data temporary…
  List<String> strList;
  List<UserModel> list;
  int i = 0;
 private usersDataHash usersData = new usersDataHash();
  //adding constructor of List<Post>
   public UserServiceImpl() throws IOException{
 //   list.clear();
    usersData.usersDataHashmapInit();
 // gamesData.gamesDataHashmapInit();
      // adding data into this constructor.
  list = new ArrayList<>();
 // list.clear();
    for ( Integer key : usersData.keySet() ) {
     strList = usersData.get(key);
     list.add(new UserModel(key,strList.get(1),strList.get(2),strList.get(3)));
    }
   }
 
 
  @Override //get list of games with specific name or just by id
  public  List<UserModel> getUsersBy (Integer id,String userId) throws Exception {
    int i =0;
    List<UserModel> tmplist;
    tmplist = new ArrayList<>();
    UserModel tmpUserModel = new UserModel();

    if(userId!=null){   //find all entities with given name
    while (i <list.size()) {
      tmpUserModel = list.get(i);
      if(userId.equals(tmpUserModel.getUserId()))
        tmplist.add(tmpUserModel);
      i++;
      }
      return tmplist;
    }
    else{   //find by id
      while (i <list.size()) {
      tmpUserModel = list.get(i);
      if(id.equals(tmpUserModel.getId()))
      {
        tmplist.add(list.get(i));
        return tmplist;
      }
      i++;
      }
    }
  throw new Exception("User was not found or does not exist" );
  } 
 

  @Override
  public List<UserModel> getUsers() {
  return list;
  } 

  @Override // add new game and rewrite hashmap with new data
  public String addUser(Integer id,String userId,String ownedGames, String totalPlayTimeHrs) throws Exception 
  {
    String str = usersData.put(id,userId,ownedGames,totalPlayTimeHrs);
    usersData.usersDataHashmapInit();
    // gamesData.gamesDataHashmapInit();
    // adding data into this constructor.
      list = new ArrayList<>();
    // list.clear();
        for ( Integer key : usersData.keySet() ) {
        strList = usersData.get(key);
        list.add(new UserModel(key,strList.get(1),strList.get(2),strList.get(2)));
        }
      return str;
  }

  //  --------------------------------------------------------------------------
  @Override
  public String deleteById(Integer id) throws IOException
  {
    String str = usersData.remove(id);
    usersData.usersDataHashmapInit();

     list = new ArrayList<>();
    // list.clear();
        for ( Integer key : usersData.keySet() ) {
        strList = usersData.get(key);
        list.add(new UserModel(key,strList.get(1),strList.get(2),strList.get(3)));
        }
    return str;

  }

}
