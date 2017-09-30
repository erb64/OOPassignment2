package shippingstore;

/**
 * This class is a specialized representation of a user, specific to employees. 
 * There are only getter methods and no setter methods and as a result an employee 
 * cannot be mutated once initialized. A employee bject can also call the two 
 * override methods via inheritance from User
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @extends User
 * @author Emily Beaudoin
 */
public class Employee extends User
{
	private final int social;
	private final Float salary;
	private final int account;

    /**
     * This constructor initializes the employee object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * employee object.
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
     * @param social an <b><CODE>int</CODE></b> that represents the employee's social 
     * security program
     *
     * @param salary a <b><CODE>Float</CODE></b> that represents the employee's monthly
     * salary
     * 
     * @param account an <b><CODE>int</CODE></b> that represents the direct deposit bank
     * account number for the employee
     *
     */
	public Employee (int idNumber, String firstName, String lastName, int social, Float salary, int account){
		super(idNumber,firstName,lastName);
		this.social = social;
		this.salary = salary;
		this.account = account;
	}

	/**
     * This method returns the employee's social security number
     *
     * @return an <b><CODE>int</CODE></b> that is the employee's social security number
     */
	public int getSocial(){
		return social;
	}

	/**
     * This method returns the employee's monthly salary
     *
     * @return a <b><CODE>Float</CODE></b> that is the employee's monthly salary in USD
     */
	public float getSalary(){
		return salary;
	}

	/**
     * This method returns the employee's direct deposit bank account number
     *
     * @return an <b><CODE>int</CODE></b> that is the employee's bank account number
     */
	public int getAccount(){
		return account;
	}
}