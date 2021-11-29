import java.lang.Comparable;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuickSorter {

	public static void main(String args[]) {
		int arg1 = -1;
		String arg2, arg3, arg4;
		arg2 = arg3 = arg4 = "";

		// Convert arg1 to int from string[]
		if (args.length > 0) {
			try {
				arg1 = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(1);
			}
		}
		// Convert arg2-4 to string from string[]
		if (args.length == 4) {
			try {
				arg2 = args[1];
				arg3 = args[2];
				arg4 = args[3];
			} catch (NumberFormatException e) {
				System.err.println("Incorrect formatting on arguments 2-4, must be STRING");
				System.exit(1);
			}
		}

		// Generate the array
		//ArrayList<Integer> list = QuickSorter.generateRandomList(arg1); 	// Fully Random
		ArrayList<Integer> list = QuickSorter.generateSortedList(arg1); 	// Sorted
		// ArrayList<Integer> list = QuickSorter.generateSorted10P(arg1); 	//Sorted, then 10% swapped

		// Write the unsorted array to file arg3.txt
		try {
			FileWriter myWriter = new FileWriter(arg3);
			for (int i = 0; i < arg1; i++) {
				myWriter.write(list.get(i).toString() + " ");
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file: " + arg3);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Write the report to file arg2.txt
		try {
			FileWriter myWriter = new FileWriter(arg2);
			String raySize = Integer.toString(arg1);
			myWriter.write("Array Size = " + raySize + "\n");
			try{
				Duration time1 = QuickSorter.timedQuickSort(list, QuickSorter.PivotStrategy.FIRST_ELEMENT);
				myWriter.write("FIRST_ELEMENT : " + time1.toString() + "\n");
	        }
	        catch(StackOverflowError e){
	        	myWriter.write("FIRST_ELEMENT : Stack Overflow Error\n");
	        	myWriter.flush();
	        	System.err.println("ouch1!");
	        }
			try{
				Duration time2 = QuickSorter.timedQuickSort(list, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
				myWriter.write("RANDOM_ELEMENT : " + time2.toString() + "\n");
	        }
	        catch(StackOverflowError e){
	        	myWriter.write("RANDOM_ELEMENT : Stack Overflow Error\n");
	        	myWriter.flush();
	        	System.err.println("ouch2!");
	        }
			try{
				Duration time3 = QuickSorter.timedQuickSort(list, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
				myWriter.write("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + time3.toString() + "\n");
	        }
	        catch(StackOverflowError e){
	        	myWriter.write("MEDIAN_OF_THREE_RANDOM_ELEMENTS : Stack Overflow Error\n");
	        	myWriter.flush();
	        	System.err.println("ouch3!");
	        }
			try{
				Duration time4 = QuickSorter.timedQuickSort(list, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
				myWriter.write("MEDIAN_OF_THREE_ELEMENTS : " + time4.toString() + "\n");
	        }
	        catch(StackOverflowError e){
	        	myWriter.write("MEDIAN_OF_THREE_ELEMENTS : Stack Overflow Error\n");
	        	myWriter.flush();
	        	System.err.println("ouch4!");
	        }
			myWriter.close();
			System.out.println("Successfully wrote to the file: " + arg2);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Write the sorted array to arg4.txt
		try {
			FileWriter myWriter = new FileWriter(arg4);
			for (int i = 0; i < arg1; i++) {
				myWriter.write(list.get(i).toString() + " ");
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file: " + arg4);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	//timedQuickSort is the main runner for this class. It takes in an ArrayList<E> and a PivotStrategy, and proceeds to call
	//the relevant sorting strategy after starting a timer using java.time.Duration. Also checks for NullPointerException before starting.
	static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotstrategy)
			throws NullPointerException {
		if (list == null || pivotstrategy == null)
			throw new NullPointerException("Input cannot be null");
		else if (pivotstrategy.equals(PivotStrategy.FIRST_ELEMENT)) {
			long startTime = System.currentTimeMillis();
			firstElementQS(list, 0, list.size() - 1);
			long endTime = System.currentTimeMillis();
			Duration totalTime = Duration.ofMillis(endTime - startTime);
			return totalTime;
		} else if (pivotstrategy == PivotStrategy.RANDOM_ELEMENT) {
			long startTime = System.currentTimeMillis();
			randomElementQS(list, 0, list.size() - 1);
			long endTime = System.currentTimeMillis();
			Duration totalTime = Duration.ofMillis(endTime - startTime);
			return totalTime;
		} else if (pivotstrategy == PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS) {
			long startTime = System.currentTimeMillis();
			medianThreeRandomQS(list, 0, list.size() - 1);
			long endTime = System.currentTimeMillis();
			Duration totalTime = Duration.ofMillis(endTime - startTime);
			return totalTime;
		} else if (pivotstrategy == PivotStrategy.MEDIAN_OF_THREE_ELEMENTS) {
			long startTime = System.currentTimeMillis();
			medianThreeQS(list, 0, list.size() - 1);
			long endTime = System.currentTimeMillis();
			Duration totalTime = Duration.ofMillis(endTime - startTime);
			return totalTime;
		}
		return null;
	}

	//QuickSort with first element as the pivot
	private static <E extends Comparable<E>> void firstElementQS(ArrayList<E> list, int low, int high) {
		if (high == -1)									//List is empty
			;
		if (low < high) {
			int pi = partitionFE(list, low, high);		//Calls private helper to perform QuickSort
			//Recursion
			firstElementQS(list, low, pi - 1);
			firstElementQS(list, pi + 1, high);
		}
	}

	//Private helper method to sort. Finds pivot first, then sorts around it
	private static <E extends Comparable<E>> int partitionFE(ArrayList<E> list, int low, int high) {
		//Pivot is the first index in range
		E pivot = list.get(low);
		int i = low - 1;
		//Loops through the entire range (low,high) given
		for (int j = low; j <= high; j++) {
			//uses compareTo to find if list[j] < pivot
			if (pivot.compareTo(list.get(j)) == 1) {
				i++;
				//swap if this is the case
				E temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		//at this point, list[i] and everything before should be smaller than the pivot
		int pivotIndex = list.indexOf(pivot); 
		//swap pivotIndex location with location of i+1, so that everything less than the pivot is to
		//the left and everything greater than the pivot is to the right
		if (i == high)
			return i + 1;
		else {
			E temp = list.get(i + 1);
			list.set(i + 1, list.get(pivotIndex));
			list.set(pivotIndex, temp);
		}
		return i + 1;
	}

	//QuickSort with random element as the pivot
	private static <E extends Comparable<E>> void randomElementQS(ArrayList<E> list, int low, int high) {
		if (high == -1)									//List is empty
			;
		else if (low < high) {
			int pi = partitionRE(list, low, high);		//Calls private helper method to perform QuickSort
			//Recursion
			randomElementQS(list, low, pi - 1);
			randomElementQS(list, pi + 1, high);
		}
	}

	//Private helper method to sort. Finds pivot first, then sorts around it
	private static <E extends Comparable<E>> int partitionRE(ArrayList<E> list, int low, int high) {
		//Pivot is randomly selected from the given range (low,high)
		Random a = new Random();
		int b = a.nextInt(high - low); 					//low to high-1, so accounts for indexing
		b = b + low;
		E pivot = list.get(b);
		int i = low - 1;
		//Loops through the entire range (low,high) given
		for (int j = low; j <= high; j++) {
			//uses compareTo to find if list[j] < pivot
			if (pivot.compareTo(list.get(j)) == 1) { 
				i++;
				//swap if this is the case
				E temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		// at this point, list[i] and everything before should be smaller than the pivot
		int pivotIndex = list.indexOf(pivot); 
		//swap pivotIndex location with location of i+1, so that everything less than the pivot is to
		//the left and everything greater than the pivot is to the right
		if (i == high)
			return i + 1;
		else {
			E temp = list.get(i + 1);
			list.set(i + 1, list.get(pivotIndex));
			list.set(pivotIndex, temp);
		}
		return i + 1;
	}
	
	//QuickSort with the median of three random elements as the pivot
	private static <E extends Comparable<E>> void medianThreeRandomQS(ArrayList<E> list, int low, int high) {
		if (high == -1)									//List is empty
			;
		else if (low < high) {
			int pi = partitionM3R(list, low, high);		//Calls private helper method to perform QuickSort
			//Recursion
			medianThreeRandomQS(list, low, pi - 1);
			medianThreeRandomQS(list, pi + 1, high);
		}
	}

	//Private helper method to sort. Finds pivot first, then sorts around it
	private static <E extends Comparable<E>> int partitionM3R(ArrayList<E> list, int low, int high) {
		//Pivot is selected as the median of three random elements from the given range (low,high)
		Random r = new Random();
		int a = (int) list.get(r.nextInt(high - low)); 
		int b = (int) list.get(r.nextInt(high - low));
		int c = (int) list.get(r.nextInt(high - low));
		int d = medianOf3(a, b, c);
		int e = list.indexOf(d);
		e = e + low;
		E pivot = list.get(e);
		int i = low - 1;
		//Loops through the entire range (low,high) given
		for (int j = low; j <= high; j++) {
			//uses compareTo to find if list[j] < pivot
			if (pivot.compareTo(list.get(j)) == 1) {
				i++;
				//swap if this is the case
				E temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		//at this point, list[i] and everything before should be smaller than the pivot
		int pivotIndex = list.indexOf(pivot); 
		//swap pivotIndex location with location of i+1, so that everything less than the pivot is to
		//the left and everything greater than the pivot is to the right
		if (i == high)
			return i + 1;
		else {
			E temp = list.get(i + 1);
			list.set(i + 1, list.get(pivotIndex));
			list.set(pivotIndex, temp);
		}
		return i + 1;
	}

	//Short private helper method to find the median given three integers
	private static int medianOf3(int a, int b, int c) {
		int[] array = new int[3];
		array[0] = a;
		array[1] = b;
		array[2] = c;
		Arrays.sort(array);
		return array[1];
	}

	//QuickSort with the median of three elements as the pivot
	private static <E extends Comparable<E>> void medianThreeQS(ArrayList<E> list, int low, int high) {
		if (high == -1)								//List is empty
			;
		else if (low < high) {
			int pi = partitionM3(list, low, high);	//Calls private helper method to perform QuickSort
			//Recursion
			medianThreeQS(list, low, pi - 1);
			medianThreeQS(list, pi + 1, high);
		}
	}
	//Private helper method to sort. Finds pivot first, then sorts around it
	private static <E extends Comparable<E>> int partitionM3(ArrayList<E> list, int low, int high) {
		//Pivot is selected as the median of three integers indexed at low, (low+high)/2, and high
		int a = (int) list.get(low);
		int mid = (int) (low + high) / 2;
		int b = (int) list.get(mid);
		int c = (int) list.get(high);
		int d = medianOf3(a, b, c);
		int e = list.indexOf(d);
		E pivot = list.get(e);
		int i = low - 1;
		//Loops through the entire range (low,high) given
		for (int j = low; j <= high; j++) {
			//uses compareTo to find if list[j] < pivot
			if (pivot.compareTo(list.get(j)) == 1) {
				i++;
				//swap if that is the case
				E temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		//at this point, list[i] and everything before should be smaller than the pivot
		int pivotIndex = list.indexOf(pivot);
		//swap pivotIndex location with location of i+1, so that everything less than the pivot is to
		//the left and everything greater than the pivot is to the right
		if (i == high)
			return i + 1;
		else {
			E temp = list.get(i + 1);
			list.set(i + 1, list.get(pivotIndex));
			list.set(pivotIndex, temp);
		}
		return i + 1;
	}

	//Generates a random list of int size. Throws IllegalArgumentException if the size input is negative,
	//returns empty ArrayList<Integer> if size == 0
	static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException {
		if (size < 0)
			throw new IllegalArgumentException("Size cannot be negative");
		else if (size == 0)
			return new ArrayList<Integer>(0);
		else {
			ArrayList<Integer> output = new ArrayList<Integer>(size);
			//Loops to fill ArrayList<Integer> with random integers
			for (int a = 0; a < size; a++) {
				Random b = new Random();
				int c = b.nextInt(Integer.MAX_VALUE);
				output.add(a, c);
			}
			return output;
		}
	}

	//Generates a sorted list of int size. Throws IllegalArgumentException if the size input is negative,
	//returns empty ArrayList<Integer> if size == 0
	static ArrayList<Integer> generateSortedList(int size) throws IllegalArgumentException {
		if (size < 0)
			throw new IllegalArgumentException("Size cannot be negative");
		else if (size == 0)
			return new ArrayList<Integer>(0);
		else {
			//Creates an array of int[] and fills with random integers.
			int[] ray = new int[size];
			for (int a = 0; a < size; a++) {
				Random b = new Random();
				int c = b.nextInt(Integer.MAX_VALUE);
				ray[a] = c;
			}
			//Sorts the int[] using Arrays.sort()
			Arrays.sort(ray);
			//Converts to ArrayList<Integer>
			ArrayList<Integer> output = new ArrayList<Integer>(size);
			for (int i = 0; i < size; i++) {
				output.add(ray[i]);
			}
			return output;
		}
	}

	//Generates a sorted list with 10% of the data swapped of int size. Throws IllegalArgumentException 
	//if the size input is negative, returns empty ArrayList<Integer> if size == 0
	static ArrayList<Integer> generateSorted10P(int size) throws IllegalArgumentException {
		//Creates a sorted ArrayList<Integer> using generateSortedList()
		ArrayList<Integer> sorted = QuickSorter.generateSortedList(size);
		int tenPercent = size / 10;
		//Changes 10% of the data by swapping
		for (int i = 0; i < tenPercent; i++) {
			Random a = new Random();
			int b = a.nextInt(size);
			int c = a.nextInt(size);
			int temp = sorted.get(b);
			sorted.set(b, sorted.get(c));
			sorted.set(c, temp);
		}
		return sorted;
	}

	//enum definitions for sorting strategies used.
	enum PivotStrategy {
		FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
	}
}
