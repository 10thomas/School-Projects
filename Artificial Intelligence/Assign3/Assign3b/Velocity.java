public class Velocity 
{
  public double[] vel;
  
  public Velocity(double[] vel) 
  {
    super();
    this.vel = vel;
  } //default Velocity
  
  public double[] getPos() 
  {
    return vel;
  } //getPos
  
  public void setPos(double[] vel) 
  {
    this.vel = vel;
  } //setPos
} //Velocity