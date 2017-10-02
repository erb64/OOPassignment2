package shippingstore;

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


/**
 * This class is used to represent a database interface for a list of
 * <CODE>Package Order</CODE>'s. It using a plain-text file "PackageDB.ser"
 * to store and write package order objects in serialized form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>packageOrderList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>packageOrderList</CODE>
 * is written to "PackageDB.ser" at the end of the <CODE>PackageDatabase</CODE> object's
 * life by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching for package orders from the list.
 *
 * @author Junye Wen, edited by Emily Beaudoin to fit this application
 */
public class PackageDatabase 
{
    private ArrayList<PackageOrder> packageOrderList;

     /**
     * Private method used as an auxiliary method to display a given ArrayList
     * of package orders in a formatted manner.
     *
     * @param orders the package order list to be displayed.
     */
    private void showPackageOrders(ArrayList<PackageOrder> orders) {

        System.out.println(" ------------------------------------------------------------------------------ ");
        System.out.println("|Tracking#|   Type   |Specification|   Class     |                             |");
        System.out.println(" ------------------------------------------------------------------------------ ");


        for (PackageOrder p : orders){
            System.out.printf("| %-8s| %-9s| %-12s| %-12s|", 
                              p.getTrackingNumber(), p.getType(), 
                              p.getSpecification(), p.getMailingClass());

            if(p instanceof Envelope){
                System.out.printf(" Height: %17din |  \n|%48s| Width: %18din |\n", ((Envelope)p).getHeight(), 
                    " ", ((Envelope)p).getWidth());
            } else if (p instanceof Box){
                System.out.printf(" Largest Dimension: %6din |\n|%48s| Volume: %15din^3 |\n", 
                    ((Box)p).getLargestDimension(), " ", ((Box)p).getVolume());
            } else if (p instanceof Crate){
                System.out.printf(" Max Load Weight: %8.2flb |\n|%48s| Contents: %17s |\n", ((Crate)p).getLoadWeight(),
                " ", ((Crate)p).getContent());
            } else { //(p instanceof Drum)
                System.out.printf(" Material: %17s |\n|%48s| Diameter: %15din |\n", ((Drum)p).getMaterial(),
                " ", ((Drum)p).getDiameter());
            }   
        }

        System.out.println(" ------------------------------------------------------------------------------\n");
    }

    /**
     * This constructor is hard-coded to open "<CODE>PackageDB.ser</CODE>" and
     * initialize the <CODE>packageOrderList</CODE> with its contents. If no such file
     * exists, then one is created. The contents of the file are "loaded" into
     * the packageOrderList ArrayList in no particular order. The file is then closed
     * during the duration of the program until <CODE>flush()</CODE> is called.
     * @throws IOException
     */
    public PackageDatabase() throws IOException {
        packageOrderList = new ArrayList<>();

        
        try{
            FileInputStream fis = new FileInputStream("PackageDB.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            packageOrderList = (ArrayList<PackageOrder>) ois.readObject();
            //Log.i("palval", "dir.exists()");

            fis.close(); 
        }
        catch(FileNotFoundException fnfe)
        {
            // If data file does not exist, create it.
            System.out.println("PackageDB.ser does not exist, creating one now . . .");
            //if the file doesn't exists, create it
            FileOutputStream fos = new FileOutputStream("PackageDB.ser");
            //close newly created file so we can reopen it
            fos.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Method showPackageOrders displays the current list of package orders in the Arraylist in no
     * particular order.
     *
     */
    public void showPackageOrders() {
        showPackageOrders(packageOrderList);
    }

    /**
     * This method can be used to find a package order in the Arraylist of orders.
     *
     * @param trackingNumber a <CODE>String</CODE> that represents the tracking number 
     * of the order that to be searched for.
     * @return the <CODE>int</CODE> index of the package orders in the Arraylist of orders,
     * or -1 if the search failed.
     */
    public int findPackageOrder(String trackingNumber) {

        int index = -1;

        for (PackageOrder p : packageOrderList) {
            String temp = p.getTrackingNumber();

            if (trackingNumber.equalsIgnoreCase(temp)) {
                index = packageOrderList.indexOf(p);
                break;
            }
        }

        return index;
    }
    
    /**
     * This method can be used to search for a package order in the Arraylist of orders.
     *
     * @param trackingNumber a <CODE>String</CODE> that represents the tracking number
     * of the order that to be searched for.
     */
    public void searchPackageOrder(String trackingNumber) {

        int index = findPackageOrder(trackingNumber);

        if (index != -1) {
            ArrayList<PackageOrder> order = new ArrayList<>(1);
            order.add(getPackageOrder(index));
            System.out.println("\nHere is the order that matched:\n");
            showPackageOrders(order);
        } else {
            System.out.println("\nSearch did not find a match.\n");
        }
    }
    

    /**
     * This method is used to add a package order to the orderList ArrayList. In order for a
     * package order to be added to the ArrayList it must comply with the following:
     * <p>
     * 1. The order is not already in the ArrayList according to the tracking number
     * as the unique key.
     * <p>
     * 2. The TrackningNumber string matches the following regular expression:
     * <CODE>"[A-Za-z0-9]{5}"</CODE> or in other words: it
     * is 5 digit alphanumeric characters.
     * <p>
     * 3. The Type of the order can be only one of the following:
     *    Envelope, Box, Crate, Drum.
     * <p>
     * 4. The Specification of the order can be only one of the following:
     *    Fragile, Books, Catalogs, Do-not-Bend, N/A.
     * <p>
     * 5. The Mailing Class of the order can be only one of the following:
     *    First-Class, Priority, Retail, Ground, Metro.
     * <p>
     * 6. Special cases have varying requirements depending on type of package.
     *    Envelope: Height in inches 0-99
     *    Box: Largest dimension in inches 0-999
     *    Crate: Maximum Load Weight in lbs must be greater than 0.00
     *    Drum: Material must be Plastic or Fiber
     * <p>
     * 7. Special cases have varying requirements depending on type of package.
     *    Envelope: Width in inches 0-99
     *    Box: Volume in inches cubed 0-999999
     *    Crate: Content of the crate 
     *    Drum: diameter in inches 0-999
     * @param toAdd the <CODE>PackageOrder</CODE> object to add to the
     * <CODE>packageOrderList</CODE>
     */
    public void addOrder(String trackingnumber, String type, String specification, String mailingclass, String special1, String special2) {

        if (this.findPackageOrder(trackingnumber) != -1) {
            System.out.println("Package Order already exists in database. \n");
            return;
        }

        if (!trackingnumber.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid Tracking Number: not proper format."
                + "Tracking Number must be 5 alphanumeric characters.");
            return;
        }

        if (!(specification.equals("Fragile") || specification.equals("Books") || specification.equals("Catalogs")
            || specification.equals("Do-not-Bend") || specification.toUpperCase().equals("N/A"))) {
            System.out.println("Invalid specification:\n"
                + "Specification must be one of following: "
                + "Fragile, Books, Catalogs, Do-not-Bend, N/A.");
            return;
        }

        if (!(mailingclass.equals("First-Class") || mailingclass.equals("Priority") || mailingclass.equals("Retail")
            || mailingclass.equals("Ground") || mailingclass.equals("Metro")) ) {
            System.out.println("Invalid Mailing Class:\n"
                + "Mailing Class must be one of following: "
                + "First-Class, Priority, Retail, Ground, Metro.");
            return;
        }

        if (type.equals("Envelope"))
        {
            if(!special1.matches("[0-9]{1,2}")){
                System.out.println("Invalid height:\n"
                    + "The envelope's height (inches) has to be an integer number between 0 and 99.");
                return;
            }
            if(!special2.matches("[0-9]{1,2}")){
                System.out.println("Invalid width:\n"
                    + "The envelope's width (inches) has to be an integer number between 0 and 99.");
                return;
            }

            // If it passed all the checks, add the order to the list
            packageOrderList.add(new Envelope(trackingnumber, specification, mailingclass,
                    Integer.parseInt(special1), Integer.parseInt(special2)));
        }
        else if (type.equals("Box"))
        {
            if(!special1.matches("[0-9]{1,3}")){
                System.out.println("Invalid dimension:\n"
                    + "The box's largest dimension (inches) has to be an integer number between 0 and 999.");
                return;
            }
            if(!special2.matches("[0-9]{1,6}")){
                System.out.println("Invalid volume:\n"
                    + "The box's volume (inches cubed) has to be an integer number between 0 and 999999.");
                return;
            }

            // If it passed all the checks, add the order to the list
            packageOrderList.add(new Box(trackingnumber, specification, mailingclass,
                    Integer.parseInt(special1), Integer.parseInt(special2)));
        }
        else if (type.equals("Crate"))
        {
            if ((Float.parseFloat(special1) < 0)) {
                System.out.println("Invalid load weight:\n"
                    + "The maximum load weight of the crate cannot be negative");
                return;
            }

            // If it passed all the checks, add the order to the list
            packageOrderList.add(new Crate(trackingnumber, specification, mailingclass,
                    Float.parseFloat(special1), special2));
        }
        else if (type.equals("Drum"))
        {
            if (!(special1.equals("Plastic") || special1.equals("Fiber"))){
                System.out.println("Invalid material:\n"
                    + "Valid drum material options are: Plastic, Fiber");
                return;
            }
            if(!special2.matches("[0-9]{1,3}")){
                System.out.println("Invalid diameter:\n"
                    + "The drums diameter (inches) has to be an integer number between 0 and 999.");
                return;
            }

            // If it passed all the checks, add the order to the list
            packageOrderList.add(new Drum(trackingnumber, specification, mailingclass,
                    special1, Integer.parseInt(special2)));
        }
        else{
            System.out.println("Not a valid type.\n"
                + "Valid package types are: Envelope, Box, Crate, or Drum\n");
            return;
        }

        // If an order was added, sort the list and display message
        System.out.println("Package Order has been added.\n");
        Collections.sort(packageOrderList);
    }

    /**
     * This method will remove an order from the <CODE>packageOrderList</CODE> ArrayList. It
     * will remove the instance of an order that matches tracking number that was
     * passed to this method. If no such order exists, it will produce an error message.
     *
     * @param trackingNum the <CODE>PackageOrder</CODE> object to be removed.
     */
    public void removeOrder(String trackingNum) 
    {
        int orderID = findPackageOrder(trackingNum);
        if (orderID == -1) {
            System.out.println("\nAction failed. No package order with the given tracking # exist in database.\n");
        }
        else {
            packageOrderList.remove(orderID);
            System.out.println("\nAction successful. Package order has been removed from the database.\n");
        }
    }

    /**
     * This method is used to retrieve the PackageOrder object from the
     * <CODE>PackageOrderList</CODE> at a given index.
     *
     * @param i the index of the desired <CODE>PackageOrder</CODE> object.
     * @return the <CODE>PackageOrder</CODE> object at the index or null if the index is
     * invalid.
     */
    public PackageOrder getPackageOrder(int i) 
    {
        if (i < packageOrderList.size() && i >= 0) {
            return packageOrderList.get(i);
        } else {
            System.out.println("Invalid Index. Please enter another command or 'h' to list the commands.");
            return null;
        }
    }

    /**
     * This method opens <CODE>"PackageOrderDB.ser"</CODE> and overwrites it with a serialization of
     * all the package orders in the <CODE>PackageOrderList</CODE>.
     * This should be the last method to be called before exiting the program.
     * @throws IOException
     */
    public void flush() throws IOException 
    {
        FileOutputStream fos = new FileOutputStream("PackageDB.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(packageOrderList);

        fos.close();
    }

}
