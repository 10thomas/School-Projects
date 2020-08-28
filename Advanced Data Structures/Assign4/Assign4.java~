//DR Java
//Thomas Tsinokas
//COSC 2P03
//DEC 07 2015

/*
 *
 * This priogram takes a .txt file with the firs input being an arrays length for the row and column
 * followed by the array and will tell you if it is a Euler circuit or not. if it is not a circuit
 * it will print out the largest subset that is
 * 
 * It then will print the circuit.largest subset followed by which one it is and its path
 *
 */
import java.util.*;
import BasicIO.*;

public class Assign4 
{
  private static ASCIIDataFile data; 
  private static int time = 1;
  public static boolean isTrue = false;
  public static int count = 0;
  public static int count2 = 0;
  public static String test[];
  public static String end = "";
  
  private static void removeEdge(Graph g, int x, int y)
  {
    if(g == null)
    {
      return;
    }
    Integer vNode = g.adj[x].get(y);
    g.list[x].remove(vNode);
    g.adj[x].remove(vNode);
    
    Integer uNode = g.adj[y].get(x);
    g.list[y].remove(uNode);
    g.adj[y].remove(uNode);
  }
  
  private static boolean DFS(Graph g, int val, int x, int y, int[] circuit, int[] end, int[] parent)
  {
    circuit[val] = end[val] = time++;
    int i=0;
    int size=0;
    int next=0;
    size = g.list[val].size();
    
    for(i=0; i < size; i++)
    {
      next = (int) g.list[val].get(i);  
      if(circuit[next] == -1)
      {
        parent[next] = val;
        if(DFS(g, next, x, y, circuit, end, parent) == true)
        {
          return true;
        }
        end[val] = Math.min(end[val], end[next]);
        if(end[next] > circuit[val] && ( (val == x && next == y) || (val == y && next == x) ))
        {
          return true;
        }
      }
      else if(next != parent[val])
      {
        end[val] = Math.min(end[val], circuit[next]);
      }
    }
    return false;
  }
  
  private static boolean BridgeEdge(Graph g, int u, int v)
  {
    int[] circuit = new int[g.V];
    int[] end = new int[g.V];
    int[] parent = new int[g.V];
    Arrays.fill(circuit,  -1);
    Arrays.fill(end,  -1);
    Arrays.fill(parent, -1);
    return DFS(g, 0, u, v, circuit, end, parent);
  }
  
  private static boolean NextEdge(Graph g, int u, int v)
  {
    int size = g.list[u].size();
    if(size == 1)
    {
      return true;
    }
    return !BridgeEdge(g, u, v);
  }
  
    private static void EulerPathCircuitUtil(Graph g, int u)
    {    
      if(g == null)
      {
        return;
      }
      int v = 0;
      for(int i=0; i<g.list[u].size(); i++)
      {
        v = (int) g.list[u].get(i);
        if(NextEdge(g, u, v))
        {
          test[count] = test[count] + (char)(u+65);
          if( i == g.list[u].size()-1)
          {
            end = "" + (char)(v+65);
          }
          removeEdge(g, u, v);
          EulerPathCircuitUtil(g, v);
        }
      }
    }
  
    //checks to see if their is a circuit
    private static boolean EulerPathCircuit(Graph g)
    {
      boolean check = true;
      if(g == null)
      {
        return check;
      }
      
      int u = 0;
      int i = 0;
      for(i = 0; i < g.V; i++)
      {
        if((g.adj[i].size() & 1) == 1)
        {
          u = i;
          check = false;
          break;
        }
      }
      
      if(i == g.V)
      {
        check = true;
      }
      EulerPathCircuitUtil(g, u);
      return check;
    }
    
    public static void print(int[][] graph)
    {
      for(int i = 0; i < graph.length; i++)
      {
        for(int j = 0; j < graph.length; j++)
        {
          System.out.print(graph[i][j]);
        }
        System.out.println();
      }
    }
    
    //deletes an entire line and stores it in a new smaller array and returns that array to the program
    public static int[][] removeLine(int[][] graph)
    {
      int size = graph.length;
      int[][] test2 = new int [size-1][size-1];
      if (size > 0)
      {
        for(int start = 0; start < size; start++)
        {
          for(int i = 0; i < size; i++)
          {
            for(int j = 0; j < size; j++)
            {
              if(i != start)
              {
                if(j != start)
                {
                  //for top left
                  if( i < start && j < start)
                  {
                    test2[i][j] = graph[i][j];
                  }
                  //bottom right
                  else if(i > start && j > start)
                  {
                    test2[i-1][j-1] = graph[i][j];
                  }
                  //bottom left
                  else if(i > start  && j < start && i != size-1)
                  {
                    test2[i][j] = graph[i+1][j];
                  }
                  //top right 
                  else if(j > start && i < start && j!= size-1)
                  {
                    test2[i][j] = graph[i][j+1];
                  }
                }
              }              
            }
          }
        }
      }    
      return test2;
    }
    
    public static int[][] read()
    {
      data = new ASCIIDataFile();
      int n = data.readInt();
      int[][] readable = new int[n][n];
      for(int i = 0; i < n; i++)
      {
        for(int j = 0; j < n; j++)
        {
          readable[i][j] = data.readInt();
        }
      }
      
      return readable;
    }
    
    public static void add(int[][]graph)
    {
      Graph g = new Graph(graph.length);
      for(int i = 0; i < graph.length; i++)
      {
        for(int j = 0; j < graph.length; j++)
        {
          if(graph[i][j] == 1)
          {
            g.addEdge(i,j);
          }      
        }
      }
      boolean check = false;
      while (isTrue == false)
      {
        check = EulerPathCircuit(g);
        if(check == true)
        {        
          print(graph);
          isTrue = true;
          break;
        }
        count++;
        graph = removeLine(graph);
        add(graph);       
      }
    }
    
    public static void main(String[] args)
    {
      int[][] graph;
      graph = read();
      
      test = new String[graph.length];
      for(int i = 0; i < graph.length; i++)
      {
        test[i] = ""; 
      }
      add(graph);
      
      if(count == 0)
      {
        System.out.print("Euler Circuit is: ");      
      }
      else if (count != 0)
      {
        System.out.print("Largest Euler subset: ");
      }
      System.out.println(test[count]+ end);
    }
}

class Graph 
{
  int V;
  HashMap<Integer, Integer> adj[];
  LinkedList<Integer> list[];
  
  public Graph(int V)
  {
    this.V = V;
    adj = new HashMap[V];
    list = new LinkedList[V];
    for(int i=0 ; i<V ; i++)
    {
      adj[i] = new HashMap<Integer, Integer>();
      list[i] = new LinkedList<Integer>();
    }
  }
  
  public void addEdge(int u, int v)
  {
    Integer obj = new Integer(v);
    list[u].addLast(obj);
    adj[u].put(v, obj);
  }
}