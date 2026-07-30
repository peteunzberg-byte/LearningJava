/*Question #3 — Largest Number
Write a Java program that:
1. Ask the user to enter 5 integers.
2. Print the largest among them.
import java.util.Scanner;
*/
public class LargestNumber{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 5; i++){
            System.out.print("Enter your number: ");
            int number = sc.nextInt();
            if(number > max){
                max = number;
            }
        }
        System.out.println("The largest number is " + max);
        sc.close();
    }
}