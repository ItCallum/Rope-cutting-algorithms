//b6030326 - Callum Simpson


/**
* The Algorithms class contains the two algorithms you have to implement for coursework 2

* Do NOT modify the names and signatures of the two algorithm methods
* 
* @author  Rouaa Yassin-Kassab & John Colquhoun
* @since   30/10/2017
* extended by @author 
*/

import java.util.*;

public class Algorithms {

	/**
	 * This method is used to implement the first fit algorithm
	 * 
	 * @param orders:
	 *            a list of integers representing customer orders
	 * @param coils
	 *            : a list of ropes representing available coils of ropes from the
	 *            manufacturer
	 * @return the number of coils used to fulfil all customer orders
	 */
	public int firstFit(List<Integer> orders, List<Rope> coils) {

		//Create a field to store how many ropes have been used
		int currentRopesUsed = 0;

		//Create a field to store how many ropes have been removed
		int ropesRemoved = 0;

		//for each order
		for (int i = 0; i < orders.size(); i++) {

			//get a rope
			for (int j = 0; j < coils.size(); j++) {

				//check to see if the ropes size is larger than the order
				if (coils.get(j).getLength() >= orders.get(i)) {
					//cut the rope
					coils.get(j).cut(orders.get(i));
					
					
					//System.out.println(j + "|" + coils.get(j).getLength());
					//If its a new rope add one to the counter
					if (currentRopesUsed <= j) {
						currentRopesUsed = currentRopesUsed + 1;
					}
					
					//if the rope is equal or less than 5
					if (coils.get(j).getLength() <= 5) {
						//remove the current rope
						coils.remove(j);
						
						//move one of the used rope into the ropesRemoved "bin"
						
						//increase the removed rope counter by 1 
						ropesRemoved = ropesRemoved + 1;
						
						//currentRopesUsed will be decrease by one
						currentRopesUsed = currentRopesUsed - 1;
					}
					//as the customers order has been forfilled we don't need to check anymore ropes 
					break;
				}

			}

		}
		//returns the total number of ropes used. 
		return currentRopesUsed + ropesRemoved;

	}

	/**
	 * This method is used to implement the next fit algorithm
	 * 
	 * @param orders:
	 *            a list of integers representing customer orders
	 * @param coils
	 *            : a list of ropes representing available coils of ropes from the
	 *            manufacturer
	 * @return the number of coils used to fulfil all customer orders
	 */
	public int nextFit(List<Integer> orders, List<Rope> coils) {

		//how many ropes have been used so far
		int currentRopesUsed = 0;
		//what is the current rope
		int slectedRope = 0;
		//What order is currently being forfilled
		int completeOrderCounter = 0;
		//how many ropes have been binned
		int removedRopes = 0;

		//for each order
		for (int i = 0; i < orders.size(); i++) {
			
			//while all the orders haven't been forfillled
			while (orders.size() > completeOrderCounter) {
				
				//If its the first rope being used (used to ensure that the correct numbers of ropes is returned
				if (currentRopesUsed == 0) {
					//increase the current ropes used by one
					currentRopesUsed = currentRopesUsed + 1;

				}
				//check to see if the ropes size is larger than the order
				if (coils.get(slectedRope).getLength() >= orders.get(i)) {
					//cut the rope
					coils.get(slectedRope).cut(orders.get(i));

					//if the rope is now less than 5 "bin" the rope
					if (coils.get(slectedRope).getLength() <= 5) {
		
						coils.remove(slectedRope);

						removedRopes = removedRopes + 1;

					}
					//move onto the next order
					completeOrderCounter = completeOrderCounter + 1;
					//order is forfilled
					break;
				
				//else get the next rope
				} else {
					
					slectedRope = slectedRope + 1;
					currentRopesUsed = slectedRope + 1;
				}

			}

		}
		//return all the ropes used
		
		return currentRopesUsed + removedRopes;
	}
}
