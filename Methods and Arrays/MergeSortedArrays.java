/*
Question 15 — Merge Two Sorted Arrays
Write: public static int[] mergeSortedArrays(int[] first, int[] second)
Rules:
* Both input arrays are already sorted.
* Return one new sorted array.
* Do not sort the merged array afterward.
* Aim for O(n + m) time.
*/ 
import java.util.Scanner;
public class MergeSortedArrays{
    public static int[] mergeSortedArrays(int[] first, int[] second){
        int n = first.length, m = second.length;
        int[] merged = new int[n + m];
        int i = 0, j = 0, k = 0;
        while((i < n) && (j < m)){
            if(first[i] < second[j]){
                merged[k] = first[i];
                i++;
            }
            else{
                merged[k] = second[j];
                j++;
            }
            k++;
        }
        while(i<n){
            merged[k] = first[i];
            i++;
            k++;
        }
        while(j<m){
            merged[k] = second[j];
            j++;
            k++;
        }
        return merged;

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your first sorted array size: ");
        int size1 = sc.nextInt();
        int[] numArray1 = new int[size1];
        System.out.print("Enter your second sorted array size: ");
        int size2 = sc.nextInt();
        int[] numArray2 = new int[size2];
        for(int i = 0; i < size1; i++){
            System.out.print("Enter first sorted array element " + (i+1) + " : ");
            numArray1[i] = sc.nextInt();
        }
        for(int i = 0; i < size2; i++){
            System.out.print("Enter second sorted array element " + (i+1) + " : ");
            numArray2[i] = sc.nextInt();
        }
        int[] mergedArray = mergeSortedArrays(numArray1, numArray2);
        for(int num: mergedArray){
            System.out.print(num + " ");
        }
        sc.close();
    }
}