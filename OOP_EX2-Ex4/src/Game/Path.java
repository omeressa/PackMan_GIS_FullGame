package Game;

import java.util.ArrayList;

import Geom.Point3D;

/**
 * Gps_points path
 * 
 * @author Omar Essa , Warda Essa
 *
 */
public class Path {

	Map map = new Map();
	public ArrayList<Fruit> fruits;
	ArrayList<Packman> packman;
	double time;

	/**
	 * Starting Constructor
	 */
	public Path() {
		fruits = new ArrayList<>();
		time = 0;
	}

	/**
	 * @return the theMap
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @return the fruits
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	/**
	 * @return the packman
	 */
	public ArrayList<Packman> getPackman() {
		return packman;
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @param theMap the theMap to set
	 */
	public void setMap(Map theMap) {
		this.map = theMap;
	}

	/**
	 * @param fruits the fruits to set
	 */
	public void setFruits(ArrayList<Fruit> fruits) {
		this.fruits = fruits;
	}

	/**
	 * @param packman the packman to set
	 */
	public void setPackman(ArrayList<Packman> packman) {
		this.packman = packman;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}
	
	/**
	 * function that gives us the time between packman and fruit
	 * @param packman packman
	 * @param fruit fruit
	 * @return time time
	 */
	public double time_P_F(Packman packman , Fruit fruit) {
		double answer = map.distance3D(packman.getPackman(), fruit.getGps_point());
		if (answer < packman.getRadius())
			return 0;
		else {	
			return (map.distance3D(packman.getPackman(), fruit.getGps_point())-packman.getRadius())/packman.getSpeed();
		}
	}
	

	/**
	 * function that gives us the total time of the path
	 * @param packman packman
	 * @return total time time
	 */
	public double totalTime_Path(Packman packman) {
		
		double answer = 0;
		double tmp = 0;
		Packman temp = new Packman(packman);
		for (int i = 0; i < packman.getPath().getFruits().size(); i++) {
			tmp = time_P_F(packman,packman.getPath().getFruits().get(i));
			answer = answer+tmp;
			packman.setPackman(packman.getPath().getFruits().get(i).getGps_point());
		}
		packman.getPath().setTime(answer);
		packman.setPackman(temp.getPackman());
    	return answer;
	}

	/**
	 * function to get us to the next fruit in the path
	 * @param packman packman 
	 * @param fruit FRUIT
	 * @param time TIME 
	 * @return fruit gps point
	 */
	public Point3D theNextPoint(Packman packman , Fruit fruit, double time) {
	double path_time = packman.getPath().getTime();
	double x = packman.getPackman().x()/path_time+0.1;
	double y = packman.getPackman().y()/path_time+0.1;
	double point_x = packman.getPackman().x()+x*(fruit.getGps_point().x()-packman.getPackman().x());
	double point_y= packman.getPackman().y()+y*(fruit.getGps_point().y()-packman.getPackman().y());
	return new Point3D(point_x,point_y);	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Path [map=" + map + ", fruits=" + fruits + ", packman=" + packman + ", time=" + time + "]";
	}


}
