/* 
Question #12 — Count Occurrences
Write: public static int countOccurrences(int[] numbers, int target)
Return how many times target appears in the array.
*/
import java.util.Scanner;
public class CountOccurrences{
    public static int countOccurrences(int[] numbers, int target){
        int count = 0;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == target){
                count++;
            }
        }
        return count;
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
        System.out.print("Enter your target element: ");
        int numTarget = sc.nextInt();
        int occurCount = countOccurrences(numArray, numTarget);
        System.out.println("Your target element " + numTarget 
                         + " appears " + occurCount + " times in the array.");
        sc.close();
    }
}
