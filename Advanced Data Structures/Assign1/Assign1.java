//DR Java
//Thomas Tsinokas
//COSC 2P03
//SEPT 28 2015
import BasicIO.*;

public class Assign1
{
  private static ASCIIDataFile data; 
  
  public static int n;
  public static int m;
  public static int i;
  public static String word;
  public static String keyWord;
  public static String newWord = "";
  public static int numberOfWords;
  public static String[] prefex;
  public static String[] suffix;

  private static void permutation(String[] prefix, String[] str) 
  {
    String test[] = new String[numberOfWords];
    String test2[] =new String[numberOfWords];
    int n = str[0].length();
    if (n == 0) 
    {
      //if the per,utation is the word we are looking for it stores each broken word 
      //into one new string for
      if (prefix[0].equals(keyWord))
      {
        for ( int k = 0; k < numberOfWords; k++)
        {
          newWord = newWord + prefix[k];
        }
      }
    }
    else 
    {
      //rearranges the letters of all the seperated strings at the same time so it all exits at once
      for (int i = 0; i < n; i++)
      {
        for ( int x = 0; x < numberOfWords; x ++)
        {
          test[x] = prefix[x] + str[x].charAt(i);
          test2[x] = str[x].substring(0, i) + str[x].substring(i+1, n);
        }
        permutation(test, test2);
      }
    }
  } 
  
  public static void readFile()
  {    
    data = new ASCIIDataFile();
    n = data.readInt();
    m = data.readInt();
    i = data.readInt();
    word = data.readString(); 
    keyWord = data.readString();
    
    numberOfWords = n/m;
    prefex = new String[numberOfWords];
    suffix  = new String [numberOfWords];
  }
  
  public static void main(String args[]) 
  { 
    readFile();
    
    //sets all the prefixes to be empty and the seprated words 
    //into the suffix so we can rearange them letter by letter
    for ( int j = 1; j <= numberOfWords; j++)
    {
      prefex[j-1] = "";
      suffix[j-1] = word.substring(j*m - m,j*m);  
    }
    
    permutation(prefex,suffix);
    linkedList list = new linkedList();

    //gets each letter on its own and stores it backwards into the linked list 
    //so when we read it the word is in order
    for (int k = 0; k < n; k++)
    {
      list.atEnd("" + newWord.charAt(k));
    }
    list.display(newWord);
    list.cipher(i, newWord);    
  }
}