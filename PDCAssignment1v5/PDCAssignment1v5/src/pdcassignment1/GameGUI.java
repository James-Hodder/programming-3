package pdcassignment1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author evelt
 */
public class GameGUI {
    private JFrame frame;
    private JTextField questionNumberField;
    private JTextArea questionTextField;
    private JRadioButton optionA, optionB, optionC, optionD;
    private JButton submitButton;
    private Question currentQuestion;
    private Game1 game;

    public GameGUI(Game1 game) {
        this.game = game;
        currentQuestion = game.selectRandomQuestion(Difficulty.EASY);
        
        frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        questionNumberField = new JTextField();
        questionNumberField.setBounds(20, 20, 50, 30);
        questionNumberField.setEditable(false);
        frame.add(questionNumberField);

        questionTextField = new JTextArea();
        questionTextField.setLineWrap(true);
        questionTextField.setWrapStyleWord(true);
        questionTextField.setBounds(80, 20, 400, 60);
        questionTextField.setEditable(false);
        frame.add(questionTextField);

        optionA = new JRadioButton("Option A");
        optionA.setBounds(20, 90, 200, 30);
        frame.add(optionA);

        optionB = new JRadioButton("Option B");
        optionB.setBounds(20, 130, 200, 30);
        frame.add(optionB);

        optionC = new JRadioButton("Option C");
        optionC.setBounds(20, 170, 200, 30);
        frame.add(optionC);

        optionD = new JRadioButton("Option D");
        optionD.setBounds(20, 210, 200, 30);
        frame.add(optionD);

        ButtonGroup group = new ButtonGroup();
        group.add(optionA);
        group.add(optionB);
        group.add(optionC);
        group.add(optionD);

        submitButton = new JButton("Submit");
        submitButton.setBounds(20, 250, 100, 30);
        frame.add(submitButton);

        // Set question data
        setQuestionData(currentQuestion, game);

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the selected option and handle the user's answer
                if (optionA.isSelected()) {
                    handleAnswer(1);
                } else if (optionB.isSelected()) {
                    handleAnswer(2);
                } else if (optionC.isSelected()) {
                    handleAnswer(3);
                } else if (optionD.isSelected()) {
                    handleAnswer(4);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an answer option!");
                }
            }
        });

        frame.setSize(500, 350);
        frame.setVisible(true);
    }

    private void setQuestionData(Question question, Game1 game) {
        // Set question number and text
        questionNumberField.setText(String.valueOf(game.getQuestionsAsked()));
        questionTextField.setText(question.getQuestionText());

        // Set answer options
        String[] options = question.getAnswerOptions();
        optionA.setText("A. " + options[0]);
        optionB.setText("B. " + options[1]);
        optionC.setText("C. " + options[2]);
        optionD.setText("D. " + options[3]);
    }

    private void handleAnswer(int selectedOption) {
        game.shuffleAnswerOptions(currentQuestion);
        //questionsAsked++;
        
        currentQuestion.setCorrectAnswerindex(currentQuestion.getCorrectAnswer());
        
        // Compare selected option with the correct answer index (assuming correct answer index starts from 1)
        if (selectedOption == currentQuestion.getCorrectAnswerIndex() + 1) {
            JOptionPane.showMessageDialog(frame, "Correct answer!");
        } else {
            JOptionPane.showMessageDialog(frame, "Incorrect answer! The correct answer is: " +
                    (currentQuestion.getCorrectAnswerIndex() + 1));
        }

        // Fetch the next question and update the GUI with new question data
        Question nextQuestion = getNextQuestion();
        if (nextQuestion != null) {
            setQuestionData(nextQuestion, game);
            currentQuestion = nextQuestion;
        } else {
            // Handle end of the quiz (no more questions)
            JOptionPane.showMessageDialog(frame, "End of the quiz!");
            frame.dispose();
        }
    }

    private Question getNextQuestion() {
        // Implement logic to get the next question based on your requirements.
        // Return the next question or null if there are no more questions.
        // For example:
        // return quiz.getNextQuestion();
        return null;
    }

    public static void main(String[] args) {
        // Example usage
        int totalQuestionsInQuiz = 20;
        int questionsPerDifficulty = 5;
        String filename = "./resources/questions.txt";
        
        Game1 game = new Game1(totalQuestionsInQuiz);
        game.loadQuestions();
        //game.startGame(Difficulty.EASY, questionsPerDifficulty, filename);
        
    }
}
