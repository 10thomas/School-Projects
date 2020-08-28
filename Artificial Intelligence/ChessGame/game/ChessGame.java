//THomas Tsinokas
package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ChessGame extends JFrame 
{
  public static boolean castleLeft = false;
  public static boolean castleRight = false;
  public static boolean comCheck = false;
  public static boolean IPassented = false;
  public static boolean playerCheck = false;
  public static boolean wait = false;
  public static boolean[][] enPassant = new boolean[8][8];//keeps track of peices that have moved forward exactly 2 spaces
  public static boolean[][] yellow = new boolean[8][8];   //keeps track of spaces a peice can move too
  public static ChessGame loadOrNew;
  public static ChessGame fileEnter;
  public static ChessGame popUp;
  public static ChessGame startScreen;
  public static ChessGame start;
  public static Node[][] play = new Node[8][8];  // the board
  public static int prevCol = -1;
  public static int prevRow = -1;
  public static int proRow = -1;
  public static int proCol = -1;
  public static JButton[][] design = new JButton[8][8];
  public static JTextField textField;
  public static String comIs;
  public static String fileName = "Chessboard.txt"; //default chess board
  public static String playerIs;
  public static String save;
  public static String turn;
  public static int[] playerKing=new int[]{7,4};
  public static int[] compKing=new int[]{0,4};
  public static int number=2;
  
  public static void main(String[] args) 
  {    
    loadOrNew = new ChessGame(0.0);
    loadOrNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loadOrNew.pack();
    loadOrNew.setLocationRelativeTo(null);
    loadOrNew.setVisible(true);
    loadOrNew.setAlwaysOnTop(true);    
  }//main
  
  public void pieceColor()
  {
    startScreen = new ChessGame(0);
    startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    startScreen.pack();
    startScreen.setLocationRelativeTo(null);
    startScreen.setVisible(true);
    startScreen.setAlwaysOnTop(true);    
  }
  
  public ChessGame(double blank)
  {
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(4, 1));
    pane.setPreferredSize(new Dimension(200, 150));
    add(new Label("Chess Game", SwingConstants.CENTER));
    add(new Label("By Javon & Thomas", SwingConstants.CENTER));
    JButton newG = new JButton("New Game");
    JButton loadG = new JButton("Load Game");
    pane.add(newG);
    pane.add(loadG);
    newG.addActionListener (new New());
    loadG.addActionListener (new File());
  }
  
  public ChessGame(int blank)
  {
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(3, 1));
    pane.setPreferredSize(new Dimension(200, 150));
    add(new Label("Select Your Color", SwingConstants.CENTER));
    
    JButton black = new JButton("Black");
    JButton white = new JButton("White");
    pane.add(black);
    pane.add(white);
    black.addActionListener (new Option("Black"));
    white.addActionListener (new Option("White"));
  } //window for the intial ChessGame
  
  public class Option implements ActionListener
  {
    public String color;
    public Option (String color)
    {
      this.color = color;
    }
    public void actionPerformed(ActionEvent e)
    {
      playerIs = color;
      if(playerIs.equals("White"))
        comIs = "Black";
      else if(playerIs.equals("Black"))
        comIs = "White";
      startScreen.setVisible(false);
      readFile();
    }
  }
  
  public class New implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      loadOrNew.setVisible(false);

      pieceColor();
    }
  }
  
  
  
  public class File implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      loadOrNew.setVisible(false);
      enterAName();
    }
  }
  //reads a .txt file
  //the first line Player: "Color" is what which way the board in the text file is facing
  //the second line is whose turn it is
  public static void readFile()
  {
    boolean valid = true; //the board read a valid file
    //sets the intial board to be blank
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        play[i][j] = new Node();
        yellow[i][j] = false;
      }
    }
    
    String line = "";
    try 
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
            
      //where the board faces
      line = bufferedReader.readLine();
      String[] saved = line.split("\t");
      save = saved[1];
      
      //whose turn is it
      line = bufferedReader.readLine();
      String[] turnIs = line.split("\t");
      turn = turnIs[1];
      
      while(true)
      {
        line = bufferedReader.readLine();
        if(line.equals("EOF"))
          break;
        String[] splited = line.split("\t");
        String color = splited[0]; // the color
        String piece = splited[1]; //the peice
        int row = Integer.parseInt(splited[2]); //row
        int col = Integer.parseInt(splited[3]); //column
        String tf = splited[4]; //if the piece has moved
        Boolean hasMoved = false;
        if(tf.equals("true"))
          hasMoved = true;
        String temp = "";
        double val = 0;
        if(piece.equals("Rook"))
        {
          temp = "r"; 
          val = 2.5;
        }
        if(piece.equals("Knight"))
        {
          temp = "k"; 
          val = 3.0;
        }
        if(piece.equals("Bishop"))
        {
          temp = "b"; 
          val = 2.5;
        }
        if(piece.equals("King"))
        {
          temp = "K"; 
          val = 99.0;
        }
        if(piece.equals("Queen"))
        {
          temp = "Q";
          val = 8.0;
        }
        if(piece.equals("Pawn"))
        {
          temp = "p";
          val = 8.0;
        }
        
        if(!playerIs.equals(save))
        {
          row = Math.abs(row-7);
          col = Math.abs(col-7);
        }
        
        play[row][col] = new Node(temp, color, true, hasMoved, val, false); //add the peice to the board
      }
      bufferedReader.close();        
    }
    //any issues with reading the text file it will ask the user to enter the file name again
    catch(FileNotFoundException ex) 
    {
      valid = false;
      System.out.println("Unable to open file '" + fileName + "'");
      System.out.println("Enter a file name");
      enterAName();
    }
    catch(IOException ex) 
    {
      valid = false;
      System.out.println("Error reading file '" + fileName + "'");
      System.out.println("Enter a file name");
      enterAName();
    }
    //if a valid file was entered the peogram runs and opens the inital board
    if(valid == true)
     initial();
  } //readFile
  public static void enterAName()
  {
    fileEnter = new ChessGame("");
    fileEnter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fileEnter.pack();
    fileEnter.setLocationRelativeTo(null);
    fileEnter.setVisible(true);
    fileEnter.setAlwaysOnTop(true); 
  }
  //the intial window qhen the program runs, has the user pick a color of load a board from a text file

  public static void initial()
  {
    start = new ChessGame();
    start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    start.pack();
    start.setLocationRelativeTo(null);
    start.setVisible(true);
  } //board
  
  public static void board()
  {
   
  
   
   
   start.setVisible(false);
    start = new ChessGame();
    start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    start.pack();
    start.setLocationRelativeTo(null);
    start.setVisible(true); //Thomas Code
  } //board
  public ChessGame() 
  {
    //if the user is choosing which peice to promote too
    if(wait == true)
      popUp();
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(8, 8));
    pane.setPreferredSize(new Dimension(400, 400));
    
    for(int i = 0; i < 8; i++)
    {
      for (int j = 0; j < 8; j++) 
      {     
        JButton button = new JButton();
        design[i][j] = button;
        //load the pictures for the black peices
        if(play[i][j].getColor().equals("Black"))
        {
          if(play[i][j].getType().equals("r"))
            design[i][j].setIcon(new ImageIcon("Black Rook.png"));
          if(play[i][j].getType().equals("k"))
            design[i][j].setIcon(new ImageIcon("Black Knight.png"));
          if(play[i][j].getType().equals("b"))
            design[i][j].setIcon(new ImageIcon("Black Bishop.png"));
          if(play[i][j].getType().equals("Q"))
            design[i][j].setIcon(new ImageIcon("Black Queen.png"));
          if(play[i][j].getType().equals("K"))
            design[i][j].setIcon(new ImageIcon("Black King.png"));
          if(play[i][j].getType().equals("p"))
            design[i][j].setIcon(new ImageIcon("Black Pawn.png"));
        }
        //load the pictures for the white peices
        else if(play[i][j].getColor().equals("White"))
        {
          if(play[i][j].getType().equals("r"))
            design[i][j].setIcon(new ImageIcon("White Rook.png"));
          if(play[i][j].getType().equals("k"))
            design[i][j].setIcon(new ImageIcon("White Knight.png"));
          if(play[i][j].getType().equals("b"))
            design[i][j].setIcon(new ImageIcon("White Bishop.png"));
          if(play[i][j].getType().equals("Q"))
            design[i][j].setIcon(new ImageIcon("White Queen.png"));
          if(play[i][j].getType().equals("K"))
            design[i][j].setIcon(new ImageIcon("White King.png"));
          if(play[i][j].getType().equals("p"))
            design[i][j].setIcon(new ImageIcon("White Pawn.png"));
        }
        pane.add(design[i][j]);
        validate();
        colorBoard(i,j);
        if(yellow[i][j] == false)
          design[i][j].addActionListener (new Identify(i,j)); 
        else
        {
          yellow[i][j] = false;
          design[i][j].setBackground(Color.YELLOW);
          if (play[i][j].pieceThere() == true)
            design[i][j].setBackground(Color.RED);
          design[i][j].addActionListener (new Move(i,j)); 
        }
        //disables any clicking on the board so they cant continue on the game without choosing a promotion
       // if(wait == true)
         // design[i][j].setEnabled(false);
      }      
    }
    if (!playerIs.equals(turn))
    {
      
      ArrayList<int[]>holder= AI();
      int one=holder.get(1)[0];
      int two=holder.get(1)[1];
      int three=holder.get(1)[2];
      int four=holder.get(1)[3];
      
       peiceMove(one, two, three, four);
       System.out.println("Opponents turn");
       System.out.println(one + " " + two + " to " + three + " " + four);
             if(turn.equals("Black"))
         turn = "White";
      else if (turn.equals("White"))
        turn = "Black";
      
      
//AIMove(three,four,one,two);
      // print(holder);
      
      //artificial();
   
    }
  }
  
  public void artificial()
  {
  
       
   
       
       
  }
  
  //the peice titles of the board are gray or white buttons
  public static void colorBoard(int row, int col)
  {
    if((row+col) % 2 == 0)
      design[row][col].setBackground(Color.WHITE);
    else
      design[row][col].setBackground(Color.GRAY);
  } //colorboard
  
  
  public void peiceMove(int prevRow, int prevCol, int rowToMove, int colToMove)
  {
    System.out.println(prevRow + " " + prevCol + " to " + rowToMove + " " + colToMove);
    boolean weCastledLeft = false;
    boolean weCastledRight = false;
    //if the king castled
    if(colToMove == prevCol+2 && play[prevRow][prevCol].getType().equals("K"))
      weCastledRight = true;
    if(colToMove == prevCol-2 && play[prevRow][prevCol].getType().equals("K"))
      weCastledLeft = true;
    
    //moved
    play[rowToMove][colToMove] =  play[prevRow][prevCol];
    play[rowToMove][colToMove].moved();
    play[prevRow][prevCol] = new Node();
    play[rowToMove][colToMove] = new Node(play[rowToMove][colToMove].getType(), play[rowToMove][colToMove].getColor(), true, true, play[rowToMove][colToMove].getPoints(), false);
    
    if(play[rowToMove][colToMove].getType().equals("p") &&(Math.abs(rowToMove-prevRow) == 2))
      play[rowToMove][colToMove] = new Node(play[rowToMove][colToMove].getType(), play[rowToMove][colToMove].getColor(), true, true, play[rowToMove][colToMove].getPoints(), true);
    
    if(weCastledLeft == true)
    {
      play[rowToMove][colToMove + 1] = play[rowToMove][0];
      play[rowToMove][colToMove + 1].moved();
      play[rowToMove][0] = new Node(); 
    }
    
    if(weCastledRight == true)
    {
      play[rowToMove][colToMove - 1] = play[rowToMove][7];
      play[rowToMove][colToMove - 1].moved();
      play[rowToMove][7] = new Node(); 
    }
    
    if(IPassented == true)
    {
      if (playerIs.equals(turn))
        play[rowToMove+1][colToMove] = new Node();
      if (!playerIs.equals(turn))
        play[rowToMove-1][colToMove] = new Node();
    }
    //player promotion
    //if either players pawn has made it to the other wise, promote
    if((playerIs.equals(play[rowToMove][colToMove].getColor()) && rowToMove == 0 && play[rowToMove][colToMove].getType().equals("p")) || (!playerIs.equals(play[rowToMove][colToMove].getColor()) && rowToMove == 7 && play[rowToMove][colToMove].getType().equals("p")))
    {
      wait = true;
      proRow = rowToMove;
      proCol=  colToMove;
      //promotion(play[rowToMove][colToMove]);
    }
    
  }
  public class Move implements ActionListener
  {
    public int rowToMove;
    public int colToMove; 
    public Move(int rowToMove, int colToMove)
    {
      this.rowToMove = rowToMove;
      this.colToMove = colToMove;
    }
    public void actionPerformed(ActionEvent e)
    {          
      boolean move = true;
      
      System.out.println(turn);
      if(turn.equals(playerIs))
      {
        Node[][] temp = new Node[8][8];
        int t1 = 0;
        int t2 = 0;
        for(int i = 0; i < 8; i++)
        {
          for(int j = 0; j < 8; j++)
            temp[i][j] = play[i][j];
        }
        peiceMove(prevRow, prevCol, rowToMove,colToMove);
        
        for(int i = 0; i < 8; i++)
        {
          for(int j = 0; j < 8; j++)
          {
            if(play[i][j].getType().equals("K") && play[i][j].getColor().equals(playerIs))
            {
              t1 = i;
              t2 = j;
            }
          }
        }
        
        if(kingCanMove(t1,t2) == true)
        {
          yellow[t1][t2] = false;
          play = temp;
          move = false;
        }
      }
      
      if(turn.equals("Black"))
         turn = "White";
      else if (turn.equals("White"))
        turn = "Black";
      board();   
    }
  }
  //pop up window for deciding promotion
  public void popUp()
  {
    popUp = new ChessGame(false);
    popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    popUp.pack();
    popUp.setLocationRelativeTo(null);
    popUp.setVisible(true);
  }
  //window for entering a file name
 
  public ChessGame(String blank)
  {
   //System.out.println("Working Directory = " +
     //         System.getProperty("user.dir"));  
   
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(3, 1));
    pane.setPreferredSize(new Dimension(100, 60));
    add(new Label("Enter a file name"));
    textField = new JTextField(20);
    add (textField);
    JButton ok = new JButton("Ok");
    pane.add(ok);
    
    
    ok.addActionListener (new Ok());
  } //Window for the fileName Chessgame 
  
  public ChessGame(boolean blank)
  {
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(5, 1));
    pane.setPreferredSize(new Dimension(100, 200));
    add(new Label("Promote to"));
    JButton rook = new JButton("Rook");
    JButton bishop = new JButton("Bishop");
    JButton queen = new JButton("Queen");
    JButton knight = new JButton("Rook");
    pane.add(rook);
    pane.add(knight);
    pane.add(bishop);
    pane.add(queen);
    rook.addActionListener (new Piece("r",2.5));
    knight.addActionListener (new Piece("k",3));
    bishop.addActionListener (new Piece("b",2.5));
    queen.addActionListener (new Piece("Q",8));
  } //Window for the promotion Chessgame
  

  public static boolean checkMove()
  {
    boolean r = false;
    for(int i = 0; i < 8 ; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        if(yellow[i][j] == true)
          r = true;
      }
    }
    return r;
  }
  
  public void diagnal(int row, int col)
  {
    //up left
    for(int i = 1; i < 6; i++)
    {
      if (row-i >= 0 && col-i >= 0)
      {
        if (play[row-i][col-i].pieceThere() == false)
          yellow[row-i][col-i] = true;
        if(play[row-i][col-i].pieceThere() == true)
        {
          if(!play[row-i][col-i].getColor().equals(play[row][col].getColor()))
            yellow[row-i][col-i] = true;
          break;
        }
      }
    }
    //down left
    for(int i = 1; i < 6; i++)
    {
      if (row+i < 8 && col-i >= 0)
      {
        if (play[row+i][col-i].pieceThere() == false)
          yellow[row+i][col-i] = true;
        if(play[row+i][col-i].pieceThere() == true)
        {
          if(!play[row+i][col-i].getColor().equals(play[row][col].getColor()))
            yellow[row+i][col-i] = true;
          break;
        }
      }
    }
    
    //up right
    for(int i = 1; i < 6; i++)
    {
      if (row-i >= 0 && col+i < 8)
      {
        if (play[row-i][col+i].pieceThere() == false)
          yellow[row-i][col+i] = true;
        if(play[row-i][col+i].pieceThere() == true)
        {
          if(!play[row-i][col+i].getColor().equals(play[row][col].getColor()))
            yellow[row-i][col+i] = true;
          break;
        }
      }
    }
    
    //down right
    for(int i = 1; i < 6; i++)
    {
      if (row+i < 8 && col+i < 8)
      {
        if (play[row+i][col+i].pieceThere() == false)
          yellow[row+i][col+i] = true;
        if(play[row+i][col+i].pieceThere() == true)
        {
          if( !play[row+i][col+i].getColor().equals(play[row][col].getColor()))
            yellow[row+i][col+i] = true;
          break;
        }
      }
    }    
  } //diagnal
  
  //knight
  public void L(int row, int col)
  {
    //Row-2, Col+1
    if(row-2 >=0 && col +1 < 8)
    {
      if(play[row-2][col+1].pieceThere() == false)
        yellow[row-2][col+1] = true;
      if(play[row-2][col+1].pieceThere() == true && !play[row-2][col+1].getColor().equals(play[row][col].getColor()))
        yellow[row-2][col+1] = true;
    }
    
    //Row+1,col-2
    if(row+1 < 8 && col-2 >= 0)
    {
      if(play[row+1][col-2].pieceThere() == false)
        yellow[row+1][col-2] = true;
      if(play[row+1][col-2].pieceThere() == true && !play[row+1][col-2].getColor().equals(play[row][col].getColor()))
        yellow[row+1][col-2] = true;
    }
    
    //Row -1, Col -2
    if(row-1 >=0 && col-2 >=0)
    {
      if(play[row-1][col-2].pieceThere() == false)
        yellow[row-1][col-2] = true;
      if(play[row-1][col-2].pieceThere() == true && !play[row-1][col-2].getColor().equals(play[row][col].getColor()))
        yellow[row-1][col-2] = true;
    } 
    
    //Row-1, Col+2
    if(row-1 >=0 && col+2 < 8)
    {
      if(play[row-1][col+2].pieceThere() == false)
        yellow[row-1][col+2] = true;
      if(play[row-1][col+2].pieceThere() == true && !play[row-1][col+2].getColor().equals(play[row][col].getColor()))
        yellow[row-1][col+2] = true;
    }
    
    //Row-2, Col-1
    if(row-2 >=0 && col -1 >=0)
    {
      if(play[row-2][col-1].pieceThere() == false)
        yellow[row-2][col-1] = true;
      if(play[row-2][col-1].pieceThere() == true && !play[row-2][col-1].getColor().equals(play[row][col].getColor()))
        yellow[row-2][col-1] = true;
    }
    
    //Row+1,Col+2
    if(row+1 < 8 && col+2 < 8)
    {
      if(play[row+1][col+2].pieceThere() == false)
        yellow[row+1][col+2] = true;
      if(play[row+1][col+2].pieceThere() == true && !play[row+1][col+2].getColor().equals(play[row][col].getColor()))
        yellow[row+1][col+2] = true;
    }
    
    //Row+2, Col +1
    if(row+2 < 8 && col+1 < 8)
    {
      if(play[row+2][col+1].pieceThere() == false)
        yellow[row+2][col+1] = true;
      if(play[row+2][col+1].pieceThere() == true && !play[row+2][col+1].getColor().equals(play[row][col].getColor()))
        yellow[row+2][col+1] = true;
    }
    
    //Row +2, Col-1
    if(row+2 < 8 && col-1 >= 0)
    {
      if(play[row+2][col-1].pieceThere() == false)
        yellow[row+2][col-1] = true;
      if(play[row+2][col-1].pieceThere() == true && !play[row+2][col-1].getColor().equals(play[row][col].getColor()))
        yellow[row+2][col-1] = true;
    }
  } //L
  
  public void line(int row, int col)
  {
    //up
    for(int i = 1; i < 8; i++)
    {
      if(row - i >= 0)
      {
        if(play[row-i][col].pieceThere() == false)
          yellow[row-i][col] = true;
        if(play[row-i][col].pieceThere() == true)
        {
          if(!play[row-i][col].getColor().equals(play[row][col].getColor()))
            yellow[row-i][col] = true;
          break;
        }
      }
    }
    
    //down
    for(int i = 1; i < 8; i++)
    {
      if(row + i < 8)
      {
        if(play[row+i][col].pieceThere() == false)
          yellow[row+i][col] = true;
        if(play[row+i][col].pieceThere() == true )
        {
          if(!play[row+i][col].getColor().equals(play[row][col].getColor()))
            yellow[row+i][col] = true;
          break;
        }
      }
    }
    
    //right
    for(int i = 1; i < 8; i++)
    {
      if(col + i < 8)
      {
        if(play[row][col+i].pieceThere() == false)
          yellow[row][col+i] = true;
        if(play[row][col+i].pieceThere() == true)
        {
          if(!play[row][col+i].getColor().equals(play[row][col].getColor()))
            yellow[row][col+i] = true;
          break;
        }
      }
    }
    
    for(int i = 1; i < 8; i++)
    {
      if(col - i >= 0)
      {
        if(play[row][col-i].pieceThere() == false)
          yellow[row][col-i] = true;
        if(play[row][col-i].pieceThere() == true )
        {
          if(!play[row][col-i].getColor().equals(play[row][col].getColor()))
            yellow[row][col-i] = true;
          break;
        }
      }
    }
  }  
  
  public void pawnMove(int row, int col)
  {
    int move = 1;
    int otherMove = 2;
    
    //if it is your turn then the pawn moves up
    if( playerIs.equals(play[row][col].getColor()))
    {
      move = -1;
      otherMove = -2;
    }    
    
    if(play[row][col].getHasMoved() == false)
    {
      //if there is no peice in front of the paw and the pawn has not moved it may move forward two
      if(play[row+move][col].pieceThere() == false && play[row+otherMove][col].pieceThere() == false)
        yellow[row+otherMove][col] = true;
    }
    if((play[row+move][col].pieceThere() == false) && (row + move >= 0 || row + move < 8))
      yellow[row+move][col] = true; 
  }
  
  public void pawnAttack(int row, int col)
  {
    int move = 1;
    
    //if it is your turn then the pawn moves up
    if( playerIs.equals(play[row][col].getColor()))
      move = -1;
    
    //top left for you, bottom right for opponent for attacking
    
    //) 
    if((row + move >= 0 && col + move >= 0  && play[row][col].getColor().equals(playerIs))|| (row + move < 8 && (col+move) < 8 && !play[row][col].getColor().equals(playerIs)))
    {
      if(!play[row+move][col+move].getColor().equals(play[row][col].getColor()) && play[row+move][col+move].pieceThere() == true)
        yellow[row+move][col+move] = true;
    }
    //top right/bottom left
    if((row+move >= 0 && col-move < 8 && play[row][col].getColor().equals(playerIs)) || (row+move < 8 && col-move >= 0 && !play[row][col].getColor().equals(playerIs)))
    {
      if(!play[row+move][col-move].getColor().equals(play[row][col].getColor()) && play[row+move][col-move].pieceThere() == true)
        yellow[row+move][col-move] = true;
    }
    
    IPassented = false;
    if (col -1 >= 0 )
    {
      if(play[row][col-1].enPassant() == true && !play[row][col-1].getColor().equals(turn))
      {
        IPassented = true;
        if (turn.equals(playerIs))
          yellow[row-1][col-1] = true;
        else if (!turn.equals(playerIs))
          yellow[row+1][col-1] = true;
      }
    }
    if( col +1 <= 7)
    {
      if(play[row][col+1].enPassant() == true && !play[row][col+1].getColor().equals(turn))
      {
        IPassented= true;
        if (turn.equals(playerIs))
          yellow[row-1][col+1] = true;
        else if (!turn.equals(playerIs))
          yellow[row+1][col+1] = true;
      }
    }
  }
  
   public boolean kingCanMove(int row, int col)
  {
    boolean valid = false;
    boolean[][] temp = new boolean[8][8];
    for(int i = 0; i < 8; i++)
    {
      for (int j = 0; j < 8; j++)
        temp[i][j] = yellow[i][j];
    }
    
    clearYellow();
    //checks if a knight is withing killing of this space
    
    if(row-2 >= 0 && col + 1 < 8)
    {
      if(play[row-2][col+1].getType().equals("k") && !play[row-2][col+1].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row-2 >= 0 && col - 1 >= 0)
    {
      if(play[row-2][col-1].getType().equals("k") && !play[row-2][col-1].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row+2 < 8 && col + 1 < 8)
    {
      if(play[row+2][col+1].getType().equals("k") && !play[row+2][col+1].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row+2 < 8 && col - 1 >= 0)
    {
      if(play[row+2][col-1].getType().equals("k") && !play[row+2][col-1].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row-1 >= 0 && col + 2 < 8)
    {
      if(play[row-1][col+2].getType().equals("k") && !play[row-1][col+2].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row-1 >= 0 && col -2 >= 0)
    {
      if(play[row-1][col-2].getType().equals("k") && !play[row-1][col-2].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row+1 < 8 && col + 2 < 8)
    {
      if(play[row+1][col+2].getType().equals("k") && !play[row+1][col+2].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    if(row+1 < 8 && col - 2 >= 0)
    {
      if(play[row+1][col-2].getType().equals("k") && !play[row+1][col-2].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    
    //bishop / queen
    
    //goes from the centre out and finds the first peice, if it is a bishop or queen it cannot be moved there
    int tempR = row;
    int tempC = col;
    for(int i = 1; i < 8; i++)
    {
      //top left
      if(row-i >= 0 && col-i >= 0)
      {
        if(play[row-i][col-i].pieceThere() == true && !play[row-i][col-i].getType().equals("K"))
        {
          tempR = row-i;
          tempC = col-i;
          break;
        }
      }
    }
    if((play[tempR][tempC].getType().equals("b") || play[tempR][tempC].getType().equals("Q"))  && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = false;
      valid = true;
    }
    
    tempR = row;
    tempC = col;
    for(int i = 1; i < 8; i++)
    {
      //top right
      if(row-i >= 0 && col+i < 8)
      {
        if(play[row-i][col+i].pieceThere() == true && !play[row-i][col+i].getType().equals("K"))
        {
          tempR = row-i;
          tempC = col+i;
          break;
        }
      }
    }
    if((play[tempR][tempC].getType().equals("b") || play[tempR][tempC].getType().equals("Q"))  && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = false;
      valid = true;
    }
    
    tempR = row;
    tempC = col;
    for(int i = 1; i < 8; i++)
    {
      //top right
      if(row+i < 8 && col-i >= 0)
      {
        if(play[row+i][col-i].pieceThere() == true && !play[row+i][col-i].getType().equals("K"))
        {
          tempR = row+i;
          tempC = col-i;
          break;
        }
      }
    }
    if((play[tempR][tempC].getType().equals("b") || play[tempR][tempC].getType().equals("Q"))  && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = false;
      valid = true;
    }
        
    tempR = row;
    tempC = col;
    for(int i = 1; i < 8; i++)
    {
      //top right
      if(row+i < 8 && col+i < 8)
      {
        if(play[row+i][col+i].pieceThere() == true && !play[row+i][col+i].getType().equals("K"))
        {
          tempR = row+i;
          tempC = col+i;
          break;
        }
      }
    }
    if((play[tempR][tempC].getType().equals("b") || play[tempR][tempC].getType().equals("Q"))  && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = false;
      valid = true;
    }
    
    //if its the rook / queen
    tempR = row;
    tempC = col;
    for(int i = 1; i < 8; i++)
    {
      if(row-i >= 0)
      {
        if(play[row-i][col].pieceThere() == true && !play[row-i][col].getType().equals("K"))
        {
          tempR = row-i;
          tempC = col;
          break;
        }
      }
    }
    if((play[tempR][tempC].getType().equals("r") || play[tempR][tempC].getType().equals("Q")) && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = true;
      valid = true;
    }
    
    //check for a rook/queen below our peice
    tempR = row;
    tempC = col;
    
    for(int i = 1; i < 8; i++)
    {
      if(row+i < 8)
      {
        if(play[row+i][col].pieceThere() == true && !play[row+i][col].getType().equals("K"))
        {
          tempR = row+i;
          tempC = col;
          break;
        }
      }
    }
if((play[tempR][tempC].getType().equals("r") || play[tempR][tempC].getType().equals("Q")) && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = true;
      valid = true;
    }
    
    //check for a rook or queen to the right of our peice
    tempR = row;
    tempC = col;
    
    for(int i = 1; i < 8; i++)
    {
      if(col+i < 8)
      {
        if(play[row][col+i].pieceThere() == true && !play[row][col+i].getType().equals("K"))
        {
          tempR = row;
          tempC = col+i;
          break;
        }
      }
    }
if((play[tempR][tempC].getType().equals("r") || play[tempR][tempC].getType().equals("Q")) && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = true;
      valid = true;
    }
    
      //check for a rook or queen to the left of our peice
    tempR = row;
    tempC = col;
    
    for(int i = 1; i < 8; i++)
    {
      if(col-i >= 0)
      {
        if(play[row][col-i].pieceThere() == true && !play[row][col-i].getType().equals("K"))
        {
          tempR = row;
          tempC = col-i;
          break;
        }
      }
    }
if((play[tempR][tempC].getType().equals("r") || play[tempR][tempC].getType().equals("Q")) && !play[tempR][tempC].getColor().equals(playerIs))
    {
      temp[row][col] = true;
      valid = true;
    }   
      
    //if there is a pawn to the top left do not move it there
    if(row-1 >= 0 && col-1 >= 0 )
    {
      if(play[row-1][col-1].getType().equals("p")&& !play[row-1][col-1].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }    
    if(row-1 >= 0 && col+1 < 8)
    {
      if(play[row-1][col+1].getType().equals("p") && !play[row-1][col+1].getColor().equals(playerIs))
      {
        temp[row][col] = false;
        valid = true;
      }
    }
    //pawns
    
    for(int i = 0; i < 8; i++)
    {
      for (int j = 0; j < 8; j++)
        yellow[i][j] = temp[i][j];
    }
    return valid;
  }
  
     public void king (int row, int col)
  {
    //castle left
    boolean canCastle = true;
    //if the king or the left rook has moved
    if(play[row][col].getHasMoved() == true || play[row][0].getHasMoved() == true)
      canCastle = false;
    
    if(canCastle == true)
    {
      //if there is any peice between them we cant castle
      for(int i = 1; i < col; i++)
      {
        if(!play[row][i].getType().equals(" "))
          canCastle = false;
      }
    }
    
    if(canCastle == true)
      yellow[row][col-2] = true;
        
    //castle right
    canCastle = true;
    //if the king or the right rook has moved
    if(play[row][col].getHasMoved() == true || play[row][7].getHasMoved() == true)
      canCastle = false;
    
    if(canCastle == true)
    {
      //if there is any peice between them we cant castle
      for(int i = col+1; i < 7; i++)
      {
        if(!play[row][i].getType().equals(" "))
          canCastle = false;
      }
    }
    if(canCastle == true)
      yellow[row][col+2] = true;
    
    
    //up
    System.out.println(kingCanMove(row-1,col));
    yellow[row][col] = false;
    
    if(row - 1 >=0)
    {
      if(play[row-1][col].pieceThere() == false && kingCanMove(row-1,col) == true)
        yellow[row-1][col] = false;
      else if(play[row-1][col].pieceThere() == false && kingCanMove(row-1,col) == false)
        yellow[row-1][col] = true;
      else if(play[row-1][col].pieceThere() == true && kingCanMove(row-1,col) == false && play[row-1][col].getColor().equals(comIs))
        yellow[row-1][col] = true;
      else if(play[row-1][col].pieceThere() == true && kingCanMove(row-1,col) == true)
        yellow[row-1][col] = false;
    }
    yellow[row][col] = false;
    
    //down
    if(row + 1 < 8)
    {
      if(play[row+1][col].pieceThere() == false && kingCanMove(row+1,col) == true)
        yellow[row+1][col] = false;
      else if(play[row+1][col].pieceThere() == false && kingCanMove(row+1,col) == false)
        yellow[row+1][col] = true;
      else if(play[row+1][col].pieceThere() == true && kingCanMove(row+1,col) == false  && play[row+1][col].getColor().equals(comIs) )
        yellow[row+1][col] = true;
      else if(play[row+1][col].pieceThere() == true && kingCanMove(row+1,col) == true)
        yellow[row+1][col] = false;
    }
    yellow[row][col] = false;
    //right
    
    System.out.println( kingCanMove(row,col+1));
    
    if(col + 1  < 8)
    {
      if(play[row][col+1].pieceThere() == false && kingCanMove(row,col+1) == true)
        yellow[row][col+1] = false;
      else if(play[row][col+1].pieceThere() == false && kingCanMove(row,col+1) == false)
        yellow[row][col+1] = true;
      else if(play[row][col+1].pieceThere() == true && kingCanMove(row,col+1) == false  && play[row][col+1].getColor().equals(comIs) )
        yellow[row][col+1] = true;
      else if(play[row][col+1].pieceThere() == true && kingCanMove(row,col+1) == true)
        yellow[row][col+1] = false;
    }
    
    yellow[row][col] = false;
    //left
    if(col - 1  >= 0)
    {
      if(play[row][col-1].pieceThere() == false && kingCanMove(row,col-1) == true)
        yellow[row][col-1] = false;
      else if(play[row][col-1].pieceThere() == false && kingCanMove(row,col-1) == false)
        yellow[row][col-1] = true;
      else if(play[row][col-1].pieceThere() == true && kingCanMove(row,col-1) == false && play[row][col-1].getColor().equals(comIs) )
        yellow[row][col-1] = true;
      else if(play[row][col-1].pieceThere() == true && kingCanMove(row,col-1) == true )
        yellow[row][col-1] = false;
    }
    
    
    //top left
    if(row-1 >= 0 && col -1 >= 0)
    {      
      if(play[row-1][col-1].pieceThere() == false && kingCanMove(row-1,col-1) == true)
        yellow[row-1][col-1] = false;
      else if(play[row-1][col-1].pieceThere() == false && kingCanMove(row-1,col-1) == false)
        yellow[row-1][col-1] = true;
      else if(play[row-1][col-1].pieceThere() == true && kingCanMove(row-1,col-1) == false && play[row-1][col-1].getColor().equals(comIs))
        yellow[row-1][col-1] = true;
      else if(play[row-1][col-1].pieceThere() == true && kingCanMove(row-1,col-1) == true)
        yellow[row-1][col-1] = false;
    }
    //top right
    //needs fixing
    if(row-1 >= 0 && col +1 < 8)
    {
      if(play[row-1][col+1].pieceThere() == false && kingCanMove(row-1,col+1) == true)
        yellow[row-1][col+1] = false;
      else if(play[row-1][col+1].pieceThere() == false && kingCanMove(row-1,col+1) == false)
        yellow[row-1][col+1] = true;
      else if(play[row-1][col+1].pieceThere() == true && kingCanMove(row-1,col+1) == false && play[row-1][col+1].getColor().equals(comIs))
        yellow[row-1][col+1] = true;
      else if(play[row-1][col+1].pieceThere() == true && kingCanMove(row-1,col+1) == true)
        yellow[row-1][col+1] = false;
    }
    
    //bottom left
    if(row+1 < 8 && col -1 >= 0)
    {
      if(play[row+1][col-1].pieceThere() == false && kingCanMove(row+1,col-1) == true)
        yellow[row+1][col-1] = false;
      else if(play[row+1][col-1].pieceThere() == false && kingCanMove(row+1,col-1) == false)
        yellow[row+1][col-1] = true;
      else if(play[row+1][col-1].pieceThere() == true && kingCanMove(row+1,col-1) == false && play[row+1][col-1].getColor().equals(comIs))
        yellow[row+1][col-1] = true;
      else if(play[row+1][col-1].pieceThere() == true && kingCanMove(row+1,col-1) == true)
        yellow[row+1][col-1] = false;
    }
    
    //bottom right
    if(row+1 < 8 && col+1 < 8)
    {
      if(play[row+1][col+1].pieceThere() == false && kingCanMove(row+1,col+1) == true)
        yellow[row+1][col+1] = false;
      else if(play[row+1][col+1].pieceThere() == false && kingCanMove(row+1,col+1) == false)
        yellow[row+1][col+1] = true;
      else if(play[row+1][col+1].pieceThere() == true && kingCanMove(row+1,col+1) == false  && play[row+1][col+1].getColor().equals(comIs))
        yellow[row+1][col+1] = true;
      else if(play[row+1][col+1].pieceThere() == true && kingCanMove(row+1,col+1) == true)
        yellow[row+1][col+1] = false;
    }
  }
  
  public void conditions(int row, int col)
  {    
    //rook
    if(play[row][col].getType().equals("r"))
      line(row,col); 
    //knight
    if(play[row][col].getType().equals("k"))
      L(row, col);
    //bishop
    if(play[row][col].getType().equals("b"))
      diagnal(row, col);
    //queen
    if(play[row][col].getType().equals("Q"))
    {
      line(row,col);
      diagnal(row, col);
    }
    //king
    if(play[row][col].getType().equals("K"))
      king(row, col);
    //pawn
    if(play[row][col].getType().equals("p"))
    {
      pawnMove(row, col);
      pawnAttack(row, col);
    }
  }
  
  //see where to move
  public class Identify implements ActionListener
  {      
    public int row;
    public int col;
    
    public Identify(int row, int col)
    {
      this.row = row;
      this.col = col;
    }
        
    public void actionPerformed (ActionEvent e)
    { 
      prevRow = row;
      prevCol = col;
      //if you click on you own peice (not a blank peice and not a peice of the same color)
     
      
      if(play[row][col].getColor().equals(turn))
       conditions(row,col);      
     
      
      start.setVisible(false);
      board(); 
    }   
  }
  

  //clears the 2d array so another peice can use it
  public static void clearYellow()
  {
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
        yellow[i][j] = false;
    }
  }
  
  public class Piece implements ActionListener
  {
    public String piece;
    public double value;
    public Piece(String piece, double value)
    {
      this.piece = piece;
      this.value = value;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      play[proRow][proCol] = new Node(piece, play[proRow][proCol].getColor(), true, true, value, false);
      //String type, String color, boolean position, boolean hasMoved, int points, boolean pass) 
      wait = false;
      start.setVisible(false);
      popUp.setVisible(false);
      board();
      //update that peice to the current one
    }
  }
  
  public class Ok implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      fileName = textField.getText(); 
      fileEnter.setVisible(false);
      pieceColor();
    }
  }
  
  
  
  
  
  
  //Javon's code
  
  
  public void print(ArrayList<int[]> a)
  {
   for(int i=0;i<a.size();i++)
   {
    for(int j=0;j<a.get(i).length;j++)
    {
  //   System.out.print(a.get(i)[j]+ " "); 
    }
    //System.out.println("");
   }
  }
  

  public static ArrayList<int[]> AIconditions(Node [][]beg,int row, int col)
  {    
    //rook
   ArrayList<int[]> holder = new ArrayList<int[]>();
    if(beg[row][col].getType().equals("r"))
      {
     //System.out.println("Rook");
     holder=AIline(row,col); 
      }
    //knight
    if(beg[row][col].getType().equals("k"))
    {
    //System.out.println("Knight");
     holder=AIL(row, col);
    }
      //bishop
    if(beg[row][col].getType().equals("b"))
    {
     //System.out.println("Bishop");
     holder=AIdiagnal(row, col);
    }
      //queen
    if(beg[row][col].getType().equals("Q"))
    {
     //System.out.println("Queen");
      holder=AIline(row,col);
      holder=AIdiagnal(row, col);
    }
    //king
    if(beg[row][col].getType().equals("K"))
    {
     //System.out.println("King");
      holder=AIking(row, col);
    }
      //pawn
    if(beg[row][col].getType().equals("p"))
    {
     //System.out.println("Pawn");
     holder=AIpawnMove(row, col);
        holder=AIpawnAttack(row, col);
    }
    return holder;
  }
 
  public static ArrayList<int[]> AIdiagnal(int row, int col)
  {
     ArrayList<int[]> holder = new ArrayList<int[]>();
    //up left
    for(int i = 1; i < 6; i++)
    {
      if (row-i >= 0 && col-i >= 0)
      {
        if (play[row-i][col-i].pieceThere() == false)
          {
         
         holder.add(new int[]{row-i,col-i});
          }
        if(play[row-i][col-i].pieceThere() == true)
        {
          if(!play[row-i][col-i].getColor().equals(play[row][col].getColor()))
            {
           
           holder.add(new int[]{row-i,col-i});
            }
          break;
        }
      }
    }
    //down left
    for(int i = 1; i < 6; i++)
    {
      if (row+i < 8 && col-i >= 0)
      {
        if (play[row+i][col-i].pieceThere() == false)
          {
        
         holder.add(new int[]{row+i,col-i});
          }
        if(play[row+i][col-i].pieceThere() == true)
        {
          if(!play[row+i][col-i].getColor().equals(play[row][col].getColor()))
            {
           
           holder.add(new int[]{row+i,col-i});
            }
          break;
        }
      }
    }
    
    //up right
    for(int i = 1; i < 6; i++)
    {
      if (row-i >= 0 && col+i < 8)
      {
        if (play[row-i][col+i].pieceThere() == false)
        {
        
         holder.add(new int[]{row-i,col+i});
        }
         
        if(play[row-i][col+i].pieceThere() == true)
        {
          if(!play[row-i][col+i].getColor().equals(play[row][col].getColor()))
            {
          
           holder.add(new int[]{row-i,col+i});
            }
          break;
        }
      }
    }
    
    //down right
    for(int i = 1; i < 6; i++)
    {
      if (row+i < 8 && col+i < 8)
      {
        if (play[row+i][col+i].pieceThere() == false)
          {
        
         holder.add(new int[]{row+i,col+i});
          }
        if(play[row+i][col+i].pieceThere() == true)
        {
          if( !play[row+i][col+i].getColor().equals(play[row][col].getColor()))
            {
            holder.add(new int[]{row+i,col+i});
            }
          break;
        }
      }
    }    
    return holder;
  } //diagnal

  //knight
  //This will give you the possible positions that the knight can move to
  //This needs to return the positions that are yellow
  //what I am adding to this class will make it return the possible places that it can move to 
  public static ArrayList<int[]> AIL(int row, int col)
  {
     ArrayList<int[]> holder = new ArrayList<int[]>();
    //Row-2, Col+1
    if(row-2 >=0 && col +1 < 8)
    {//the first one is saying if there is not a piece there
     //the second one is saying if there is a piece there and the color of that piece is not the color of the piece attacking
     //in other words you cannot attack your own person. 
      if(play[row-2][col+1].pieceThere() == false)
      {
     
       holder.add(new int[]{row-2,col+1});
      }
    
       
      
       if(play[row-2][col+1].pieceThere() == true && !play[row-2][col+1].getColor().equals(play[row][col].getColor()))
       {
        
        holder.add(new int[]{row-2,col+1});
       }
      
        
    }
    
    //Row+1,col-2
    if(row+1 < 8 && col-2 >= 0)
    {
      if(play[row+1][col-2].pieceThere() == false)
         {
       
        holder.add(new int[]{row+1,col-2});
         }
      if(play[row+1][col-2].pieceThere() == true && !play[row+1][col-2].getColor().equals(play[row][col].getColor()))
      {
     
       holder.add(new int[]{row+1,col-2});
      }
      
      
    }
    
    //Row -1, Col -2
    if(row-1 >=0 && col-2 >=0)
    {
      if(play[row-1][col-2].pieceThere() == false)
        {
     
       holder.add(new int[]{row-1,col-2});
        }
      if(play[row-1][col-2].pieceThere() == true && !play[row-1][col-2].getColor().equals(play[row][col].getColor()))
        {
     
       holder.add(new int[]{row-1,col-2});
        }
    } 
    
    //Row-1, Col+2
    if(row-1 >=0 && col+2 < 8)
    {
      if(play[row-1][col+2].pieceThere() == false)
        {
     
       holder.add(new int[]{row-1,col+2});
        }
      if(play[row-1][col+2].pieceThere() == true && !play[row-1][col+2].getColor().equals(play[row][col].getColor()))
        {
      
       holder.add(new int[]{row-1,col+2});
        }
    }
    
    //Row-2, Col-1
    if(row-2 >=0 && col -1 >=0)
    {
      if(play[row-2][col-1].pieceThere() == false)
      {
      
       holder.add(new int[]{row-2,col-1});
     
      }
      if(play[row-2][col-1].pieceThere() == true && !play[row-2][col-1].getColor().equals(play[row][col].getColor()))
      {
       
       holder.add(new int[]{row-2,col-1});
      }
    }
    
    //Row+1,Col+2
    if(row+1 < 8 && col+2 < 8)
    {
      if(play[row+1][col+2].pieceThere() == false)
        {
     
       holder.add(new int[]{row+1,col+2});
        }
      if(play[row+1][col+2].pieceThere() == true && !play[row+1][col+2].getColor().equals(play[row][col].getColor()))
        {
      
       holder.add(new int[]{row+1,col+2});
        }
      
      
    }
    
    //Row+2, Col +1
    if(row+2 < 8 && col+1 < 8)
    {
      if(play[row+2][col+1].pieceThere() == false)
        {
     
       holder.add(new int[]{row+2,col+1});
        }
      if(play[row+2][col+1].pieceThere() == true && !play[row+2][col+1].getColor().equals(play[row][col].getColor()))
        {
       
       holder.add(new int[]{row+2,col+1});
        } 
    }
    
    //Row +2, Col-1
    if(row+2 < 8 && col-1 >= 0)
    {
      if(play[row+2][col-1].pieceThere() == false)
        {
     
       holder.add(new int[]{row+2,col-1});
        }
      if(play[row+2][col-1].pieceThere() == true && !play[row+2][col-1].getColor().equals(play[row][col].getColor()))
        {
      
       holder.add(new int[]{row+2,col-1});
        } 
    }
    return holder;
  } //L
  //this is just moving for the rooks where to go
  public static ArrayList<int[]> AIline(int row, int col)
  {
     ArrayList<int[]> holder = new ArrayList<int[]>();
    //up
    for(int i = 1; i < 8; i++)
    {
      if(row - i >= 0)
      {
        if(play[row-i][col].pieceThere() == false)
        {
         
         holder.add(new int[]{row-i,col});
        }
        if(play[row-i][col].pieceThere() == true)
        {
          if(!play[row-i][col].getColor().equals(play[row][col].getColor()))
          {
          
           holder.add(new int[]{row-i,col});
          }
          break;
        }
      }
    }
    
    //down
    for(int i = 1; i < 8; i++)
    {
      if(row + i < 8)
      {
        if(play[row+i][col].pieceThere() == false)
        {
         
         holder.add(new int[]{row+i,col});
        }
        if(play[row+i][col].pieceThere() == true )
        {
          if(!play[row+i][col].getColor().equals(play[row][col].getColor()))
            {
          
           holder.add(new int[]{row+i,col});
            }
          break;
        }
      }
    }
    
    //right
    for(int i = 1; i < 8; i++)
    {
      if(col + i < 8)
      {
        if(play[row][col+i].pieceThere() == false)
         {
          
          holder.add(new int[]{row,col+i});
         }
        if(play[row][col+i].pieceThere() == true)
        {
          if(!play[row][col+i].getColor().equals(play[row][col].getColor()))
          {
          
           holder.add(new int[]{row,col+i});
          }
          break;
        }
      }
    }
    
    for(int i = 1; i < 8; i++)
    {
      if(col - i >= 0)
      {
        if(play[row][col-i].pieceThere() == false)
          {
         
         holder.add(new int[]{row,col-i});
          }
        if(play[row][col-i].pieceThere() == true )
        {
          if(!play[row][col-i].getColor().equals(play[row][col].getColor()))
            {
           
           holder.add(new int[]{row,col-i});
            }
          break;
        }
      }
    }
    return holder;
  }  

  public static ArrayList<int[]> AIpawnMove(int row, int col)
  {
     ArrayList<int[]> holder = new ArrayList<int[]>();
    int move = 1;
    int otherMove = 2;
    
    //if it is your turn then the pawn moves up
    if(playerIs.equals(play[row][col].getColor()))
    {
      move = -1;
      otherMove = -2;
    }    
    
    if(play[row][col].getHasMoved() == false)
    {
      //if there is no peice in front of the paw and the pawn has not moved it may move forward two
      if(play[row+move][col].pieceThere() == false && play[row+otherMove][col].pieceThere() == false)
        {
       
       holder.add(new int[]{row+otherMove,col});
        }
    }
    if(play[row+move][col].pieceThere() == false && (row + move >= 0 || row + move < 8))
      {
     
      holder.add(new int[]{row+move,col});
      }
    return holder;
  }
 
  public static ArrayList<int[]> AIpawnAttack(int row, int col)
  {
    int move = 1;
    ArrayList<int[]> holder = new ArrayList<int[]>();
    
    //if it is your turn then the pawn moves up
    if( playerIs.equals(play[row][col].getColor()))
      move = -1;
    
    //top left for you, bottom right for opponent for attacking
    if(row + move >= 0 && col + move >= 0  && play[row][col].getColor().equals(playerIs) || row + move < 8 && (col+move) < 8 && !play[row][col].getColor().equals(playerIs))
    {
      if(!play[row+move][col+move].getColor().equals(play[row][col].getColor()) && play[row+move][col+move].pieceThere() == true)
        holder.add(new int[]{row+move,col+move});
    }
    //top right/bottom left
    if(row+move >= 0 && col-move < 8 && play[row][col].getColor().equals(playerIs)  || row+move < 8 && col-move >= 0 && !play[row][col].getColor().equals(playerIs))
    {
      if(!play[row+move][col-move].getColor().equals(play[row][col].getColor()) && play[row+move][col-move].pieceThere() == true)
       holder.add(new int[]{row+move,col-move});
    }
    return holder;
  }
  
  public static ArrayList<int[]> AIking(int row, int col)
  {
 ArrayList<int[]> holder = new ArrayList<int[]>();  
  
    //castle left
    boolean canCastle = true;
    //if the king or the left rook has moved
    if(play[row][col].getHasMoved() == true || play[row][0].getHasMoved() == true)
    {
     
      canCastle = false;
    }
    if(canCastle == true)
    {
      //if there is any peice between them we cant castle
      for(int i = 1; i < col; i++)
      {
        if(!play[row][i].getType().equals(" "))
         
          canCastle = false;
      }
    }
    
    if(canCastle == true)
    holder.add(new int[]{row,col-2});
        
    //castle right
    canCastle = true;
    //if the king or the right rook has moved
    if(play[row][col].getHasMoved() == true || play[row][7].getHasMoved() == true)
    {
     
      canCastle = false;
    }
    if(canCastle == true)
    {
      //if there is any peice between them we cant castle
      for(int i = col+1; i < 7; i++)
      {
        if(!play[row][i].getType().equals(" "))
          canCastle = false;
      
      }
    }
    if(canCastle == true)
    holder.add(new int[]{row,col+2});
    
    //up
    if(row - 1 >=0)
    {
      if(play[row-1][col].pieceThere() == false)
       holder.add(new int[]{row-1,col});
      if(play[row-1][col].pieceThere() == true && !play[row-1][col].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row-1,col});
    }
    
    //down
    if(row + 1 < 8)
    {
      if(play[row+1][col].pieceThere() == false)
       holder.add(new int[]{row+1,col});
      if(play[row+1][col].pieceThere() == true && !play[row+1][col].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row+1,col});
    }
    //right
    if(col + 1  < 8)
    {
      if(play[row][col+1].pieceThere() == false)
       holder.add(new int[]{row,col+1});
      if(play[row][col+1].pieceThere() == true && !play[row][col+1].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row,col+1});
    }
    
    if(col - 1  >= 0)
    {
      if(play[row][col-1].pieceThere() == false)
        holder.add(new int[]{row,col-1});
      if(play[row][col-1].pieceThere() == true && !play[row][col-1].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row,col-1});
    }
    
    //top left
    if(row-1 >= 0 && col -1 >= 0)
    {
      if(play[row-1][col-1].pieceThere() == false)
      holder.add(new int[]{row-1,col-1});
      if(play[row-1][col-1].pieceThere() == true && !play[row-1][col-1].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row-1,col-1});
    }
    
    //top right
    if(row-1 >= 0 && col +1 < 8)
    {
      if(play[row-1][col+1].pieceThere() == false)
      holder.add(new int[]{row-1,col+1});
      if(play[row-1][col+1].pieceThere() == true && !play[row-1][col+1].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row-1,col+1});
    }
    
    //bottom left
    if(row+1 < 8 && col -1 >= 0)
    {
      if(play[row+1][col-1].pieceThere() == false)
       holder.add(new int[]{row+1,col-1});
      if(play[row+1][col-1].pieceThere() == true && !play[row+1][col-1].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row+1,col-1});
    }
    
    //bottom right
    if(row+1 < 8 && col+1 < 8)
    {
      if(play[row+1][col+1].pieceThere() == false)
      holder.add(new int[]{row+1,col+1});
      if(play[row+1][col+1].pieceThere() == true && !play[row+1][col+1].getColor().equals(play[row][col].getColor()))
       holder.add(new int[]{row+1,col+1});
    }
    return holder;
  }
  
  
  
  
  public Node[][] unModify(Node[][]state,ArrayList<int[]>v,int a,int b,int c,int d)
  {
  // Node[][] hold=Node.newInstance(state);//new Node[8][8];
   Node[][] hold=state;
   
  
     hold[a][b] =  hold[c][d];
     hold[a][b].moved();
     
   if(!hold[c][d].isKilledValue())
   {//this will return the killed value an remove it from the list
   hold[c][d].type=hold[c][d].killedValue();
   //System.out.println(hold[c][d].type + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXone");
   }else
   {
     hold[c][d].blank();
   }
   //  hold[v.get(v.size()-1)[2]][v.get(v.size()-1)[3]] =  hold[v.get(v.size()-1)[0]][v.get(v.size()-1)[1]];
    // hold[v.get(v.size()-1)[2]][v.get(v.size()-1)[3]].moved();
   //  hold[v.get(v.size()-1)[0]][v.get(v.size()-1)[1]] = new Node();
   
     return hold;
  }
 
public Node[][] modify(Node[][]state,ArrayList<int[]>v,int a,int b,int c,int d)
{
 //Node[][] hold=Node.newInstance(state);//new Node[8][8];
 Node[][] hold=state;
 //if there is a piece there then you have to save it
 hold[c][d].check();
 if(hold[c][d].position)
 {
  //System.out.println(hold[c][d].type + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
  hold[c][d].addKilled(hold[c][d].type);
  
 }
 
 hold[c][d] =  hold[a][b];
   
   hold[c][d].moved();
   hold[a][b].blank();// = new Node();
 //  hold[v.get(v.size()-1)[2]][v.get(v.size()-1)[3]] =  hold[v.get(v.size()-1)[0]][v.get(v.size()-1)[1]];
  // hold[v.get(v.size()-1)[2]][v.get(v.size()-1)[3]].moved();
 //  hold[v.get(v.size()-1)[0]][v.get(v.size()-1)[1]] = new Node();
 
   return hold;
}

 public ArrayList<int[]> alphaBeta(Node[][] state)
 {
  //long startTime = System.currentTimeMillis();
  ArrayList<int[]> vertex= new ArrayList<int[]>();
  ArrayList<int[]> previous= new ArrayList<int[]>();
  Node[][]p = Node.newInstance(state);
  
  vertex=maxValue(p,Integer.MIN_VALUE,Integer.MAX_VALUE,vertex,number,previous);
  //System.out.println("ending");
  print(vertex);
  Node[][]ag=Node.newInstance(state);
  
  for(int i=1;i<vertex.size();i++)
  {
   ag=modify(ag,vertex,vertex.get(i)[0],vertex.get(i)[1],vertex.get(i)[2],vertex.get(i)[3]);
   //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
   printNode(ag);
  
  }
   //long stopTime = System.currentTimeMillis();
       //long elapsedTime = stopTime - startTime;
       //System.out.println(elapsedTime);
  
  
  
  //printNode(state);
  return vertex;
  //So i need a function that will look at the state of the board and find a move 
 }
 //all you are doing is returning values of each new state
 //I dont want to be returning values, I want to be returning combinations that will equal the new states 
 public ArrayList<int[]> maxValue(Node[][] state,double alpha,double beta,ArrayList<int[]> a,int down,ArrayList<int[]> prev)
 {
 //j
 

  
  ArrayList<int[]> blackHolder=a;
  if(blackHolder.isEmpty())
  {
   blackHolder.add(new int[5]);
   
  }
  //System.out.println("HERE1");
  if(down==0)
  {
   //Node[][]beginning=modify(state,blackHolder);
   
   int level=0;
   //this is returning for the min and so you are trying to find the lowest number
      blackHolder.get(0)[4]=evaluateFunction(state,false,level);
   //out.exit();
   
   return blackHolder;
  }
  
  
  
  blackHolder.get(0)[4]=Integer.MIN_VALUE;
  int hold;
  ArrayList<int[]> location= new ArrayList<int[]>();
  //System.out.println("HERE2");
  boolean valid=false;
  
  
 
  
    for(int i=0;i<8;i++)
    {
     for(int j=0;j<8;j++)
     {
      if(state[i][j].getColor().equals("Black"))
      {
       if(state[i][j].type.equals("K"))
       {  
        valid=true;
       }
       
    
       if(pinned(state,i,j,"Black"))
        {   
        //System.out.println("This black piece is pinned");
          continue;
        }
       if(!prev.isEmpty())
       {
      if(prev.get(0)[0]==i&&prev.get(0)[1]==j)
      {
        location.add(0,new int[]{i,j});
      }
      else
      {
       location.add(new int[]{i,j});
      }
       }else
       {
        location.add(new int[]{i,j});
       }
      
      }
     }
    } 
    
   // System.out.println("Got here");
    if(!prev.isEmpty())
   {
     prev.remove(0);
    //then you re-organize location to fit what prev is, then you remove one from prev 
   }
    
    if(!valid)//this shows that you have lost the game
    {
    // ArrayList<int[]> bad = new ArrayList<int[]>();
     blackHolder.get(0)[4]=Integer.MIN_VALUE;
     return blackHolder;//The king is off the board and you have lost
    }
    
    ArrayList<int[]> test= new ArrayList<int[]>();// = AIconditions(posX,posY);
    ArrayList<int[]> succesor= new ArrayList<int[]>();
   for(int i=0;i<blackHolder.size();i++)
   {
    succesor.add(blackHolder.get(i));
   }
    
    ArrayList<int[]> temp= new ArrayList<int[]>();
   // succesor.add(new int[]{Integer.MAX_VALUE,0});
    hold =--down;
    
    //System.out.println(hold);
   
   //System.out.println("HERE2");
    //System.out.println(location.size() + " Location Size");
   
    for(int i=0;i<location.size();i++)
    {
     test.clear();
     
     int[] inCheck=check(state,"Black");
     //System.out.println("KKKKKKKKKK");
     if(inCheck[0]==-1)
     {
      test.addAll(AIconditions(state,location.get(i)[0],location.get(i)[1]));      
     }else if(inCheck[0]==9)
     {
      if(state[location.get(i)[0]][location.get(i)[1]].type.equals("K"))
      {
      test.addAll(kingCheck(state,"Black"));
      }
     }else
     {
      if(state[location.get(i)[0]][location.get(i)[1]].type.equals("K"))
      {
       //System.out.println("hhh");
      test.addAll(kingCheck(state,"Black"));
      }
      else
     {
         //System.out.println("wwww");
      ArrayList<int[]> contain=inBetween(state,inCheck[0],inCheck[1],"Black");
      ArrayList<int[]> div= AIconditions(state,location.get(i)[0],location.get(i)[1]);
      //System.out.println(div.size() + "divsize");
      for(int k=0;k<div.size();k++)
      {
       if(within(contain,div.get(k)[0],div.get(k)[1]))
       {
        test.add(div.get(k));
       }
      }
      print(test);
     }
     }
    //if it is in check and is not a king
   

    //AIConditions(Node [][]beg,int row, int col,ArrayList<int[]> ret
    //if piece move is not the king
    //pinned(Node[][] hold,int moveRow, int moveCol,String color
    //within(ArrayList<int[]> a,int x,int y
    //determines if King is in check
    //

    //AIKIng(int row, int col
     
   
     
     
       //legalMoveComputer(state,location.get(i)[0],location.get(i)[1]));
     
     
     //  promptEnterKey();
     //System.out.println(i + "MAX I ");
     for(int j=0;j<test.size();j++)
     {
      
      Node[][] beginning = modify(state,blackHolder,location.get(i)[0],location.get(i)[1],test.get(j)[0],test.get(j)[1]);
      blackHolder.add(new int[]{location.get(i)[0],location.get(i)[1],test.get(j)[0],test.get(j)[1]});//;}(0)[0]=location.get(i)[0];
     // blackHolder.get(0)[1]=location.get(i)[1];
      //System.out.println(hold);
      //System.exit(0);
      temp=minValue(beginning,alpha,beta,blackHolder,hold,prev);
      //System.out.println("here");
      print(temp);
      
     
        if(succesor.get(0)[4]<=temp.get(0)[4])
        {
         //System.out.println("Success");
         succesor.clear();
         for(int k=0;k<temp.size();k++)
         {
          succesor.add(temp.get(k));
         }
         //succesor=temp;
        }  
        beginning = unModify(state,blackHolder,location.get(i)[0],location.get(i)[1],test.get(j)[0],test.get(j)[1]);
        blackHolder.remove(blackHolder.size()-1);
        if(succesor.get(0)[4]>beta){
         //System.out.println(succesor.get(0)[4]);
         //System.out.println(beta);
         //System.out.println("BEta exit");
        // System.exit(0);
         return succesor;
        }
       // System.exit(0);
        alpha=Math.max(alpha,succesor.get(0)[4]);
      
        print(succesor);
        if(i==3)
        {
         //System.out.println(i + "I equals");
       // System.exit(0);
        }
     }   
    }
    return succesor;
 
    
   
  
  //if it is not the first you just need to add where you are going and pass the new array list 
  //then remove it from the array list 

  //get holder of all possible moves
  //f=minValue(Node[][] state,double alpha,double beta,ArrayList<int[]> a,int down,int prevX,int prevY)
 }
 
 public ArrayList<int[]> minValue(Node[][] state,double alpha,double beta,ArrayList<int[]> a,int down,ArrayList<int[]> prev)
 {
  
 ArrayList<int[]> blackHolder=a;
 if(blackHolder.isEmpty())
 {
  blackHolder.add(new int[5]);
 //System.out.println("no");
 System.exit(0);
 }
  //System.out.println("HERE1");
  if(down==0)
  {
   //Node[][]beginning=modify(state,blackHolder);
   
  int level=0;
     blackHolder.get(0)[4]=evaluateFunction(state,true,level);
   //this is returning for the max
    // so you are trying to find the highest negative number
   return blackHolder;
  }
  
  
  
  blackHolder.get(0)[4]=Integer.MAX_VALUE;
  int hold;
  ArrayList<int[]> location= new ArrayList<int[]>();
  //System.out.println("HERE2");
  boolean valid=false;
    for(int i=0;i<8;i++)
    {
     for(int j=0;j<8;j++)
     {
      if(state[i][j].getColor().equals("White"))
      {
       if(state[i][j].type.equals("K"))
       {  
        valid=true;
       }
     
        if(pinned(state,i,j,"White"))
        {   
         //System.out.println("HHHH");
          continue;
        }
       
       //if piece is not pinned
       if(!prev.isEmpty())
       {
      if(prev.get(0)[0]==i&&prev.get(0)[1]==j)
      {
        location.add(0,new int[]{i,j});
      }
      else{
         location.add(new int[]{i,j});
      }
       }else
       {
        location.add(new int[]{i,j});
       }
     
      }
     }
    } 
    if(!valid)//this shows that you have won the game
    {
    // ArrayList<int[]> bad = new ArrayList<int[]>();
     blackHolder.get(0)[4]=Integer.MAX_VALUE;
     return blackHolder;//The king is off the board and you have lost
    }
    if(!prev.isEmpty())
   {
     prev.remove(0);
    //then you re-organize location to fit what prev is, then you remove one from prev 
   }
    
    
    ArrayList<int[]> test= new ArrayList<int[]>();// = AIconditions(posX,posY);
    ArrayList<int[]> succesor= new ArrayList<int[]>();
    for(int i=0;i<blackHolder.size();i++)
    {
     succesor.add(blackHolder.get(i));
    }
    ArrayList<int[]> temp= new ArrayList<int[]>();
   // succesor.add(new int[]{Integer.MAX_VALUE,0});
    hold =--down;
    //System.out.println(location.size() + " Location Size");

    
   
    for(int i=0;i<location.size();i++)
    {
     
   test.clear();
     
     int[] inCheck=check(state,"White");
     
     if(inCheck[0]==-1)
     {
      test.addAll(AIconditions(state,location.get(i)[0],location.get(i)[1]));
      
      
     }else if(inCheck[0]==9)
     {
      if(state[location.get(i)[0]][location.get(i)[1]].type.equals("K"))
      {
      test.addAll(kingCheck(state,"White"));
      }
     }else
     {
    
      if(state[location.get(i)[0]][location.get(i)[1]].type.equals("K"))
      {
      test.addAll(kingCheck(state,"White"));
      }
       else
     {
        
      ArrayList<int[]> contain=inBetween(state,inCheck[0],inCheck[1],"White");
      ArrayList<int[]> div= AIconditions(state,location.get(i)[0],location.get(i)[1]);
      
      for(int k=0;k<div.size();k++)
      {
       if(within(contain,div.get(k)[0],div.get(k)[1]))
       {
        test.add(div.get(k));
       }
      }
     }
     }
    //if it is in check and is not a king
   

    //AIConditions(Node [][]beg,int row, int col,ArrayList<int[]> ret
    //if piece move is not the king
    //pinned(Node[][] hold,int moveRow, int moveCol,String color
    //within(ArrayList<int[]> a,int x,int y
    //determines if King is in check
    //

    //AIKIng(int row, int col
     
   
   
     
     
     
  
     //System.out.println(i + "MAX I ");
     for(int j=0;j<test.size();j++)
     {
      
      Node[][] beginning = modify(state,blackHolder,location.get(i)[0],location.get(i)[1],test.get(j)[0],test.get(j)[1]);
      blackHolder.add(new int[]{location.get(i)[0],location.get(i)[1],test.get(j)[0],test.get(j)[1]});//;}(0)[0]=location.get(i)[0];
     // blackHolder.get(0)[1]=location.get(i)[1];
      //printNode(beginning);
      //System.out.println(hold);
      printNode(beginning);
      temp=maxValue(beginning,alpha,beta,blackHolder,hold,prev);
      print(temp);
      print(succesor);
     
     
        if(succesor.get(0)[4]>=temp.get(0)[4])
        {
         //System.out.println("SuccessWhite");
         succesor.clear();
         for(int k=0;k<temp.size();k++)
         {
          succesor.add(temp.get(k));
         }
         //succesor.addAll(temp);
        // print(temp);
         print(succesor);
        }
        
        beginning = unModify(state,blackHolder,location.get(i)[0],location.get(i)[1],test.get(j)[0],test.get(j)[1]);
        blackHolder.remove(blackHolder.size()-1);
        if(blackHolder.get(0)[4]<alpha){
        // System.exit(0);
         return succesor;
        }
        //System.out.println(alpha);
        //System.exit(0);
        beta=Math.min(beta,succesor.get(0)[4]);
        //System.out.println(beta+"Beta");
       
     }
     
    }
    return succesor;
  
  
  //if it is not the first you just need to add where you are going and pass the new array list 
  //then remove it from the array list 

  //get holder of all possible moves
  //f=minValue(Node[][] state,double alpha,double beta,ArrayList<int[]> a,int down,int prevX,int prevY)
 }
 
 public void printNode(Node[][] a)
 {
  //System.out.println("");
  for(int i=0;i<8;i++)
  {
   for(int j=0;j<8;j++)
   {
    //System.out.print(a[i][j].type+ "\t");
   }
   //System.out.println("");
  }
  
 }
 
 public static void printN(Node[][] a)
 {
  //System.out.println("");
  for(int i=0;i<8;i++)
  {
   for(int j=0;j<8;j++)
   {
   // System.out.print(a[i][j].type+ "\t");
   }
 //  System.out.println("");
  }
  
 }

  public ArrayList<int[]> kingCheck(int row,int col)
  {
  ArrayList<int[]> holder = new ArrayList<int[]>();
  //up
  if(row - 1 >=0)
  {
    if(play[row-1][col].pieceThere() == false)
    {
     holder=checkCheck(row-1,col,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row-1][col] = true;
     }
    }
     
    if(play[row-1][col].pieceThere() == true && !play[row-1][col].getColor().equals(play[row][col].getColor()))
  {
     holder=checkCheck(row-1,col,play[row][col].color);
  if(holder.isEmpty())
  {
    yellow[row-1][col] = true;
  }
    }
  }
  
  //down
  if(row + 1 < 8)
  {
    if(play[row+1][col].pieceThere() == false)
    {
     holder=checkCheck(row+1,col,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row+1][col] = true;
     }
    }
      
    if(play[row+1][col].pieceThere() == true && !play[row+1][col].getColor().equals(play[row][col].getColor()))
      {
     holder=checkCheck(row+1,col,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row+1][col] = true;
     }
      }
  }
  //right
  if(col + 1  < 8)
  {
    if(play[row][col+1].pieceThere() == false)
    {
     holder=checkCheck(row,col+1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row][col+1] = true;
     }
    }
   
    if(play[row][col+1].pieceThere() == true && !play[row][col+1].getColor().equals(play[row][col].getColor()))
      {
     holder=checkCheck(row,col+1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row][col+1] = true;
     }
      }
  }
  
  if(col - 1  >= 0)
  {
    if(play[row][col-1].pieceThere() == false)
    {
     holder=checkCheck(row,col-1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row][col-1] = true;
     }
    }
      
    if(play[row][col-1].pieceThere() == true && !play[row][col-1].getColor().equals(play[row][col].getColor()))
    {
     holder=checkCheck(row,col-1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row][col-1] = true;
     }
    }
  }
  
  //top left
  if(row-1 >= 0 && col -1 >= 0)
  {
    if(play[row-1][col-1].pieceThere() == false)
    {
     holder=checkCheck(row-1,col-1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row-1][col-1] = true;
     }
    }
    if(play[row-1][col-1].pieceThere() == true && !play[row-1][col-1].getColor().equals(play[row][col].getColor()))
    {
     holder=checkCheck(row-1,col-1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row-1][col-1] = true;
     }
    }
  }
  
  //top right
  if(row-1 >= 0 && col +1 < 8)
  {
    if(play[row-1][col+1].pieceThere() == false)
    {
     holder=checkCheck(row-1,col+1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row-1][col+1] = true;
     }
    }
    if(play[row-1][col+1].pieceThere() == true && !play[row-1][col+1].getColor().equals(play[row][col].getColor()))
    {
     holder=checkCheck(row-1,col+1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row-1][col+1] = true;
     }
    }
  }
  
  //bottom left
  if(row+1 < 8 && col -1 >= 0)
  {
    if(play[row+1][col-1].pieceThere() == false)
    {
     holder=checkCheck(row+1,col-1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row+1][col-1] = true;
     }
    }
    if(play[row+1][col-1].pieceThere() == true && !play[row+1][col-1].getColor().equals(play[row][col].getColor()))
    {
     holder=checkCheck(row+1,col-1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row+1][col-1] = true;
     }
    }
  }
  
  //bottom right
  if(row+1 < 8 && col+1 < 8)
  {
    if(play[row+1][col+1].pieceThere() == false)
    {
     holder=checkCheck(row+1,col+1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row+1][col+1] = true;
     }
    }
    if(play[row+1][col+1].pieceThere() == true && !play[row+1][col+1].getColor().equals(play[row][col].getColor()))
    {
     holder=checkCheck(row+1,col+1,play[row][col].color);
     if(holder.isEmpty())
     {
       yellow[row+1][col+1] = true;
     }
    }
  }
  
  return holder;
}
  


 
  public ArrayList<int[]> checkCheck(int row, int col,String colour)
  {
   //check if there is a knight in any of the L places from the king
   //check if there is a bishop or queen in any of the diagonal places from king
   //check if there is a rook or queen in any of the straigt places from the king
   //if the king is in check then you are limited in the moves that you can make this is done by finding all of the moves that you can make 
   
    ArrayList<int[]> knight = AIL(row,col);
    ArrayList<int[]> line= AIline(row,col);
    ArrayList<int[]> diagnal=AIdiagnal(row,col);
    ArrayList<int[]> pawn=AIpawnAttack(row,col);
    ArrayList<int[]> loc = new ArrayList<int[]>();
    int check=0;
    for(int[] s :knight)
    {
     if(play[s[0]][s[1]].type.equals("k"))
     {
      if(!colour.equals(play[s[0]][s[1]].color))
      {
       check++;
       loc.add(new int[]{s[0],s[1]});
      }
     }
    }    
    for(int[] s :line)
    {
     if(play[s[0]][s[1]].type.equals("r")||play[s[0]][s[1]].type.equals("Q"))
     {
      if(!colour.equals(play[s[0]][s[1]].color))
      {
       check++;
       loc.add(new int[]{s[0],s[1]});
      }
     }
    }
    for(int[] s :diagnal)
    {
     if(play[s[0]][s[1]].type.equals("b")||play[s[0]][s[1]].type.equals("Q"))
     {
      if(!colour.equals(play[s[0]][s[1]].color))
      {
       check++;
       loc.add(new int[]{s[0],s[1]});
      }
     }
    }
    for(int[] s :pawn)
    {
     if(play[s[0]][s[1]].type.equals("p"))
     {
      if(!colour.equals(play[s[0]][s[1]].color))
      {
       check++;
       loc.add(new int[]{s[0],s[1]});
      }
     }
    }
    
    if(check==0)
    {
     //loc.add(new int[]{0});
     return loc;
    }else if(check==1)
    {
     return loc;
    }else if(check==2)
    {
     loc.add(0,new int[]{0,0,0});
    // System.out.println("Double Checked");
     return loc;
    }else
    {
     //System.out.println("Something went wrong It is being checked by more than two pieces" );
     loc.add(0,new int[]{0,0,0,0});
     return loc;
    }
    
    
    
 
  }

 
  
  
  
  
  
  
  public int pawnStructure(Node[][] holder,String color)
  {
   ArrayList<int[]>pawnLocation= new ArrayList<int[]>();
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(holder[i][j].type.equals("p")&&holder[i][j].getColor().equals(color))
     {
      pawnLocation.add(new int[]{i,j});
     }
    }
   }
   
  int row;
  int col;
  int total=0;
  for(int k=0;k<pawnLocation.size();k++)
  {
  row=pawnLocation.get(k)[0];
  col=pawnLocation.get(k)[1];  
  //up
     if(row - 1 >=0)
     {
       if(holder[row-1][col].pieceThere() == true&& holder[row-1][col].type.equals("p"))
       {
        total=total-1;
       }
     }
     
     //down
     if(row + 1 < 8)
     {
           if(holder[row+1][col].pieceThere() == true&& holder[row+1][col].type.equals("p"))
         {
          total=total-1;
         }    
     }
     //right
     if(col + 1  < 8)
     {
       if(holder[row][col+1].pieceThere() == true&& holder[row][col+1].type.equals("p"))
       {
        //total=total-1;
       }
      
     }
     
     if(col - 1  >= 0)
     {
       if(holder[row][col-1].pieceThere() == true&& holder[row][col-1].type.equals("p"))
       {
        //total=total-1;
       }
         
     }
     
     //top left
     if(row-1 >= 0 && col -1 >= 0)
     {
      if(holder[row-1][col-1].pieceThere() == true&& holder[row-1][col-1].type.equals("p"))
        {
         total=total-3;
        }
     }
     
     //top right
     if(row-1 >= 0 && col +1 < 8)
     {
      if(holder[row-1][col+1].pieceThere() == true&& holder[row-1][col+1].type.equals("p"))
        {
         total=total-3;
        }
     }
     
     //bottom left
     if(row+1 < 8 && col -1 >= 0)
     {
      if(holder[row+1][col-1].pieceThere() == true&& holder[row+1][col-1].type.equals("p"))
        {
         total=total+5;
        }
     }
     
     //bottom right
     if(row+1 < 8 && col+1 < 8)
     {
      if(holder[row+1][col-1].pieceThere() == true&& holder[row+1][col-1].type.equals("p"))
        {
         total=total+5;
        }
     }
  }
   
 return total;  
   
   
  }
 
  
  public int arrayWithin(ArrayList<int[]> a , ArrayList<int[]> b)
  {
   //if any parts of b are within a then return the parts of b that are in a 
   //ArrayList<int []> holder = new ArrayList<int[]>();
   int ret=0;
   for(int i=0;i<b.size();i++)
   {
    if(within(a,b.get(i)[0],b.get(i)[1]))
    {
     ret=ret+1;
    }
   }
   return ret;
  }
  
  
  public int controlCentre (Node[][]holder,ArrayList<int[]> loc,String color)
  {
   //this is attacking inner centre
   ArrayList<int[]> choice = new ArrayList<int[]>();
   //ArrayList<int[]> test = new ArrayList<int[]>();
   
   //loc is the locations of all of the black or white pieces that you would like to analyze
   ArrayList<int[]> inner = new ArrayList<int[]>();
   ArrayList<int[]> outer = new ArrayList<int[]>();
   inner.add(new int[]{3,3});
   inner.add(new int[]{4,3});
   inner.add(new int[]{3,4});
   inner.add(new int[]{4,4});
   
   outer.add(new int[]{2,2});
   outer.add(new int[]{2,3});
   outer.add(new int[]{2,4});
   outer.add(new int[]{2,5});
   outer.add(new int[]{5,2});
   outer.add(new int[]{5,3});
   outer.add(new int[]{5,4});
   outer.add(new int[]{5,5});
   outer.add(new int[]{3,2});
   outer.add(new int[]{4,2});
   outer.add(new int[]{3,5});
   outer.add(new int[]{4,5});
   
   
   //if where I am attacking in the inner is
   //add one to total the first time that it is attacking
   int price=0;
   int total=0;
   int xyz=0;
   boolean inThere=true;
   for(int i=0;i<loc.size();i++)
   {
    choice=AIconditions(holder,loc.get(i)[0],loc.get(i)[1]);
    xyz=arrayWithin(inner,choice);
    if(xyz!=0)
    {
     total=total+1;
     price=price+(4*xyz);
     continue;
    }
    
   // choice=AIconditions(holder,loc.get(i)[0],loc.get(i)[1]);
    inThere=within(inner,loc.get(i)[0],loc.get(i)[1]);
    if(inThere)
    {
     total++;
     price=price+(3);
     continue;
    }
    
    //choice=AIconditions(holder,loc.get(i)[0],loc.get(i)[1]);
    xyz=arrayWithin(outer,choice);
    if(xyz!=0)
    {
     total=total+1;
     price=price+(2*xyz);
     continue;
    }  
    
    inThere=within(inner,loc.get(i)[0],loc.get(i)[1]);
    if(inThere)
    {
     total++;
     price=price+(1);
     continue;
    }
  
    
   }
   
   int a = price/total;
   a=a/loc.size();
   
   return a;
   
   
   
   //calculate how many pieces are attacking the center if they are on or attacking outer rim =1
   
   //+1
   //if they are on or attacking inner rim the +2
   //then keep track of how many pieces are attacking the outer and inner rim 
   
   //check if attacking inner rim if so the break; 4
   //check if on inner rim if so break; 3
   //check if attacking on outer rim break; 2
   //check if on outter rim if so break; 1
   //if nothering return zero
   //calculate total of attacking pieces
   
  // /
 //  total points/5= the t
   
   //otal percentage of "white pieces attacking centre;
   //totalWhite pieces
   
   //that # should be divided by the total amount of pieces on the board
   
   //when you are checking you should have the option to exclude the king and queen
 
  
  }
  public void motion()
  {}

  public int promotion(Node[][]holder,String color)
  {
   ArrayList<int[]>pawnLocation= new ArrayList<int[]>();
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(holder[i][j].type.equals("p")&&holder[i][j].getColor().equals(color))
     {
      pawnLocation.add(new int[]{i,j});
     }
    }
   }
  int total=0;
   for(int k=0;k<8;k++)
   {
    int x=pawnLocation.get(k)[0];
    int y=pawnLocation.get(k)[1];
    while(inBoard(x,y))
    {
     if(x==7)
     {
      total=total+50;
     }
     x+=1;
    }
   }
   return total;
   
   
  }
  public void AIMove(int rowTo, int colTo,int row,int col)
  {
   int rowToMove=rowTo;
    int colToMove=colTo; 
   int pastRow= row;
   int pastCol=col;
    
          
      boolean weCastledLeft = false;
      boolean weCastledRight = false;
      //if the king castled
      if(colToMove == pastCol+2 && play[pastRow][pastCol].getType().equals("K"))
        weCastledRight = true;
      if(colToMove == pastCol-2 && play[pastRow][pastCol].getType().equals("K"))
        weCastledLeft = true;
      
      //moved
      //startingBoard.add(new int[]{pastRow,pastCol,rowToMove,colToMove});
      
      play[rowToMove][colToMove] =  play[pastRow][pastCol];
      play[rowToMove][colToMove].moved();
      play[pastRow][pastCol] = new Node();
      
      if(weCastledLeft == true)
      {
        play[rowToMove][colToMove + 1] = play[rowToMove][0];
        play[rowToMove][colToMove + 1].moved();
        play[rowToMove][0] = new Node(); 
      }
      
      if(weCastledRight == true)
      {
        play[rowToMove][colToMove - 1] = play[rowToMove][7];
        play[rowToMove][colToMove - 1].moved();
        play[rowToMove][7] = new Node(); 
      }

      
     //start.setVisible(false);
      //Right here is where the modified board is set
   //   System.out.println("Move");
     // board();
      //System.exit(0);
      
      //if blacks turn it becomes whites turn and vise versa   
    
  }
  
  public ArrayList<int[]> AI()
  {
 ArrayList<int[]> holder= alphaBeta(play);
 return holder;
  }
  
  
 /* public static void promptEnterKey(){
  //    System.out.println("Press \"ENTER\" to continue...");
      try {
          int read = System.in.read(new byte[2]);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  */
  public boolean pinned(Node[][] hold,int moveRow, int moveCol,String color)
  {
 
   int kingx=0;
   int kingy=0;
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(i>8)
     {
     // System.out.println("wrong");
     }
     if(hold[i][j].getColor().equals(color))
     {
      if(hold[i][j].type.equals("K"))
      {
      kingx=i;
      kingy=j;
      i=9;
      j=9;
     // System.out.println("King was found through PIN");
      }
     }
    }
   }
   if(kingx==0&&kingy==0)
   {
  //  System.out.println("wrong");
   // System.exit(0);
   }
   
   if(kingx==moveRow&&kingy==moveCol)
   {
    return false;
   }
   
   int XKing=kingx;
   int YKing=kingy;
   int XMove= moveRow;
   int YMove=moveCol;
   //diagonal
  
   if(Math.pow(XKing-XMove,2)==Math.pow(YKing-YMove,2))
   {
    int x=-1;
    int y=-1;
    if(XKing-XMove<0)
    {
     x=1;
    }
    if(YKing-YMove<0)
    {
     y=1;
    }
    
//    System.out.println("y equals: " + y);
  //  System.out.println("x equals: " + x); 
   XMove+=x;
   YMove+=y;
    
    while(inBoard(XMove,YMove))
    {
     if(hold[XMove][YMove].pieceThere() == true)
     {
      if(!hold[XKing][YKing].getColor().equals(hold[XMove][YMove].getColor()))
      {
       if(hold[XMove][YMove].type.equals("b"))
       {
        return true;
       }
       else
       {
      //  System.out.println("Didnt equal bishop");
       }
       if(hold[XMove][YMove].type.equals("Q"))
       {
        return true;
       }
       return false;
      }
      return false;
     }
     XMove+=x;
     YMove+=y;
    }
    return false;
    
   }
 
  
   //return false;
   //This is checking up and down
  else 
    if(XKing==XMove)
   {
  
    int y=1;
   if(XKing-XMove<0)
    {
     y=-1;
    }
 //   System.out.println("y equals" + y);
     YMove+=y;
     while(inBoard(XMove,YMove))
     {
      if(hold[XMove][YMove].pieceThere() == true)
      {
       if(!hold[XKing][YKing].getColor().equals(hold[XMove][YMove].getColor()))
       {
        if(hold[XMove][YMove].type.equals("r"))
        {
         return true;
        }
        if(hold[XMove][YMove].type.equals("Q"))
        {
      //   System.out.println("Equaled Queen");
         return true;
        }
        return false;
       }
       return false;
      }
     
      YMove+=y;
     }
     return false;
    
   }
    else
    if(YKing==YMove)
   {
    
    int x=-1;
   if(YKing-YMove<0)
    {
     x=1;
    }
     XMove+=x;
     while(inBoard(XMove,YMove))
     {
      if(hold[XMove][YMove].pieceThere() == true)
      {
       if(!hold[XKing][YKing].getColor().equals(hold[XMove][YMove].getColor()))
       {
        if(hold[XMove][YMove].type.equals("r"))
        {
         return true;
        }
        if(hold[XMove][YMove].type.equals("Q"))
        {
         return true;
        }
        return false;
       }
       return false;
      }
     
      XMove+=x;
     }
     return false;  
   }else
   {
    return false;
   }
   
   
  }
 
  public ArrayList<int[]> kingCheck(Node[][] hold,String color)
  {
   
   int XKing=0;
   int YKing=0;
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(i>8)
     {
 //    System.out.println("wrong");
     }
     if(hold[i][j].getColor().equals(color))
     {
      if(hold[i][j].type.equals("K"))
      {
      XKing=i;
      YKing=j;
      i=9;
      j=9;
     // System.out.println("King was found through PIN");
      }
     }
    }
   }
   
   
   
   
   
   
   ArrayList<int[]> holder = AIking(XKing,YKing);
   ArrayList<int[]> ret = new  ArrayList<int[]>();
   int [] the = new int[2];
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere()==false)
    {
     the = kingMove(hold,holder.get(i)[0],holder.get(i)[1],color); 
     if(the[0]==-1)
     {
      ret.add(new int[]{holder.get(i)[0],holder.get(i)[1]});
     }
    }
    else if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere()==true&&!(hold[holder.get(i)[0]][holder.get(i)[1]].getColor().equals(color)))
    {
     the = kingMove(hold,holder.get(i)[0],holder.get(i)[1],color); 
     if(the[0]==-1)
     {
      ret.add(new int[]{holder.get(i)[0],holder.get(i)[1]});
     }
    } 
    else
    {
 //    System.out.println("This position was not added");
    }
   }
   
   //System.out.println("This is the end holderRRRRRRRRRRRRR");
   //print(holder);
   //System.out.println("This is the end holderRRRRRRRRRRRRRRR");
   
   //ArrayList<int[]> test = new ArrayList<int[]>();
   //System.out.println("This is the end holderEEEEEEEEE");
   //print(holder);
   //System.out.println("This is the end holderEEEEEEEE");
   for(int i = 0;i<holder.size();i++)
   {
  if(!within(ret,holder.get(i)[0],holder.get(i)[1]))
  {
   holder.remove(i);
  }
   
  
   }
   
  // System.out.println("This is the end holder");
   //print(holder);
   //System.out.println("This is the end holder");
   return holder;
   
   
   
  }
  
  public boolean within(ArrayList<int[]> a,int x,int y)
  {
   for(int i=0;i<a.size();i++)
   {
    if(a.get(i)[0]==x&&a.get(i)[1]==y)
     return true;
   }
   return false;
  }
 
  public int[] check(Node[][] hold,String color)
  {
   int check=0;
   int [] test = new int[2];
   
   
   //move row
   //move col
   //they are the position of the piece that is attacking the king
   ArrayList<int[]> holder = new ArrayList<int[]>();
   int XKing=0;
   int YKing=0;
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(i>8)
     {
    //  System.out.println("wrong");
     }
     if(hold[i][j].getColor().equals(color))
     {
      if(hold[i][j].type.equals("K"))
      {
      XKing=i;
      YKing=j;
      i=9;
      j=9;
     // System.out.println("King was found through PIN");
      }
     }
    }
   }
   
   
   holder = AIL(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("k"))
      {
    //   System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      }    
     }
    }
   }
   
   holder = AIdiagnal(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("b"))
      {
     //  System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      }  
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("Q"))
      {
     //  System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      } 
    
     }
    }
   }
   holder = AIpawnAttack(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("p"))
      {
  //     System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      } 
    
     }
    }
   }
   holder = AIline(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("r"))
      {
    //   System.out.println("The king is in check by the Rook");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      }    
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("Q"))
      {
   //    System.out.println("The king is in check by the QueenLine");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      } 
     }
    }
   }
     
     
     
     if(check==0)
     {
      return new int[]{-1,-1};
     }
     if(check==1)
     {
      return test;
     }else
     {
    //  System.out.println("The king is in a double check ");
      return new int[]{9,9};
     }
  }
  
  public boolean inBoard(int row,int col)
  {
   if (row >= 0 && col >= 0&&row < 8 && col < 8)
   return true;
        
   return false;
  }

  public ArrayList<int[]> inBetween(Node[][] hold,int moveRow, int moveCol,String color)
  {
   
   
   //move row
   //move col
   //they are the position of the piece that is attacking the king
   ArrayList<int[]> holder = new ArrayList<int[]>();
   int kingx=0;
   int kingy=0;
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(i>8)
     {
      //System.out.println("wrong");
     }
     if(hold[i][j].getColor().equals(color))
     {
      if(hold[i][j].type.equals("K"))
      {
      kingx=i;
      kingy=j;
      i=9;
      j=9;
   //   System.out.println("King was found through PIN");
      }
     }
    }
   }
   holder.add(new int[]{moveRow,moveCol});
   if(kingx==0&&kingy==0)
   {
  //  System.out.println("wrong");
   // System.exit(0);
   }
   
 
   
   int XKing=kingx;
   int YKing=kingy;
   int XMove= moveRow;
   int YMove=moveCol;
   //diagonal
  
   if(Math.pow(XKing-XMove,2)==Math.pow(YKing-YMove,2))
   {
    int x=-1;
    int y=-1;
    if(XKing-XMove<0)
    {
     x=1;
    }
    if(YKing-YMove<0)
    {
     y=1;
    }
    
  //  System.out.println("y equals: " + y);
    //System.out.println("x equals: " + x); 
   XKing+=x;
   YKing+=y;
    
    while(inBoard(XKing,YKing))
    {
     if(hold[XKing][YKing].pieceThere() == false)
     {
      holder.add(new int[]{XKing,YKing});
     }
     else
     {
      return holder;
     }
     XKing+=x;
     YKing+=y;
    }
    return holder;
    
   }
 
  
   //return false;
   //This is checking up and down
  else 
    if(XKing==XMove)
   {
  
    int y=1;
   if(XKing-XMove<0)
    {
     y=-1;
    }
//    System.out.println("y equals" + y);
     YKing+=y;
     while(inBoard(XKing,YKing))
     {
      if(hold[XKing][YKing].pieceThere() == false)
      {
       holder.add(new int[]{XKing,YKing});
       
      }
      else
      {
       return holder;
      }
     
      YKing+=y;
     }
     return holder;
    
   }
    else
    if(YKing==YMove)
   {
    
    int x=-1;
   if(YKing-YMove<0)
    {
     x=1;
    }
     XKing+=x;
     while(inBoard(XKing,YKing))
     {
      if(hold[XKing][YKing].pieceThere() == false)
      {
       holder.add(new int[]{XKing,YKing});
    
      }
      else
      {
       return holder;
      }
     
      XMove+=x;
     }
     return holder;  
   }else
   {
    return holder;
   }
   

  }
  
 
 
  
  //I need the method to determine if king is in check 
  //determine if king is in double or single check
  //Is the king in check and where the kng is in check 
  public int[] kingMove(Node[][] hold,int x, int y,String color)
  {
   
   int XKing=x;
   int YKing=y;
 
   
   
   
   int check=0;
   int [] test = new int[2];
   
   
   //move row
   //move col
   //they are the position of the piece that is attacking the king
   ArrayList<int[]> holder = new ArrayList<int[]>();
   
 //ifthe king is in check, you need to locate the positions that will not put it into check such as 
   
   //if(hold)
   holder = AIL(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("k"))
      {
     //  System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      }    
     }
    }
   }
   
   holder = AIdiagnal(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("b"))
      {
      // System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      }  
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("Q"))
      {
    //   System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      } 
    
     }
    }
   }
   holder = AIpawnAttack(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("p"))
      {
   //    System.out.println("The king is in check by the Knight");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      } 
    
     }
    }
   }
   holder = AIline(XKing,YKing);
   for(int i=0;i<holder.size();i++)
   {
    if(hold[holder.get(i)[0]][holder.get(i)[1]].pieceThere() == true)
    {
     if(!hold[holder.get(i)[0]][holder.get(i)[1]].color.equals(color))
     {
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("r"))
      {
  //     System.out.println("The king is in check by the Rook");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      }    
      if(hold[holder.get(i)[0]][holder.get(i)[1]].type.equals("Q"))
      {
     //  System.out.println("The king is in check by the QueenLine");
       test[0]=holder.get(i)[0];
     test[1]=holder.get(i)[1];   
       ++check;
      } 
     }
    }
   }
     
     
     
     if(check==0)
     {
      return new int[]{-1,-1};
     }
     if(check==1)
     {
      return test;
     }else
     {
     // System.out.println("The king is in a double check ");
      return new int[]{9,9};
     }
  }
  
  public void checkCheck(int row, int col)
  {
    L(row,col);
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        //if there is a spot that has an enemy peice on it
        if(yellow[i][j] == true && play[i][j].getColor().equals(comIs) && play[i][j].getType().equals("k"))
        {
          if(play[row][col].getColor().equals(playerIs))
            playerCheck = true;
          else if (play[row][col].getColor().equals(comIs))
            comCheck = true;
        }          
      }
    }
    clearYellow();
    
    line(row,col);
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        //if there is a spot that has an enemy peice on it
        if(yellow[i][j] == true && play[i][j].getColor().equals(comIs) && (play[i][j].getType().equals("r") ||play[i][j].getType().equals("q") ))
        {
          if(play[row][col].getColor().equals(playerIs))
            playerCheck = true;
          else if (play[row][col].getColor().equals(comIs))
            comCheck = true;
        }
      }
    }
    clearYellow();
    
    diagnal(row,col);
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        //if there is a spot that has an enemy peice on it
        if(yellow[i][j] == true && play[i][j].getColor().equals(comIs) && (play[i][j].getType().equals("b") ||play[i][j].getType().equals("q") ))
        {
          if(play[row][col].getColor().equals(playerIs))
            playerCheck = true;
          else if (play[row][col].getColor().equals(comIs))
            comCheck = true;
        }
      }
    }
    clearYellow();
    
    pawnAttack(row,col);
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        //if there is a spot that has an enemy peice on it
        if(yellow[i][j] == true && play[i][j].getColor().equals(comIs) && (play[i][j].getType().equals("b") ||play[i][j].getType().equals("q") ))
        {
          if(play[row][col].getColor().equals(playerIs))
            playerCheck = true;
          else if (play[row][col].getColor().equals(comIs))
            comCheck = true; 
        }
      }
    }    
    if(playerCheck == true)
    {
      playerCheck = false;
   //   System.out.println("You are in check");
    }
    else if(comCheck == true)
    {
      comCheck = false;
    //  System.out.println("They are in check");
    }
  }
  
  public static ArrayList<int[]> AIconditions(Node [][]beg,int row, int col,ArrayList<int[]> ret)
  {    
    //rook
   ArrayList<int[]> holder = new ArrayList<int[]>();
    if(beg[row][col].getType().equals("r"))
      {
 //    System.out.println("Rook");
     holder=AIline(row,col); 
      }
    //knight
    if(beg[row][col].getType().equals("k"))
    {
   //  System.out.println("Knight");
     holder=AIL(row, col);
    }
      //bishop
    if(beg[row][col].getType().equals("b"))
    {
     //System.out.println("Bishop");
     holder=AIdiagnal(row, col);
    }
      //queen
    if(beg[row][col].getType().equals("Q"))
    {
     //System.out.println("Queen");
      holder=AIline(row,col);
      holder=AIdiagnal(row, col);
    }
    //king
    if(beg[row][col].getType().equals("K"))
    {
     //System.out.println("King");
      holder=AIking(row, col);
    }
      //pawn
    if(beg[row][col].getType().equals("p"))
    {
    // System.out.println("Pawn");
     holder=AIpawnMove(row, col);
        holder=AIpawnAttack(row, col);
    }
    return holder;
  }
 
  

  
 
  public int evaluateFunction(Node[][]a,boolean choice,int state)
  {//if choice is true then you tend to the white pieces
  // String colour;
   int fitness=0;
   for(int i=0;i<8;i++)
   {
    for(int j=0;j<8;j++)
    {
     if(a[i][j].getColor().equals("Black"))
     {
       fitness+=a[i][j].getPoints();  
     }
     if(a[i][j].getColor().equals("White"))
     {
       fitness-=a[i][j].getPoints();  
     }
    }
   }
   //int total=0;
   
   if(fitness==0)//equal
   {
    
   }
   else if(fitness<0)//white is winning
   {
    fitness=-100;
   }
   else if(fitness>0)//black is winning
   {
    fitness=50;
   }
   
  if(choice)
  {
   fitness=0;
     for(int i=0;i<8;i++)
     {
      for(int j=0;j<8;j++)
      {
       if(a[i][j].getColor().equals("White"))
       {
         fitness-=a[i][j].getPoints();
        
       }
      }
     }
  }else
  {
   //trying to minimize black, trying to 
   //protect King
   //kill king
   //keep pieces
   //control center
   //fiderchedo bishop
   //castle
   //control main lines- 
   //rook have nothing in front
   //bishop X line
   //center or anything that spreads from the center
  // colour="black";
   fitness=0;
  //AICondition
   //if you are on,or control these squares
   //I need to know where all of the black pieces are 
   
   ArrayList<int[]> location = new ArrayList<int[]>();
     for(int i=0;i<8;i++)
     {
      for(int j=0;j<8;j++)
      {
       if(a[i][j].getColor().equals("Black"))
       {
        if(a[i][j].type.equals("k")||a[i][j].type.equals("b")||(i==3&&j==3)||(i==3&&j==4)||(i==4&&j==4)||(i==4&&j==3)||(i==2&&j==2)||(i==2&&j==3)||(i==2&&j==4)||(i==2&&j==5))
        {
        location.add(new int[]{i,j});
        }
       }
      }
     } 
     
     ArrayList<int[]> test= new ArrayList<int[]>();// = AIconditions(posX,posY);
   
   
  
     

    int middle=0; 
    
     for(int i=0;i<location.size();i++)
     {
      //test.clear();
      test.addAll(AIconditions(a,location.get(i)[0],location.get(i)[1]));
   
     }
     
    for(int i=0;i<test.size();i++)
    {
     if(test.get(i)[0]==3)
     {
      if(test.get(i)[1]==3)
      {
       middle++;
      }
     }
     if(test.get(i)[0]==3)
     {
      if(test.get(i)[1]==4)
      {
       middle++;
      }
     }
     if(test.get(i)[0]==4)
     {
      if(test.get(i)[1]==3)
      {
       middle++;
      }
     }
     if(test.get(i)[0]==4)
     {
      if(test.get(i)[1]==4)
      {
       middle++;
      }
     }
   }
   
    fitness+=middle;
    //this is checking for 
    //this is checking for to see if all of the minor pieces have been developed
    if(!a[0][1].type.equals("k"))
     fitness+=10;
    if(!a[0][2].type.equals("b"))
     fitness+=10;
    if(!a[0][5].type.equals("k"))
     fitness+=10;
    if(!a[0][6].type.equals("b"))
     fitness+=10;
    
    //I need to check if the king has been castled
    //So i need to check if in the original state the king was castled 
    //if the king was not castled in the original state and is castled in this state, then it should be castled
    /*if(originalStateCastle)
    {
     
    }
    else
    {
     if(nodeStateCastle)
     {
      
     }
    }*/
    
    
    
   
   
   
  }
  
  return fitness;
  

}
 

  
}

