//Travel.java

import java.util.ArrayList;

public class Travel
{
  private ArrayList<City> travel = new ArrayList<City>();
  private double fittest = 0.0;
  private int distance = 0;
  
  public Travel()
  {
    for (int i = 0; i < Guide.numberOfCities(); i++) 
      travel.add(null);
  } //Travel
  
  public Travel(ArrayList<City> travel)
  {
    this.travel = travel;
  } //Travel
  
  public void generateIndividual() 
  {
    // Loop through all our destination cities and add them to our tour
    for (int cityIndex = 0; cityIndex < Guide.numberOfCities(); cityIndex++) 
      setCity(cityIndex, Guide.getCity(cityIndex));
  } //generateIndividual
  
  public City getCity(int currentPos) 
  {
    return (City)travel.get(currentPos);
  } //getCity
  
  public void setCity(int currentPos, City city) 
  {
    travel.set(currentPos, city);
    // If the tours been altered we need to reset the fitness and distance
    fittest = 0;
    distance = 0;
  } //setCity
  
  // Gets the tours fitness
  public double getFittest() 
  {
    if (fittest == 0) 
      fittest = 1/(double)getDistance();
    return fittest;
  } //getFitest
  
  public int getDistance()
  {
    if (distance == 0) 
    {
      int tourDistance = 0;
      // Loop through our tour's cities
      for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) 
      {
        // Get city we're travelling from
        City fromCity = getCity(cityIndex);
        // City we're travelling to
        City destinationCity;
        
        //set the last city we visit to the first city
        if(cityIndex+1 < tourSize())
          destinationCity = getCity(cityIndex+1);
        else
          destinationCity = getCity(0);
        tourDistance += fromCity.distance(destinationCity);
      }
      distance = tourDistance;
    }
    return distance;
  }//getDistance
  
  public int tourSize() 
  {
    return travel.size();
  } // tourSize
  
  public boolean containsCity(City city)
  {
    return travel.contains(city);
  } //ContainsCity
  
  public String toString() 
  {
    String geneString = "";
    for (int i = 0; i < tourSize(); i++) 
      geneString += "|" + getCity(i)+ "|" + "\n"; 
    return geneString;
  }// toString
}