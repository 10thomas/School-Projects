//DR Java
//Thomas Tsinokas
//COSC 2P03
//OCT 16 2015
import BasicIO.*;
import java.util.*;
public class Assign2
{
  private static ASCIIDataFile data; 
  public static int zeroes = 0;
  public static int count = 0;
  
  public static void addZeroes(int[][] board)
  {
    for(int i = 0; i < 4; i ++)
    {
      for(int j = 0; j< 4; j++)
      {
        if(board[i][j] == 0)
        {
          zeroes++;
        }
      }
    }
  }
  
  public static void readFile(int[][]board)
  {    
    data = new ASCIIDataFile();
    int n;
    for(int i = 0; i < 4; i++)
    {
      for(int j = 0; j < 4; j++)
      {
        n = data.readInt();
        board[i][j] = n;
      }
    }
  }
  public static void Print(int board[][])
  {
    for(int i = 0; i < 4; i++)
    {
      for(int j = 0; j <4; j ++)
      {
       System.out.print(board[i][j]); 
      }
      System.out.println();
    }
    System.out.println();
  }
  
  public static int[][] reset(int[][]board)
  {
    int[][]test = new int[4][4];
    for(int i = 0; i < 4; i ++)
    {
      for(int j = 0; j < 4; j++)
      {
        test[i][j] = board[i][j];
      }
    }
    return test;
  }
  
  public static boolean checkBox(int[][]board, int posX, int posY)
  {
    //sets all the values of the array to a new loation to fix a glitch that would turn all the nodes to equal the last inputed node
    int[][] test = new int[4][4];
    test[0][0] = board[posX][posY];
    test[1][0] = board[posX][posY+1];
    test[2][0] = board[posX+1][posY];
    test[3][0] = board[posX+1][posY+1];
    if(IsValidColumn(test,0))
    {
      return true;
    }
    return false;
  }
  
  static boolean IsValidRow(int[][] board, int referenceRow)
  {
    //Compare each value in the row to each other
    for(int i = 0; i < 4; i++)
    {
      for(int j = i + 1; j < 4; j++)
      {
        if(board[referenceRow][i] == board[referenceRow][j])
          return false;
      }
    }
    return true;
  }
  
  public static boolean noZeroes(int[][] board)
  {
    int sum = 0;
    for(int i = 0; i < 4; i++)
    {
      for(int j = 0; j < 4; j++)
      {
        sum = sum+ board[i][j];
      }
    }
    //each row if corect will add up to 10, 4 rows giving us 40 sum total. if the sum of each inidivual number adds 
    //up to 40 with every row, column and square being correct it proves that there are no zeroes in the equation
    if(sum==40)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  public static boolean IsValidColumn(int[][] board, int referenceColumn)
  {
    //Compare each value in the column to each other
    for(int i = 0; i < 4; i++)
    {
      for(int j = i + 1; j < 4; j++)
      {
        if(board[i][referenceColumn] == board[j][referenceColumn])
          return false;
      }
    }
    return true;
  }
  
  public static boolean checker(int[][]board)
  {
    int count = 0;
    for(int i = 0; i < 4; i++)
    {
      if(IsValidRow(board,i)&&IsValidColumn(board,i))
      {
        count = count + 1;
      }      
    }
    //if the row, column, and box are all correct and none of the entires are zeroes
    if(checkBox(board,0,0)&&checkBox(board,2,0)&&checkBox(board,0,2)&&checkBox(board,2,2)&&count == 4&&noZeroes(board))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  
  public static void main(String[] args)
  {
    int sum = 1;
    
    int[][]board = new int[4][4];
    readFile(board);
    tree t = new tree();
    addZeroes(board);
    t.add(board);
    
    for( int i = 1; i <= zeroes; i++)
    {
      sum = sum *5;
    }
    
    for(int x = 1; x <= sum-1; x++)
    {
      for(int i = 0; i < 4; i++)
      {
        for(int j = 0; j < 4; j++)
        {
          if(board[i][j] == 0)
          {
            for(int k =1; k <= 4; k++)
            {
              board = reset(board);
              board[i][j] = k;
              t.addLast(board);
              
            }
          }
        }
      }
      board = t.get(x);
      if(checker(board))
      {
        count = count +1;
        Print(board);
      }
    }
    System.out.println( count + " number of solutons");
  }
  
  
  
}