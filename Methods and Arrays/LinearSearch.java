/*
Question #11 — Linear Search
Write this method: public static int linearSearch(int[] numbers, int target)
What it should do
* Search for target in the array.
* If found, return its index.
* If not found, return -1.
Inside main()
1. Ask for array size.
2. Input array elements.
3. Ask for the target.
4. Call:
*/
import java.util.Scanner;
public class LinearSearch{
    public static int linearSearch(int[] numbers, int target){
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == target){
                return i;
            }
        }
        return -1;
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
        int lSearch = linearSearch(numArray, numTarget);
        if(lSearch == -1){
            System.out.println("Target not found!");
        }
        else{
            System.out.println("Target found at " + lSearch);
        }
        sc.close();
    }
}