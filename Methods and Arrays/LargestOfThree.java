/*
Question 6 — Find the Largest of Three Numbers (Using a Method)
Problem
Write a method: public static int largest(int a, int b, int c)
The method should:
* Take three integers as parameters.
* Return the largest integer.
*/
import java.util.Scanner;
public class LargestOfThree{
    public static int largest(int a, int b, int c){
        if ((a >= b) && (a >= c)){
            return a;
        }
        else if ((b >= a) && (b >= c)){
            return b;
        }
        else{
            return c;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your first number: ");
        int num1 = sc.nextInt();
        System.out.print("Enter your second number: ");
        int num2 = sc.nextInt();
        System.out.print("Enter your third number: ");
        int num3 = sc.nextInt();
        int maxNum = largest(num1, num2, num3);
        System.out.println("The largest number is " + maxnum);
        sc.close();
    }
}