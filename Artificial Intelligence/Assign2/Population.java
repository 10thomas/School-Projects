//Population.java

public class Population 
{
    Travel[] travels;
    
    public Population(int pop_Size, boolean valid)
    {
      travels = new Travel[pop_Size];
      if (valid) 
      {
        // Loop and create individuals
        for (int i = 0; i < populationSize(); i++) 
        {
          Travel newTravel = new Travel();
          newTravel.generateIndividual();
          saveTravel(i, newTravel);
        }
      }
    } //Population
    
    public void saveTravel(int index, Travel travel) 
    {
      travels[index] = travel;
    } //saveTravel
    
    public Travel getTravel(int index) 
    {
      return travels[index];
    } //getTravel
    
    public Travel getFittest() 
    {
      Travel fittest = travels[0];
      //finds the fittest path out of what we have
      for (int i = 1; i < populationSize(); i++) 
      {
        if (fittest.getFittest() <= getTravel(i).getFittest()) 
          fittest = getTravel(i);
      }
      return fittest;
    } // getFitest
    
    public int populationSize() 
    {
      return travels.length;
    } //populatonSize
} //Population