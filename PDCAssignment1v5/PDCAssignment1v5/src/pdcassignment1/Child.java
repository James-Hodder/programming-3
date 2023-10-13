package pdcassignment1;

/**
 *
 * @author james
 */

//Creates a child user
public class Child extends User 
{

    public Child(String UserName, int age, int points) {
        super(UserName, age, points);
    }

    @Override
    public double pointsMultiplier(int TotalNumOfCorrectAnswers) 
    {
        int Multiplier = 60000;
        
        double money = TotalNumOfCorrectAnswers*Multiplier;
        return money;
    }
}
