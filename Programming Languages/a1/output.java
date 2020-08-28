class output
{
  public void printSudoku(int[][] board)
  {
    for(int i = 0; i < board.length; i++)
    {
      for(int j = 0; j <board.length; j++)
      {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }    
  }
}