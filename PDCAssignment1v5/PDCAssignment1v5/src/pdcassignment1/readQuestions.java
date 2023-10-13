package pdcassignment1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author evelt
 */

//Reads questions from the questions.txt file in resources

public class readQuestions 
{
    public static ArrayList<Question> readQuestionsFromFile(String fileName) {
        ArrayList<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String questionText = parts[0];
                    String[] options = parts[1].split(", ");
                    String correctAnswer = parts[2];
                    Difficulty difficultyLevel = Difficulty.valueOf(parts[3]);
                    questions.add(new Question(questionText, options, correctAnswer, difficultyLevel));
                }
            }       
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
