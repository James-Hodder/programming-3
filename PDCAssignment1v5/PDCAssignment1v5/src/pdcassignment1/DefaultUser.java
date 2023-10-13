package pdcassignment1;

/**
 *
 * @author james
 */

//Creats a default user when the user does not fit the criteria of an adult, child or teenager.
public class DefaultUser extends User
{

    public DefaultUser(String UserName, int age, int points) {
        super(UserName, age, points);
    }

    @Override
    public double pointsMultiplier(int TotalNumOfCorrectAnswers) {
        double money = 0; // default users can not win any Money. 
        return money;
        }
}
