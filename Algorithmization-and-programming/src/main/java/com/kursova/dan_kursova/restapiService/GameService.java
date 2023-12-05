package com.kursova.dan_kursova.restapiService;

import java.io.IOException;
import java.util.List;
import com.kursova.dan_kursova.restapiModel.GameModel;

public interface GameService {
  public List<GameModel> getGames();
  public List<GameModel> getGameBy(Integer id,String name) throws Exception ;
  public String addGame(Integer id,String name,String developer) throws Exception ;
  public String deleteById(Integer id) throws IOException;
 
}
