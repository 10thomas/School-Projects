public class tree
{
  public Node head;
  
  public tree()
  {
    head = null;
  }
  
  public void add(int[][] data)
  {
    head = new Node(data,head);
  }  
  
  public int[][] toNext()
  {
    Node current = head.next;
    
    return null;
  }
  
  
  public void addLast(int[][] item)
  {
    Node tmp = head;
    while(tmp.next != null)
      tmp = tmp.next;
    
    tmp.next = new Node (item, null);
  } 
  
  public int[][] get(int pos)
  {
    Node tmp = head;
    for(int k = 0; k < pos; k++)
    {
      tmp = tmp.next;
    }
    return tmp.data;
  }

  public void checking(int[][] board)
  {
    System.out.println(board[0][0]+ " " +board[0][1]); 
  }
  
  public int[][] output()
  {
    Node current = head;
    int[][] test = current.getData();
    for(int i = 0; i < 4; i ++)
    {
      for(int j = 0; j < 4;j++)
      {
        System.out.print(test[i][j]+" ");
      }
      System.out.println();
    }  
    return test;
  }
  
  
}