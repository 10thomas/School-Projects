public class Equations
{
  public static final double XLow = 1;
  public static final double XHigh = 4;
  public static final double VLow = -1;
  public static final double VHigh = 1;
  
  public static final double error = 1E-20; 
  
  public static double evaluate(Location location) 
  {
    double result = 0;
    double x = location.getLoc()[0]; // the "x" part of the location
    result = Math.pow((x*x*x*x)-(2*x*x*x),2); //x^4 -2x^3
    return result;
  } //evaluate
    
  public static double evaluate2(Location location) 
  {
    double result = 0;
    double x = location.getLoc()[0]; // the "x" part of the location
    int xx = 0;
    
    int sum = 30;
    double cos = 0;
    for(int i = 1; i <= sum;  i++)
    {
      xx = xx + i*i;
      cos = cos + Math.cos(2 * Math.PI * i);
    }
      
    result = 20 * Math.exp(-0.2*Math.sqrt(1/sum* xx)/x) - Math.exp(1/sum * cos)/x + 20 + Math.exp(1);
     
    return result;
  } //evaluate2
} //ProblemSet