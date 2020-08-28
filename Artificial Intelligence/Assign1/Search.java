// Search.java
// Thomas Tsinokas
// 5618293
// COSC 3P71
// Oct 17 2016

//The program one at a time wil place a queen from the top row to the bottom, 
//if the new queen is threatened it moves it to the column to the right, 
//if it cant move right it goes back one row and moves the queen to the next unthreatened position
//once the first complete board is found the program exits

public class Search
{
  public static boolean completeBoard = false;
  
  public static void makeBoard(int n)
  {
    boolean board[][] = new boolean[n][n]; 
    
    for (int i = 0; i < n; i++)
    {
      for (int j = 0; j < n ; j++)
      {
        board[i][j] = false;
      }       
    } 
    inputQueen(board,0,0);
  } //makeBoard
  
  
  public static void inputQueen(boolean board[][], int row, int col)
  {
    if (completeBoard == false)
    {
      boolean queenSafe = true;     
      
      board[row][col] = true;
      queenSafe = checkDiagnal(board, row, col);
      queenSafe = checkVertical(queenSafe, board, row, col);
            
      if (queenSafe == true)
      {
        if(row + 1 == board.length)
        {
          completeBoard = true;
          printBoard(board);
        }
        else
        {
          inputQueen(board, row + 1, 0);
        }
      }  
      else if (queenSafe == false)
      {
        board[row][col] = false;
        if ( col + 1 == board.length)
        {
          returnRow(board, row - 1);
        }
        else
        {
          if(col+1 < board.length)
            inputQueen(board, row, col + 1);          
        }
      }
    }
  } //inputQueen

  public static void returnRow(boolean[][] board, int row)
  {
    for (int i = 0; i < board.length; i++)
    {
      if (board[row][i] == true)
      {
        board[row][i] = false;
        if (i + 1 == board.length)
        {
          returnRow(board, row-1);
        }
        else
          inputQueen(board, row, i + 1);
      }
    }
  } //returnRow
  
  public static boolean checkVertical(boolean queen, boolean[][] board, int row, int col)
  {
    //above
    for (int i = 1; i <= board.length; i++)
    {
      if (row - i < 0)
        break;
      if (board[row-i][col] == true)
        queen = false; //unsafe
    }
    
    //below
    for (int i = 1; i <= board.length; i++)
    {
      if (row + i >= board.length)
        break;
      if (board[row+i][col] == true)
        queen = false; //unsafe
    }
    
    //left
    for (int i = 1; i <= board.length; i++)
    {
      if (col - i < 0 )
        break;
      if (board[row][col-i] == true)
        queen = false; //unsafe
    }
    
    //right
    for (int i = 1; i <= board.length; i++)
    {
      if (col + i >= board.length )
        break;
      if (board[row][col+i] == true)
        queen = false; //unsafe
    }
   return queen; 
  }
  
  public static boolean checkDiagnal(boolean[][] board, int row, int col)
  {
    boolean queen = true;    
    //top left check
    for (int i = 1; i <= board.length; i++)
    {
      if (row - i < 0|| col - i < 0)
        break;
      if (board[row-i][col-i] == true)
        queen = false; //unsafe
    }
    
    //bottom left
    for (int i = 1; i < board.length; i++)
    {
      if (row + i >= board.length || col - i < 0)
        break;
      if (board[row+i][col-i] == true)
        queen = false; //unsafe
    }
    //top right check
    for (int i = 1; i < board.length; i++)
    {
      if (row - i < 0 || col + i >= board.length)
        break;
      if (board[row-i][col+i] == true)
        queen = false; //unsafe
    }
    //bottom right check
    for (int i = 1; i < board.length; i++)
    {
      if (row + i >= board.length || col + i >= board.length)
        break;
      if (board[row+i][col+i] == true)
        queen = false; //unsafe
    }
    return queen;
  } //checkDiagnal
  
  public static void printBoard(boolean board[][])
  {
    for (int i = 0; i < board.length; i++)
    {
      for (int j = 0; j < board.length ; j++)
      {
        if (board[i][j] == false)
          System.out.print("*");
        if (board[i][j] == true)
          System.out.print("Q");
      }       
      System.out.println();
    } 
    System.out.println();
  } //printBoard
  
  public static void main(String[] args) 
  { 
    int n = 8;
    makeBoard(n);
  } //main
} //Search