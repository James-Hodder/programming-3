package pdcassignment1;

/**
 *
 * @author james
 */

//Creates a teenaged user
public class Teenager extends User
{

    public Teenager(String UserName, int age, int points) {
        super(UserName, age, points);
    }
    
    @Override
    public double pointsMultiplier(int TotalNumOfCorrectAnswers) 
    {
        int Multiplier = 55000;
        
        double money = TotalNumOfCorrectAnswers*Multiplier;
        return money;
    }
    
}
