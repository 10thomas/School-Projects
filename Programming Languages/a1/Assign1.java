// need 2 more classes
// proper commenting
// detect invalid board

public class Assign1
{
  public static int[][] matrix; 
  public static void main(String[] args)
  {
    //have the user search for a text file to open
    sudokuFile file = new sudokuFile();
    output boardOutput = new output();
    backTrack solve = new backTrack();
    
    String fileName = file.getFileName();
    matrix = file.readFile(fileName);
    
    boardOutput.printSudoku(matrix);
    System.out.println(" ");
    
    if (solve.solveSudoku(matrix))
       boardOutput.printSudoku(matrix);
    else
      System.out.println("No solution");
    
  }
}