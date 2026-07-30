/*
Question #4 — Count Even and Odd Numbers
Problem
Write a Java program that:
1. Ask the user how many integers they want to enter.
2. Read all those integers.
3. Print:
    * Number of even integers
    * Number of odd integers
*/
import java.util.Scanner;
public class CountEvenOdd{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("How many integers they want to enter?: ");
        int count = sc.nextInt();
        int evenCount = 0;
        int oddCount = 0;
        for(int i = 0; i < count; i++){
            System.out.print("Enter your number: ");
            int number = sc.nextInt();
            if(number % 2 == 0){
                evenCount += 1;
            }
            else{
                oddCount += 1;
            }
        }
        System.out.println("Number of even integers: " + evenCount);
        System.out.println("Number of odd integers: " + oddCount);
        sc.close();
    }
}