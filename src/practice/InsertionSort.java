/* 
 * Implementation of insertion sort
 * Best case - 
 * Average case -
 *  Worse case - O(n^2)
 */
package practice;

public class InsertionSort {
	public static int[] insertionSort(int[] values) {
		for (int i = 1; i < values.length; i++) {
			int currentIndex = i;
			int compareIndex = i - 1;
			while (compareIndex >= 0) {
				if (values[currentIndex] < values[compareIndex]) {
					swap(values, compareIndex, currentIndex);
					currentIndex--;
				}
				compareIndex--;
			}
		}
		return values;
	}
	
	public static void swap(int[] values, int currentIndex, int minIndex) {
		int temp = values[minIndex];
		values[minIndex] = values[currentIndex];
		values[currentIndex] = temp;
	}

	public static void main(String args[]) {
		int[] values = { 4, 7, 2, 10, 1, 8 };
		int[] sortedValues = insertionSort(values);
		for (int i : sortedValues) {
			System.out.println(i);
		}
	}	
}
