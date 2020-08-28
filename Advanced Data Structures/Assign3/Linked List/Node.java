class Node
{
  public int data;
  public Node next;
  
  public Node()
  { 
    next = null; 
  }
  
  public Node(int val)
  {
    next = null;
    data = val;
  }
  
  public void insert(Node k)
  {
    Node previous = null;
    Node current = next;
    while(current != null && k.data > current.data)
    {
      previous = current;
      current = current.next;
    }
    if(previous==null)
      next = k;
    else
      previous.next = k;
    k.next = current;
  }
  
  public int deleteMin(int size)
  {
    if(next == null)
    {
     return size; 
    }
    else
    {
      next = next.next;
      size = size -1;
      return size;
    }
  }
  
  public Node(int val, Node nextValue)
  {
    next = nextValue;
    data = val;
  }
  
  public Node pop()
  {
    Node temp = next;
    next = next.next;
    return temp;
  }
  
  public int getData()
  {
    return data;
  }
  
  public void setData(int val)
  {
    data = val;
  }

  public Node getNext()
  {
    return next;
  }
  
  public void setNext(Node n)
  {
    next = n;
  }
}