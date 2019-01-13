package Game;

import Coords.MyCoords;
import Geom.Point3D;

public class Box {

	static int count = 0;

	Point3D p1; // left_down
	Point3D p2; // right_up
	Point3D p3; // right_down
	Point3D p4; // left_up

	MyCoords coord = new MyCoords();
	
	/**
	 * constructor
	 * @param p1
	 * @param p2
	 */
	public Box(Point3D p1 , Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = new Point3D(p1.x(),p2.y());
		this.p4 = new Point3D(p2.x(),p1.y());
	}
	
	/**
	 * @return the p1
	 */
	public Point3D getP1() {
		return p1;
	}
	/**
	 * @return the p2
	 */
	public Point3D getP2() {
		return p2;
	}
	/**
	 * @return the p3
	 */
	public Point3D getP3() {
		return p3;
	}
	/**
	 * @return the p4
	 */
	public Point3D getP4() {
		return p4;
	}
	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(Point3D p1) {
		this.p1 = p1;
	}
	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(Point3D p2) {
		this.p2 = p2;
	}
	/**
	 * @param p3 the p3 to set
	 */
	public void setP3(Point3D p3) {
		this.p3 = p3;
	}
	/**
	 * @param p4 the p4 to set
	 */
	public void setP4(Point3D p4) {
		this.p4 = p4;
	}

	public boolean checking (Point3D p, Point3D close_p) {
		Point3D tmp  = new Point3D(p),ans;
		while(coord.distance3d(tmp, close_p) >= 1) {
			if((tmp.x() >= p1.x() && tmp.x() <= p2.x()) && (tmp.y() <= p2.y() && tmp.y() >= p1.y())) {
				return true;
			}
			ans = next_point(tmp, close_p);
			tmp = ans;
		}
		return false;
	}

	public Box add() {
		return new Box(new Point3D(this.p1.x()-0.0010,this.p1.y()+0.0010),
				new Point3D(this.p2.x()+0.0010,this.p2.y()-0.0010));
	}

	public double inside_box(Point3D m, Point3D theClose) {
		double meter = 1;
		Point3D Height_x_right = new Point3D(m.x(),p2.y());
		Point3D Height_x_left = new Point3D(m.x(),p1.y());
		Point3D Width_y_up = new Point3D(p2.x(),m.y());

		if((coord.distance3d(m, Height_x_right) <= meter)){
			if(m.x() > p2.x() && m.y() > p1.y()) {
				return 3;
			}else {
				return 4;
			}
		}
		if((coord.distance3d(m, Width_y_up) <=meter)&& (m.y() <= p2.y()) && (m.y() >= p1.y())){
			if(!checking(m,theClose)) {
				System.out.println("can do 3");
				return 5;
			}
			return 3;
		}
		if((coord.distance3d(m, Height_x_left) <= meter) && m.x() >= p1.x()){
			System.out.println(coord.distance3d(m, Height_x_left));
			if(!checking(m,theClose)) {

				System.out.println("can do 4");
				return 5;
			}
			return 4;
		}
		return 5;
	}

	public  Point3D next_point(Point3D p1 , Point3D f1) {
		double dt = 1000;
		double x = p1.x()/dt;
		double y = p1.y()/dt;
		return new Point3D(p1.x()+x*(f1.x()-p1.x()),p1.y()+y*(f1.y()-p1.y()));
	}



}
