package pdcassignment1;

/**
 *
 * @author james
 */

//Calculates the users score and amount of money won
public class CalculatePoints 
{
    private int newScore;
    private int TQA;
    private int TQIQ;
    private int oldTQA;
    private int oldTQ;
    private int oldScore;
    
    CalculatePoints(int NewScore, int OldScore, int NewTQA, int OldTQA, int TQ, int oldTQ)
    {
        this.newScore = NewScore;
        this.oldScore = OldScore;
        this.oldTQA = OldTQA;
        this.TQA = NewTQA;
        this.TQIQ = TQ;
        this.oldTQ = oldTQ;
    }
    
     public int NewScores()
    {
        int NewScore = newScore + this.oldScore;
        this.newScore = NewScore;
        return this.newScore;
    }
    
    public int NewTQA()
    {
        int TA = TQA + oldTQA;
        this.TQA = TA;
        return this.TQA;
    }
    
    public int NewTQ()
    {
         int newTQ = TQIQ + oldTQ;
         this.TQIQ = newTQ;
         return this.TQIQ;
    }
    
    public double AmountOfMoneyWonTotal(double oldTotal, User u)
    {
        int score = this.newScore-this.oldScore;
        double thisround = u.pointsMultiplier(score);
        thisround += oldTotal;
        return thisround; // this creates a new total amount which is returned.
    }
}
