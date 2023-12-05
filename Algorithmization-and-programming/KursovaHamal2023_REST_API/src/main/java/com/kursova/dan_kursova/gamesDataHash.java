package com.kursova.dan_kursova;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.opencsv.CSVReader;

public class gamesDataHash {
        public MultiValueHashMap<Integer, String> gamesDataHashmap = new MultiValueHashMap<>();

      public void gamesDataHashmapInit () throws IOException
      {  
        gamesDataHashmap.clear();
       
//-----------------Reading game data from file to Hash map  ------------------------------------------------
  
        String str = System.getProperty("user.dir");
		str = str.replace("\\","\\\\");

		File gamesFile = new File(str+"\\csvFiles\\gamesData.csv");
    //----------------Check if games data exist-----------------------------------
        if(!gamesFile.isFile())
        {
            System.out.println("File does not exist: " + gamesFile );
            throw new FileNotFoundException();
        }
        else{
            System.out.println("exist");
        }

    
    //-------------------------------------------------------------------------   
       FileReader filereader = new FileReader(gamesFile); 
  
        // create csvReader object passing 
        // file reader as a parameter 
        CSVReader csvReader = new CSVReader(filereader); 
        String[] nextRecord; 
        Integer Key;
        // we are going to read data line by line 
        csvReader.readNext();
        while ((nextRecord = csvReader.readNext()) != null) { 
            Key=-1;   
            for (String cell : nextRecord) { 
                if(Key==-1)
                    Key = Integer.valueOf(cell);
                gamesDataHashmap.put(Key,cell);
                System.out.print(cell + "\t"); 

            } 
             
        } 
        System.out.print("\n------------------------------------------------------------\n"); 
    
    }



    public String put(Integer id,String name,String developer) throws IOException 
    {
        String str = System.getProperty("user.dir");    
		str = str.replace("\\","\\\\");
		File gamesFile = new File(str+"\\csvFiles\\gamesData.csv");     
    //-------------------------------------------------------------------------   
  
       for ( Integer key : gamesDataHashmap.keySet() ) { 
            if(id.equals(key)){ //check if entity with id already exist, update data in it
            replaceGame(gamesFile,id,name,developer);
            return "element with id: "+id+" was updated successfully";
            }
        } 

        //------------append data to file----------------------
        FileWriter filewriter = new FileWriter(gamesFile,true); //if not exist write to an end of the file
        filewriter.write("\n"+id+","+name+","+developer); 
        filewriter.close();
        return "new data was added successfully";
    }

    public List<String> get(Integer key) {
        return gamesDataHashmap.get(key);
    }

    public Set<Integer> keySet() {
        return gamesDataHashmap.keySet();
    }

     public String remove(Integer id) throws IOException {
        //find row with id which needs to be deleted, add new data than add rest of the hash map and rewrite file with correct data
        List<String> strList;
        strList = new ArrayList<>();
        List<String> tmp;
        for ( Integer key : gamesDataHashmap.keySet() )
        {
            if(id.equals(key)){}
            else
            {
                tmp = gamesDataHashmap.get(key);
                for(String a:tmp)
                    strList.add(a);
            }
        }
        String str = System.getProperty("user.dir");    
		str = str.replace("\\","\\\\");
		File gamesFile = new File(str+"\\csvFiles\\gamesData.csv");  
        rewriteFile(gamesFile,strList);
        return "element with id: "+id+" was deleted successfully";
      }
 
      
    private void replaceGame(File file,Integer id,String name,String developer)throws IOException
    {
        //find row with id which needs to be replaced, add new data than add rest of the hash map and rewrite file with correct data
        List<String> strList;
        strList = new ArrayList<>();
        List<String> tmp;
        for ( Integer key : gamesDataHashmap.keySet() )
        {
            if(id.equals(key))  
            {   
                System.out.print("id is equal"+"\n");
                strList.add(id.toString());
                strList.add(name);
                strList.add(developer);
            }
            else
            {
                tmp = gamesDataHashmap.get(key);
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
        String atributes="id,name,developer";

        FileWriter filewriter = new FileWriter(file,false); //rewriting data in file
        filewriter.write(atributes); //write attributes
        for(String s:strList)
        {
        if(i==2)        //write in file 
            filewriter.write(s);
        else if (i==0)
            filewriter.write("\n"+s+",");
        else
            filewriter.write(s+",");
        i++;
        if(i==3)
            i=0;
        }
        filewriter.close();

      }
}
