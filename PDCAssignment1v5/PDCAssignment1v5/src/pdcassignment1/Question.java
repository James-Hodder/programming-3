package pdcassignment1;

/**
 *
 * @author evelt
 */
//Handles the contents of each question: questiontext, options, correct answer and difficulty

import java.lang.String;

public class Question {
    private String questionText;
    private String[] answerOptions;
    private String correctAnswer;
    private int correctAnswerIndex;
    private Difficulty difficultyLevel;
    
    public Question(String questionText, String[] answerOptions, String correctAnswer, Difficulty difficultyLevel) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
    }
    
    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }
    
    public void setAnswerOptions(String[] newOptions) {
        this.answerOptions = newOptions;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    public void setCorrectAnswerindex(String correctAnswer) {
        int i =0;
        while(i<this.answerOptions.length)
        {
            if(this.answerOptions[i].toLowerCase().equals(this.correctAnswer.toLowerCase()))
            {
               this.correctAnswerIndex = i;
            }
            i++;
        }
    }
    
    
    public int getCorrectAnswerIndex() 
    {
            return this.correctAnswerIndex; 
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }
    
    
}
