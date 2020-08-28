// GA.java

/* 
 * The Genetic Algporithm for this program find the fittest paths among the population and applies mutation
 * and crossover to it and creates a new population using those children
 * it used tournament selection to find the fittest parents and uses that to find elitism
 * 
*/
public class GA 
{
  public static final int tournamentSize = 10;
  public static final boolean elitism = true;
  
  public static Population evolvePopulation(Population pop, double mutationRate, double crossoverRate) 
  {
    Population newPopulation = new Population(pop.populationSize(), false);
    
    // Keep our best individual if elitism is enabled
    int elitismValue = 0;
    if (elitism) 
    {
      newPopulation.saveTravel(0, pop.getFittest());
      elitismValue = 1;
    }
    
    // Crossover rate
    for (int i = elitismValue; i < newPopulation.populationSize(); i++) 
    {
      Travel parent1 = selection(pop);
      Travel parent2 = selection(pop);
      
      Travel child = crossover(parent1, parent2);
      
      if (Math.random() >= crossoverRate)
        child = parent1;
      newPopulation.saveTravel(i,child);
    }

    // Mutate the new population
    for (int i = elitismValue; i < newPopulation.populationSize(); i++) 
      mutate(newPopulation.getTravel(i), mutationRate);
    
    //System.out.println(pop.getFittest().getDistance());
    
    return newPopulation;
  } //evolvePopulation
  
  public static void mutate(Travel tour, double mutationRate) 
  {
    for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++)
    {
      if(Math.random() < mutationRate)
      {
        int tourPos2 = (int) (tour.tourSize() * Math.random());
        
        City city1 = tour.getCity(tourPos1);
        City city2 = tour.getCity(tourPos2);
        
        //swaps the cities
        tour.setCity(tourPos2, city1);
        tour.setCity(tourPos1, city2);
      }
    }
  } //mutuate
  
  public static Travel crossover(Travel parent1, Travel parent2) 
  {
    Travel child = new Travel();
    int startPos = (int) (Math.random() * parent1.tourSize());
    int endPos = (int) (Math.random() * parent1.tourSize());
    
    for (int i = 0; i < child.tourSize(); i++) 
    {
      if (startPos < endPos && i > startPos && i < endPos) 
        child.setCity(i, parent1.getCity(i));
      else if (startPos > endPos) 
      {
        if (!(i < startPos && i > endPos)) 
          child.setCity(i, parent1.getCity(i));
      }
    }
    
    // Loop through parent2's city tour
    for (int i = 0; i < parent2.tourSize(); i++) 
    {
      if (!child.containsCity(parent2.getCity(i))) 
      {
        for (int j = 0; j < child.tourSize(); j++) 
        {
          //add city if there sint one
          if (child.getCity(j) == null) 
          {
            child.setCity(j, parent2.getCity(i));
            break;
          }
        }
      }
    }
    return child;
  }// crossover
  
  public static Travel selection(Population pop) 
  {
    Population tournament = new Population(tournamentSize, false);
    for (int i = 0; i < tournamentSize; i++) 
    {
      int randomId = (int) (Math.random() * pop.populationSize());
      tournament.saveTravel(i, pop.getTravel(randomId));
    }
    Travel fittest = tournament.getFittest();
    return fittest;
  }//selection
}//GA