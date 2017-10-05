package shippingstore;

/**
 * This class is a specialized representation of a user, specific to customers. 
 * A customer bject can also call the two override methods <CODE>toString()</CODE> 
 * and <CODE>equals()</CODE>
 *
 * @author Emily Beaudoin
 */
public class Customer extends User
{
	private String phone;
	private String address;

    /**
     * This constructor initializes the customer object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * customer object.
     *
     * @param idNumber an <b><CODE>Integer</CODE></b> that represents the user's ID number.
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
	public Customer (Integer idNumber, String firstName, String lastName, String phone, String address)
     {
		super(idNumber,firstName,lastName);
		this.phone = phone;
		this.address = address;
	}

     /**
     * This method changes the customer's phone number.
     *
     *@param phone a <b><CODE>String</CODE></b> that is the customer's phone number
     * account number
     */ 
     public void setPhone (String phone) 
     {
          this.phone = phone;
     }

	/**
     * This method returns the customer's phone number
     *
     * @return a <b><CODE>String</CODE></b> that is the customer's phone number
     */
	public String getPhone()
     {
		return phone;
	}

     /**
     * This method changes the customer's address
     *
     *@param address a <b><CODE>String</CODE></b> that is the customer's address
     * account number
     */ 
     public void setAddress (String address) 
     {
          this.address = address;
     }

	/**
     * This method returns the customer's address
     *
     * @return a <b><CODE>String</CODE></b> that is the customer's address
     */
	public String getAddress()
     {
		return address;
	}

}