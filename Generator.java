//b6030326 - Callum Simpson
/**
* The Generator class generates coils of rope and customer orders 

* Do NOT modify the names and signatures of the two generator methods
* 
* @author  Rouaa Yassin-Kassab & John Colquhoun
* @since   30/10/2017
* extended by @author 
*/

import java.util.ArrayList;
import java.util.List;

public class Generator {
	// This variable represents the longest possible length of rope that a customer
	// can order
	private final int MAX_ORDER_LENGTH = 100;

	// These variables represent the longest and smallest possible coils of rope
	// that the manufacturers can supply
	private final int MIN_ROPE_LENGTH = 100;
	private final int MAX_ROPE_LENGTH = 200;

	/**
	 * This method will generate a list of orders of random lengths between the min
	 * and max order sizes (currently 1 and 100m)
	 * 
	 * @param numberOfOrders:
	 *            the number of customer orders to generate
	 * @return a list of customer orders
	 */
	public List<Integer> generateMultipleOrders(int numberOfOrders) {
		//create a array list that will hold orders
		List<Integer> orderList = new ArrayList<Integer>();

		/*
		 * Note that for a list of Integers, you can add ints without converting to
		 * Integers Example: orderList.add (1) will work, you don't need to do
		 * orderList.add (new Integer(1))
		 */

		//for every order
		for (int i = 0; i < numberOfOrders; i++) {
			
			//generate a random order
			int randomOrder = (int) (Math.random() * (MAX_ORDER_LENGTH) + 1);
			//add the new random order to the order list
			orderList.add(randomOrder);
		}
		//return the list of orders
		return orderList;
	}

	/**
	 * This method will generate a list of new coils of rope from the manufacturer
	 * of random lengths between the min and max order sizes (currently 100 and
	 * 200m)
	 * 
	 * @param numberOfCoils:
	 *            the number of ropes to generate
	 * @return a list of coils of rope supplied by the manufacturer
	 * 
	 */

	public List<Rope> orderRopeFromManufacturer(int numberOfCoils) {
		//create a array list that will hold all the generated ropes
		List<Rope> coils = new ArrayList<Rope>();
		//for every rope we want to make
		for (int i = 0; i < numberOfCoils; i++) {
			//generate a random sided rope
			int randomCoilLenght = (int) (Math.random() * (MAX_ROPE_LENGTH - MIN_ROPE_LENGTH)) + MIN_ROPE_LENGTH;
			//add the rope to the coil list
			coils.add(new Rope(randomCoilLenght));

		}
		//return the list of coils
		return coils;
	}

}
