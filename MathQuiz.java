/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathquiz;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author DoyleS
 */
public class MathQuiz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //consoleApp();
        MathGUI mg = new MathGUI();
        mg.main();
    }
    
    public static void consoleApp(){
        
        MathQuiz mq = new MathQuiz();
        Scanner sc = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<Player>();
        
        System.out.println("***********\n*MATH QUIZ*\n***********");
        w:
        while(true){
        //options:
        //1. display high scores
        System.out.println("1. Display High Scores");
        //2. start a quiz
        System.out.println("2. Start a Quiz");
        System.out.println("3. Exit");
        String choice = sc.next();
        fileAccess fa = new fileAccess();
        //players = fa.loadData();
        
        players = fa.loadScoreFile();
        
        switch(choice){
            case"1":{
                //enter show high scores method
                mq.highScores(players);
                break;
            }
            case"2":{
                //enter quiz method
                Player p = mq.startQuiz();
                players.add(p);
                //sort and save updated high scores to file
                fa.sortList(players);
                break;
            }
            case"3":{
                //exit program
                break w;
            }
            default:{
                System.out.println("Not a valid choice.  Try again.");
                break;
            }
        }
        }//while
    }//main
    
    public Player startQuiz(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your name.");
        String name = sc.nextLine();
        int score = 0;
        Player p;
        w:
        while(true){
        System.out.println("Enter difficulty level:\n1. Easy\n2. Medium\n3. Hard");
        String difficulty = sc.nextLine();
        switch(difficulty){
            case"1":{
                //easy
                p = makeQuestion(1, score, 1, name);
                break w;
            }
            case"2":{
                //medium
                p = makeQuestion(10, score, 1, name);
                break w;
            }
            case"3":{
                //hard
                p = makeQuestion(100, score, 1, name);
                break w;
            }
            default:{
                System.out.println("Not a valid choice.  Try again.");
                break;
            }
        }//switch
        }//while
        return p;
    }//startQuiz
    
    public Player makeQuestion(int difficulty, int score, int question, String name){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        String userAns = "";
        double correctAns = 0;
        //easy questions - single digits
        //medium - two digits
        //hard - three digits
        int lives = 3;
        
        while(lives>0){
        int num1 = r.nextInt(10*difficulty);
        int num2 = r.nextInt(10*difficulty);
        int operator = r.nextInt(4);
        System.out.println("-----------\nQuestion " + question);
        switch(operator){
            case 0:{
                //+
                System.out.println(num1 + " + " + num2 + " =");
                correctAns = (num1 + num2);
                break;
            }
            case 1:{
                //-
                System.out.println(num1 + " - " + num2 + " =");
                correctAns = (num1 - num2);
                break;
            }
            case 2:{
                //*
                System.out.println(num1 + " * " + num2 + " =");
                correctAns = num1 * num2;
                break;
            }
            case 3:{
                //divide
                //for division by 0
                while(num2==0){
                    System.out.println("num2 was 0");
                    num2=r.nextInt(10*difficulty);
                }
                System.out.println(num1 + " / " + num2 + " =");
                correctAns = Math.round(num1 / num2);
                break;
            }
        }//switch
        
        userAns = sc.nextLine();
        //check user answer
        try{
            if(Math.round(Double.parseDouble(userAns))==correctAns){
                //set score based on difficulty level
                if(difficulty==1){
                    score += 1;
                }
                else if(difficulty==10){
                    score+= 2;
                }
                else
                    score+= 4;
                System.out.println("Correct!\nScore: " + score + "\nLives: " + lives);
            }
            else{
                lives--;
                System.out.println("Wrong!\nScore: " + score + "\nLives: " + lives);
            }
        }
        catch(Exception e){
            lives--;
            System.out.println("Wrong!\nScore: " + score + "\nLives: " + lives);        
        }
        question++;
        }//while
        System.out.println("No lives left.  Game Over.");
        Player p = new Player(name, score, difficulty, "");
        //add user name and score to high score table
        return p;
    }//makeQuestion
    
    public void highScores(ArrayList<Player> players){
            
        System.out.println("----------HIGH SCORE TABLE-----------");
        System.out.println("Player\t\tDifficulty\tScore");
        System.out.println("-------------------------------------");
        for(int i = 0; i < 10; ++i){
            if(players.size()==0){
                System.out.println("(Empty)");
                break;
            }
            else if(i>=players.size())
                break;
            else{
                //player is added if score is greater than any of the top 10;
                System.out.println(players.get(i).getName() + "\t\t" + players.get(i).getDifficulty() + "\t\t" + players.get(i).getScore());
            }
        }//for
        System.out.println("-------------------------------------");
        
    }//highScores
}
