public class Particle 
{
  public double fitnessValue;
  public Velocity velocity;
  public Location location;
  
  public Particle() 
  {
    super();
  } //Default Particle class
  
  public Particle(double fitnessValue, Velocity velocity, Location location) 
  {
    super();
    this.fitnessValue = fitnessValue;
    this.velocity = velocity;
    this.location = location;
  } //set partical class
  
  public Velocity getVelocity() 
  {
    //gets the Velocity from Velocity.java
    return velocity;
  } //getVelocity
  
  public void setVelocity(Velocity velocity) 
  {
    this.velocity = velocity;
  } //set the Velocity
  
  public Location getLocation() 
  {
    //get the location from Location.java
    return location;
  } //getLocation class
  
  public void setLocation(Location location) 
  {
    this.location = location;
  }//setLocation
  
  public double getFitnessValue() 
  {
    fitnessValue = Equations.evaluate(location);
    return fitnessValue;
  } //getFitnessValue
}