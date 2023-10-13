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
public class Game1 {
    private ArrayList<Question> questions;
    private int questionsAsked = 0;
    private int totalQuestionsInQuiz;
    private int correctAnswerIndex;
    HashMap AskedQuestion = new HashMap();
    
    public Game1(int totalQuestionsInQuiz) {
        questions = new ArrayList<>();
        this.totalQuestionsInQuiz = totalQuestionsInQuiz;
        this.correctAnswerIndex = 0;
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
    
    public void startGame(Difficulty difficulty, int numQuestions, String FileName) {
        int questionsAskedForDifficulty = 0;
        System.out.println("\nCurrent difficulty: " + difficulty);
        
        while (questionsAskedForDifficulty < numQuestions) {
           Question randomQuestion = selectRandomQuestion(difficulty);
            
            if(randomQuestion != null) 
            { 
                this.handleQuestion(randomQuestion, FileName);
                questionsAskedForDifficulty++;
            }
            if(randomQuestion == null)
            {
                System.out.println("no more questions found for the desired difficulty level.");
                break;    
            }
            if(questionsAsked == totalQuestionsInQuiz)
            { 
                System.out.println("\n Quiz session ended. ");
              
               
                String contents = "\nNumber Of Questions Asked=" + questionsAsked +"\n"+ "\nTotal Questions In Quiz=" + totalQuestionsInQuiz;
                System.out.println("Ending the Game...");
             
            }
        }
    }
        
    private void handleQuestion(Question question, String FileName) {
        shuffleAnswerOptions(question);
        questionsAsked++;

        System.out.println("\nQuestion " + questionsAsked + ": " + question.getQuestionText());
        String[] answerOptions = question.getAnswerOptions();
        for (int i = 0; i < answerOptions.length; i++) {
            System.out.println((i + 1) + ". " + answerOptions[i]);
        }
        
        question.setCorrectAnswerindex(question.getCorrectAnswer());
        int correctAnswerindex = question.getCorrectAnswerIndex();
        
        int userAnswer = this.getUserAnswer(FileName);
        if (userAnswer == question.getCorrectAnswerIndex() + 1) {
            System.out.println("Correct!");
        } else {    
            System.out.println("Incorrect! The correct answer was: " + (question.getCorrectAnswerIndex() + 1));
            
            String contents = "\n"+"Number Of Questions Asked=" + questionsAsked +"\n"+ "Total Questions In Quiz=" + totalQuestionsInQuiz;
       
            System.out.println("Ending the Game...");
               
            System.exit(0);
        }
    }
    
    private int getUserAnswer(String fileName) {
        while (true) {
            System.out.print("Your answer (enter the option number): ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.trim().equalsIgnoreCase("x")) 
            {
                String contents = "\n"+"Number Of Questions Asked=" + questionsAsked +"\n"+ "Total Questions In Quiz=" + totalQuestionsInQuiz;
                
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
    
    @Override
    public String toString() 
    {
        return "\nNumber Of Questions Asked=" + questionsAsked + "\nTotal Questions In Quiz=" + totalQuestionsInQuiz;
    }
    
    
    
}
