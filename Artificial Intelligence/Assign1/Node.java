// Node.java
// Thomas Tsinokas
// 5618293
// COSC 3P71
// Oct 17 2016

// Node class for Searc2

import java.util.Arrays;
import java.util.LinkedList;

public class Node
{
  public Node node;
  public int[] board;
  public int[] worstQueen;
  public int heuristic;
    
  public Node(Node node, int[] board) 
  {
    this.node = node;
    this.board = new int[board.length];
    System.arraycopy(board, 0, this.board, 0, board.length);
    this.heuristic = setHeuristic();
  } //Node
  
  public Node(int[] board) 
  {
    this.board = new int[board.length];
    System.arraycopy(board, 0, this.board, 0, board.length);
    this.heuristic = setHeuristic();
  } //Node
  
  public Node getNode() 
  {
    return node;
  } //getNode
  
  public int getHeuristic() 
  {
    return heuristic;
  } //getHeuristic
  
  public int[] getBoard() 
  {
    return board;
  } //getBoard
  
  // gets the queen with the heighest heuristic value
  // sets a node to have every other possible outcome
  //
  public Node[] getChildren() 
  {
    int[] highestQueens = getHighestQueen();
    Node[] totalChildren = new  Node[(board.length * (board.length-1)) * highestQueens.length];
    int count = 0;
    int[] tempBoard = new int[board.length];
    System.arraycopy(board, 0, tempBoard, 0, board.length);
    
    for (int queen : highestQueens) 
    {
      for (int i = 0; i < (board.length*board.length);i++) 
      {
        System.arraycopy(board, 0, tempBoard, 0, board.length);
        if (boardContains(board, i) || i == queen) 
          continue;
        else 
        {
          tempBoard[queen] = i;
          Arrays.sort(tempBoard);
          totalChildren[count++] = new  Node(this,tempBoard);
        }
      }
    }
    return totalChildren;
  } //getChildren
  
  public boolean boardContains(int[] list, int item) 
  {
    for (int i = 0; i < list.length; i++) 
    {
      if (list[i] == item) 
        return true;
    }
    return false;
  } // boardContains
  
  public boolean solved() 
  {
    for (int i : worstQueen) 
    {
      if (i != 0) 
        return false;
    }
    return true;
  } //solved
  

  
  public int[] getHighestQueen() 
  {
    int queen = -1;
    int numQueens = 0;
    
    for (int i = 0; i < worstQueen.length; i++) 
    {
      if (worstQueen[i] >= queen) 
        queen = worstQueen[i];
    }
    
    for (int i = 0; i < worstQueen.length; i++) 
    {
      if (worstQueen[i] == queen) 
        numQueens++;
    }
    
    int[] mostThreatenedQueens = new int[numQueens];
    int temp = 0;
    
    for (int i = 0; i < worstQueen.length; i++) 
    {
      if (worstQueen[i] == queen) 
        mostThreatenedQueens[temp++] = i;
    }
    return mostThreatenedQueens;
  } //getHighestQueen
  
  public int setHeuristic() 
  {
    int n = board.length;
    LinkedList<Integer> list = new LinkedList<Integer>();
    int[] count = new int[board.length];
    
    for (int column = 0; column < n*n; column += n) 
    {
      for (int row = 0; row < n; row++) 
      {
        int queen = column + row;
        if (boardContains(board, queen)) 
        {
          list.add(queen);
        }
      }
      if (list.size() > 1) 
      {
        while (!list.isEmpty()) 
        {
          int removed = list.remove();
          int temp = Arrays.binarySearch(board, removed);
          count[temp]++;
        }
      }
      list.clear();
    }
    
    //vertical
    for (int row = 0; row < n; row++) 
    {
      for (int column = 0; column < n*n; column += n) 
      {
        int queen = column+row;
        if (boardContains(board, queen)) 
          list.add(queen);
      }
      if (list.size() > 1) 
      {
        while (!list.isEmpty()) 
        {
          int removed = list.remove();
          int temp = Arrays.binarySearch(board, removed);
          count[temp]++;
        }
      }
      list.clear();
    }
    
    // down diagonal
    for (int vertical = 1; vertical < (n+1); vertical++) 
    {
      for (int horizontal = 0; horizontal < vertical; horizontal++) 
      {
        int queen = ((n - vertical) * n) + (horizontal) * (n + 1);
        if (boardContains(board, queen)) 
          list.add(queen);
      }
      if (list.size() > 1) 
      {
        while (!list.isEmpty()) 
        {
          int removed = list.remove();
          int temp = Arrays.binarySearch(board, removed);
          count[temp]++;
        }
      }
      list.clear();
    }
    
    for (int horizontal = 1; horizontal < n; horizontal++) 
    {
      for (int vertical = 0; vertical < (n - horizontal); vertical++) 
      {
        int queen = horizontal + (vertical)*(n+1);
        if (boardContains(board, queen))
          list.add(queen);
      }
      if (list.size() > 1) 
      {
        while (!list.isEmpty()) 
        {
          int removed = list.remove();
          int temp = Arrays.binarySearch(board, removed);
          count[temp]++;
        }
      }
      list.clear();
    }
    
    // up diagonal
    for (int horizontal = 0; horizontal < n; horizontal++) 
    {
      for (int vertical = 0; vertical < (horizontal + 1); vertical++) 
      {
        int queen = horizontal + (vertical)*( n - 1);
        if (boardContains(board, queen))
          list.add(queen);
      }
      if (list.size() > 1) 
      {
        while (!list.isEmpty()) 
        {
          int removed = list.remove();
          int temp = Arrays.binarySearch(board, removed);
          count[temp]++;
        }
      }
      list.clear();
    }
    
    for (int horizontal = 2; horizontal < (n + 1); horizontal++) 
    {
      for (int vertical = 0; vertical < ((n + 1) - horizontal); vertical++) 
      {
        int queen = ((horizontal * n) - 1) + (vertical)* ( n - 1);
        if (boardContains(board, queen)) 
          list.add(queen);
      }
      
      if (list.size() > 1) 
      {
        while (!list.isEmpty()) 
        {
          int removed = list.remove();
          int temp = Arrays.binarySearch(board, removed);
          count[temp]++;
        }
      }
      list.clear();
    }
    
    worstQueen = count;
    int horizontalValue = n;
    
    for (int i : worstQueen) 
    {
      if (i != 0) 
        horizontalValue--;
    }
    
    return horizontalValue;
  } //setHeuristic  
} //  Node