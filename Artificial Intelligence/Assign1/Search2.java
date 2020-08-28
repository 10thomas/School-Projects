// Search2.java
// Thomas Tsinokas
// 5618293
// COSC 3P71
// Oct 17 2016

// The program sets the first n int of a one dimensial array to be valid, 
// It moves them randomly along those comluns based on the seed variable
// then it stores them into a node and traverses by moving one of the queens 
// and placing it as the next node. if the program reaches a point where a 
// childs path is no longer a valid option it recurses. removing that path 
// and moving to the next child that is a different path
// when it finds the path that has no threatened queens it exits

import java.util.Random;
import java.util.Arrays;
import java.util.Vector;

public class Search2 
{
  public static void traverse(Node board) 
  {    
    Vector<Node> allPaths = new Vector<Node>();
    Vector<Node> correctPaths = new Vector<Node>();
    Node path = null;
    boolean solve = false;
    
    allPaths.add(board);
    while(!allPaths.isEmpty()) 
    {
      int worst = -1;
      int value = -1;
      for (int i = 0; i < allPaths.size(); i++) 
      {
        // find the path with the highest heuristic
        if (allPaths.get(i).getHeuristic() > value) 
        {
          value = allPaths.get(i).getHeuristic();
          worst = i;
        }
      }
      //remove that path
      path = allPaths.remove(worst);
      //take the changed path and add it to the list of possibly correct paths
      correctPaths.add(path);
      
      if (path.solved()) 
      {
        solve = true;
        break;
      } 
      else 
      {
        Node[] bestChild = path.getChildren();
        for (Node node : bestChild) 
        {
          if (node == null) 
            continue;
          if (allPaths.contains(node) || correctPaths.contains(node)) 
            continue;
          if (node.getHeuristic() >= path.getHeuristic()) 
            allPaths.add(node);
        }
      }
    }
    
    if (solve) 
    {
      printBoard(path.getBoard());
      System.out.println("Solved!");
    } 
    else 
      System.out.println("Stuck");
  } //traverse
  
  public static void printBoard(int[] board) 
  {
    int[] newBoard = new int[board.length * board.length];
    for (int i = 0; i < board.length; i++) 
      newBoard[board[i]] = 1;
    
    for (int i = 0; i < newBoard.length; i++)
    {
      if (i != 0 && i % board.length == 0) 
        System.out.println();
      if (newBoard[i] == 1) 
      {
        System.out.print("Q");
      }
      else 
        System.out.print("*");
    }
    System.out.println();
  } //printBoard
  
  public static void main(String[] args) 
  {
    int n = 8;
    int seed = 4;
    
    System.out.println("N = " + n);
    System.out.println("Seed = " + seed);
    System.out.println();
    
    Random rand = new Random(seed);
    //1 dimensial array so i dont have to have double for loops to progress it all
    int[] startBoard = new int[n * n];
    
    //blank start board where verything is on the first line
    for (int i = 0; i < n*n; i++) 
      startBoard[i] = i;
    
    // moves each  column to a random row so that there is exactly one queen per column 
    for(int i = 0; i < n; i++)
      startBoard[i] = i + rand.nextInt(n-1) * n;
    
    int[] board = new int[n];
    //copy the array so the location is different when added to the node
    System.arraycopy(startBoard, 0, board, 0, board.length);
    Arrays.sort(board);
    
    System.out.println("Start board");
    printBoard(board);
    System.out.println();
    Node head = new  Node(board);    
    System.out.println("Found the solution");
    traverse(head);
  }//main
} //  Search