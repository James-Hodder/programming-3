package pdcassignment1;

/**
 *
 * @author james
 */

//Contains all the user information: age, points and username
public abstract class User 
{
   protected String UserName;
   private int age;
   private int points;

    public User(String UserName, int age, int points) {
        this.UserName = UserName;
        this.age = age;
        this.points = points;
    }
    
   
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public void setPoints(int Points)
    {
        this.points = Points;
    }
    
    public abstract double pointsMultiplier(int TotalNumOfCorrectAnswers);
    
    @Override
    public String toString() {
        return "User{" + "UserName=" + UserName + ", age=" + age + ", points=" + points + '}';
    }
   
   
    
}
