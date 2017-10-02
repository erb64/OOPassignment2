package shippingstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

/**
 * This class is used to represent a database interface for a list of 
 * <CODE>User</CODE>'s. It using a serial file "UserDB.ser"  to store and 
 * write package order objects in readable text form. It contains
 * an <CODE>ArrayList</CODE> called <CODE>userList</CODE> to store the
 * database in a runtime friendly data structure. The <CODE>userList</CODE>
 * is written to "UserDB.ser" at the end of the <CODE>UserDatabase</CODE> object's
 * life by calling <CODE>flush()</CODE>. This class also provides methods for
 * adding, remove, and searching for users from the list.
 *
 * @author Emily Beaudoin
 */
public class UserDatabase 
{
    private ArrayList<User> userList;

    /**
     * Private method used as an auxiliary method to display a given ArrayList
     * of Users in a formatted manner.
     *
     * @param orders the package order list to be displayed.
     */
    private void showUsers(ArrayList<User> users) 
    {
        System.out.println(" -------------------------------------------------------------------------- ");
        System.out.println("| ID # | Name               |                                              |");
        System.out.println(" -------------------------------------------------------------------------- ");


        for (User u : users){
            System.out.printf("|%6d| %-19s|", u.getIdNumber(), u.getFullName());

            if(u instanceof Customer){
                System.out.printf(" Phone number: %s\n", ((Customer)u).getPhone());
                System.out.printf("|%27s| Address: %s\n", " ", ((Customer)u).getAddress());
            } else { //(u instance of Employee)
                System.out.printf(" Social Security Number: %09d\n", ((Employee)u).getSocial());
                System.out.printf("|%27s| Monthly Salary: %.02f\n", " ", ((Employee)u).getSalary());
                System.out.printf("|%27s| Bank Account Number: %d\n", " ", ((Employee)u).getAccount());
            }   
        }

        System.out.println(" --------------------------------------------------------------------------\n");
    }

    /**
     * This constructor is hard-coded to open "<CODE>UserDB.txt</CODE>" and
     * initialize the <CODE>packageOrderList</CODE> with its contents. If no such file
     * exists, then one is created. The contents of the file are "loaded" into
     * the packageOrderList ArrayList in no particular order. The file is then closed
     * during the duration of the program until <CODE>flush()</CODE> is called.
     * @throws IOException if the FileInputStream cannot find a file - handled
     */
    public UserDatabase() throws IOException 
    {
        userList = new ArrayList<>();

        
        try{
            FileInputStream fis = new FileInputStream("UserDB.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<User>) ois.readObject();
            fis.close(); 
        }
        catch(FileNotFoundException fnfe)
        {
            // If data file does not exist, create it.
            System.out.println("UserDB.ser does not exist, creating one now . . .");
            //if the file doesn't exists, create it
            FileOutputStream fos = new FileOutputStream("UserDB.ser");
            //close newly created file so we can reopen it
            fos.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method showUsers displays the current list of Users in the Arraylist in the
     * order they are in storage 
     *
     */
    public void showUsers() 
    {
        showUsers(userList);
    }

    /**
     * This method can be used to find a user in the Arraylist of users.
     *
     * @param idNumber a <CODE>String</CODE> that represents the id number 
     * of the order that to be searched for.
     * @return the <CODE>int</CODE> index of the package orders in the Arraylist of orders,
     * or -1 if the search failed.
     */
    public int findUser(String idNumber) 
    {
        int index = -1;
        if(idNumber.matches("[0-9]{6}")){
            for (User u : userList) {
                Integer temp = u.getIdNumber();

                if (temp.equals(Integer.parseInt(idNumber))) {
                    index = userList.indexOf(u);
                    break;
                }
            }
        }

        return index;
    }

    /**
     * This method is used to add a user to the userList ArrayList. In order for a
     * user to be added to the ArrayList it must comply with the following:
     * <p>
     * 1. The user is not already in the ArrayList according to the ID number
     * as the unique key.
     * <p>
     * 2. The ID number is 6 digit numeric value
     * <p>
     * 3. The Type of the user can be only one of the following:
     *    Employee or Customer
     * <p>
     * 4. For Employees: Social security 
     *
     * @param type the <CODE>String</CODE> which represents the type of user to be 
     * added
     *
     * @param idNumber the <CODE>String</CODE> which represents the user's ID number
     * 
     * @param firstName the <CODE>String</CODE> that is the user's first name
     *
     * @param lastName the <CODE>String</CODE> that is the user's last name
     *
     * @param special1 the <CODE>String</CODE> that is either the employee's social
     * or the Customer's phone number
     *
     * @param special2 the <CODE>String</CODE> that is either the employee's monthly 
     * salary OR the customer's address
     *
     * @param special3 the <CODE>String</CODE> that is either the employee's account
     * number for direct deposit OR ignored for customer typed users
     */
    public void addUser(String type, String idNumber, String firstName, String lastName,
                         String special1, String special2, String special3) 
    {

        if (this.findUser(idNumber) != -1) {
            System.out.println("User already exists in database. \n");
            return;
        }

        if (!idNumber.matches("[0-9]{6}")) {
            System.out.println("Invalid ID Number: not proper format."
                + "ID Number must be 6 digit positive number.\n");
            return;
        }

        if (type.equals("Employee")){
            if (!special1.matches("[0-9]{9}")) {
                System.out.println("Invalid Social Security number. "
                    + "SS number must be 9 digits.\n");
                return;
            }

            if (Float.parseFloat(special2) < 0) {
                System.out.println("Invalid salary:\n"
                    + "Monthly salary cannot be negative\n");
                return;
            }

            if (!special3.matches("[0-9]{8,15}")) {
                System.out.println("Invalid bank account number:\n"
                    + "Bank account number must be between 8 and 15 digits.");
                return;
            }

            // If it passed all the checks, add the user to the list
            userList.add(new Employee(Integer.parseInt(idNumber), firstName, lastName, 
                    Integer.parseInt(special1), Float.parseFloat(special2), 
                    Integer.parseInt(special3)));
        } else {// (type.equals("Customer"))
            // If it passed all the checks, add the order to the list
            userList.add(new Customer(Integer.parseInt(idNumber), firstName, lastName, 
                    special1, special2)); //phone, address
        }

        // If an order was added, sort the list and display message
        System.out.println("User has been added.\n");
        Collections.sort(userList);
    }

    /**
     * This method will remove a user from the <CODE>userList</CODE> ArrayList. It
     * will remove the instance of a user that matches ID number that was
     * passed to this method. If no such user exists, it will produce an error message.
     *
     * @param idNum the <CODE>String</CODE> ID number of the user to be removed.
     */
    public void removeUser(String idNum) 
    {
        int userIndex = findUser(idNum);
        if (userIndex == -1) {
            System.out.println("\nAction failed. No user with the given ID # exists in database.\n");
        }
        else {
            userList.remove(userIndex);
            System.out.println("\nAction successful. User has been removed from the database.\n");
        }
    }

    /**
     * This method is used to retrieve the User object from the
     * <CODE>userList</CODE> at a given index.
     *
     * @param i the index of the desired <CODE>User</CODE> object.
     * @return the <CODE>PackageOrder</CODE> object at the index or null if the index is
     * invalid.
     */
    public User getUser(int i) 
    {
        if (i < userList.size() && i >= 0) {
            return userList.get(i);
        } else {
            System.out.println("Invalid Index. Please enter another command or 'h' to list the commands.");
            return null;
        }
    }

    /**
    * This method returns the type of user in the <CODE>userList</CODE> at the given
    * index
    * 
    * @param i the index of the desired <CODE>User</CODE>
    * @return the <CODE>String</CODE> description of the type of user
    */
    public String getUserType(int i)
    {
        User u = userList.get(i);
        if ( u instanceof Employee ) {
            return "Employee";
        }
        else {//(u instanceof Customer) 
            return "Customer";
        }
    }

    /**
    * This method is used to change a user's info after they have already been added to the
    * <CODE>userList</CODE>
    *
    * @param index an <b><CODE>int</CODE></b> that represents the index of the user in the
    * array list
    *
    * @param type a <b><CODE>String</CODE></b> that represents the type of user to be updated
    *
    * @param field a <b><CODE>String</CODE></b> that represents the type of information 
    * being updated
    * 
    * @param update a <b><CODE>String</CODE></b> that represents the updated information
    */
    public void updateUserInfo (int index, String type, String field, String update)
    {
        if (field.equalsIgnoreCase("First-name")) 
            (userList.get(index)).setFirstName(update);
        else if (field.equalsIgnoreCase("Last-name"))
            (userList.get(index)).setLastName(update);
        else if (type.equals("Employee")){
            if (field.equalsIgnoreCase("Social")) {
                if (!update.matches("[0-9]{9}"))
                    System.out.println("\nError: Social Security number must be 9 digits long.\n");
                else 
                    ((Employee)userList.get(index)).setSocial(Integer.parseInt(update));

            } else if (field.equalsIgnoreCase("Salary")) {
                if (!(Float.parseFloat(update) > 0))
                    System.out.println("\nError: Salary must be greater than 0.\n");
                else 
                    ((Employee)userList.get(index)).setSalary(Float.parseFloat(update));
            } else if (field.equalsIgnoreCase("Account")) {
                if (!update.matches("[0-9]{8,15}"))
                    System.out.println("\nError: Account number must be between 8 and 15 digits\n");
                else
                    ((Employee)userList.get(index)).setAccount(Integer.parseInt(update));
            } else {
                System.out.println("\nError: this user doesnt have that field to update\n");
                return;
            }
        } else { //type.equals("Customer")
            if (field.equalsIgnoreCase("Phone")) 
                ((Customer)userList.get(index)).setPhone(update);
            else if (field.equalsIgnoreCase("Address")) 
                ((Customer)userList.get(index)).setAddress(update);
            else{
                System.out.println("\nError: this user doesn't have that field to update\n");
                return;
            }
        }

        System.out.println("\nUpdate succssful.\n");

    }

    /**
     * This method opens <CODE>"UserDB.ser"</CODE> and overwrites it with a serialization of
     * all the users in the <CODE>userList</CODE>.
     * This should be the last method to be called before exiting the program.
     * @throws IOException
     */
    public void flush() throws IOException 
    {
        FileOutputStream fos = new FileOutputStream("UserDB.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(userList);

        fos.close();
    }

}

