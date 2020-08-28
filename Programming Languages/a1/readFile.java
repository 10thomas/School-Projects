import java.io.*;
import java.util.Scanner;
class readFile
{
  public String getFileName()
  {
    System.out.print("Enter the file name: ");
    Scanner scan = new Scanner(System.in);
    String fileName = scan.nextLine();
    scan.close();
    return fileName;
  } //getFileName
  
  public void readFile(String fileName)
  {
    String line = null;
    try 
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      bufferedReader.close();         
    }
    catch(FileNotFoundException ex) 
    {
      System.out.println( "Unable to open file '" + fileName + "'");  
      //String file = getFileName();
     //readFile(file);
    }
    catch(IOException ex) 
    {
      System.out.println("Error reading file '" + fileName + "'");  
      //String file = getFileName();
      //readFile(file);
    }
  }//readFile
}