//Thomas Tsinokas
//COSC 2P03
//SEPT 28 2015
class linkedList
{
  public  Node start;
  public  Node end;
  public String ciph = "";
  
  public linkedList()
  {
    start =new Node(null);
  }
  
  public void atEnd(String val)
  {
    Node nod = new Node (val,null);
    nod.setNext(start);
    
    if(end == null)
    {
      start = nod;
      nod.setNext(start);
      end = start;
    }
    else
    {
      end.setNext(nod);
      end = nod;
    }
  }
  
  public void cipher(int i, String newWord)
  {    
    Node head = start;
    Node backup = start;
    
    
    for ( int k = 0; k < newWord.length(); k ++)
    {
      head = head.getNext();
      //we print the next value which is the letter we want
      System.out.print(head.getNext().value());//n
      //since "head" is still the 2nd letter of the list we 
      //use .next.next to move over two letters and have the
      //2nd letter point to the 4th and 'delte' the third
      head.setNext(head.getNext().getNext());
      //we now move to the 3rd letter that was the 4th before
      head = head.getNext();
    }
    System.out.println(); 
  }
  
  public void display(String word)
  {
    Node head = start;
    
    for ( int i = 0; i < word.length(); i ++)
    {
      System.out.print(head.value());
      head = head.getNext();
    }
    System.out.println();
  }
}