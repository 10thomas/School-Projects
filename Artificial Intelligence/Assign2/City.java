//City.java

public class City 
{
  String name;
  double x;
  double y;
  
  // Constructs a city at chosen x, y location
  public City(String name, double x, double y)
  {
    this.name = name;
    this.x = x;
    this.y = y;
  } //City
  
  //get city's name
  public String getName()
  {
    return this.name;
  } //getName
  
  // Gets city's x coordinate
  public double getX()
  {
    return this.x;
  } //getX
  
  // Gets city's y coordinate
  public double getY()
  {
    return this.y;
  } //getY
  
  // Gets the distance from the curret city to the nxt city
  public double distance(City city)
  {
    double xDistance = Math.abs(getX() - city.getX());
    double yDistance = Math.abs(getY() - city.getY());
    double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
    
    return distance;
  } //distanceTo
  
  public String toString()
  {
    return getName() + " " + getX() + ", " + getY();
  } // toString
}