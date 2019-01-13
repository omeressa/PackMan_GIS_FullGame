package Game;

import Coords.MyCoords;
import Geom.Point3D;

public class Player {

	Point3D player_point;
	double speed;
	double radius;

	/**
	 * constructor
	 * @param player_point
	 * @param speed
	 * @param radius
	 */
	public Player(Point3D player_point, double speed, double radius) {
		super();
		this.player_point = player_point;
		this.speed = speed;
		this.radius = radius;
	}

	/**
	 * @return the player_point
	 */
	public Point3D getPlayer_point() {
		return player_point;
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
	 * @param player_point the player_point to set
	 */
	public void setPlayer_point(Point3D player_point) {
		this.player_point = player_point;
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
		return "Player [player_point=" + player_point + ", speed=" + speed + ", radius=" + radius + "]";
	}

	/**
	 * helping function
	 * @param point
	 * @return
	 */
    public double angelToMove(Point3D point)
    {
        return MyCoords.azimuth_elevation_dist2(this.player_point, point)[0];
    }
	
    /**
     * helping function
     * @param num
     * @return
     */
	public Point3D next_Point (double num) {
		double tmp = num*Math.PI/180;
		double x,y,a=100.0D;
		x = this.getSpeed()*Math.cos(tmp);
		y = this.getSpeed()*Math.sin(tmp);
		return new Point3D(this.getPlayer_point().x()+x*a,this.getPlayer_point().y()+y*a);
	}


}
