public class Node
{
  public String name;
  public double x;
  public double y;  
  public Node next;
  
  public String map;
  public double distance;
  
  public Node()
  {
    this.name = "";
    this.x = 0;
    this.y = 0;
    this.next = null;
  }
  
  public Node(String name, double x, double y) 
  {
    this.name = name;
    this.x = x;
    this.y = y;
    this.next = null;
  } //Node
  
  public Node(String map, double distance)
  {
    this.map = map;
    this.distance = distance;
  }
  
  public String getmap()
  {
    return map;
  }
  
  public Double getDistance()
  {
    return distance;
  }
  
  public String Index()
  {
    return name;
  }
  
  public void nextNode(Node next)
  {
    this.next = next;
  }

  public Node next()
  {
    return next;
  }
  
  public double nodeX()
  {
    return x;
  }
  public double nodeY()
  {
    return y;
  }
} //  Node