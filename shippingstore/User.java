package shippingstore;

import java.io.Serializable;
/**
 * This class is a very simple representation of a user. A user object can call 
 * the two override methods <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @author Emily Beaudoin
 */
public class User implements Comparable, Serializable
{

    private final Integer idNumber;
    private String firstName;
    private String lastName;

    /**
     * This constructor initializes the user object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * user object.
     *
     * @param idNumber an <b><CODE>Integer</CODE></b> that represents the user's ID number.
     * Each user's ID is unique and cannot be changed
     *
     * @param firstName a <b><CODE>String</CODE></b> that represents the first name of 
     * the user
     *
     * @param lastName a <b><CODE>String</CODE></b> that represents the last name of the 
     * user
     *
     */
    public User (Integer idNumber, String firstName, String lastName) 
    {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * This method returns the user's ID number.
     *
     * @return an <b><CODE>Integer</CODE></b> that is the ID number of the user.
     */
    public Integer getIdNumber() 
    {
        return idNumber;
    }

    /**
     * This method returns the user's full name in the format "First Last"
     *
     * @return a <b><CODE>String</CODE></b> that is the user's full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * This method changes the user's first name
     *
     * @param firstName a <b><CODE>String</CODE></b> that is the user's first name.
     */
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    /**
     * This method returns the user's first name
     *
     * @return a <b><CODE>String</CODE></b> that is the user's first name.
     */
    public String getFirstName() 
    {
        return firstName ;
    }

    /**
     * This method changes the user's last name
     *
     * @param lastName a <b><CODE>String</CODE></b> that is the user's last name.
     */
    public void setlastName(String lastName) 
    {
        this.lastName = lastName;
    }

    /**
     * This method returns the user's last name
     *
     * @return a <b><CODE>String</CODE></b> that is the user's last name.
     */
    public String getLastName() 
    {
        return lastName;
    }

    /**
     * This method returns the user's fields as a string representation.
     *
     * @return a <b><CODE>String</CODE></b> that lists the fields of the user
     * object delineated by a space and in the same order as the constructor
     */
    @Override
    public String toString() 
    {
        return idNumber + " " + firstName + " " + lastName + "\n";
    }

    /**
     * This method provides a way to compare two users.
     *
     * @param c a <b><CODE>User</CODE></b> object that is used to compare to
     * <b><CODE>this</CODE></b> user. Two users are equal if their ID number is the
     * same.
     * @return the <CODE>boolean</CODE> value of the comparison.
     */
    public boolean equals(User c) 
    {
        return c.getIdNumber().equals(this.idNumber);
    }

     /**
     * This method provides a compareTo in order to sort by tracking number 
     *
     * @param c a <b><CODE>PackageOrder</CODE></b> object that is used to compare to
     * <b><CODE>this</CODE></b> package order. 
     * @return the <CODE>int</CODE> value a negative integer, zero, or a positive integer 
     * when this object is less than, equal to, or greater than (respectively) the 
     * specified object.
     */
    @Override
    public int compareTo(Object c) 
    {
        Integer idNum = ((User)c).idNumber;
        return idNumber.compareTo(idNum);
    }
}
