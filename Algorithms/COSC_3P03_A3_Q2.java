//Thomas Tsinokas
//5618293
//COSC 3P03
//Question 2
import java.util.Scanner;

public class COSC_3P03_A3_Q2 
{ 
  public static String temp = "";
  public static int oldCount = Integer.MAX_VALUE;
  public static String finalList;
  
  //calculates the cost of the current value by recuring
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
  
  public static String matrixM(int s[], int i, int j) 
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
          temp = "(" + matrixM(s, i, k) + " x " + matrixM(s, k+1,j) + ")";
          
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
    
    matrixM(s, 1, n - 1);
    System.out.println(finalList + " = " + oldCount);
  } 
}