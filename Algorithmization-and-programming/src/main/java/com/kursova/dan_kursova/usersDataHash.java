package com.kursova.dan_kursova;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.converters.IntegerConverter;

import com.opencsv.CSVReader;

public class usersDataHash {
        public MultiValueHashMap<Integer, String> usersDataHashmap = new MultiValueHashMap<>();

        public void usersDataHashmapInit () throws IOException{
          usersDataHashmap.clear(); 
          File[] emptyArr={};
          File[] matchingFiles = findAllCsvFiles();
          Integer Key=-1;     
       //-----------------Reading game data from file to Hash map  ----------------------
          if(!matchingFiles.equals(emptyArr)){   //if there are user files
            for(File userFile:matchingFiles){
              FileReader filereader = new FileReader(userFile); 
               // create csvReader object passing 
              // file reader as a parameter 
              CSVReader csvReader = new CSVReader(filereader); 
              String[] nextRecord; 
              ArrayList<Integer> tmplist = new ArrayList<Integer>();
              // we are going to read data line by line 
              csvReader.readNext(); //skip first line with attributes
              //if id already exist in hash map skip it
              while ((nextRecord = csvReader.readNext()) != null) 
              { 
                    
                  for (String cell : nextRecord) { 
                    if(Key==-1)
                      Key = Integer.valueOf(cell);
                    if(tmplist.contains(Key)==false)
                    {
                    usersDataHashmap.put(Key,cell);
 //                   System.out.print(cell + "\t"); 
                    }  

                         
                  } 
              tmplist.add(Key); 
              Key=-1;    
              } 

            }
         }else{
            System.out.print("User data files were not found");
            // throw error
          }
 
    }

//---------------Hash map methods-----------------------------------
        public String put(Integer id,String userId,String ownedGames, String totalPlayTimeHrs) throws IOException 
        {
          DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
          Date date = new Date();
          CreateFile();
          String str = System.getProperty("user.dir");    
          str = str.replace("\\","\\\\");
          File usersFile = new File(str+"\\csvFiles\\usersData"+dateFormat.format(date)+".csv");     
          //-----------------update data------------------------------------------   
        
             for ( Integer key : usersDataHashmap.keySet() ) { 
                  System.out.println(key +"   " +id);
                  if(id.equals(key)){ //check if entity with id already exist, update data in it
                  replaceUser(usersFile,id,userId,ownedGames,totalPlayTimeHrs);
                  return "element with id: "+id+" was updated successfully";
                  }
              } 
      
            //------------append data to file----------------------
            FileWriter filewriter = new FileWriter(usersFile,true); //if not exist write to an end of the file

            filewriter.write("\n"+id+","+userId+","+ownedGames+","+totalPlayTimeHrs); 
            filewriter.close();
            
            
            return "new data was added successfully";
        }


        public List<String> get(Integer key) {
            return usersDataHashmap.get(key);
        }

        public String remove(Integer id) throws IOException {
               //find row with id which needs to be deleted, add new data than add rest of the hash map and rewrite file with correct data
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        
        List<String> strList;
        strList = new ArrayList<>();
        List<String> tmp;
        CreateFile(); //create user file with current date
        for ( Integer key : usersDataHashmap.keySet() )
        {
            if(id.equals(key)){}
            else
            {
                tmp = usersDataHashmap.get(key);
                for(String a:tmp)
                    strList.add(a);
            }
        }
        String str = System.getProperty("user.dir");    
		    str = str.replace("\\","\\\\");
		    File gamesFile = new File(str+"\\csvFiles\\usersData"+dateFormat.format(date)+".csv");  
        rewriteFile(gamesFile,strList);
        return "element with id: "+id+" was deleted successfully";
        }

        public Set<Integer> keySet() {
        return usersDataHashmap.keySet();
        }



//--------------------------------------------------------------------
 private void replaceUser(File file,Integer id,String userId,String ownedGames,String totalPlayTimeHrs)throws IOException
    {
        //find row with id which needs to be replaced, add new data than add rest of the hash map and rewrite file with correct data
        List<String> strList;
        strList = new ArrayList<>();
        List<String> tmp;
        for ( Integer key : usersDataHashmap.keySet() )
        {
            if(id.equals(key))  
            {   
                System.out.print("id is equal"+"\n");
                strList.add(id.toString());
                strList.add(userId);
                strList.add(ownedGames);
                strList.add(totalPlayTimeHrs);
            }
            else
            {
                tmp = usersDataHashmap.get(key);
                for(String a:tmp)
                    strList.add(a);

            }
        }
        //--------------Filling file with new data--------------     
        rewriteFile(file,strList);

    }


    private void rewriteFile (File file,List<String> strList) throws IOException
    {
      int i =0;
      String atributes="id,userId,ownedGames,totalPlayTimeHrs";
      FileWriter filewriter = new FileWriter(file,false); //rewriting data in file
      filewriter.write(atributes); //write attributes
      for(String s:strList)
      {
      if(i==3)        //write in file 
          filewriter.write(s);
      else if (i==0)
          filewriter.write("\n"+s+",");
      else
          filewriter.write(s+",");
      i++;
      if(i==4)
          i=0;
      }
      filewriter.close();

    }

    public  void CreateFile() {     
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String atributes="id,userId,ownedGames,totalPlayTimeHrs";
        try {
        String str = System.getProperty("user.dir");
        str = str.replace("\\","\\\\");


	  	File file = new File(str+"\\csvFiles\\usersData"+dateFormat.format(date)+".csv");

        if (file.createNewFile()) 
          System.out.println("File created: " + file.getName());
         else 
          System.out.println("File already exists.");

        FileWriter filewriter = new FileWriter(file); 
        filewriter.write(atributes); //write attributes
        filewriter.close();

      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    
 
    }


//-----------------------------------------------------------
public  File[]  findAllCsvFiles() throws FileNotFoundException 
    {
          DateFormat shortDateFormat = new SimpleDateFormat("yyyyMM");
          Date shortDate = new Date();
          String strShortDate = shortDateFormat.format(shortDate);  
          //------------List all files in directory created in current month---------------------

          String str = System.getProperty("user.dir");
          str = str.replace("\\","\\\\");
          File usersFile = new File(str+"\\csvFiles");
          
          File[] matchingFiles = usersFile.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
              return name.startsWith("usersData"+strShortDate) && name.endsWith(".csv");
            }
          });
       
        return matchingFiles;

    }

}



