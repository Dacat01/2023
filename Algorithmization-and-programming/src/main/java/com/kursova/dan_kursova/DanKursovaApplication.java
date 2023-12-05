package com.kursova.dan_kursova;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kursova.dan_kursova.restapiModel.GameModel;
import com.opencsv.CSVReader;



@SpringBootApplication
public class DanKursovaApplication {

	public  static void main(String[] args) throws Exception {
		SpringApplication.run(DanKursovaApplication.class, args);

  //      gamesDataHash g = new gamesDataHash();
  //      usersDataHash u = new usersDataHash();

        	
    }
}