// Thomas Tsinokas
// 5618293
// Cosc 3P71
// DEc 18 2016

import java.io.*;
import java.util.Scanner;

public class Assign3a
{
  public static String[] list;
  public static Node store;
  public static double min = (double) Integer.MAX_VALUE;
  public static Node order = new Node();
  public static Node top;
  public static double totalDistance = 0.0;
  public static int dim = 0;
  public static Node head = new Node();
  public static Node read;
  
  public static String getFileName()
  {
    System.out.print("Enter the file name: ");
    Scanner scan = new Scanner(System.in);
    String fileName = scan.nextLine();
    scan.close();
    return fileName;
  } //getFileName()
  
  public static void readFile(String fileName)
  {
    String line = "";
    
    try 
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      boolean t = true;
      while(t == true)
      {
        line = bufferedReader.readLine();
        String[] temp = line.split(" ");
        if(temp[0].equals("DIMENSION:"))
          dim = Integer.parseInt(temp[1]);
        else if (temp[0].equals("DISPLAY_DATA_SECTION"))
          break;
      }
      //removes all the spaces within the tt file and gets us the city index/name and its x and y coordinates for each one
      
      for(int j = 0; j < dim; j++)
      //while(true)
      {
        line = bufferedReader.readLine();
        if(line.equals("EOF"))
          break;
        int x = 0;
        String c = "";
        //remove the first few spaces
        for(int i = 0; i < line.length(); i++)
        {
          c = "" + line.charAt(i);
          if(!c.equals(" "))
          {
            x = i;
            break;          
          }
        }
        
        String newLine = "";
        for(int i = x; i < line.length(); i++)
          newLine = newLine + line.charAt(i);
        
        String[] nl = newLine.split(" ");
        String word1 = nl[0];
        
        System.out.print(word1 + " ");
        //get to the next space
        for(int i = x+1 ; i < line.length(); i++)
        {
          c = "" + line.charAt(i);
          if(c.equals(" "))
          {
            x = i;
            break;
          }
        }
        
        //get to the next word (x coord
        for(int i = x; i < line.length(); i++)
        {
          c = "" + line.charAt(i);
          if(!c.equals(" "))
          {
            x = i;
            break;          
          }
        }
        
        newLine = "";
        for(int i = x; i < line.length(); i++)
          newLine = newLine + line.charAt(i);
        nl = newLine.split(" ");
        String word2 = nl[0];
        
        System.out.print(word2 + " ");
        
        //get to the next space
        for(int i = x+1 ; i < line.length(); i++)
        {
          c = "" + line.charAt(i);
          if(c.equals(" "))
          {
            x = i;
            break;
          }
        }
        
        //get to the next word (x coord
        for(int i = x; i < line.length(); i++)
        {
          c = "" + line.charAt(i);
          if(!c.equals(" " ))
          {
            x = i;
            break;          
          }
        }
        
        newLine = "";
        for(int i = x; i < line.length(); i++)
          newLine = newLine + line.charAt(i);
        nl = newLine.split(" ");
        String word3 = nl[0];
        
        System.out.println(word3);
        
        read = new Node(word1, Double.parseDouble(word2), Double.parseDouble(word3));
        head.nextNode(read);
        head = head.next();
      }
    }
    catch(FileNotFoundException ex) 
    {
      System.out.println("Unable to open file '" + fileName + "'");
      System.out.println("Enter a file name");
      String file = getFileName();
      readFile(file);
    }
    catch(IOException ex) 
    {
      System.out.println("Error reading file '" + fileName + "'");
      System.out.println("Enter a file name");
      String file = getFileName();
      readFile(file);
    }
  } //readFile()

  public static void main(String [] args) 
  { 
    store = head;
    String file = getFileName();
    readFile(file);
    
    head = store.next();    
   
    int random = (int )(Math.random() * dim-1);
    for(int i = 0; i < random;i++)
      head = head.next(); //randomly pick a starting points
    
    Node temp = head;
    order = new Node (head.Index(), head.nodeX(), head.nodeY());
    
    top = order;
    for(int i = 0; i < dim-1; i++)
    {
      temp = getPath(temp);
      min = (double) Integer.MAX_VALUE;
    }
    order = top;
   
    //to bring us back to where we started
    String index = order.Index();
    double x1 = order.nodeX();
    double y1 = order.nodeY();
    double x2 = 0;
    double y2 = 0;
    while(order != null)
    {
      System.out.print(order.Index() + " ");
      x2 = order.nodeX();
      y2 = order.nodeY();
      order = order.next();
    }
    System.out.print(index);
    totalDistance = totalDistance + Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2)));
    System.out.println();
    System.out.println("With a distace of: " + totalDistance);
    
    order = top;
  }
  
  public static boolean checkPrevious(Node node)
  {
    boolean c = true;
    while(order != null)
    {
      if (node.Index().equals(order.Index()))
        c = false;
      order = order.next();
    }
    order = top;
    return c;
  }
  
  public static Node getPath(Node head)
  {    
    Node start = store.next();
    Node temp = new Node();
    
    while (start != null)
    {
      boolean c = checkPrevious(start);
      double calc = Math.sqrt(((head.nodeX() - start.nodeX()) * (head.nodeX() - start.nodeX())) + ((head.nodeY() - start.nodeY()) * (head.nodeY() - start.nodeY())));
      
      if (calc < min && calc != 0.0 && c == true)
      {
        temp = start;
        min = calc;
      }
      start = start.next();
    }
    
    Node t = new Node(temp.Index(), temp.nodeX(), temp.nodeY());
    
    while(order.next() != null)
    {
      order = order.next();
    }
    totalDistance = totalDistance + min;
    order.nextNode(t);
    order = order.next();
    
    return temp;
  }
}