package pdcassignment1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author james
 */

//Saves the game to a file
public class SaveGame
{
    private double currentScore;
    private final String FileName;
    private String filePath;
    private String UserName;
    
    public SaveGame(String Username,String FileName)
    {
        this.UserName = Username;
        this.FileName = FileName;
        this.currentScore = 0;
        this.filePath = "./resources/Users/" + this.UserName +"/"+this.FileName+".txt";
    }
    
    public void createSaveGame(String contents)
    {
        //CreateSaveGame method, which saves the game to the user and creates a file with the questions that have allready been asked.
    //Also this file will contain the users information and final save score. This file will not Override Pre-existing saveGames. 
           
        try 
        {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)); 
                writer.write(contents);
                writer.close();
                System.out.println("The File: "+this.FileName);
        }
        catch (IOException e) 
        {
            System.out.println("Did not Save");
        }
    }
   
    public void BackupSave(String oldSaveName, String newSaveName)
    {
        
        String sourceFilePath = "./resources/Users/" + this.UserName +"/"+oldSaveName+".txt";
        String destFilePath = "./resources/Users/" + this.UserName +"/"+newSaveName+".txt";
        
        LoadGameSave Loading = new LoadGameSave(oldSaveName);
       // Loading.LoadFile("Saves/" + this.userName +"/"+oldSaveName+".txt");
        
        Loading.copyFile(sourceFilePath, destFilePath);
        //BackupSave method is a type of copy method which creates a copy of a selected save.
    }
    
    public void OverWriteSave(String Contents)
    {
            this.deleteSave();
            this.createSaveGame(Contents);
        
                //OverWriteSave Method, will create a new save and if a save with the same name allready exists then the method will overWrite the save.
     
    }
    
    public void deleteSave() 
    {
        String Match ="";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            Match = reader.readLine();
            reader.close();
            
            if(!(Match.equals("")))
            {
                File dSave = new File(this.filePath); //creates a new fileobject of that certain path, if a file allready existing in that path then it will be deleted.
                if(dSave.delete())
                {
                    
                }
                else
                {
                    System.out.println("Unable to delete SaveFile");
                }
            }
        } catch(IOException e)
                {
                    System.out.println("no file found");
                }
        
            
       //DeleteSave Method - Deletes a Allready existing saveGame text file if it exists.
    }
    
//Note: The save game files Will be stored in a Folder for each user. The user folder will be named the UserName and saved as such.
    // a new CLass will be created which will create New File directories for the Save games to be saved into. 
    //
}
