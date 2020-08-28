class Node
{
  public Node next;
  public int[][] data;
  public int numOfNodes = 0 ;
  
  public Node(int[][] data, Node next)
  {
    this.next = next;
    this.data = data;
  }
  
  public int[][] getData()
  {
    return data;
  }
  
  public void setData(int[][] data)
  {
    this.data = data;
  }
    
  public Node getNext()
  {
    return next;
  }
  
  public void setNext(Node next)
  {
    this.next = next;
  }
}