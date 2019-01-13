package Game;

import Geom.Point3D;

public class Ghost {

	Point3D point;
	double speed;
	double radius;

	/**
	 * Constructor
	 * @param point
	 * @param speed
	 * @param radius
	 */
	public Ghost(Point3D point, double speed, double radius) {
		super();
		this.point = point;
		this.speed = speed;
		this.radius = radius;
	}
	
	/**
	 * copy constructor
	 * @param g
	 */
	public Ghost(Ghost g) {
		this.point=g.point;
		this.speed=g.speed;
		this.radius=g.radius;
	}

	/**
	 * @return the point
	 */
	public Point3D getPoint() {
		return point;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Point3D point) {
		this.point = point;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ghost [point=" + point.toString() + ", speed=" + speed + ", radius=" + radius + "]";
	}

}
