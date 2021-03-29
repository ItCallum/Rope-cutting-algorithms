/**
* The Rope class represents a single coil of rope.
* DO NOT MODIFY THE EXISTING METHODS, You may add additional methods if you wish
* 
* @author  John Colquhoun & Rouaa Yassin-Kassab 
* @since   30/10/2017
* extended by @author 
*/
 
public class Rope {
	
	private int length; //current length of the rope
	
	 /**
	   * A Rope constructor to set the length specified by the manufacturer.
	   * @param length of the new coil of rope
	   *
	   */
	public Rope (int length)  
	{
		if(length <= 0) {
			throw new IllegalArgumentException("Length can't be <= 0");
		}
		this.length = length;
	}
	
	
	
	/**
	 * @return current length of the rope
	 */
	public int getLength ()
	{
		return length;
	}
	
	/**
	 * set a length of the rope
	 */
    public void setLength (int len)
	{
		this.length = len;
	}
	
	/**
	   * This method is used to cut the rope by a specified 
	   * amount. This amount would represent a customer order 
	   * @param amountToCut: This is the length of rope to be cut
	   * @return true if the rope was cut correctly, false if the amount to cut is bigger than the length of the rope
	   * IMPORTANT: if the method returns false, the rope is not cut
	   */
	public boolean cut (int amountToCut)
	{
		//rope can't be negative length so check the amount to cut is not longer than the rope itself
		if (amountToCut <= length) 
		{
			length = length - amountToCut;
			return true;
		}
		
		return false; 
	}

}
