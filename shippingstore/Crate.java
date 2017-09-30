package shippingstore;

 /**
 * This class is a specialized representation of a package order, specific crates. 
 * There are only getter methods and no setter methods and as a result a crate package 
 * cannot be mutated once initialized. A crate package order object can also call the two 
 * override methods (via inheritance from PackageOrder)
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @extends PackageOrder
 * @author Emily Beaudoin
 */
public class Crate extends PackageOrder
{
	private final Float loadweight;
	private final String content;

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
     * @param loadweight a <b><CODE>Float</CODE></b> that represents the maximum load 
     * weight in pounds (lbs) 
     *
     * @param content a <b><CODE>String</CODE></b> that represents the contents of the 
     * crate
     *
     */
	public Crate (String trackingnumber, String specification, String mailingclass, Float loadweight, String content){
		super(trackingnumber, specification, mailingclass);
		this.loadweight = loadweight;
		this.content = content;
	}

	/**
     * This method returns the crate's load weight
     *
     * @return a <b><CODE>Float</CODE></b> that is the crate's maximum load weight.
     */
	public Float getLoadWeight(){
		return loadweight;
	}

	/**
     * This method returns the crate's content
     *
     * @return a <b><CODE>String</CODE></b> that describes the crate's content.
     */
	public String getContent(){
		return content;
	}
}
