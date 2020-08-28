package game;
import java.util.ArrayList;
public class Node 
{
  public String type;
  public String color;  
  public boolean position;
  public boolean hasMoved;
  public double points;
  public ArrayList<String> killed;
  public boolean pass;
  public Node node; 
  public boolean promotion=false;
  
  public Node() 
  {
    this.type = " ";
    this.color = " ";
    this.position = false;
    this.hasMoved = false;
    this.points = 0;
    this.killed = new ArrayList<String>();  
    this.pass = false;
    
  } //Node
  
  
  public Node(String type, String color, boolean position, boolean hasMoved, double points, boolean pass) 
  {
    this.type = type;
    this.color = color;
    this.position = position;
    this.hasMoved = hasMoved;
    this.points=points;
    this.killed = new ArrayList<String>();
    this.pass = pass;
    this.promotion=false;
 
  } //Node
  
  
  /*
  public Node(String type, String color, boolean position, boolean hasMoved, double points) 
  {
    this.type = type;
    this.color = color;
    this.position = position;
    this.hasMoved = hasMoved;
    this.points=points;
    this.killed = new ArrayList<String>();
  } //Node
  
  */
  
  public static Node[][] newInstance(Node[][]a)
  {
   Node[][]b=new Node[8][8];
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     b[i][j]=new Node(a[i][j].type,a[i][j].color,a[i][j].position,a[i][j].hasMoved,a[i][j].points,a[i][j].pass);
    }
   }
   return b;
  }
  
  public void truePromote()
  {
   this.promotion=true;
  }
  public boolean getPromote()
  {
   return this.promotion;
  }
  public void falsePromote()
  {
   this.promotion=false;
  }
  public void addKilled(String s)
  {
   killed.add(s);
  }
  public String killedValue()
  { 
   return killed.remove(killed.size()-1);
  }
  public boolean isKilledValue()
  {
   if(killed.isEmpty())
   {
    return true;
   }
   return false;
  }
  public void check()
  {
   if(type.equals("p"))
    {position=true;} 
   if(type.equals("k"))
    {position=true;} 
   if(type.equals("b"))
    {position=true;} 
   if(type.equals("r"))
    {position=true;} 
   if(type.equals("Q"))
    {position=true;} 
   if(type.equals("K"))
    {position=true;} 
  
   
   
  
  }
  
  public void blank()
  {
   this.type = " ";
     this.color = " ";
     this.position = false;
     this.hasMoved = false;
     this.points = 0;
    
  }
  
 
  
  public void moved()
  {
    this.hasMoved = true;
  }
  
  public double getPoints()
  {
    return points;
  }
  
  public boolean getHasMoved()
  {
    return hasMoved;
  }
    
  public void setPiece(boolean position)
  {
   this.position = position; 
  }
  
  public Node getNode() 
  {
    return node;
  } //getNode
  
  public String getType() 
  {
    return type;
  }
  
  public String getColor()
  {
    return color;
  }
  public Boolean pieceThere()
  {
    return position;
  }
  public Boolean enPassant()
  {
    return pass;
  }
  
 } //  Node