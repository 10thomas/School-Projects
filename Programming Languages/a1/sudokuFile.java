import java.io.*;
import java.util.Scanner;
class sudokuFile
{
  public String getFileName()
  {
    System.out.print("Enter the file name: ");
    Scanner scan = new Scanner(System.in);
    String fileName = scan.nextLine() + ".txt";
    scan.close();
    return fileName;
  } //getFileName
   
  public int[][] readFile(String fileName)
  {    
    String line = null;
    int[][] sudokuBoard = new int [9][9];
    try 
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      line = bufferedReader.readLine();
      int boardSize = Integer.parseInt(line);
      sudokuBoard = new int [boardSize][boardSize];
      for(int i = 0; i < boardSize; i++)
      {
        line = bufferedReader.readLine();
        String[] parts = line.split(" ");
        for(int j = 0; j < boardSize; j++)
          sudokuBoard[i][j] = Integer.parseInt(parts[j]);
      }
      bufferedReader.close();
    }
    catch(FileNotFoundException ex) 
    {
      System.out.println( "Unable to open file '" + fileName + "'");  
      String file = getFileName();
      readFile(file);
    }
    catch(IOException ex) 
    {
      System.out.println("Error reading file '" + fileName + "'");  
      String file = getFileName();
      readFile(file);
    }
    return sudokuBoard;
  }//readFile
}