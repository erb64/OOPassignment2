package shippingstore;

/**
 * This class is a specialized representation of a package order, specific to envelopes. 
 * There are only getter methods and no setter methods and as a result an envelope package 
 * cannot be mutated once initialized. A envelope package order object can also call the two 
 * override methods via inheritance from PackageOrder
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @extends PackageOrder
 * @author Emily Beaudoin
 */
public class Envelope extends PackageOrder
{
	private final int height;
	private final int width;

    /**
     * This constructor initializes the envelope object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * envelope object.
     *
     * @param trackingnumber a <b><CODE>String</CODE></b> that represents the tracking number
     *
     * @param specification a <b><CODE>String</CODE></b> that represents the specification.
     * Specification: Fragile, Books, Catalogs, Do-not-Bend, N/A - one per package
     *
     * @param mailingclass a <b><CODE>String</CODE></b> that represents the mailing class
     * Mailing class: First-Class, Priority, Retail, Ground, Metro.
     *
     * @param height an <b><CODE>int</CODE></b> that represents the height of the envelope in
     * inches
     *
     * @param width an <b><CODE>int</CODE></b> that represents the width of the envelope in
     * inches
     *
     */
	public Envelope (String trackingnumber, String specification, String mailingclass, int height, int width){
		super(trackingnumber, specification, mailingclass);
		this.height = height;
		this.width = width;
	}

	/**
     * This method returns the envelope's height
     *
     * @return an <b><CODE>int</CODE></b> that is the envelope's height.
     */
	public int getHeight(){
		return height;
	}

	/**
     * This method returns the envelope's width
     *
     * @return an <b><CODE>int</CODE></b> that is the envelope's width in inches.
     */
	public int getWidth(){
		return width;
	}
}