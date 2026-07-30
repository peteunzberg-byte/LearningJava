/*
Question #7 — Factorial (Methods + Loops)
Problem
Write a method: public static long factorial(int number)
The method should return the factorial of the given number.
*/
import java.util.Scanner;
public class Factorial{
    public static long factorial(int number){
        long fact = 1;
        for(int i = 2; i <= number; i++){
            fact *= i;
        }
        return fact;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your number: ");
        int num = sc.nextInt();
        long factNum = factorial(num);
        System.out.println("The factorial of " + num + " is " + factNum);
        sc.close();
    }
}