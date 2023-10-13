/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcassignment1;
import java.io.*;
/**
 *
 * @author james
 */

//Loads a previously saved game from a file
public class LoadGameSave 
{
    private String saveFilePath;

    public LoadGameSave(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public String getSave() {
        
        return saveFilePath;
    }

    public int loadScore() {
        int Score = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(saveFilePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Score=")) {
                    Score = Integer.parseInt(line.substring(6).trim());
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        
        catch (IOException e) 
        {
            System.out.println("There was an error From file: "+ e.getMessage());
        }
        return Score;
    }
    
        public int loadNOQA() {
        int NOQA = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(saveFilePath));
            String line ="";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Number Of Questions Asked=")) {
                    NOQA = Integer.parseInt(line.substring(26).trim());
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        
        catch (IOException e) {
            System.out.println("There was an error From file: "+ e.getMessage());
        }
        return NOQA;
    }
        
        public int loadNOQ() {
        int NOQA = 0;
        try {
            FileReader fileReader = new FileReader(saveFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Total Questions In Quiz=")) {
                    NOQA = Integer.parseInt(line.substring(24).trim());
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        
        catch (IOException e) {
            System.out.println("There was an error From file: "+ e.getMessage());
        }
        return NOQA;
    }
    
    public String LoadUserName()
    {
        String Username = "";
        try {
            FileReader fileReader = new FileReader(saveFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Username=")) {
                    Username = line.substring(9).trim();
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        
        catch (IOException e) {
            System.out.println("There was an error From file: "+ e.getMessage());
        }
        return Username;
    }
    
     public int loadAge() {
        int Age = 0;
        try {
            FileReader fileReader = new FileReader(saveFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Age=")) {
                    Age = Integer.parseInt(line.substring(4).trim());
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        
        catch (IOException e) {
            System.out.println("There was an error From file: "+ e.getMessage());
        }
        return Age;
    }
     
     public double loadMoney() {
        double money = 0;
        try {
            FileReader fileReader = new FileReader(saveFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Money Won =")) {
                    money = Double.parseDouble(line.substring(11).trim());
                    break;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        
        catch (IOException e) {
            System.out.println("There was an error From file: "+ e.getMessage());
        }
        return money;
    }
    
    public void copyFile(String sourceFilePath, String destFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(sourceFilePath);
            FileOutputStream outputStream = new FileOutputStream(destFilePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("There was an error from file: "+e.getMessage());
        }
    }

    public boolean MatchFile() 
    {
            File fileReader = new File(saveFilePath);
            
            return fileReader.exists();
    }
}
