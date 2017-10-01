package shippingstore;

import java.io.Serializable;
/**
 * This class is a very simple representation of a package order. There are only getter
 * methods and no setter methods and as a result a package order cannot be mutated once
 * initialized. A package order object can also call the two override methods
 * <CODE>toString()</CODE> and <CODE>equals()</CODE> and <CODE>compareTo()</CODE>
 *
 * @author Junye Wen, edited by Emily Beaudoin to fit this application
 */
public class PackageOrder implements Comparable, Serializable
{
    private final String trackingnumber;
    private final String specification;
    private final String mailingclass;
    private final String type;

    /**
     * This constructor initializes the package order object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * package order object.
     *
     * @param trackingnumber a <b><CODE>String</CODE></b> that represents the tracking number
     *
     * @param specification a <b><CODE>String</CODE></b> that represents the specification.
     * Specification: Fragile, Books, Catalogs, Do-not-Bend, N/A - one per package
     *
     * @param mailingclass a <b><CODE>String</CODE></b> that represents the mailing class
     * Mailing class: First-Class, Priority, Retail, Ground, Metro.
     *
     */
    public PackageOrder(String trackingnumber, String specification, String mailingclass, String type) {
        this.trackingnumber = trackingnumber;
        this.type = type;
        this.specification = specification;
        this.mailingclass = mailingclass;

    }

    /**
     * This method returns the package order's tracking number.
     *
     * @return a <b><CODE>String</CODE></b> that is the tracking number of the package order.
     */
    public String getTrackingNumber() {
        return trackingnumber;
    }

    
     /**
     * This method returns the package's type
     *
     * @return a <b><CODE>String</CODE></b> that describes the package
     */
     public String getType(){
          return type;
     }
     

    /**
     * This method returns the package order's specification.
     *
     * @return a <b><CODE>String</CODE></b> that is the package order's specification.
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * This method returns the package order's mailing class.
     *
     * @return a <b><CODE>string</CODE></b> that is the package order's mailing class
     */
    public String getMailingClass() {
        return mailingclass;
    }

    /**
     * This method returns the package order's fields as a string representation.
     *
     * @return a <b><CODE>String</CODE></b> that lists the fields of the package order
     * object delineated by a space and in the same order as the constructor
     */
    @Override
    public String toString() {
        return trackingnumber + " " + type + " " + specification + " " + mailingclass;
    }

    /**
     * This method provides a way to compare two package order objects.
     *
     * @param c a <b><CODE>PackageOrder</CODE></b> object that is used to compare to
     * <b><CODE>this</CODE></b> package order. Two orders are equal if their TrackingNumber is the
     * same.
     * @return the <CODE>boolean</CODE> value of the comparison.
     */
    public boolean equals(PackageOrder c) {
        return c.getTrackingNumber().equals(this.trackingnumber);
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
    public int compareTo(Object c) {
        String trackingnum = ((PackageOrder)c).trackingnumber;
        return trackingnumber.compareTo(trackingnum);
    }

}
