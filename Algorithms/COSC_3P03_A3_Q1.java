//Thomas Tsinokas
//5618293
//COSC 3P03
//Question 1
public class COSC_3P03_A3_Q1
{
  public static int tempList = 0;
  public static String maxList = "";
  
  public static int longestAscendingSubsequence(int[] s, int i, int n, int prev, String list)
  {
    if (i == n) 
    {
      //compares the current list we are checking to see if its longer then pasts lists
      //if it is we store that as the new longest we store that o be printed at the end
      String[] listSize = list.split(" "); 
      if(listSize.length > tempList)
      {
        tempList = listSize.length;
        maxList = list;
      }
      return 0;
    }
    int adv = longestAscendingSubsequence(s, i+1, n, prev, list);
    int hold = 0;
    if (s[i] > prev)
      hold = 1 + longestAscendingSubsequence(s, i+1, n, s[i], list +s[i] + " ");
    return Integer.max(hold, adv);
  }
  public static void main(String[] args)
  {
    int s[] = {11, 17, 5, 8, 6, 4, 7, 12, 3}; 
    String list = "";
    int listSize = longestAscendingSubsequence(s, 0, s.length, Integer.MIN_VALUE, list);
    System.out.print("With the array S = ");
    for(int i = 0; i < s.length-1; i++)
      System.out.print(s[i] + ", ");
    System.out.println(s[s.length-1]);
    System.out.print("The Longest Ascending Subsequence is " + maxList);
    System.out.println("with a length of " + listSize);
  }
}