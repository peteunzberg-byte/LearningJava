/*
Question #10 — Check if an Array is Sorted
Write: public static boolean isSorted(int[] numbers)
Return true if the array is sorted in non-decreasing order.
*/
import java.util.Scanner;
public class ArraySortCheck{
    public static boolean isSorted(int[] numbers){
        for(int i = 1; i < numbers.length; i++){
            if(numbers[i-1] > numbers[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Array Size: ");
        int size = sc.nextInt();
        int[] numArray = new int[size];
        for(int i = 0; i < size; i++){
            System.out.print("Enter array element " + (i+1) + " : ");
            numArray[i] = sc.nextInt();
        }
        boolean sortCheck = isSorted(numArray);
        if(sortCheck){
            System.out.println("Your array is sorted!");
        }
        else{
            System.out.println("Your array is not sorted");
        }
        sc.close();
    }
}