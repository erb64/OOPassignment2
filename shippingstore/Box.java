package shippingstore;

/**
 * This class is a specialized representation of a package order, specific to boxes. 
 * There are only getter methods and no setter methods and as a result a box package 
 * cannot be mutated once initialized. A box package order object can also call the two 
 * override methods via inheritance from PackageOrder
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @extends PackageOrder
 * @author Emily Beaudoin
 */
public class Box extends PackageOrder
{
	private final int largestdimension; //in inches
	private final int volume; //in inches cubed

    /**
     * This constructor initializes the box object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * box object.
     *
     * @param trackingnumber a <b><CODE>String</CODE></b> that represents the tracking number
     *
     * @param specification a <b><CODE>String</CODE></b> that represents the specification.
     * Specification: Fragile, Books, Catalogs, Do-not-Bend, N/A - one per package
     *
     * @param mailingclass a <b><CODE>String</CODE></b> that represents the mailing class
     * Mailing class: First-Class, Priority, Retail, Ground, Metro.
     *
     * @param largestdimension an <b><CODE>int</CODE></b> that represents the largest dimension
     * of the box in inches
     *
     * @param volume an <b><CODE>int</CODE></b> that represents the volume of the box in 
     * inches cubed
     *
     */
	public Box (String trackingnumber, String specification, String mailingclass, int largestdimension, int volume){
		super(trackingnumber, specification, mailingclass);
		this.largestdimension = largestdimension;
		this.volume = volume;
	}

	/**
     * This method returns the box's largest dimension
     *
     * @return an <b><CODE>int</CODE></b> that is the box's largest dimension in inches.
     */
	public int getLargestDimension(){
		return largestdimension;
	}

	/**
     * This method returns the box's volume
     *
     * @return an <b><CODE>int</CODE></b> that is the box's volume in inches cubed.
     */
	public int getVolume(){
		return volume;
	}
}
