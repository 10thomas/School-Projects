//Guide.java

import java.util.ArrayList;

public class Guide
{
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    public static void addCity(City city) 
    {
      destinationCities.add(city);
    } //addCity
    
    public static City getCity(int index)
    {
      return (City)destinationCities.get(index);
    } //getCity
    
    public static int numberOfCities()
    {
      return destinationCities.size();
    } //numberOfCities
} //Guide