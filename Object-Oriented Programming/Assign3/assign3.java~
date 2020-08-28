import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;import java.awt.Dimension;
import java.awt.event.*;  
import java.util.concurrent.ThreadLocalRandom;

public class assign1 extends JPanel 
{
  public static JFrame frame;
  public static  Map saveHT = null;
  public static  Map saveHB = null;
  public static  Map saveVR = null;
  public static  Map saveVL = null;
  public static  Map endHT = null;
  public static  Map endHB = null;
  public static  Map endVR = null;
  public static  Map endVL = null;
  public static  Map intHT = null;
  public static  Map intHB = null;
  public static  Map intVR = null;
  public static  Map intVL = null;
  
  public static JButton leftTurn = new JButton("Left Turn");
  public static JButton straight = new JButton("Go Straight");
  public static JButton rightTurn = new JButton("Right Turn");
    
  public static lightStatusNode light, light2;
  //basic intersection. its a 12 x 12 grid
  // l is land, where vehicles will never drive on
  // vl is teh vertical left lane
  //vr is the vertical right lane
  //ht is the horizontal top lane
  //ht is the horizontal bottom lane
  //i is the intersection
  //by spillit up the road into differnt peices it makes it easier to create the path the vehicle can drive on
  public static String[][] board = {
        {"l", "l", "l", "l", "vl", "vr", "l", "l", "l", "l"},
        {"l", "l", "l", "l", "vl", "vr", "l", "l", "l", "l"},
        {"l", "l", "l", "l", "vl", "vr", "l", "l", "l", "l"},
        {"l", "l", "l", "l", "vli", "vr", "l", "l", "l", "l"},
        
        {"ht", "ht", "ht", "ht", "i", "i", "hti", "ht", "ht", "ht"},
        {"hb", "hb", "hb", "hbi", "i", "i", "hb", "hb", "hb", "hb"},
        
        {"l", "l", "l", "l", "vl", "vri", "l", "l", "l", "l"},
        {"l", "l", "l", "l", "vl", "vr", "l", "l", "l", "l"},
        {"l", "l", "l", "l", "vl", "vr", "l", "l", "l", "l"},
        {"l", "l", "l", "l", "vl", "vr", "l", "l", "l", "l"}
      };
   
  //the display for the intersection
  public void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    //inital light is red
    String intLight = "Red";
    
    
    //loops through the 2d array of the intersection and draws a sqare for each value that is colored dependnig 
    //on the value inisde the array
    for(int i = 0; i < 10; i++)
    {
      for(int j = 0; j < 10; j++)
      {
        if ( board[i][j] == "l") //land aka not where i can can drive
        {
          g.setColor(Color.gray);
          g.fillRect(i*50, j*50, 50, 50);
        }
        else if ( board[j][i] == "vr") // vertical right lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 50, 50);
          g.setColor(Color.yellow);
          g.fillRect(i*50, j*50, 1, 50);
        }
        else if ( board[j][i] == "vl") //vertical left lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 50, 50);
        }
        else if ( board[j][i] == "hb") //horizontal bottom lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 50, 50);
          g.setColor(Color.yellow);
          g.fillRect(i*50, j*50, 50, 1);
        }
        
        else if ( board[j][i] == "ht") //horizontal top lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 50, 50);
        }
        else if ( board[j][i] == "i") //intersection
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 50, 50);
        }
        else if (board[j][i] == "vri") //the space before the intersection for the vertical right lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50+5, 50, 45);
          g.setColor(Color.yellow);
          g.fillRect(i*50, j*50, 1, 50);
          //light
          if(light.getStatus().equals("Red"))
            g.setColor(Color.red);
          else if(light.getStatus().equals("Yellow"))
            g.setColor(Color.yellow);
          else if(light.getStatus().equals("Green"))
            g.setColor(Color.green);
          g.fillRect(i*50, j*50, 50, 5);
        }
        else if (board[j][i] == "vli") //the space before the intersection for the vertical left lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 50, 45);
          //light
          if(light.getStatus().equals("Red"))
            g.setColor(Color.red);
          else if(light.getStatus().equals("Yellow"))
            g.setColor(Color.yellow);
          else if(light.getStatus().equals("Green"))
            g.setColor(Color.green);
          g.fillRect(i*50, (j+1)*50-5, 50, 5);
        }
        else if (board[j][i] == "hbi") //the space before the intersection for the horizontal bottom lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50, j*50, 45, 50);
          g.setColor(Color.yellow);
          g.fillRect(i*50, j*50, 50, 1);
          //light2
          if(light2.getStatus().equals("Red"))
            g.setColor(Color.red);
          else if(light2.getStatus().equals("Yellow"))
            g.setColor(Color.yellow);
          else if(light2.getStatus().equals("Green"))
            g.setColor(Color.green);
          g.fillRect((i+1)*50-5, j*50, 5, 50);
        }
        else if (board[j][i] == "hti") //the space before the intersection for the horizontal top lane
        {
          g.setColor(Color.black);
          g.fillRect(i*50+5, j*50, 45, 50);
          //light2
          if(light2.getStatus().equals("Red"))
            g.setColor(Color.red);
          else if(light2.getStatus().equals("Yellow"))
            g.setColor(Color.yellow);
          else if(light2.getStatus().equals("Green"))
            g.setColor(Color.green);
          g.fillRect(i*50, j*50, 5, 50);
        }
      }
    }    
    //screates a linked list that is ach lane, every node inside the list will either have a car or be empty
    Map scan;
    scan = saveHB;
    while(scan != null)
    {
      //each ehicle tpye is a different color so its easy to see which vehicle is which
      if(scan.getVehicle() != null)
      {
        if (scan.getVehicle().getColor().equals("Blue"))
          g.setColor(Color.blue);
        else if (scan.getVehicle().getColor().equals("Red"))
          g.setColor(Color.red);
         else if (scan.getVehicle().getColor().equals("Gray"))
          g.setColor(Color.gray);
      }
      //if there is a vehicle in this space draw the vehicle
      if(scan.getVehicle() != null)
      {
        g.fillRect(scan.getXCoord()*50,scan.getYCoord()*50+5, scan.getVehicle().xVal(), scan.getVehicle().yVal());
        //System.out.println("At: " + scan.getXCoord() + " " + scan.getYCoord()+ " " + scan.getVehicle().xVal() + " " + scan.getVehicle().yVal() + " " + scan.getVehicle().vehicleType);
      }
      scan = scan.Next();
    }
    scan = saveHT;
    while(scan != null)
    {
      if(scan.getVehicle() != null)
      {
        if (scan.getVehicle().getColor().equals("Blue"))
          g.setColor(Color.blue);
        else if (scan.getVehicle().getColor().equals("Red"))
          g.setColor(Color.red);
         else if (scan.getVehicle().getColor().equals("Gray"))
          g.setColor(Color.gray);
      }
      if(scan.getVehicle() != null)
      {
        g.fillRect(scan.getXCoord()*50,scan.getYCoord()*50+5, scan.getVehicle().xVal(), scan.getVehicle().yVal());
        //System.out.println("At: " + scan.getXCoord() + " " + scan.getYCoord()+ " " + scan.getVehicle().xVal() + " " + scan.getVehicle().yVal() + " " + scan.getVehicle().vehicleType);
      }
      scan = scan.Next();
    }
    
    scan = saveVR;
    while(scan != null)
    {
      if(scan.getVehicle() != null)
      {
        if (scan.getVehicle().getColor().equals("Blue"))
          g.setColor(Color.blue);
        else if (scan.getVehicle().getColor().equals("Red"))
          g.setColor(Color.red);
         else if (scan.getVehicle().getColor().equals("Gray"))
          g.setColor(Color.gray);
      }
      if(scan.getVehicle() != null)
      {
        g.fillRect(scan.getXCoord()*50,scan.getYCoord()*50+5, scan.getVehicle().xVal(), scan.getVehicle().yVal());
        //System.out.println("At: " + scan.getXCoord() + " " + scan.getYCoord()+ " " + scan.getVehicle().xVal() + " " + scan.getVehicle().yVal() + " " + scan.getVehicle().vehicleType);
      }
      scan = scan.Next();
    }
    scan = saveVL;
    while(scan != null)
    {
      if(scan.getVehicle() != null)
      {
        if (scan.getVehicle().getColor().equals("Blue"))
          g.setColor(Color.blue);
        else if (scan.getVehicle().getColor().equals("Red"))
          g.setColor(Color.red);
         else if (scan.getVehicle().getColor().equals("Gray"))
          g.setColor(Color.gray);
       }
      if(scan.getVehicle() != null)
      {
        g.fillRect(scan.getXCoord()*50,scan.getYCoord()*50+5, scan.getVehicle().xVal(), scan.getVehicle().yVal());
        //System.out.println("At: " + scan.getXCoord() + " " + scan.getYCoord()+ " " + scan.getVehicle().xVal() + " " + scan.getVehicle().yVal() + " " + scan.getVehicle().vehicleType);
      }
      scan = scan.Next();
    }
  }
  
  //every turn the light moves to the next color in the looped list, 
  //add addition light to extend the time the light will be that color for
  //nose the red light must be >= the light for green and yellow
  public static void lightSetUp()
  {
    lightStatusNode green = new lightStatusNode("Green");
    lightStatusNode green2 = new lightStatusNode("Green");
    lightStatusNode green3 = new lightStatusNode("Green");
    lightStatusNode yellow = new lightStatusNode("Yellow");
    lightStatusNode yellow2 = new lightStatusNode("Yellow");
    lightStatusNode yellow3 = new lightStatusNode("Yellow");
    lightStatusNode red = new lightStatusNode("Red");
    lightStatusNode red2 = new lightStatusNode("Red");
    lightStatusNode red3 = new lightStatusNode("Red");
    lightStatusNode red4 = new lightStatusNode("Red");
    lightStatusNode red5 = new lightStatusNode("Red");
    lightStatusNode red6 = new lightStatusNode("Red");
    green.setNext(green2);
    green2.setNext(green3);
    green3.setNext(yellow);
    yellow.setNext(yellow2);
    yellow2.setNext(yellow3);
    yellow3.setNext(red);
    red.setNext(red2);
    red2.setNext(red3);
    red3.setNext(red4);
    red4.setNext(red5);
    red5.setNext(red6);
    red6.setNext(green);
    light = green;
    light2 = red;
  }
  
  public static void straightScan(Map temp, Map temp2, lightStatusNode tempLight)
  {
    leftTurn.setEnabled(false);
    rightTurn.setEnabled(false);
    Boolean canGo = true;
    while(temp != null)
    {
      //if a car is at the light of an intersection
      if(temp == temp2 && temp.getVehicle() != null)
      {
        System.out.println("Yes");
        //if the light changed colors and its now yellow
        //if(light.getStatus() != light.getNext().getStatus())
        //System.out.println(light.getNext().getStatus());
        
        //if the light hasnt changed and its yellow
        if(tempLight.getStatus() == tempLight.getNext().getStatus() && tempLight.getNext().getStatus().equals("Yellow"))
          canGo = false;
        //if the light changed colors and is now red
        else if(tempLight.getStatus() != tempLight.getNext().getStatus() && tempLight.getNext().getStatus().equals("Red"))
          canGo = false;
        //if the light is red
       else if(tempLight.getStatus() == tempLight.getNext().getStatus() && tempLight.getNext().getStatus().equals("Red"))
          canGo = false;
      }
      if(temp.getVehicle() != null && canGo == true)
        goStraight(temp);
      temp = temp.Prev();
    }
  }
  
  //buttons the user can click to input
  public static void buttonSetup(JFrame f)
  {
    int x = 3;
    int y = 3;
    JPanel panel = new JPanel();
    JPanel buttonRow1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel buttonRow2 = new JPanel();
    JButton insertCar = new JButton("Insert Car");
    JButton insertBus = new JButton("Insert Bus");
    JButton insertTruck = new JButton("Insert Truck");
    rightTurn.addActionListener(new ActionListener()
                                 {  
      public void actionPerformed(ActionEvent e)
      {  
        frame.setVisible(false);
        nextTurn("right");
      }  
    }); 
    
    JButton endOfTurn = new JButton("Next Turn");
    straight.addActionListener(new ActionListener()
                                 {  
      public void actionPerformed(ActionEvent e)
      {  
        frame.setVisible(false);
        nextTurn("Straight");
      }  
    }); 
    endOfTurn.addActionListener(new ActionListener()
                          {  
      public void actionPerformed(ActionEvent e)
      {  
        frame.setVisible(false);
        nextTurn("Straight");
      }  
    }); 
    
    insertCar.addActionListener(new ActionListener()
                                  {  
      public void actionPerformed(ActionEvent e)
      {  
        frame.setVisible(false);
        insertCar.setEnabled(false);
        insertBus.setEnabled(false);
        insertTruck.setEnabled(false);
        straight.setEnabled(true);
        randomInsert("Car", "Player1");
        intersection();
      }  
    }); 
    
     insertBus.addActionListener(new ActionListener()
                                  {  
      public void actionPerformed(ActionEvent e)
      {  
        frame.setVisible(false);
        insertCar.setEnabled(false);
        insertBus.setEnabled(false);
        insertTruck.setEnabled(false);
        straight.setEnabled(true);
        randomInsert("Bus", "Player1");
        intersection();
      }  
    }); 
     
      insertTruck.addActionListener(new ActionListener()
                                  {  
      public void actionPerformed(ActionEvent e)
      {  
        
        frame.setVisible(false);
        insertCar.setEnabled(false);
        insertBus.setEnabled(false);
        insertTruck.setEnabled(false);
        straight.setEnabled(true);
        randomInsert("Truck", "Player1");
        intersection();
      }  
    }); 
    
    
    leftTurn.setEnabled(false);
    straight.setEnabled(false);
    rightTurn.setEnabled(false);
    
    buttonRow1.setLayout(new GridLayout(1, 3));
    buttonRow1.add(leftTurn);
    buttonRow1.add(straight);
    buttonRow1.add(rightTurn);
    
    buttonRow2.setLayout(new GridLayout(1, 3));
    buttonRow2.add(insertCar);
    buttonRow2.add(insertBus);
    buttonRow2.add(insertTruck);
    
    panel.setLayout(new GridLayout(3, 1));
    panel.add(buttonRow1);
    panel.add(buttonRow2);
    
    panel.add(endOfTurn);
    JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    container.add(panel);
    JScrollPane scrollPane = new JScrollPane(container);
    f.getContentPane().add(scrollPane);
  }
  public static void laneSetup()
  {
    Map horizontalTop = new Map(9,4,board[9][4]);
    saveHT = horizontalTop;
    Map horizontalBottom = new Map(0,5,board[0][5]);
    saveHB = horizontalBottom;
    Map verticalRight = new Map(5,9, board[5][9]);
    saveVR = verticalRight;
    Map verticalLeft = new Map(4,0, board[4][0]);
    saveVL = verticalLeft;
    
    
    for(int i = 8; i >= 0; i--)
    {
      
      horizontalTop.setNext(new Map(i,4, board[i][4]));
      horizontalTop.Next().setPrev(horizontalTop);
      horizontalTop = horizontalTop.Next();
      
      if(horizontalTop.getRoadType().equals("vli"))
        intHT = horizontalTop.Prev().Prev().Prev();
      
      horizontalBottom.setNext(new Map(9-i,5, board[9-i][5]));
      horizontalBottom.Next().setPrev(horizontalBottom);
      horizontalBottom = horizontalBottom.Next();
      
      if(horizontalBottom.getRoadType().equals("vri"))
        intHB = horizontalBottom.Prev().Prev().Prev();
      
      verticalRight.setNext(new Map(5,i, board[5][i]));
      verticalRight.Next().setPrev(verticalRight);
      verticalRight = verticalRight.Next();
      
      if(verticalRight.getRoadType().equals("hbi"))
        intVR = verticalRight.Prev().Prev().Prev();
      
      verticalLeft.setNext(new Map(4,9-i, board[4][9-i]));
      verticalLeft.Next().setPrev(verticalLeft);
      verticalLeft = verticalLeft.Next();
      
      if(verticalLeft.getRoadType().equals("hti"))
        intVL = verticalLeft.Prev().Prev().Prev();
    }
    endHT = horizontalTop;
    endHB = horizontalBottom;
    endVR = verticalRight;
    endVL = verticalLeft;
    horizontalTop = saveHT;
    horizontalBottom = saveHB;
    verticalRight = saveVR;
    verticalLeft = saveVL;
  }
  
  public static void changeLane(Map currentLane, Map desiredLane)
  {
    desiredLane.setVehicle(currentLane.getVehicle());
    currentLane.setVehicle(null);
  }
  
  public static void insertCar(Map lane,Boolean vDirection, String driver)
  {    
    
    Car c = new Car();
    if (vDirection == false) 
      c.changeDirection();
    Vehicle v = c;
    v.setDriver(driver);
    lane.setVehicle(v);
  }
  
  public static void insertTruck(Map lane,Boolean vDirection, String driver)
  {    
    Truck t = new Truck();
    if (vDirection == false) 
      t.changeDirection();
    Vehicle v = t;
    v.setDriver(driver);
    lane.setVehicle(t);
  }
  
  public static void insertBus(Map lane,Boolean vDirection, String driver)
  {    
    Bus b = new Bus();
    if (vDirection == false) 
      b.changeDirection();
    Vehicle v = b;
    v.setDriver(driver);
    lane.setVehicle(b);
  }
  
  //the intersection GUI
  public static void intersection()
  {
    assign1 a1 = new assign1();
    frame = new JFrame("GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(a1);
    frame.setSize(550, 550);
    frame.setVisible(true);
  }
  
  public static void goStraight(Map square)
  {
    Map temp = square;
    if(square.Next() != null)
    {
      temp = square.Next();
      temp.setVehicle(square.getVehicle());
      square.setVehicle(null);
    }
    else
    {
      square.setVehicle(null);
    }
  }
  
  //every turn there is a chance a car inters the intersection, the car is driven by a npc
  public static void randomInsert(String vType, String driver)
  {
    int randomNum;
    Map temp = null;
    Boolean dir = false;
    randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);//the 4 lanes, 1 is bottomhorizontal, 2 is top horizontal, 3 is rightvertical, 4 is left veritical lane
    if(randomNum == 1)
    {
      temp = saveHB;
      dir = false;
    }
    else if (randomNum == 2)
    {
      temp = saveHT;
      dir = false;
    }
    else if (randomNum ==3)
    {
      temp = saveVR;
      dir = true;
    }
    else if (randomNum == 4)
    {
      temp = saveVL;
      dir = true;
    }
    if(vType.equals("Car"))
      insertCar(temp, dir, driver);
    else if (vType.equals("Truck"))
      insertTruck(temp, dir, driver);
    else if (vType.equals("Bus"))
      insertBus(temp, dir, driver);
  }
  
  public static void nextTurn(String action)
  {
    straightScan(endHB, intHB, light2);
    straightScan(endHT, intHT, light2);
    straightScan(endVL, intVL, light);
    straightScan(endVR, intVR, light);
    int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);//radomly chooses a car bus or truck to isert
    if (randomNum == 1)
      randomInsert("Car", "CPU");
    else if (randomNum == 2)
      randomInsert("Bus", "CPU");
    else if (randomNum == 3)
      randomInsert("Truck", "CPU");
    
    String temp1 = light.getStatus();
    
    light = light.getNext();
    light2 = light2.getNext();
    
    String temp2 = light.getStatus();        
    intersection();
  }
  
  public static void main (String args[])
  {
    lightSetUp();
    laneSetup();
    randomInsert("Car", "CPU");
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    buttonSetup(f);
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
    intersection();
  }
}