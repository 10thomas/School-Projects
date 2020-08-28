public class Car extends Vehicle
{
  String vehicleType = "Car";
  String vehicleColor = "Red";
  int xVal = 20;
  int yVal = 40;
  Car()
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