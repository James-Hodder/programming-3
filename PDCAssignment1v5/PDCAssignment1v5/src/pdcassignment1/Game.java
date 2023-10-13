package pdcassignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author evelt and james
 */

//Handles all of the game information.
public class Game {
    private ArrayList<Question> questions;
    private int questionsAsked = 0;
    private int totalQuestionsInQuiz;
    private int newScore = 0;
    private int oldScore;
    private int correctAnswerIndex;
    private User U;
    private int oldTQ;
    private int oldTQA;
    private double MoneyWon;
    HashMap AskedQuestion = new HashMap();
    
    public Game(int totalQuestionsInQuiz, User U, int oldTQ, int oldTQA, int oldScore, double MoneyWon) {
        questions = new ArrayList<>();
        this.oldTQ = oldTQ;
        this.oldTQA = oldTQA;
        this.totalQuestionsInQuiz = totalQuestionsInQuiz;
        this.correctAnswerIndex = 0;
        this.oldScore = oldScore;
        this.MoneyWon = MoneyWon;
    }
    
    public int getQuestionsAsked() {
        return questionsAsked;
    }
       
    public Question selectRandomQuestion(Difficulty difficulty) {
        ArrayList<Question> filteredQuestions = new ArrayList<>();
        for (Iterator<Question> it = questions.iterator(); it.hasNext();) 
        {
            Question question = it.next();
            if (question.getDifficultyLevel() == difficulty) {
                filteredQuestions.add(question);
            }
        }
        if(filteredQuestions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        
        boolean f = true;
         
        while(f == true)
        {
        int index = random.nextInt(filteredQuestions.size());
        Question Q;
        Q = filteredQuestions.get(index);
        
       
        if(AskedQuestion.containsKey(index))
        {
            if(AskedQuestion.containsValue(Q))
            {
                f = false;
            }
            else
            {
                AskedQuestion.put(index, Q);
                return Q;
            }
        }
        else
        {
             AskedQuestion.put(index, Q);
             return Q;
        }
        }
        return null;
    }
    
    public void shuffleAnswerOptions(Question question) {
        ArrayList<String> optionsList = new ArrayList<>();
        Collections.addAll(optionsList, question.getAnswerOptions());
        Collections.shuffle(optionsList);
        question.setAnswerOptions(optionsList.toArray(new String[0]));
    }
    
    public void loadQuestions() {
        readQuestions rQuestions = new readQuestions();
        String filename = "./resources/questions.txt";
        questions = rQuestions.readQuestionsFromFile(filename);
    }
    
    public void startGame(Difficulty difficulty, int numQuestions,String FileName, User U) {
        int questionsAskedForDifficulty = 0;
        System.out.println("\nCurrent difficulty: " + difficulty);
        
        while (questionsAskedForDifficulty < numQuestions) {
           Question randomQuestion = selectRandomQuestion(difficulty);
            
            if(randomQuestion != null) 
            { 
                this.handleQuestion(randomQuestion, FileName, U);
                questionsAskedForDifficulty++;
            }
            if(randomQuestion == null)
            {
                System.out.println("no more questions found for the desired difficulty level.");
                break;    
            }
            if(questionsAsked == totalQuestionsInQuiz)
            { 
                CalculatePoints C = new CalculatePoints(newScore,this.oldScore,this.questionsAsked,this.oldTQA,this.totalQuestionsInQuiz,this.oldTQ);
                newScore = C.NewScores();
                totalQuestionsInQuiz= C.NewTQ();
                questionsAsked=C.NewTQA();
                MoneyWon = C.AmountOfMoneyWonTotal(MoneyWon, U);
                
                System.out.println("\n Quiz session ended. ");
                System.out.println("Your score: "+ newScore + "/" + totalQuestionsInQuiz);
                System.out.println("The Amount Of Money Won! $"+ MoneyWon);
                
                SaveGame S = new SaveGame(U.getUserName(),FileName);
               
                String contents = "Age="+U.getAge()+"\n"+"Username="+U.getUserName()+"\n"+"\nNumber Of Questions Asked=" + questionsAsked +"\n"+ "\nTotal Questions In Quiz=" + totalQuestionsInQuiz +"\n"+"Score=" + newScore+"\n"+"Money Won ="+MoneyWon+"\n";
                S.OverWriteSave(contents);
                System.out.println("Ending the Game...");
             
                this.saveMenuEnd(S, FileName);
            }
        }
    }
        
    private void handleQuestion(Question question, String FileName, User U) {
        shuffleAnswerOptions(question);
        questionsAsked++;

        System.out.println("\nQuestion " + questionsAsked + ": " + question.getQuestionText());
        String[] answerOptions = question.getAnswerOptions();
        for (int i = 0; i < answerOptions.length; i++) {
            System.out.println((i + 1) + ". " + answerOptions[i]);
        }
        
        question.setCorrectAnswerindex(question.getCorrectAnswer());
        int correctAnswerindex = question.getCorrectAnswerIndex();
        
        int userAnswer = this.getUserAnswer(FileName,U);
        if (userAnswer == question.getCorrectAnswerIndex() + 1) {
            System.out.println("Correct!");
            newScore++;
        } else {
            CalculatePoints C = new CalculatePoints(newScore,this.oldScore,this.questionsAsked,this.oldTQA,this.totalQuestionsInQuiz,this.oldTQ);
            newScore = C.NewScores();
            totalQuestionsInQuiz= C.NewTQ();
            questionsAsked=C.NewTQA();
            MoneyWon =C.AmountOfMoneyWonTotal(MoneyWon, U);
                
            
            System.out.println("Incorrect! The correct answer was: " + (question.getCorrectAnswerIndex() + 1));
            System.out.println("Your score: " + newScore + "/" + totalQuestionsInQuiz);
            System.out.println("The Amount Of Money Won! $"+ MoneyWon);
            
            SaveGame S = new SaveGame(U.getUserName(),FileName);
            
            String contents = "Age="+U.getAge()+"\n"+"Username="+U.getUserName()+"\n"+"Number Of Questions Asked=" + questionsAsked +"\n"+ "Total Questions In Quiz=" + totalQuestionsInQuiz +"\n"+"Score=" + newScore+"\n"+"Money Won ="+MoneyWon+"\n";
            S.OverWriteSave(contents);
            
            this.saveMenuEnd(S, FileName);
            System.out.println("Ending the Game...");
               
            System.exit(0);
        }
    }
    
    private int getUserAnswer(String fileName,User U) {
        while (true) {
            System.out.print("Your answer (enter the option number): ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.trim().equalsIgnoreCase("x")) 
            {
                CalculatePoints C = new CalculatePoints(newScore,this.oldScore,this.questionsAsked,this.oldTQA,this.totalQuestionsInQuiz,this.oldTQ);
                newScore = C.NewScores();
                totalQuestionsInQuiz= C.NewTQ();
                questionsAsked=C.NewTQA();
                MoneyWon =C.AmountOfMoneyWonTotal(MoneyWon, U);
                
                System.out.println("You got: " + newScore + "/" + totalQuestionsInQuiz);
                System.out.println("The Amount Of Money Won! $"+ MoneyWon);
                
                // added the file handling in here for saving the game. 
                SaveGame S = new SaveGame(U.getUserName(),fileName);
                
                String contents = "Age="+U.getAge()+"\n"+"Username="+U.getUserName()+"\n"+"Number Of Questions Asked=" + questionsAsked +"\n"+ "Total Questions In Quiz=" + totalQuestionsInQuiz +"\n"+"Score=" + newScore+"\n"+"Money Won ="+MoneyWon+"\n";
                S.OverWriteSave(contents); //autosaves it to the text File Set at the start.
                
                this.saveMenuEnd(S, fileName);
                System.out.println("Ending the Game...");
                
                System.exit(0);
            }

            try {
                int userAnswer = Integer.parseInt(line);
                if (userAnswer >= 1 && userAnswer <= 4) {
                    return userAnswer;
                } else {
                    System.out.println("Invalid input. Enter an option between 1 and 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    
    public void saveMenuEnd(SaveGame S, String fileName)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to create a Backup Save of the current save?(b) delete the Current Save?(d) or do neither?(any other input, or none) ");
                char input =scanner.next().toLowerCase().charAt(0);
                
                
                if (input == 'b')
                {
                    String i = fileName;
                    do
                    {
                    System.out.println("What would you like to call this new Save?(can not be the same name as the Current save)");
                    scanner.nextLine();
                    i = scanner.nextLine();
                    if(!(i.equals(fileName)))
                    {
                    S.BackupSave(fileName, i);
                    }
                    else
                    {
                        System.out.println("Please enter a different Name!");
                    }
                    }while(i.equals(fileName));
                }
                if(input =='d')
                {
                    System.out.println("Deleting the Current Game File Save");
                    S.deleteSave();
                }
        
    }
    
    @Override
    public String toString() 
    {
        return "Age="+U.getAge()+"\nUsername="+U.getUserName()+"\nNumber Of Questions Asked=" + questionsAsked + "\nTotal Questions In Quiz=" + totalQuestionsInQuiz + "\nScore=" + newScore +'}';
    }
    
    
    
}
