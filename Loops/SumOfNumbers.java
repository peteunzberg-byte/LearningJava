/*
Question #2 - Sum of Numbers
Write a Java program that:
1. Ask the user for a positive integer n.
2. Calculate the sum of all numbers from 1 to n.
3. Print the answer.
*/
import java.util.Scanner;
public class SumOfNumbers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your number: ");
        int number = sc.nextInt();
        int sum = 0;
        for(int i = 1; i <= number; i++){
            sum += i;
        }
        System.out.println("The sum of all integers from 1 to " 
        + number + " is " + sum);
    }
}