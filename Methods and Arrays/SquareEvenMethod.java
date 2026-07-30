/*
Question #5 - public static int square(int number)
The method should:
* Receive an integer.
* Return its square.
Inside main():
1. Ask the user for a number.
2. Call square().
3. Store the returned value.
4. Print: The square is " " 
public static boolean isEven(int number)
The method should return:
* true if the number is even.
* false otherwise.
Inside main():
Ask the user for a number.
Call the method.
If the method returns true, print: The number is even
*/

import java.util.Scanner;
public class SquareEvenMethod{
    public static int square(int number){
        return number * number;
    }
    public static boolean isEven(int number){
        return (number % 2 == 0);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your number: ");
        int num = sc.nextInt();
        int numSquare = square(num);
        System.out.println("The square of " + num + " is " + numSquare);
        if(isEven(num)){
            System.out.println("Your number is Even!");
        }
        else{
            System.out.println("Your number is Odd!");
        }
        sc.close();
    }
}