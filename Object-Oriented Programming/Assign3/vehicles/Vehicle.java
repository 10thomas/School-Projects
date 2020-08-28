class Vehicle
{
  String driver = "CPU";
  String designation = "Vehicle";
  String nextMove = "Straight";
  int xVal = 0;
  int yVal = 0;
  String vehicleType;
  String vehicleColor= "Black";
  String type;
  
  String getDriver()
  {
    return driver;
  }
  
  String getColor()
  {
    return vehicleColor;
  }
  
  void updateDriver(String newDriver)
  {
    this.driver = newDriver;
  }
  
  void newVal(int x, int y)
  {
    this.xVal = x;
    this.yVal = y;
  }
  void does()
  {
    System.out.println(" is Driving");
  }
  void statusUpdate(String newMove)
  {
    nextMove = newMove;
  }
  void setDriver(String newDriver)
  {
    this.driver = newDriver;
  }
  String getStatus()
  {
    return nextMove;
  } 
  void updateVehicle(int x, int y, String vehicleType, String vehicleColor)
  {
    this.xVal = x;
    this.yVal = y;
    this.vehicleType = vehicleType;
    this.vehicleColor = vehicleColor;
  }
  int xVal()
  {
    return xVal;
  }
  int yVal()
  {
    return yVal;
  }
  
}