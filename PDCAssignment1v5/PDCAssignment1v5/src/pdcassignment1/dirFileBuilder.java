package pdcassignment1;
import java.io.File;

/**
 *
 * @author james
 */

//Handles user operations
public class dirFileBuilder
{   
    public dirFileBuilder()
    {
        //constructor.
    }
    
    public boolean CreateUser(String UserName)
    {
            File User = new File("resources/Users");
            boolean r;
            if(!(User.exists()))
            {
                r = User.mkdirs();
            }
            else
            { //it allready exists, so it returns false as nothing new was created.
            r = false;
            }
            
            if(User.exists()) //check to see if the base file exists before creating a nested directory.
            {
                File createUserDir = new File("resources/Users/"+UserName);
               if(!(createUserDir.exists())) // checks to see that it does not exist.
               {
                   r = createUserDir.mkdir(); //if it executes it will return true.
                   return r;
               }
            }
        return r;
        //CreateUser - a method which creates a new folder under the "user" directory. The Folder created will then be used to store the user information of that user.
    }
    
    public boolean FindUser(String Username)
    {  
       File User = new File("resources/Users/"+Username);
       
       if(User.exists())
       {
           return true;
       }
       else
       {
           return false;
       }
        //Find FileUser
        //FindUser - a search function to find The file Directory of a searched for user. A user object will be passed in and used to get a match.
    }
    
    public void deleteUser(String userName)
    {
        
        File userFolder = new File("resources/Users/"+userName);        
        //Deletes it if it exists
       
            if(userFolder.exists() && userFolder.isDirectory())
            {
                if(userFolder.delete())
                {
                    System.out.println("Folder Deleted!");
                }
                else
                {
                    System.out.println("Could not delete the users folder");
                }
            }
        // uses the find user Search function first to get a match Then will compare with the next peice to get the final bit
        
        //Temp.deleteOnExit();
        
        //DeleteUser - Deletes a Users file directory and all the files in it. 
    }
}
