/*
Question #9 — Reverse an Array
Write this method: public static void reverse(int[] numbers)
Inside main()
1. Ask the user for array size.
2. Read the array.
3. Call reverse(numbers).
4. Print the reversed array.
*/
import java.util.Scanner;
public class ReverseArray{
    public static void reverse(int[] numbers){
        int n = numbers.length;
        for(int i = 0; i < n/2; i++){
            int temp = numbers[i];
            numbers[i] = numbers[n-i-1];
            numbers[n-i-1] = temp;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your array size: ");
        int size = sc.nextInt();
        int[] numArray = new int[size];
        for(int i = 0; i < size; i++){
            System.out.print("Enter your array element: ");
            numArray[i] = sc.nextInt();
        }
        // printing loop before reversal
        for(int i = 0; i < size; i++){
            System.out.print(numArray[i] + " ");
        }
        reverse(numArray);
        System.out.println("\nReversed Array: ");
        // printing loop after reversal
        for(int i = 0; i < size; i++){
            System.out.print(numArray[i] + " ");
        }
        sc.close();
    }
}