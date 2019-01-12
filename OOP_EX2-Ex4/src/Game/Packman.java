package Game;

import Geom.Point3D;

/**
 * class PackMan
 * this class contain all the data of one PackMan that is needed for Ex3
 * @author omar essa , warda wssa
 *
 */
public class Packman {
	
	public Point3D Packman;
	double radius;
	double speed;
	Path path;
	
	/**
	 * Constructor
	 * @param packman packman
	 * @param radius radius
	 * @param speed speed
	 */
	public Packman(Point3D packman, double radius, double speed) {
		super();
		Packman = packman;
		this.radius = radius;
		this.speed = speed;
		path = new Path();
	}
	
	/**
	 * Copy Constructor
	 * @param other other packman
	 */
	public Packman(Packman other) {
		this.Packman = other.Packman;
		this.radius = other.radius;
		this.speed = other.speed;
		this.path = new Path();
	}

	/**
	 * @return the packman
	 */
	public Point3D getPackman() {
		return Packman;
	}

	/**
	 * @param packman the packman to set
	 */
	public void setPackman(Point3D packman) {
		Packman = packman;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}


	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}


	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Packman [Packman=" + Packman.toString() + ", radius=" + radius + ", speed=" + speed + ", path=" + path
				+ "]";
	}
	
	


}
