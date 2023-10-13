package pdcassignment1;

/**
 *
 * @author james
 */

//Creates an adult user
public class Adult extends User 
{
    public Adult(String UserName, int age, int points) {
        super(UserName, age, points);
    }
    
      @Override
    public double pointsMultiplier(int TotalNumOfCorrectAnswers) 
    { 
        int Multiplier = 50000;
        
        double money = TotalNumOfCorrectAnswers*Multiplier;
        return money;
    }
}
