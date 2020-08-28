public class lightStatusNode
{
  public lightStatusNode next;
  public String status;
  public lightStatusNode (String status)
  {
    this.status = status;
  }
  public void setStatus(String status)
  {
    this.status = status;  
  }
  public void setNext(lightStatusNode node)
  {
    next = node;
  }
  public String getStatus()
  {
    return status;
  }
  public lightStatusNode getNext()
  {
    return next;
  }
}