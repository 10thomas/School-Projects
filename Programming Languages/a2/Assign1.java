/* Thomas Tsinokas
 * 5618293
 * COSC 2P05
 * Jan 28 2017
 * ------------------------------------------------------------------
 * This program has the user enter a file name, with its extensions. 
 * then loads a sudoku board from the file name
 * It then uses backstracking to solve that sudoku board
 * ------------------------------------------------------------------
 */
import java.io.*;
import java.util.Scanner;
public class Assign1 
{
  public static int boardSize;
  public static int board[][];
  public static class Digit 
  {
    int row, col;
    public Digit(int row, int col) 
    {
      super();
      this.row = row;
      this.col = col;
    }
  }//Digit

  public static void readFile(String fileName)
  {
    String line = null;
    try 
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      //the first line is the size of the sudoku board. could be a 4 x4 board, 9x9 board, 16 x 16 board and still solve
      line = bufferedReader.readLine();
      boardSize = Integer.parseInt(line);
      board = new int [boardSize][boardSize];
      
      //loops through the remaining lines inside the file. spliting by spaces and converting the strings 
      //into integers and stroing inside the board
      for(int i = 0; i < boardSize; i++)
      {
        line = bufferedReader.readLine();
        String[] parts = line.split(" ");
        for(int j = 0; j < boardSize; j++)
          board[i][j] = Integer.parseInt(parts[j]);
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
  }//readFile
  
  public static String getFileName()
  {
    System.out.print("Enter the file name: ");
    Scanner scan = new Scanner(System.in);
    String fileName = scan.nextLine();
    scan.close();
    return fileName;
  } //getFileName
  
  //looks to see if the digit passed exists in now, col and box, if not return true else return false
  public static boolean isValid(Digit digit, int value) 
  {  
    if (board[digit.row][digit.col] != 0) 
      throw new RuntimeException();
  
    for (int col2 = 0; col2 < boardSize; col2++) 
    {
      if (board[digit.row][col2] == value)
        return false;
    }
    
    for (int row2 = 0; row2 < boardSize; row2++) 
    {
      if (board[row2][digit.col] == value)
        return false;
    }
    
    int x1 = 3 * (digit.row / 3);
    int y1 = 3 * (digit.col / 3);
    int x2 = x1 + 2;
    int y2 = y1 + 2;
    
    for (int i = x1; i <= x2; i++)
    {
      for (int j = y1; j <= y2; j++)
      {
        if (board[i][j] == value)
          return false;
      }
    }
    return true;
  }
  
 //gets the next value inside the board, if last value in volumn goes to next row
  static Digit getNextCell(Digit cur) 
  {
    int row = cur.row;
    int col = cur.col;
    col++;
    //if in the last column go to the furst slot on the next row
    //fix a glitch by having it > (boardSize-1) rather thn == boardSize
    if (col > (boardSize-1)) 
    {
      col = 0;
      row++;
    }
    // if we reached the end of the row we return null
    if (row > (boardSize-1))
      return null;
    Digit next = new Digit(row, col);
    return next;
  }
 
  public static boolean backtrack(Digit currentDigit) 
  {
    // if the cell is null, we have reached the end of the line
    if (currentDigit == null)
      return true; 
    //if the current digit is not 0 we check the next digit
    if (board[currentDigit.row][currentDigit.col] != 0) 
      return backtrack(getNextCell(currentDigit));    
    // try each possible value, use 1 to <= board size so i can be the actual digit added
    for (int i = 1; i <= boardSize; i++) 
    {
      // check if valid, if valid, then update the digit
      boolean valid = isValid(currentDigit, i);
      if (!valid) // if not valid move onto the next possible digit
        continue;
      board[currentDigit.row][currentDigit.col] = i;
      boolean solved = backtrack(getNextCell(currentDigit));
      if (solved)
        return true;
      else
        board[currentDigit.row][currentDigit.col] = 0; // reset
    } 
    return false;
  }
  
  public static void printBoard(int board[][]) 
  {
    for (int row = 0; row < boardSize; row++) 
    {
      for (int col = 0; col < boardSize; col++)
      {
        System.out.print(board[row][col]);
        if(col % Math.sqrt(boardSize) == Math.sqrt(boardSize)-1 && col != boardSize-1)
          System.out.print("|");
      }
      if(row % Math.sqrt(boardSize) == Math.sqrt(boardSize)-1 && row != boardSize-1)
      {
        System.out.println();
        
        for(int i = 0; i < boardSize; i++)
        {
          System.out.print("-");
          if(i % Math.sqrt(boardSize) == Math.sqrt(boardSize)-1 && i != boardSize-1)
            System.out.print("+");
        }
        System.out.println();
      }
      else
        System.out.println();
    }
    System.out.println();
  }//printBoard
  
  public static void main(String[] args) 
  {
    String file = getFileName();
    readFile(file);
    System.out.println("Input: ");
    printBoard(board);
    boolean solved = backtrack(new Digit(0, 0));
    if (!solved) 
      System.out.println("Cant solve");
    else
    {
       System.out.println("Solved");
       printBoard(board);
    }
  }
}