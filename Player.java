/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathquiz;
import java.io.Serializable;
/**
 *
 * @author DoyleS
 */
public class Player implements Serializable{
    private String name;
    private int score;
    private int difficulty;
    private String diff;
    
    public Player(String n, int s, int d, String df){
        this.name = n;
        this.score = s;
        this.difficulty = d;
        this.diff = df;
    }
    
    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getDifficulty(){
        if((difficulty==1)||(diff.equals("Easy")))
            return "Easy";
        else if((difficulty==10)||(diff.equals("Medium")))
            return "Medium";
        else
            return "Hard";
    }
}
