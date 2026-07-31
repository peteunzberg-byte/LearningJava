/*
Question 14 — Move All Zeros to the End
Write: public static void moveZerosToEnd(int[] numbers)
Rules
Modify the original array.
Don’t create another array.
Try to do it in one traversal (O(n)).
Preserve the order of the non-zero elements.
*/
import java.util.Scanner;
public class ZerosToArrayEnd{
    public static void moveZerosToEnd(int[] numbers){
        int nonZeroCount = 0;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] != 0){
                numbers[nonZeroCount] = numbers[i];
                nonZeroCount++;
            }
        }
        for(int i = nonZeroCount; i < numbers.length; i++){
            numbers[i] = 0;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your array size: ");
        int size = sc.nextInt();
        int[] numArray = new int[size];
        for(int i = 0; i < size; i++){
            System.out.print("Enter array element " + (i+1) + " : ");
            numArray[i] = sc.nextInt();
        }
        // printing the array before moving zeros
        for(int num: numArray){
            System.out.print(num + " ");
        }
        moveZerosToEnd(numArray);
        //printing the array after moving zeros
        System.out.println("\nNew Array: ");
        for(int num: numArray){
            System.out.print(num + " ");
        }
        sc.close();
    }
}
