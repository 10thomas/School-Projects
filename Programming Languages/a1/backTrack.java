class backTrack
{
  public int[][] matrix;
  public void solve (int[][] matrix)
  {
    this.matrix = matrix;
  }
  
  private int[] numberUnassigned(int row, int col)
  {
    int numunassign = 0;
    for(int i = 0; i < matrix.length; i++)
    {
      for(int j=0; j < matrix.length; j++)
      {
        //cell is unassigned
        if(matrix[i][j] == 0)
        {
          //changing the values of row and col
          row = i;
          col = j;
          //there is one or more unassigned cells
          numunassign = 1;
          int[] a = {numunassign, row, col};
          return a;
        }
      }
    }
    int[] a = {numunassign, -1, -1};
    return a;
  }  
  //function to check if we can put a
  //value in a paticular cell or not
  private boolean isSafe(int n, int r, int c)
  {
    //checking in row
    for(int i = 0; i < matrix.length; i++)
    {
      //there is a cell with same value
      if(matrix[r][i] == n)
        return false;
    }
    //checking column
    for(int i=0; i < matrix.length; i++)
    {
      //there is a cell with the value equal to i
      if(matrix[i][c] == n)
        return false;
    }
    //checking sub matrix
    int row_start = (r/3)*3;
    int col_start = (c/3)*3;
    for(int i = row_start; i < row_start + 3; i++)
    {
      for(int j = col_start; j < col_start + 3; j++)
      {
        if(matrix[i][j]==n)
          return false;
      }
    }
    return true;
  }
  
  //function to solve sudoku
  //using backtracking
  public boolean solveSudoku(int[][] matrix)
  {
    this.matrix = matrix;
    
    int row=0;
    int col=0;
    int[] a = numberUnassigned(row, col);
    //if all cells are assigned then the sudoku is already solved
    //pass by reference because number_unassigned will change the values of row and col
    if(a[0] == 0)
      return true;
    //number between 1 to 9
    row = a[1];
    col = a[2];
    for(int i = 1; i <= matrix.length; i++)
    {
      //if we can assign i to the cell or not
      //the cell is matrix[row][col]
      if(isSafe(i, row, col))
      {
        matrix[row][col] = i;
        //backtracking
        if(solveSudoku(matrix))
          return true;
        //if we can't proceed with this solution
        //reassign the cell
        matrix[row][col]=0;
      }
    }
    return false;
  }
}