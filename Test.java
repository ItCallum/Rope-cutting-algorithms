//b6030326 - Callum Simpson
/**
* The Test class is your main testing suite for the coursework

* You can add additional methods if you need to in this class 
* 
* @author  Rouaa Yassin-Kassab & John Colquhoun
* @since   30/10/2017
* extended by @author 
*/

import java.util.*; //needed for usage of the List interface

public class Test {

	// input: up to you if you populate the arguments array

	/**
	 * This method is the main method to run your algorithms
	 * 
	 */
	public static void main(String[] args) {
		/*
		 * You must complete the Generator class in order to generate a random test
		 * values You must complete the Algorithms class in order to call the two
		 * algorithms Remember: You need to calculate the time and number of coils used
		 * for each run of each algorithm You can use any additional method you created
		 * in this class
		 */

		Test p = new Test();

		System.out.println("*********** Correctness testing ************");
		System.out.println();

		p.correctnessTestForFirstFit();

		System.out.println("************** Main testing **************");
		System.out.println("*********** Performance analysis **************");
		System.out.println();
		/*
		 * Here you will need to do performance testing
		 */

		int noOfTests = 5; // total number of tests - you need to CHANGE this value
		int noOfRep = 10; // number of times to run each test - you need to CHANGE this value
		int noOfOrders = 10000; // the number of customer orders needed for the first run - you need to CHANGE
								// this value
		int increment = 10000; // the increment in the number of orders - you need to CHANGE this value

		performanceTesting(noOfTests, noOfRep, noOfOrders, increment);
		
	}

	/**
	 * performanceTesting (comparing the two algorithms in term of time and total
	 * number of coils used)
	 * 
	 * @param noOfTests
	 *            - the number of tests
	 * @param noOfRep
	 *            - the number of repetitions for each test
	 * @param noOfOrders
	 *            - the number of orders in the first order sequence
	 * @param increment
	 *            - increment of the number of orders
	 *
	 */
	public static void performanceTesting(int noOfTests, int noOfRep, int noOfOrders, int increment) {
		/*
		 * Here you will need to set up and run your Performance analysis You are
		 * expected to run several tests (e.g. noOfTests=5) of your programs for, 10000,
		 * 20000, 30000, 40000, 50000 orders (noOfOrders=10000, increment=10000) so that
		 * one can see how the algorithms perform for large datasets.
		 * 
		 * You are expected to run the same test a number of times to ensure accurate
		 * result (noOfRep=4). In this case, you need to calculate the average time and
		 * coils needed for each run
		 */

		Generator gen = new Generator();

		Algorithms alg = new Algorithms();

		System.out.format("\n%25s | %25s | %25s | %25s | %25s |", "Number of orders", "Average FirstFit",
				"Average FirstFit Time", "Average NextFit", "Average NextFit Time");

		for (int i = 1; i <= noOfTests; i++) {

			//for every test set these values to be zero
			int averageFirstFit = 0;

			int averageNextFit = 0;

			long averageTimeNextFit = 0;

			long averageTimeFirstFit = 0;
			
			//for every rep
			for (int j = 1; j <= noOfRep; j++) {
				
				//create a random list of ropes and orders of size noOfOrders
				List<Integer> testlist = gen.generateMultipleOrders(noOfOrders);

				List<Rope> testRope = gen.orderRopeFromManufacturer(noOfOrders);

				//increase next fit average
				averageNextFit = averageNextFit
						+ alg.nextFit(dulpicateOrdersList(testlist), dulpicateRopeList(testRope));
				//increase next fit time average
				averageTimeNextFit = averageTimeNextFit + TimeNextFit(alg, testRope, testlist);
				//increase Frist fit average
				averageFirstFit = averageFirstFit
						+ alg.firstFit(dulpicateOrdersList(testlist), dulpicateRopeList(testRope));
				//increase First fit time average
				averageTimeFirstFit = averageTimeFirstFit + TimeFirstFit(alg, testRope, testlist);

			}

			//print out all the average
			System.out.format("\n%25d | %25d | %25d | %25d | %25d |", noOfOrders, averageFirstFit / noOfRep,
					averageTimeFirstFit / noOfRep, averageNextFit / noOfRep, averageTimeNextFit / noOfRep);
			
			//increase the order size by increment
			noOfOrders = noOfOrders + increment;
		}

		System.out.println("\n");
	}

	//print off a list of orders and a list of ropes
	public void report(List<Rope> testlistRope, List<Integer> testlistInt) {

		System.out.println("\n--- Here is the Orders ---");
		for (int j = 0; j < testlistInt.size(); j++) {
			System.out.print("||" + testlistInt.get(j));
		}
		System.out.print("||\n");

		System.out.println("\n--- Here are the Ropes ---");
		for (int i = 0; i < testlistRope.size(); i++) {
			System.out.print("||" + testlistRope.get(i).getLength());
		}
		System.out.print("||\n");
	}

	//a test for correctness
	public void correctnessTestForFirstFit() {
		
		
		Algorithms alg = new Algorithms();

		
		//create a predermined list of ropes
		List<Rope> testlistRope = new ArrayList<Rope>();

		testlistRope.add(new Rope(120));
		testlistRope.add(new Rope(110));
		testlistRope.add(new Rope(140));
		testlistRope.add(new Rope(150));
		testlistRope.add(new Rope(130));
		testlistRope.add(new Rope(160));
		
		//create a predermined list of orders
		List<Integer> testlistInt = new ArrayList<Integer>();

		testlistInt.add(99);
		testlistInt.add(98);
		testlistInt.add(20);
		testlistInt.add(60);
		testlistInt.add(56);
		testlistInt.add(4);

		System.out.println("\nTo test correctness I will first make a non-random list of orders and a ropes \n");
		System.out.println("I am doing so so I can visually compare the results to the results I have manually worked out\n");

		System.out.println("\nEach should be six long so I will first test that the generators are creating the amount \n");
		
		System.out.println(6 == testlistRope.size()
				? "The correct number of ropes has been generated"
				: "The wrong number of ropes has been generated");
		
		
		System.out.println(6 == testlistInt.size()
				? "The correct number of orders has been generated"
				: "The wrong number of orders has been generated");
		
		
		report(testlistRope, testlistInt);

		System.out.println("\n ------ Correctness test for fist fit------\n");
		
		System.out.println("I am expecting to get a result of 3 ropes used");
		System.out.println("Orders one and three will use rope 1");
		System.out.println("Orders two and four will use rope 2");

		System.out.println("\nResult retrieved from  firstfit ;  "
				+ alg.firstFit(dulpicateOrdersList(testlistInt), dulpicateRopeList(testlistRope)));

		System.out.println(3 == alg.firstFit(dulpicateOrdersList(testlistInt), dulpicateRopeList(testlistRope))
				? "The algorthim for first fit got 3 which is the expected value"
				: "The algorthim for first fit didn't work as expected");

		System.out.println("\n  ------ Correctness test for next fit------ \n");

		System.out.println("I am expecting to get a result of 4 ropes used\n");
		System.out.println("Orders one will use rope 1");
		System.out.println("Orders two will use rope 2");
		System.out.println("Orders three , four and five will use rope 3\n");
		System.out.println("Orders 6 will use rope 4\n");

		System.out.println("\nResult retrieved from nextfit :  "
				+ alg.nextFit(dulpicateOrdersList(testlistInt), dulpicateRopeList(testlistRope)));

		System.out.println(4 == alg.nextFit(dulpicateOrdersList(testlistInt), dulpicateRopeList(testlistRope))
				? "The algorthim for next fit got 4 which is the expected value"
				: "The algorthim for next fit didn't work as expected ");

		System.out.println("\n");
		
		
		//Test Best case 
		List<Rope> testBestcaselistRope = new ArrayList<Rope>();

		testBestcaselistRope.add(new Rope(200));
		testBestcaselistRope.add(new Rope(110));
		testBestcaselistRope.add(new Rope(140));
		testBestcaselistRope.add(new Rope(150));
		testBestcaselistRope.add(new Rope(130));
		testBestcaselistRope.add(new Rope(160));

		List<Integer> testBestCaselistInt = new ArrayList<Integer>();

		testBestCaselistInt.add(20);
		testBestCaselistInt.add(20);
		testBestCaselistInt.add(20);
		testBestCaselistInt.add(20);
		testBestCaselistInt.add(20);
		testBestCaselistInt.add(20);
		
		System.out.println("\nTo test correctness I will also need to test best fit\n");
		
		report(testBestcaselistRope, testBestCaselistInt);
		
		System.out.println("\nIf i have done this correctly then both algothim should return 1 rope used\n");
		
		System.out.println(1 == alg.firstFit(dulpicateOrdersList(testBestCaselistInt), dulpicateRopeList(testBestcaselistRope))
				? "The algorthim for first fit got 1 which is the expected value"
				: "The algorthim for first fit didn't work as expected");
		
		System.out.println(1 == alg.nextFit(dulpicateOrdersList(testBestCaselistInt), dulpicateRopeList(testBestcaselistRope))
				? "The algorthim for next fit got 1 which is the expected value"
				: "The algorthim for next fit didn't work as expected");
		
		
		
		//test worst case
		List<Rope> testworstcaselistRope = new ArrayList<Rope>();

		testworstcaselistRope.add(new Rope(101));
		testworstcaselistRope.add(new Rope(101));
		testworstcaselistRope.add(new Rope(101));
		testworstcaselistRope.add(new Rope(101));
		testworstcaselistRope.add(new Rope(101));
		testworstcaselistRope.add(new Rope(101));

		List<Integer> testworstCaselistInt = new ArrayList<Integer>();

		testworstCaselistInt.add(70);
		testworstCaselistInt.add(70);
		testworstCaselistInt.add(70);
		testworstCaselistInt.add(70);
		testworstCaselistInt.add(70);
		testworstCaselistInt.add(70);
		
		System.out.println("\nTo test correctness I will also need to test worst case\n");
		
		report(testworstcaselistRope, testworstCaselistInt);
		
		System.out.println("\nIf i have done this correctly then both algothim should return 6 rope used\n");
		
		System.out.println(6 == alg.firstFit(dulpicateOrdersList(testworstCaselistInt), dulpicateRopeList(testworstcaselistRope))
				? "The algorthim for first fit got 6 which is the expected value"
				: "The algorthim for first fit didn't work as expected");
		
		System.out.println(6 == alg.nextFit(dulpicateOrdersList(testworstCaselistInt), dulpicateRopeList(testworstcaselistRope))
				? "The algorthim for next fit got 6 which is the expected value"
				: "The algorthim for next fit didn't work as expected");
		
		
		
		
		
	}
	//duplicates a list of orders
	public static List<Integer> dulpicateOrdersList(List<Integer> testlistInt) {
		//creates a new order list
		List<Integer> dupOrderList = new ArrayList<Integer>();
		//for each order we want to copy
		for (int i = 0; i < testlistInt.size(); i++) {
			//add the current order to the new order list
			dupOrderList.add(new Integer(testlistInt.get(i)));

		}
		//return the duplicated list
		return dupOrderList;

	}

	//duplicates a list of ropes
	public static List<Rope> dulpicateRopeList(List<Rope> testlistRope) {
		
		//creates a new list of ropes
		List<Rope> dupOrderList = new ArrayList<Rope>();

		//for each rope we want to copy
		for (int i = 0; i < testlistRope.size(); i++) {
			//add the current rope to the new rope list
			dupOrderList.add(new Rope(testlistRope.get(i).getLength()));
		}
		//returns the copied list of ropes
		return dupOrderList;
	}

	//work out how long it takes for first fit to run
	public static long TimeFirstFit(Algorithms alg, List<Rope> testlistRope, List<Integer> testlistInt) {

		// duplicate a list of ropes
		List<Rope> dupTestlistRope = dulpicateRopeList(testlistRope);
		// duplicate a list of orders
		List<Integer> dupTestlistInt = dulpicateOrdersList(testlistInt);

		//start the timer
		long startTime = System.nanoTime();
		//run the first fit algorthim
		alg.firstFit(dupTestlistInt, dupTestlistRope);
		//return how long it took to run the algorthim
		return System.nanoTime() - startTime;

	}

	//work out how long it takes for next fit to run
	public static long TimeNextFit(Algorithms alg, List<Rope> testlistRope, List<Integer> testlistInt) {
		// duplicate the list of ropes
		List<Rope> dupTestlistRope = dulpicateRopeList(testlistRope);
		
		// duplicate the list of orders
		List<Integer> dupTestlistInt = dulpicateOrdersList(testlistInt);
	
		//start the timer
		long startTime = System.nanoTime();
		//run next fit
		alg.nextFit(dupTestlistInt, dupTestlistRope);
		//return how long it took to run next fit
		return System.nanoTime() - startTime;

	}
}
