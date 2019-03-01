/* 
 * Implementation of insertion sort
 * Best case - O(n)
 *  Worse case - O(n^2)
 */
package practice;

public class InsertionSort {
	public static int[] insertionSort(int[] values) {
		int currentIndex;
		for (int i = 1; i < values.length; i++) {
			currentIndex = i;
			while (currentIndex > 0 && values[currentIndex] < values[currentIndex - 1]) {
				swap(values, currentIndex, currentIndex - 1);
				currentIndex--;
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
