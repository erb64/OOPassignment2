package shippingstore;

/**
 * This class is a specialized representation of a package order, specific to drums. 
 * There are only getter methods and no setter methods and as a result an drum package 
 * cannot be mutated once initialized. A drum package order object can also call the two 
 * override methods via inheritance from PackageOrder
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @extends PackageOrder
 * @author Emily Beaudoin
 */
public class Drum extends PackageOrder
{
	private final String material;
	private final int diameter;

    /**
     * This constructor initializes the drum object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * drum object.
     *
     * @param trackingnumber a <b><CODE>String</CODE></b> that represents the tracking number
     *
     * @param specification a <b><CODE>String</CODE></b> that represents the specification.
     * Specification: Fragile, Books, Catalogs, Do-not-Bend, N/A - one per package
     *
     * @param mailingclass a <b><CODE>String</CODE></b> that represents the mailing class
     * Mailing class: First-Class, Priority, Retail, Ground, Metro.
     *
     * @param material a <b><CODE>String</CODE></b> that represents the material the drum
     * is made from: Plastic or Fiber
     *
     * @param diameter an <b><CODE>int</CODE></b> that represents the diameter of the drum
     * in inches
     *
     */
	public Drum (String trackingnumber, String specification, String mailingclass, String material, int diameter){
		super(trackingnumber, specification, mailingclass);
		this.material = material;
		this.diameter = diameter;
	}

	/**
     * This method returns the drum's material
     *
     * @return a <b><CODE>String</CODE></b> that is the drum's material.
     */
	public String getMaterial(){
		return material;
	}

	/**
     * This method returns the drum's diameter
     *
     * @return an <b><CODE>int</CODE></b> that is the drum's diameter in inches.
     */
	public int getDiameter(){
		return diameter;
	}
}