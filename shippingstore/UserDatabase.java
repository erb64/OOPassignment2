package shippingstore;

import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * This class is used to represent a database interface for a list of 
 * <CODE>User</CODE>'s. It using a plain-text file "UserDB.txt"  to store and 
 * write package order objects in readable text form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>userList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>userList</CODE>
 * is written to "UserDB.txt" at the end of the <CODE>UserDatabase</CODE> object's
 * life by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching for users from the list.
 *
 * @author Emily Beaudoin
 */
public class UserDatabase {

    private ArrayList<PackageOrder> packageOrderList;

    /**
     * This constructor is hard-coded to open "<CODE>UserDB.txt</CODE>" and
     * initialize the <CODE>packageOrderList</CODE> with its contents. If no such file
     * exists, then one is created. The contents of the file are "loaded" into
     * the packageOrderList ArrayList in no particular order. The file is then closed
     * during the duration of the program until <CODE>flush()</CODE> is called.
     * @throws IOException
     */
    // public UserDatabase() throws IOException {
    //     packageOrderList = new ArrayList<>();
    //     Scanner orderScanner;

    //     File dataFile = new File("UserDB.txt");

    //     // If data file does not exist, create it.
    //     if (!dataFile.exists()) {
    //         System.out.println("UserDB.txt does not exist, creating one now . . .");
    //         //if the file doesn't exists, create it
    //         PrintWriter pw = new PrintWriter("UserDB.txt");
    //         //close newly created file so we can reopen it
    //         pw.close();
    //     }

    //     orderScanner = new Scanner(new FileReader(dataFile));

    //     //Initialize the Array List with package orders from PackageOrderDB.txt
    //     while (orderScanner.hasNextLine()) {

    //         // split values using the space character as separator
    //         String[] temp = orderScanner.nextLine().split(" ");

    //         packageOrderList.add(new User(temp[0], temp[1], temp[2], temp[3],
    //                 Float.parseFloat(temp[4]), Integer.parseInt(temp[5])));
    //     }

    //     //User list is now in the ArrayList completely so we can close the file
    //     orderScanner.close();
    // }
}
