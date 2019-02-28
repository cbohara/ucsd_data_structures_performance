/* Implementation of selection sort
 * 
 *  Best case - O(n^2)
 *  Average case - O(n^2)
 *  Worse case - O(n^2)
 */
package practice;

public class SelectionSort {
	public static int[] selectionSort(int[] values) {
	    int currentIndex = 0;
		while (currentIndex < values.length) {
			int minValueIndex = currentIndex;
			for (int i = currentIndex; i < values.length; i++) {
				if (values[i] < values[minValueIndex]) {
					minValueIndex = i;
				}
			}
			swap(values, currentIndex, minValueIndex);
			currentIndex++;
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
		int[] sortedValues = selectionSort(values);
		for (int i : sortedValues) {
			System.out.println(i);
		}
	}	
}