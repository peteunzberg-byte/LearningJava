/*
Question #13 — Second Largest Element
Write: public static int secondLargest(int[] numbers)
Return the second largest distinct element.
*/
import java.util.Scanner;
public class SecondLargestElement{
    public static int secondLargest(int[] numbers){
        int largestElement = Integer.MIN_VALUE;
        int secondLargestElement = Integer.MIN_VALUE;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] > largestElement){
                secondLargestElement = largestElement;
                largestElement = numbers[i];
            }
            else if((numbers[i] > secondLargestElement) && (numbers[i] < largestElement)){
                secondLargestElement = numbers[i];
            }
        }
        if(secondLargestElement == Integer.MIN_VALUE){
            return -1;
        }
        return secondLargestElement;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = sc.nextInt();
        int[] numArray = new int[size];
        for(int i = 0; i < size; i++){
            System.out.print("Enter array element " + (i+1) + " : ");
            numArray[i] = sc.nextInt();
        }
        int secondMax = secondLargest(numArray);
        if(secondMax == -1){
            System.out.println("There is no second largest distinct array element");
        }
        else{
            System.out.println("The second largest distinct array element is " + secondMax);
        }
        sc.close();
    }
}