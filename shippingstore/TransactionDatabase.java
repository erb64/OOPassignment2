package shippingstore;

import shippingstore.PackageOrder;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;
import java.lang.ClassNotFoundException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * This class is used to represent a database interface for a list of
 * <CODE>Transaction</CODE>'s. It using a plain-text file "TransactionDB.ser"
 * to store and write transaction objects in serialized form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>transactionList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>transactionList</CODE>
 * is written to "TransactionDB.ser" at the end of the <CODE>transactionList</CODE> object's
 * life by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching for package orders from the list.
 *
 * @author Emily Beaudoin
 */
public class TransactionDatabase
{
	private ArrayList<Transaction> transactionList;

	/**
     * Private method used as an auxiliary method to display a given ArrayList
     * of package orders in a formatted manner.
     *
     * @param orders the package order list to be displayed.
     */
    private void showTransactions(ArrayList<Transaction> tlist) 
    {

        System.out.println(" ------------------------------------------------------------------------------ ");
        System.out.println("| Customer ID | Tracking # | Ship Date | Deliver Date |   Cost   | Employee ID |");
        System.out.println(" ------------------------------------------------------------------------------ ");

        for (Transaction t : tlist){
            System.out.printf("| %12s| %11s| %3$tm/%3$te/%3$tY | %4$tm/%4$te/%4$tY | %5$8.2f | %6$11d |\n", 
                              t.getCustomerId(), t.getTrackingNumber(), t.getShippingDate(), 
                              t.getDeliverDate(), t.getCost(), t.getEmployeeId());
        }

        System.out.println(" ------------------------------------------------------------------------------\n");
    }

    /**
     * This constructor is hard-coded to open "<CODE>TransactionDB.ser</CODE>" and
     * initialize the <CODE>transactionList</CODE> with its contents. If no such file
     * exists, then one is created. The contents of the file are "loaded" into
     * the transactionList ArrayList in no particular order. The file is then closed
     * during the duration of the program until <CODE>flush()</CODE> is called.
     *
     * @throws IOException if it cannot create a file in the current directory
     */
    public TransactionDatabase() throws IOException 
    {
        transactionList = new ArrayList<>();

        try{
            FileInputStream fis = new FileInputStream("TransactionDB.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            transactionList = (ArrayList<Transaction>) ois.readObject();

            fis.close(); 
        }
        catch(FileNotFoundException fnfe)
        {
            // If data file does not exist, create it.
            System.out.println("TransactionDB.ser does not exist, creating one now . . .");
            //if the file doesn't exists, create it
            FileOutputStream fos = new FileOutputStream("TransactionDB.ser");
            //close newly created file so we can reopen it
            fos.close();
        } catch (ClassNotFoundException e) {
        	System.out.print(e);
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.print(e);
            e.printStackTrace();
        } 
    }

    /**
     * Method showTransactions displays the current list of all completed transactions
     * in no particular order
     *
     */
    public void showTransactions() 
    {
        showTransactions(transactionList);
    }

    /**
     * This method is used to add a transaction to the transaction ArrayList. In order 
     * for a transactino to be completed and added to the ArrayList it must comply with 
     * the following:
     * <p>
     * 1. Must have users of type customer and employee: an customer to request, and an
     *    employee to complete the transaction
     * <p>
     * 2. Valid ship and deliver dates formatted "MM/DD/YY"
     * <p>
     * 3. A non-negative cost.
     * 
     * @param customer the <CODE>User</CODE> object who requested the transaction
     * 
     * @param employee the <CODE>User</CODE> object who completed the transaction
     * 
     * @param p the <CODE>PackageOrder</CODE> object shipped for this transaction
     *
     * @param ship the <CODE>String</CODE> which represents the shipping date 
     *
	 * @param deliver the <CODE>String</CODE> which represents the delivery date 
     *
     * @param cost the <CODE>String</CODE> which represents the shipping cost
     */
    public void addTransaction(User customer, User employee, PackageOrder p, 
    	String ship, String deliver, String cost) 
    {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy"); 
        Date shipping, delivering;

    	if (!(employee instanceof Employee && customer instanceof Customer)){
    		System.out.println("\nError: Employee and customer specific users are required\n");
   			return;
    	}
        try{
            shipping = dateFormatter.parse(ship);
            delivering = dateFormatter.parse(deliver);
        } catch (ParseException pe) {
            System.out.println("\nError: Date not in correct format. Correct format is \"MM/DD/YY\"\n");
                return;
        }


        if (Float.parseFloat(cost) < 0) {
            System.out.println("Invalid cost:\n"
                + "The cost of the transaction cannot be negative");
                return;
        }

        // If it passed all the checks, add the order to the list
    	transactionList.add(new Transaction(customer.getIdNumber(), p.getTrackingNumber(), 
    		shipping, delivering, Float.parseFloat(cost), 
    		employee.getIdNumber()));
        // If an order was added, sort the list and display message
        System.out.println("Transaction complete.\n");
    }

    /**
     * This method opens <CODE>"TransactionDB.ser"</CODE> and overwrites it with a serialization of
     * all the package orders in the <CODE>transactionList</CODE>.
     * This should be the last method to be called before exiting the program.
     * @throws IOException if it cannot create a file in the current directory
     */
    public void flush() throws IOException 
    {
        FileOutputStream fos = new FileOutputStream("TransactionDB.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(transactionList);

        fos.close();
    }
}
