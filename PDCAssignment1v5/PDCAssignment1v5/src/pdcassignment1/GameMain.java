package pdcassignment1;

import java.util.Scanner;
/**
 *
 * @author evelt and james
 */

//Run this file to start the program.
public class GameMain 
{
    public static void main(String[] args) 
    {
        int totalQuestionsInQuiz = 20;
        int questionsPerDifficulty = 5;
        Game quizgame;
        GameMain m = new GameMain();
        dirFileBuilder dir = new dirFileBuilder();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("/////////////////////////////////");
        System.out.println("  WHO WANTS TO BE A MILLIONARE! ");
        System.out.println("/////////////////////////////////");
        
        User u = new DefaultUser("Default",1,0);
        String Filename = "";
        String SaveName;
        int score = 0;
        double Money = 0;
        int questionsAnsweredOld = 0;
        int totalQuestionsOld = 0;
        String username ="";
        quizgame = new Game(20,u,0,0,0,0);
        int Age = 0;
        boolean Menu = true;
        int counter = 1;
        
        while(Menu == true) // this menu loop will basically not allow the user to start the game without the user providing some information
        {     
                String in="";
                while(counter >= 0)
                {
                    System.out.println("Find your save game, ");
                    counter++;
                    System.out.println("You have "+counter+" attempts left to find your user folder, If you are new just keep entering the username you wish to use.");
                    System.out.println("what is your username? ");
                    in=scanner.nextLine();
                    
                    boolean dir1 = dir.FindUser(in)== true;
                    counter--;
                    if(dir1 == true) // this checks the condition. If the Folder exists then it creates a infinite loop until a save is found.
                        // it is important that the user does not exit until a question has been awnsered so it saves properly. 
                    {
                        while(dir1 = true)
                        {
                            
                        Filename = m.getSaveInfo();
                        String filepath = "./resources/Users/"+in+"/"+Filename+".txt";
                        LoadGameSave loading = new LoadGameSave(filepath);
                        
                        
                        if(loading.MatchFile() == true)
                        {
                            questionsAnsweredOld = loading.loadNOQA();
                            totalQuestionsOld = loading.loadNOQ();
                            username = loading.LoadUserName();
                            Age = loading.loadAge();
                            score = loading.loadScore();
                            Money = loading.loadMoney();
                            
                            if(Age <= 12)
                            {
                            u = new Child(username,Age,score);
                            }
                            if(Age <= 12 && Age <20)
                            {
                                u = new Teenager(username,Age,score);
                            }
                            if(Age >= 20)
                            {
                                u = new Adult(username, Age, score);
                            }
                            
                            quizgame = new Game(totalQuestionsInQuiz,u,totalQuestionsOld,questionsAnsweredOld,score,Money);
                            quizgame.loadQuestions();
                            counter = -1;
                            dir1 = false;
                            Menu = false;
                            break;
                        }
                        else
                        {
                            dir1 = true;
                            System.out.println("No such save was found. Try again...");
                            counter--;
                        }
                        }
                    }
                    else if(counter <= 0)
                    {
                        System.out.println("Does this user allready exist:"+ dir.FindUser(in));
                        System.out.println("Creating Directories:"+dir.CreateUser(in));
                        
                        System.out.println("You ran out of search attempts!");
                        System.out.println("We are considering you a New User");
                        System.out.println("WARNING: DO NOT EXIT OR CANCEL THE PROGRAM TILL ALL INFORMATION HAS BEEN ENTERED");
                        counter--;
                        
                        System.out.println("Before we start the game we need to get the missing details.");
                        u = m.getUserInfo(in);
                
                        // saves this to a hashmap.
                        System.out.println("Thank you, we have your Uuer details, we now need you to provide a name for the new save");
                        Filename = m.getSaveInfo();
                        //initiating the quiz, The old informations is automatically 0 as there is no previous save for this game.
                        quizgame = new Game(totalQuestionsInQuiz,u,0,0,0,0);
                        SaveGame S = new SaveGame(u.UserName,Filename);
                        
                        String newSave = "Age="+u.getAge()+"\n"+"Username="+u.getUserName()+"\n"+"\nNumber Of Questions Asked=" + 0 +"\n"+ "\nTotal Questions In Quiz=" + 0 +"\n"+"Score=" + 0+"\n"+"Money Won ="+0+"\n";
                        S.createSaveGame(newSave);
                        
                        System.out.println("it is now safe to exit the program!");
                        //creates an empty savegame to prevent crashing if the user is new.
                        quizgame.loadQuestions();
                        Menu = false;
                        
                    }
                    else
                    {
                        System.out.println("Please provide some input!");
                        counter--;
                        Menu = true;
                    }
                }
        }
        
        if(Filename.equals(""))
        {
            Filename = "Demo";
            SaveGame S = new SaveGame(u.UserName,Filename);
            String newSave = "Age="+u.getAge()+"\n"+"Username="+u.getUserName()+"\n"+"\nNumber Of Questions Asked=" + 0 +"\n"+ "\nTotal Questions In Quiz=" + 0 +"\n"+"Score=" + 0+"\n"+"Money Won ="+0+"\n";
            S.OverWriteSave(newSave);
        }
        
        System.out.println("Thank you for filling out your user information, enjoy! ");
        
        System.out.println("/////////////////////////////////");
        System.out.println("  WHO WANTS TO BE A MILLIONARE! ");
        System.out.println("/////////////////////////////////");
        System.out.println("Your goal is to correctly answer 20 questions and win the million dollars");
        System.out.println("The questions will start off easy, and then get progressively harder.");
        System.out.println("Enter 'x' at any time to exit the quiz.");
        System.out.println("Good Luck!");
       
        
        System.out.println("Enter p to play: ");
        char c;
        c= scanner.next().toLowerCase().charAt(0);
        
        if(c =='p')
        {
            System.out.println("Loading...");
            quizgame.startGame(Difficulty.EASY, questionsPerDifficulty, Filename, u);
            quizgame.startGame(Difficulty.MEDIUM, questionsPerDifficulty, Filename, u);
            quizgame.startGame(Difficulty.HARD, questionsPerDifficulty, Filename, u);
            quizgame.startGame(Difficulty.EXTRAHARD, questionsPerDifficulty, Filename, u);
        }
        else
        { 
            while(!((c =='p') && (c != 'x')))
            {
            if(c == 'x'){
                SaveGame S = new SaveGame(u.UserName,Filename);
                String newSave = "Age="+u.getAge()+"\n"+"Username="+u.getUserName()+"\n"+"\nNumber Of Questions Asked=" + 0 +"\n"+ "\nTotal Questions In Quiz=" + 0 +"\n"+"Score=" + 0+"\n"+"Money Won ="+0+"\n";
                S.OverWriteSave(newSave);
                System.exit(0);
            }
                System.out.println("Incorrect input!");
                System.out.println("Please try again...");
                System.out.println("enter p to play: ");
                c = scanner.next().toLowerCase().charAt(0);
            }
        }
            System.out.println("Loading...");
            quizgame.startGame(Difficulty.EASY, questionsPerDifficulty, Filename, u);
            quizgame.startGame(Difficulty.MEDIUM, questionsPerDifficulty, Filename, u);
            quizgame.startGame(Difficulty.HARD, questionsPerDifficulty, Filename, u);
            quizgame.startGame(Difficulty.EXTRAHARD, questionsPerDifficulty, Filename, u);
        }
        
        //once the game is over the user is told their score. This is then automatically saved to the user file aswell as the questions and the awnsers the user entered in the order that the user was asked the questions.
        // the score is calculated based on the difficulty of the questions received and with more correct awnsers a multiplier is added as a bonus for getting them correct. The multiplier is also dependant on difficulty.
        // the users save file will be saved as a text and will contain the username, age, score. this will then be added to a leaderboard.
        // the leaderboard will load up once the game is finished.
    
    public User getUserInfo(String UserName)
    {
        Scanner scanner = new Scanner(System.in);
        
        int age = 0;
            do{
            System.out.println("Enter your Age: ");
            age = scanner.nextInt();
            if(age > 150 || age < 0)
            {
                System.out.println("Invalid Age, try again.");
            }
            }while(!(age>0 || age<150));
        
        //because its a new user we automatically make the score 0
        
        User u;
        if(age <= 12)
        {
            u = new Child(UserName,age,0);
        }
         else if(age <= 12 && age <20)
        {
            u = new Teenager(UserName,age,0);
        }
         else if(age >= 20)
        {
            u = new Adult(UserName, age,0);
        }
        else
         {
             u = new DefaultUser(UserName, age, 0);
         }
        
        return u; 
    }
    
    public String getSaveInfo()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Save Name? ");
        String FileName = scanner.nextLine();   
        return FileName;
    }
}

