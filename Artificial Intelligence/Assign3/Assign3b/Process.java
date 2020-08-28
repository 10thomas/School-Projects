import java.util.Random;
import java.util.Vector;

public class Process implements Constants 
{
  public Vector<Particle> swarm = new Vector<Particle>();
  public double[] pBest = new double[swarmSize];
  public Vector<Location> pBestLocation = new Vector<Location>();
  public double gBest;
  public Location gBestLocation;
  public double[] fitnessValueList = new double[swarmSize];
  
  Random generator = new Random();
  
  public void execute2()
  {
    initializeSwarm();
    updateFitnessList();
    
    for(int i=0; i < swarmSize; i++) 
    {
      pBest[i] = fitnessValueList[i];
      pBestLocation.add(swarm.get(i).getLocation());
    }
    
    int t = 0;
    double w;
    double err = 9999;
    
    while(t < maxIteration && err > Equations.error) 
    {
      for(int i=0; i < swarmSize; i++) 
      {
        if(fitnessValueList[i] < pBest[i]) 
        {
          pBest[i] = fitnessValueList[i];
          pBestLocation.set(i, swarm.get(i).getLocation());
        }
      }
      
      int bestParticleIndex = Utility.getMinPos(fitnessValueList);
      if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) 
      {
        gBest = fitnessValueList[bestParticleIndex];
        gBestLocation = swarm.get(bestParticleIndex).getLocation();
      }
      
      w = upperbound - (((double) t) / maxIteration) * (upperbound - lowerbound);
      
      for(int i = 0; i < swarmSize; i++) 
      {
        double r1 = generator.nextDouble();
        double r2 = generator.nextDouble();
        
        Particle p = swarm.get(i);
        
        double[] newVel = new double[dimension];
        newVel[0] = (w * p.getVelocity().getPos()[0]) 
          + (r1 * c1) * (pBestLocation.get(i).getLoc()[0] 
                           - p.getLocation().getLoc()[0]) 
          + (r2 * c2) * (gBestLocation.getLoc()[0] 
                           - p.getLocation().getLoc()[0]);
        Velocity vel = new Velocity(newVel);
        p.setVelocity(vel);
        
        double[] newLoc = new double[dimension];
        newLoc[0] = p.getLocation().getLoc()[0] + newVel[0];
        Location loc = new Location(newLoc);
        p.setLocation(loc);
      }
      
      err = Equations.evaluate2(gBestLocation) - 0;
      
      System.out.println("Iteration " + t + ": ");
      System.out.println("Best X: " + gBestLocation.getLoc()[0]);
      System.out.println("Equation 1 Value: " + Equations.evaluate2(gBestLocation));
      t++;
      updateFitnessList();
    }
    
    System.out.println();
    System.out.println("Solution found on run " + (t - 1));
    
    System.out.println("At " + gBestLocation.getLoc()[0]);
    System.out.println("We get " + Equations.evaluate2(gBestLocation));
    System.out.println();
  }
  
  public void execute() 
  {
    initializeSwarm();
    updateFitnessList();
    
    for(int i=0; i < swarmSize; i++) 
    {
      pBest[i] = fitnessValueList[i];
      pBestLocation.add(swarm.get(i).getLocation());
    }
    
    int t = 0;
    double w;
    double err = 9999;
    
    while(t < maxIteration && err > Equations.error) 
    {
      for(int i=0; i < swarmSize; i++) 
      {
        if(fitnessValueList[i] < pBest[i]) 
        {
          pBest[i] = fitnessValueList[i];
          pBestLocation.set(i, swarm.get(i).getLocation());
        }
      }
      
      int bestParticleIndex = Utility.getMinPos(fitnessValueList);
      if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) 
      {
        gBest = fitnessValueList[bestParticleIndex];
        gBestLocation = swarm.get(bestParticleIndex).getLocation();
      }
      
      w = upperbound - (((double) t) / maxIteration) * (upperbound - lowerbound);
      
      for(int i = 0; i < swarmSize; i++) 
      {
        double r1 = generator.nextDouble();
        double r2 = generator.nextDouble();
        
        Particle p = swarm.get(i);
        
        double[] newVel = new double[dimension];
        newVel[0] = (w * p.getVelocity().getPos()[0]) 
          + (r1 * c1) * (pBestLocation.get(i).getLoc()[0] 
                           - p.getLocation().getLoc()[0]) 
          + (r2 * c2) * (gBestLocation.getLoc()[0] 
                           - p.getLocation().getLoc()[0]);
        Velocity vel = new Velocity(newVel);
        p.setVelocity(vel);
        
        double[] newLoc = new double[dimension];
        newLoc[0] = p.getLocation().getLoc()[0] + newVel[0];
        Location loc = new Location(newLoc);
        p.setLocation(loc);
      }
      
      err = Equations.evaluate(gBestLocation) - 0;
      
      System.out.println("Iteration " + t + ": ");
      System.out.println("Best X: " + gBestLocation.getLoc()[0]);
      System.out.println("Value: " + Equations.evaluate(gBestLocation));
      t++;
      updateFitnessList();
    }
    
    System.out.println("\n");
    System.out.println("Solution found on  run " + (t - 1));
    
    System.out.println("At " + gBestLocation.getLoc()[0]);
    System.out.println("We get " + Equations.evaluate(gBestLocation));
  }
 
  public void initializeSwarm() 
  {
    Particle p;
    for(int i = 0; i < swarmSize; i++) 
    {
      p = new Particle();
      
      double[] loc = new double[dimension];
      loc[0] = Equations.XLow + generator.nextDouble() * (Equations.XHigh - Equations.XLow);
      Location location = new Location(loc);
      
      double[] vel = new double[dimension];
      vel[0] = Equations.VLow + generator.nextDouble() * (Equations.VHigh - Equations.VLow);
      Velocity velocity = new Velocity(vel);
      
      p.setLocation(location);
      p.setVelocity(velocity);
      swarm.add(p);
    }
  }
  
  public void updateFitnessList() 
  {
    for(int i = 0; i < swarmSize; i++)
      fitnessValueList[i] = swarm.get(i).getFitnessValue(); 
  }
}