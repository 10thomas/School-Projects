import BasicIO.*;

class PriorityQueue
{
  private static ASCIIDataFile data; 
  
  public static Node traverse(Node theNode,int size)
  {
    Node tmp = theNode;
    for(int j = 0; j < size; j++)
    {
      System.out.print(theNode.pop().data + " ");
    }
    System.out.println(); 
    tmp = theNode;
    return tmp;
  }
  
  public static void main(String[] args)
  {
    data = new ASCIIDataFile();
    int n = data.readInt();
    Node theNode = new Node();
    Node tmp = theNode;
    for(int i = 0; i < n; i++)
    {
      int val = data.readInt(); //the value of the array number
      Node newLink = new Node(val);
      theNode.insert(newLink);          
    } 
    int size = n;
    theNode = traverse(theNode,n);
    for(int i = 0; i < size; i++)
    {
      n = theNode.deleteMin(n); //delete min 
      theNode = traverse(theNode,n);
    }
  }
}