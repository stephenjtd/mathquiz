/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathquiz;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author DoyleS
 */
public class fileAccess {
    
    public fileAccess(){}
    
       /*public void saveData(ArrayList<Player> players){
        try{
            //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("scores.txt", true)));
            PrintWriter writer = new PrintWriter("scores.txt", "UTF8");
            for(int i = 0; i < players.size(); i++){
                //writer.println();
                writer.print(players.get(i).getName());
                writer.print("|");
                writer.print(players.get(i).getDifficulty());
                writer.print("|");
                writer.print(players.get(i).getScore());
                writer.print("|");
                writer.println();
            }
            
            writer.close();
            System.out.println("High Scores have been saved.");
        }
        catch (Exception e){
            System.out.println("No data to write.");
        }
    }//saveData*/
    
    /*public ArrayList<Player> loadData(){
        ArrayList<Player> players = new ArrayList<Player>();
        int lineCount = 0;
      	try{
            BufferedReader br = new BufferedReader(new FileReader("scores.txt"));
            boolean b = true;
            String line="";  
            while(line!=null){
            line = br.readLine();
            //System.out.println(">"+line+"<");
            if(line==(null)){
                break;
            }
            else if(line==""){
                continue;
            }
            else{
            int sIndex = line.indexOf('|');
            String name = line.substring(0, sIndex);
            String remaining = line.substring(sIndex+1);
            sIndex = remaining.indexOf('|');
            String difficulty = remaining.substring(0, sIndex);
            int score = Integer.parseInt(remaining.substring(sIndex+1, remaining.length()-1));
            Player p = new Player(name, score, 0, difficulty);
            players.add(p);
            
            //line=br.readLine();
            }
            }//while
        }
        catch(FileNotFoundException e){
            System.out.println("Input file not found.");
  	}
        catch(NullPointerException e){
            System.out.println("Null Pointer Exception.");
        }
        catch(IOException e){
            System.out.println("IO Exception.");
        }
        catch(Exception e){
            System.out.println("Other Exception.");
        }
        
        return players;
    }//loadData*/
    
    public ArrayList<Player> sortList(ArrayList<Player> players){
        ArrayList<Player> sorted = new ArrayList<Player>();
        while(players.size()>0){
        int highest = 0;
        int highIndex = 0;    
        for (int i = 0; i < players.size(); ++i){
            if(players.get(i).getScore() > highest){
                highest = players.get(i).getScore();
                highIndex = i;
            }
        }
        sorted.add(players.get(highIndex));
        players.remove(highIndex);
        if(sorted.size()==10)
            break;
        }
        //saveData(sorted);
        updateScoreFile(sorted);
        return sorted;
    }
    
        public ArrayList<Player> loadScoreFile() 
    {
        ObjectOutputStream outputStream = null;
        ArrayList<Player> scores = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("scores.txt"));
            scores = (ArrayList<Player>) inputStream.readObject();
        } 
        catch (FileNotFoundException e) {
            System.out.println("[Load] FNF Error: " + e.getMessage());
            scores = new ArrayList<Player>();
        } 
        catch (IOException e) {
            System.out.println("[Load] IO Error: empty  " + e.getMessage());
            scores = new ArrayList<Player>();
        } 
        catch (ClassNotFoundException e) {
            System.out.println("[Load] CNF Error: " + e.getMessage());
        } 
        finally {
                try {
                        if (outputStream != null) 
                        {
                            outputStream.flush();
                            outputStream.close();
                        }
                    } 
                catch (IOException e) 
                    {
                    System.out.println("[Load] IO Error: " + e.getMessage());
                    }
                }
        return scores;
    }
    
    public void updateScoreFile(ArrayList<Player> players) 
    {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("scores.txt"));
            outputStream.writeObject(players);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } 
        catch (IOException e) 
        {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (outputStream != null) 
                {
                    outputStream.flush();
                    outputStream.close();
                }
            } 
            catch (IOException e) 
            {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
}
