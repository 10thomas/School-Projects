import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class readBoard
{
  public String[][] returnBoard()
  {
    return board;
  }
  
  public static int xVal, yVal;
  public static String[][] board;
  public static void printBoard()
  {
    for(int i = 0; i < xVal; i++)
    {
      for(int j = 0; j < yVal; j++)
        System.out.print(board[i][j] + " ");
      System.out.println();
    }
  }
  public static void run()
  {
    try 
    {
      File fXmlFile = new File("board.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      
      doc.getDocumentElement().normalize();
              
      NodeList xList = doc.getElementsByTagName("boardX");
      NodeList yList = doc.getElementsByTagName("boardY");
      
      NodeList xInt = doc.getElementsByTagName("sizeX");
      NodeList yInt = doc.getElementsByTagName("sizeY");
      Node xIntNode = xInt.item(0);
      Node yIntNode = yInt.item(0);
      Element xIntElement = (Element) xIntNode;
      Element yIntElement = (Element) yIntNode;
      xVal = Integer.parseInt(xIntElement.getAttribute("index"));
      yVal = Integer.parseInt(xIntElement.getAttribute("index"));
      board = new String[xVal][yVal];
      
      for (int temp = 0; temp < xList.getLength(); temp++) 
      {
        Node xNode = xList.item(temp);
        Node yNode = yList.item(temp);
        
        if (xNode.getNodeType() == Node.ELEMENT_NODE) 
        {
          Element xElement = (Element) xNode;
          Element yElement = (Element) yNode;
          
          int i = 0;
          while ( xElement.getElementsByTagName("boardY").item(i) != null)
          {
            yNode = yList.item(i);
            yElement = (Element) yNode;
            //System.out.println("boardX index : " + xElement.getAttribute("index") + " " + yElement.getAttribute("index"));
            board[Integer.parseInt(xElement.getAttribute("index"))][Integer.parseInt(yElement.getAttribute("index"))] = xElement.getElementsByTagName("boardY").item(i).getTextContent();
            i++;
          }
        }
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String argv[]) 
  {
    run();
  }
}