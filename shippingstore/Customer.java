package shippingstore;

/**
 * This class is a specialized representation of a user, specific to customers. 
 * There are only getter methods and no setter methods and as a result a customer 
 * cannot be mutated once initialized. A customer bject can also call the two 
 * override methods via inheritance from User
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @extends User
 * @author Emily Beaudoin
 */
public class Customer extends User
{
	private final String phone;
	private final String address;

    /**
     * This constructor initializes the customer object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * customer object.
     *
     * @param idNumber an <b><CODE>int</CODE></b> that represents the user's ID number.
     * Each user's ID is unique
     *
     * @param firstName a <b><CODE>String</CODE></b> that represents the first name of 
     * the user
     *
     * @param lastName a <b><CODE>String</CODE></b> that represents the last name of the 
     * user
     *
     * @param phone a <b><CODE>String</CODE></b> that represents the phone number of the
     * customer
     *     
     * @param address a <b><CODE>String</CODE></b> that represents the address of the 
     * customer
     *
     */
	public Customer (int idNumber, String firstName, String lastName, String phone, String address){
		super(idNumber,firstName,lastName);
		this.phone = phone;
		this.address = address;
	}

	/**
     * This method returns the customer's phone number
     *
     * @return a <b><CODE>String</CODE></b> that is the customer's phone number
     */
	public String getPhone(){
		return phone;
	}

	/**
     * This method returns the customer's address
     *
     * @return a <b><CODE>String</CODE></b> that is the customer's address
     */
	public String getAddress(){
		return address;
	}

}