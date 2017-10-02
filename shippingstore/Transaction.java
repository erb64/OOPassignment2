package shippingstore;

import java.util.Date;
import java.io.Serializable;

/**
 * This class is a very simple representation of a transaction. There are only getter
 * methods and no setter methods and as a result a transaction cannot be mutated once
 * initialized. 
 *
 * @author Emily Beaudoin
 */
public class Transaction implements Serializable
{
	private final Integer customerID;
	private final String trackingNumber;
	private final Date shippingDate;
	private final Date deliverDate;
	private final Float cost;
	private final Integer employeeID;

	 /**
     * This constructor initializes the transaction object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * user object.
     *
     * @param customerID an <b><CODE>Integer</CODE></b> that represents the ID # of the customer
     * who ordered the transaction
     *
     * @param trackingNumber a <b><CODE>String</CODE></b> that represents the tracking number of
     * the package in the transaction.
     *
     * @param shippingDate a <b><CODE>Date</CODE></b> that represents the day the package was sent
     *
     * @param deliverDate a <b><CODE>Date</CODE></b> that represents the day the package was received
     *
     * @param lastName a <b><CODE>String</CODE></b> that represents the last name of the 
     * user
     *
     * @param cost a <b><CODE>Float</CODE></b> that represents the cost of the transaction
     *
     * @param employeeID a <b><CODE>Integer</CODE></b> that represents the ID of the employee 
     * who completed the transaction
     *
     */
    public Transaction (Integer customerID, String trackingNumber, Date shippingDate, Date deliverDate,
    	Float cost, Integer employeeID) 
    {
        this.customerID = customerID;
        this.trackingNumber = trackingNumber;
        this.shippingDate = shippingDate;
        this.deliverDate = deliverDate;
        this.cost = cost;
        this.employeeID = employeeID;
    }

    /**
     * This method returns ID # of the <CODE>Customer</CODE> who made the transaction
     *
     * @return an <b><CODE>Integer</CODE></b> that is the ID # of the customer
     */
    public Integer getCustomerId ()
    {
    	return customerID;
    }

    /**
     * This method returns tracking number of the <CODE>PackageOrder</CODE> in the transaction
     *
     * @return a <b><CODE>String</CODE></b> that is the tracking number
     */
    public String getTrackingNumber ()
    {
    	return trackingNumber;
    }

    /**
     * This method returns the <CODE>Date</CODE> the package in the transaction was shipped.
     * 
     * @return a <b><CODE>Date</CODE></b> that is the shipping date.
     */
    public Date getShippingDate ()
    {
    	return shippingDate;
    }

    /**
     * This method returns the <CODE>Date</CODE> the package in the transaction was delivered.
     * 
     * @return a <b><CODE>Date</CODE></b> that is the deliver date.
     */
    public Date getDeliverDate ()
    {
    	return deliverDate;
    }
    /**
     * This method returns the cost of the transaction.
     *
     * @return a <b><CODE>Float</CODE></b> that is the transaction cost.
     */
    public Float getCost () 
    {
    	return cost;
    }
    /**
     * This method returns the ID # of the <CODE>Employee</CODE> who completed the transaction
     * @return an <b><CODE>Integer</CODE></b> that is the ID number of the user.
     */
    public Integer getEmployeeId ()
    {
    	return employeeID;
    }

}
