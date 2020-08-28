public class Bus extends Vehicle
{
  String vehicleType = "Bus";
  String vehicleColor = "Blue";
  int xVal = 25;
  int yVal = 45;
  Bus()
  {
    updateVehicle(xVal,yVal, vehicleType, vehicleColor);
  }
  String type()
  {
    return vehicleType;
  }
  int xVal()
  {
    return xVal;
  }
  int yVal()
  {
    return yVal;
  }
  void changeDirection()
  {
    int temp = xVal;
    xVal = yVal;
    yVal = temp;
  }
}