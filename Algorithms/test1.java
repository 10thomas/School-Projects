import java.util.Scanner;

public class test1 
{ 
  public static String temp = "";
  public static int oldCount = Integer.MAX_VALUE;
  public static int count = 0;
  public static String finalList;
  
  public static int calcM(int s[], int i, int j)
  {
    if (i == j) 
      return 0; 
    int min = Integer.MAX_VALUE; 
    for (int k = i; k < j; k++) 
    {
      int lowerChain = calcM(s, i, k);
      int upperChain = calcM(s, k + 1, j);
      int calc = lowerChain + upperChain + s[i - 1] * s[k] * s[j]; 
      if (calc < min) 
        min = calc; 
    }
      return min; 
  }
  
  public static String MatrixChainOrder(int s[], int i, int j) 
  {
    
      if (i == j) 
      {
        //System.out.print("M" + i);
        return ("M" + i);
      }
      else
      {
        for(int k=i; k < j; k++)
        {
          int count = calcM(s, i, k) + calcM(s, k + 1, j) + s[i-1] * s[k] * s[j]; 
          temp = "(" + MatrixChainOrder(s, i, k) + " x " + MatrixChainOrder(s, k+1,j) + ")";
          
          //System.out.println(temp + " = " + count);
          String[] listSize = temp.split("x"); 
          if(listSize.length == (s.length-1))
          {
            if(count < oldCount)
            {
              oldCount = count;
              finalList = temp;
            }
          }
        }
      }
    
      return temp;
  } 
  
  public static void main(String args[]) 
  { 
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter a value for n: ");
    int n = scan.nextInt();
    int s[] = new int[n];
    for(int i = 0; i < n; i++)
    {
      System.out.print("Enter a value for r" + i + ": ");
      s[i] = scan.nextInt();
    }
    scan.close();
    
    MatrixChainOrder(s, 1, n - 1);
    System.out.println(finalList + " = " + oldCount);
  } 
}