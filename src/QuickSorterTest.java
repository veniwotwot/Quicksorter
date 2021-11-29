import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class QuickSorterTest {
	ArrayList<Integer> testList, testList2;
	ArrayList<Integer> testListAns, testListAns2;
	ArrayList<Integer> list;

	@Before
	public void setUp() throws Exception {
		testList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 99, 4, 5, 885, 6, 45, 16, 31, 77, 14));
		testListAns = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 14, 16, 31, 45, 77, 99, 885));
		
		testList2 = new ArrayList<Integer>(Arrays.asList(1051128265, 1463997224, 304720629, 494371959, 905940027,
				1090924968, 1704307363, 436307558, 470174146, 1702164219, 849801923, 778567511, 289039541, 2062454415,
				677758658, 2007140352, 398951633, 759512323, 637660850, 930543345, 1393077336, 1271922093, 1701086946,
				1069699805, 1600259870, 496348103, 993418931, 1483160025, 1428718661, 943271352, 1682677039, 871164194,
				515457750, 1503313143, 1311785246, 976334563, 1582795550, 580077950, 530219975, 1553809807, 662971298,
				688787188, 2091464568, 862918334, 1283672976, 1300213135, 2104654558, 979959352, 1598665628, 55318507));
		testListAns2 = new ArrayList<Integer>(Arrays.asList(55318507, 289039541, 304720629, 398951633, 436307558,
				470174146, 494371959, 496348103, 515457750, 530219975, 580077950, 637660850, 662971298, 677758658,
				688787188, 759512323, 778567511, 849801923, 862918334, 871164194, 905940027, 930543345, 943271352,
				976334563, 979959352, 993418931, 1051128265, 1069699805, 1090924968, 1271922093, 1283672976, 1300213135,
				1311785246, 1393077336, 1428718661, 1463997224, 1483160025, 1503313143, 1553809807, 1582795550,
				1598665628, 1600259870, 1682677039, 1701086946, 1702164219, 1704307363, 2007140352, 2062454415,
				2091464568, 2104654558));
		
	}

	//@Test(expected = IllegalArgumentException.class)
	public void GenerateNegativeSize() {
		ArrayList<Integer> list = QuickSorter.generateRandomList(-1);
	}

	//@Test
	public void GenerateRandomListTest() {
		ArrayList<Integer> list = QuickSorter.generateRandomList(0);
		assertEquals(list.size(), 0);
		ArrayList<Integer> list2 = QuickSorter.generateRandomList(500);
		assertEquals(list2.size(), 500);
		ArrayList<Integer> list3 = QuickSorter.generateRandomList(500);
		assertEquals(list3.size(), 500);
		//ArrayList<Integer> testList = QuickSorter.generateRandomList(50);
		//System.out.println(testList);
		//System.out.println("");
	}
	
	@Test					//no assert included, just for personal use
	public void sorted10PTest() {
		ArrayList<Integer> list3 = QuickSorter.generateRandomList(5);
		System.out.println("RandomList size 5: "+list3);
		ArrayList<Integer> list4 = QuickSorter.generateSortedList(5);
		System.out.println("SortedList size 5: "+list4);
		System.out.println("");
	}

	//@Test(expected = NullPointerException.class)
	public void nullInput() {
		QuickSorter.timedQuickSort(null, QuickSorter.PivotStrategy.FIRST_ELEMENT);
	}

	//@Test
	public void firstElementTest() {
		System.out.println("First Element Test ArrayList Before: " + testList);
		Duration time = QuickSorter.timedQuickSort(testList, QuickSorter.PivotStrategy.FIRST_ELEMENT);
		System.out.println("FIRST_ELEMENT : " + time);
		System.out.println("ArrayList After: " + testList);
		System.out.println("");
		assertEquals(testList, testListAns);
	}

	//@Test
	public void firstElementTest2() {
		System.out.println("First Element Test ArrayList Before: " + testList2);
		Duration time = QuickSorter.timedQuickSort(testList2, QuickSorter.PivotStrategy.FIRST_ELEMENT);
		System.out.println("FIRST_ELEMENT : " + time);
		System.out.println("ArrayList After: " + testList2);
		System.out.println("");
		assertEquals(testList2, testListAns2);
	}

	//@Test
	public void randomElementTest() {
		System.out.println("Random Element Test ArrayList Before: " + testList);
		Duration time = QuickSorter.timedQuickSort(testList, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
		System.out.println("RANDOM_ELEMENT : " + time);
		System.out.println("ArrayList After: " + testList);
		System.out.println("");
		assertEquals(testList, testListAns);
	}

	//@Test
	public void randomElementTest2() {
		System.out.println("Random Element Test ArrayList Before: " + testList2);
		Duration time = QuickSorter.timedQuickSort(testList2, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
		System.out.println("RANDOM_ELEMENT : " + time);
		System.out.println("ArrayList After: " + testList2);
		System.out.println("");
		assertEquals(testList2, testListAns2);
	}

	//@Test
	public void medThreeRandomTest() {
		System.out.println("medThreeRandom ArrayList Before: " + testList);
		Duration time = QuickSorter.timedQuickSort(testList, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
		System.out.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + time);
		System.out.println("ArrayList After: " + testList);
		System.out.println("");
		assertEquals(testList, testListAns);
	}

	//@Test
	public void medThreeRandomTest2() {
		System.out.println("medThreeRandom ArrayList Before: " + testList2);
		Duration time = QuickSorter.timedQuickSort(testList2,
				QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
		System.out.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + time);
		System.out.println("ArrayList After: " + testList2);
		System.out.println("");
		assertEquals(testList2, testListAns2);
	}

	//@Test
	public void medThreeElementTest() {
		System.out.println("medThreeElement ArrayList Before: " + testList);
		Duration time = QuickSorter.timedQuickSort(testList, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
		System.out.println("MEDIAN_OF_THREE_ELEMENTS : " + time);
		System.out.println("ArrayList After: " + testList);
		System.out.println("");
		assertEquals(testList, testListAns);
	}

	//@Test
	public void medThreeElementTest2() {
		System.out.println("medThreeElement ArrayList Before: " + testList2);
		Duration time = QuickSorter.timedQuickSort(testList2, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
		System.out.println("MEDIAN_OF_THREE_ELEMENTS : " + time);
		System.out.println("ArrayList After: " + testList2);
		System.out.println("");
		assertEquals(testList2, testListAns2);
	}
	


}
