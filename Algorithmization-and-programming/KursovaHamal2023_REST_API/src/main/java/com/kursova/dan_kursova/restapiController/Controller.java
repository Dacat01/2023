package com.kursova.dan_kursova.restapiController;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kursova.dan_kursova.restapiModel.GameModel;
import com.kursova.dan_kursova.restapiService.GameService;
import com.kursova.dan_kursova.restapiModel.UserModel;
import com.kursova.dan_kursova.restapiService.UserService;


@RestController
public class Controller {
 
  //to call PostServiceImpl() method we need to create object of parent i.e PostService.java
  @Autowired
  private GameService gameService;
  @Autowired
  private UserService userService;

  //---------------GET MAPPING FOR GAMES-----------------------------------
  @GetMapping("/")
  public String test() {
    return "api is working fine";
  }
 

  @GetMapping("/game")  // to call use http://localhost:8080/game?name=Hades   ?id=1
  public List<GameModel>  getGameByIdOrName(@RequestParam(required = false) Integer id,@RequestParam(required = false) String name) throws Exception{
    //calling child class getPosts() method of PostServiceImpl using postService(parent).
    return this.gameService.getGameBy(id,name);
  }

 
  
  @GetMapping("/games")
  public List<GameModel> getGames(){
    //calling child class getPosts() method of PostServiceImpl using postService(parent).
    return this.gameService.getGames();
  }

//-------------------POST MAPPING FOR GAMES-------------------------------
 @PostMapping("/game/")
  public String AddGame(@RequestParam Integer id,@RequestParam String name,@RequestParam String developer) throws Exception{
    return this.gameService.addGame(id,name,developer);

  }

//-------------------DELETE MAPPING FOR GAMES-------------------------------
  @DeleteMapping("/game")
  public String deleteGame(@RequestParam Integer id) throws IOException {
    return this.gameService.deleteById(id);
  }
//-------------------------------------------------------------------------

//---------------GET MAPPING FOR USERS-----------------------------------
  @GetMapping("/user")  
  public List<UserModel>  getUserByIdOrName(@RequestParam(required = false) Integer id,@RequestParam(required = false) String userId) throws Exception{
    //calling child class getPosts() method of PostServiceImpl using postService(parent).
    return this.userService.getUsersBy(id,userId);
  }

  @GetMapping("/users")
  public List<UserModel> getUsers(){
    //calling child class getPosts() method of PostServiceImpl using postService(parent).
    return this.userService.getUsers();
  }

//-------------------POST MAPPING FOR USERS-------------------------------
 @PostMapping("/user/")
  public String AddUser(@RequestParam Integer id,@RequestParam String userId,@RequestParam String ownedGames,@RequestParam String totalPlayTimeHrs) throws Exception{
    return this.userService.addUser(id,userId,ownedGames,totalPlayTimeHrs);

  }

//-------------------DELETE MAPPING FOR USERS-------------------------------
  @DeleteMapping("/user")
  public String deleteUser(@RequestParam Integer id) throws IOException {
    return this.userService.deleteById(id);
  }
//-------------------------------------------------------------------------

}