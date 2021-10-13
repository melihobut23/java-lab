import java.util.Scanner;
/*
 * Class that returns number of e in string
 * 
 * @date 25.04.2020
 * @author melihobut
 */

public class ECounter
{
   public static void main(String[] args) //main method
   {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter a string.");
      String input = scan.nextLine();
      System.out.println("Number of 'e' in the string is " + counter(input));
   }
   public static int counter(String input ){
      char e = 'e';
      if (input.length() == 0) {
         return 0;
      }
      int count = 0;
      if (input.charAt(0) == e) {
         count++;
      }
      return count + counter(input.substring(1));
   }
}