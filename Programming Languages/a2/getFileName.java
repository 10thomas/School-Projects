import java.io.*;
import java.util.Scanner;
class getFileName
{
  public String getFileName()
  {
    System.out.print("Enter the file name: ");
    Scanner scan = new Scanner(System.in);
    String fileName = scan.nextLine();
    scan.close();
    return fileName;
  } //getFileName
}