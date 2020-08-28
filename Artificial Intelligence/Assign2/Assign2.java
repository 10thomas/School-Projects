//Assign2.java
//Thomas Tsinokas
//COSC 3P71
//Nov 6, 2016

/*
 * This program is to used Genetic aAlgorithm to solve the Traveling Salesman Problem and produceone of the best paths 
 * for him to take
 */

import java.util.Scanner;
import java.io.*;

public class Assign2
{
  public static double mutationRate = 1.0;
  public static double crossoverRate = 0.0;
  public static int pop_Size;
  public static String fileName = "";
  
  public static void getCrossover()
  {
    System.out.print("Enter the Crossover rate (90% = 0.90): ");
    Scanner scan = new Scanner(System.in);
    
    String temp = scan.nextLine();
    try
    {
      crossoverRate = Double.parseDouble(temp);
    }
    catch(NumberFormatException nfe)
    {
      System.out.println("Not a valid rate enter again");
      scan.close();
      getCrossover();
    }
  } //gtCrossover
  
  public static void getMutation()
  {
    System.out.print("Enter the Mutation rate (10% = 0.1): ");
    Scanner scan = new Scanner(System.in);
    
    String temp = scan.nextLine();
    try
    {
      mutationRate = Double.parseDouble(temp);
    }
    catch(NumberFormatException nfe)
    {
      System.out.println("Not a valid rate enter again");
      scan.close();
      getMutation();
    }
  } //getMutation
  
  public static void getFileName()
  {
    System.out.print("Enter the file name: ");
    Scanner scan = new Scanner(System.in);
    fileName = scan.nextLine();
    scan.close();
  } //getFileName()
  
  public static void readFile()
  {
    String line = "";
    
    try 
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      while(true)
      {
        line = bufferedReader.readLine();
        String[] splited = line.split(": ");
        
        if (splited[0].equals("DIMENSION ") || splited[0].equals("DIMENSION"))
        {
          pop_Size = Integer.parseInt(splited[1]);   
          break;
        }
      }
      
      while(true)
      {
        line = bufferedReader.readLine();
        if(line.equals("DISPLAY_DATA_SECTION") || line.equals("NODE_COORD_SECTION"));
        {
          line = bufferedReader.readLine();
          break;
        }
      }      
      
      while(true)
      {
        int count = 0;
        line = bufferedReader.readLine();
        if(line.equals("EOF"))
          break;
        String[] temp = line.split(" ");
        String[] value = line.split(" ");
        String[] seperate = line.split(" : ");
        for(int i = 0; i < 3; i++)
        {
          if(temp[i].equals(" "))
          {
          }
          else
          {
            value[count] = temp[i];
            count++;
          }
        }
        
        if(value.length == 3 && seperate.length == 1)
        {
          String cityName = value[0];
          double xVal = Double.parseDouble(value[1]);
          double yVal = Double.parseDouble(value[2]);
          
          City city = new City(cityName, xVal, yVal);
          Guide.addCity(city);
        }
      }
      bufferedReader.close();        
    }
    catch(FileNotFoundException ex) 
    {
      System.out.println("Unable to open file '" + fileName + "'");
      System.out.println("Enter a file name");
      getFileName();
      readFile();
    }
    catch(IOException ex) 
    {
      System.out.println("Error reading file '" + fileName + "'");
      System.out.println("Enter a file name");
      getFileName();
      readFile();
    }
  } //readFile()
  
  public static void main(String [] args) 
  {
    getFileName();
    readFile();
    //getCrossover();
    //getMutation();
    Population pop = new Population(pop_Size, true);
    System.out.println("Initial Distance: " + pop.getFittest().getDistance());
    //System.out.println("Map: ");
    //System.out.println(pop.getFittest());
        
    for (int i = 0; i < pop_Size; i++) 
      pop = GA.evolvePopulation(pop, mutationRate, crossoverRate);
    System.out.println("Crossover Distance: " + pop.getFittest().getDistance());
    //System.out.println("Map: ");
    //System.out.println(pop.getFittest());
  } //main
} //Assign2