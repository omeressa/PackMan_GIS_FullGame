package Game;

import Geom.Point3D;

/**
 * Fruit class
 * 
 * @author Omar Essa , Warda Essa
 *
 */
public class Fruit {
	
	Point3D gps_point;
	int weight;
	
	/**
	 * Constructor
	 * @param gps_point mark the place fruit is on
	 * @param weight weight of the fruit
	 */
	public Fruit(Point3D gps_point, int weight) {
		super();
		this.gps_point = gps_point;
		this.weight = weight;
	}
	
	/**
	 * Copy Constructor
	 * @param other another fruit
	 */
	public Fruit(Fruit other) {
		this.gps_point = other.gps_point;
		this.weight = other.weight;
	}

	
	/**
	 * getter
	 * @return fruit gps point
	 */
	public Point3D getGps_point() {
		return gps_point;
	}

	/**
	 * getter
	 * @return fruit weight
	 */
	public int getWeight() {
		return weight;
	}



	/**
	 * no need for setters according to Ex3
	 */
//	public void setGps_point(Point3D gps_point) {
//		this.gps_point = gps_point;
//	}
//
//	public void setWeight(int weight) {
//		this.weight = weight;
//	}
	
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fruit [gps_point=" + gps_point.toString() + ", weight=" + weight + "]";
	}
	

}
