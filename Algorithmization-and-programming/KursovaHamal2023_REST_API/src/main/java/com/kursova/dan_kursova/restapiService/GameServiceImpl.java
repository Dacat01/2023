package com.kursova.dan_kursova.restapiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.stereotype.Service;

import com.kursova.dan_kursova.gamesDataHash;
import com.kursova.dan_kursova.restapiModel.GameModel;

@Service
public class GameServiceImpl implements GameService {
   //storing data temporary…
  List<String> strList;
  List<GameModel> list;
  int i = 0;
 private gamesDataHash gamesData = new gamesDataHash();
  //adding constructor of List<Post>
   public GameServiceImpl() throws IOException{
 //   list.clear();
    gamesData.gamesDataHashmapInit();
 // gamesData.gamesDataHashmapInit();
      // adding data into this constructor.
  list = new ArrayList<>();
 // list.clear();
    for ( Integer key : gamesData.keySet() ) {
     strList = gamesData.get(key);
     list.add(new GameModel(key,strList.get(1),strList.get(2)));
    }
   //System.out.print("--------------------------------------------------------");
 //  System.out.print(gamesData.replace(1,"testHades","testdevel"));
 // list.add(new GameModel(1, "first post", "Hey, this is my first post"));
 // list.add(new GameModel(2, "second post", "Hey,this is my second post"));
   }
 
 
  @Override //get list of games with specific name or just by id
  public  List<GameModel> getGameBy (Integer id,String name) throws Exception {
    int i =0;
    List<GameModel> tmplist;
    tmplist = new ArrayList<>();
    GameModel tmpGameModel = new GameModel();

    if(name!=null){   //find all entities with given name
    while (i <list.size()) {
      tmpGameModel = list.get(i);
      if(name.equals(tmpGameModel.getName()))
        tmplist.add(tmpGameModel);
      i++;
      }
      return tmplist;
    }
    else{   //find by id
      while (i <list.size()) {
      tmpGameModel = list.get(i);
      if(id.equals(tmpGameModel.getId()))
      {
        tmplist.add(list.get(i));
        return tmplist;
      }
      i++;
      }
    }
  throw new Exception("Game with given parametres was not found" );
  } 
 

  @Override
  public List<GameModel> getGames() {
  return list;
  } 

  @Override // add new game and rewrite hashmap with new data
  public String addGame(Integer id,String name,String developer) throws Exception 
  {
    String str = gamesData.put(id,name,developer);
    gamesData.gamesDataHashmapInit();
    // gamesData.gamesDataHashmapInit();
    // adding data into this constructor.
      list = new ArrayList<>();
    // list.clear();
        for ( Integer key : gamesData.keySet() ) {
        strList = gamesData.get(key);
        list.add(new GameModel(key,strList.get(1),strList.get(2)));
        }
      return str;
  }

  //  --------------------------------------------------------------------------
  @Override
  public String deleteById(Integer id) throws IOException
  {
    String str = gamesData.remove(id);
    gamesData.gamesDataHashmapInit();

     list = new ArrayList<>();
    // list.clear();
        for ( Integer key : gamesData.keySet() ) {
        strList = gamesData.get(key);
        list.add(new GameModel(key,strList.get(1),strList.get(2)));
        }
    return str;

  }

}
