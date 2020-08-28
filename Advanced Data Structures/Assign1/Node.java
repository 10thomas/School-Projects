//Thomas Tsinokas
//COSC 2P03
//SEPT 28 2015
class Node
{
  Node next;
  String data;
  
  public Node(String _data)
  {
    next = null;
    data = _data;
  }
  
  public Node(String _data, Node _next)
  {
    next = _next;
    data = _data;
  }
  
  public String value()
  {
    return data;
  }
  
  public void setValue(String _data)
  {
    data = _data;
  }
  
  public void setNext(Node _next)
  {
    next = _next;
  }
  
  public Node getNext()
  {
    return next;
  }
}