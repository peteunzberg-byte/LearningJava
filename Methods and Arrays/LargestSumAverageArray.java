/*
Question #8
Problem
Write a method: public static int findLargest(int[] numbers)
The method should return the largest number in the array.
Inside main():
1. Ask the user size
2. Create an array of that size.
3. Read all the numbers into the array.
4. Call
5. Print the result.
(Also write methods for array sum and array average)
*/
import java.util.Scanner;
public class LargestSumAverageArray{
    public static int findLargest(int[] numbers){
        int largestNumber = numbers[0];
        for(int i = 1; i < numbers.length; i++){
            if(numbers[i] > largestNumber){
                largestNumber = numbers[i];
            }
        }
        return largestNumber;
    }
    public static int arraySum(int[] numbers){
        int sum = 0;
        for(int i = 0; i < numbers.length; i++){
            sum += numbers[i];
        }
        return sum;
    }
    public static double arrayAverage(int[] numbers){
        return arraySum(numbers)/ (double)numbers.length;
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
        int maxNum = findLargest(numArray);
        int numSum = arraySum(numArray);
        double numAverage = arrayAverage(numArray);
        System.out.println("The largest array element is " + maxNum 
                         + ", the sum of all array elements is " + numSum 
                         + " and the average of all array elements is " + numAverage);   
        sc.close();         
    }
}
