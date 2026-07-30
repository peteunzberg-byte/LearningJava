/*
Question #1 - Even or Odd
Write a Java program that:
1. Takes an integer as input from the user.
2. Prints:
    * "Even" if the number is even.
    * "Odd" if the number is odd.
*/
import java.util.Scanner;
public class EvenOrOdd{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);   
        System.out.print("Enter your number: "); 
        int number = sc.nextInt();
        if(number % 2 == 0){
            System.out.println(number + " is even!");
        }
        else{
            System.out.println(number + " is odd!");
        }
        sc.close();
    }
}