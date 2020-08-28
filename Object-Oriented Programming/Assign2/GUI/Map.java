public class Map
{
  public Map next, prev;
  public String status = "Green";; //green, yellow, red
  public String roadType; 
  public Vehicle vehicle = null;
  public int xVal;
  public int yVal;
  public Map (int x, int y,String roadType)
  {
    this.xVal = x;
    this.yVal = y;
    this.roadType = roadType;
  }
  public int getXCoord()
  {
    return xVal;
  }
  public int getYCoord()
  {
    return yVal;
  }
  
  public void setVehicle(Vehicle vehicle)
  {
    this.vehicle = vehicle;
  }
  
  public Vehicle getVehicle()
  {
    return vehicle;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  public void setNext(Map map)
  {
    this.next = map;  
  }
  public void setPrev(Map map)
  {
    this.prev = map;
  }
  public Map Next()
  {
    return next;
  }
  public Map Prev()
  {
    return prev;
  }
  public String getStatus()
  {
    return status;
  }
  public String getRoadType()
  {
    return roadType;
  }
  
}