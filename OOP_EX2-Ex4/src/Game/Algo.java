package Game;

import java.util.ArrayList;

import Coords.MyCoords;
import Geom.Point3D;

public class Algo {

	private ArrayList<Fruit> fruits = new ArrayList<>(); 
	private ArrayList<Packman> Packmans = new ArrayList<>();
	private ArrayList<Box> boxs = new ArrayList<>();
	private ArrayList<Ghost> ghosts = new ArrayList<>();
	private ArrayList<Point3D> ans;
	private ArrayList<Box> new_b;
	private MyCoords coord;
	private Player player = new Player(new Point3D(0,0,0),1,1);
	private Map map = new Map();
	int verefie=0;

/**
 * constructor
 * @param game
 */
	public Algo(Game game) {	

		ArrayList<Fruit> tmp = new ArrayList<Fruit>(game.fruits.size());  for (Fruit item : game.fruits) tmp.add(item);
		this.fruits = tmp;
		this.Packmans = game.packmans;
		this.player = game.player;
		this.boxs = game.boxs;
		this.ghosts = game.Ghosts;
		this.coord = new MyCoords();
		this.ans = new ArrayList<>();
		this.ans = add_to_list(this.Packmans,this.fruits);
		this.new_b = box_list(boxs);
	}



	/**
	 * function that update the game
	 * @param p2
	 * @param d
	 * @return
	 */
	public double update_Game(Player p2, double d) { 
		Point3D theClose = close_fruit(p2,ans);
		if(check_path(p2.getPlayer_point(),theClose)==false) {
			d = coord.myDir(theClose,p2.getPlayer_point());
			return d;
		}
		else {
			Point3D connverTogo = nearest_conner(p2.getPlayer_point(),theClose);
			double rotate = check_box(p2.getPlayer_point(),d,theClose);
			System.out.println("rotate is:"+rotate);
			if(rotate !=5) {
				return rotate;
			}else {
				d = coord.myDir(connverTogo,p2.getPlayer_point());
				System.out.println("the dir is: "+d);
				return d;	

			}
		}
	}

/**
 * boxs function
 * @param boxs
 * @return
 */
	private ArrayList<Box> box_list(ArrayList<Box> boxs) {
		ArrayList<Box> answer = new ArrayList<>();
		for (int i = 0; i < boxs.size(); i++) {
			Box tmp = boxs.get(i).add();
			Point3D box_point = map.Pixel2Gps(tmp.getP1().x(), tmp.getP1().y());
			Point3D box_point2 = map.Pixel2Gps(tmp.getP2().x(), tmp.getP2().y());
			Box b = new Box(box_point,box_point2);
			answer.add(b);
		}
		return answer;
	}

	
	private ArrayList<Point3D> add_to_list(ArrayList<Packman> Packmans,ArrayList<Fruit> fruits) {
		ArrayList<Point3D> ans = new ArrayList<>();
		for (int i = 0; i < fruits.size(); i++) {
			Point3D f_point = map.Pixel2Gps(fruits.get(i).getGps_point().x(), fruits.get(i).getGps_point().y());
			ans.add(f_point);
		}
		return ans;
	}


	/**
	 * close fruit to player
	 * @param M
	 * @param fruits_packs
	 * @return
	 */
	public Point3D close_fruit(Player M,ArrayList<Point3D> fruits_packs) {
		double fast_time = time_M_P(M,fruits_packs.get(0));
		Point3D closer = fruits_packs.get(0);
		double tmp = 0;
		for (int i = 1; i < fruits_packs.size(); i++) {
			tmp = time_M_P(M, fruits_packs.get(i));
			if(tmp < fast_time)	{
				fast_time = tmp;
				closer = fruits_packs.get(i);
			}	
		}
		return closer;
	}


	/**
	 * count distance between player and a point
	 * @param M
	 * @param point
	 * @return
	 */
	public double time_M_P(Player M , Point3D point) {
		if (coord.distance3d(M.getPlayer_point(), point) < M.getRadius())
			return 0;
		else 
			return (coord.distance3d(M.getPlayer_point(), point)-M.getRadius())/M.getSpeed();
	}

	public double check_box(Point3D p1, double d, Point3D p2) {
		for (int i = 0; i < new_b.size(); i++) {
			if(new_b.get(i).inside_box(p1, p2) == 1) {
				d = 90;
				return d;
			}
			else if(new_b.get(i).inside_box(p1, p2) == 2) {
				d = 0;
				return d;
			}
			else if(new_b.get(i).inside_box(p1, p2) == 3) {
				d = 270;
				return d;
			}
			else if(new_b.get(i).inside_box(p1, p2) == 4) {
				d = 180;
				return d;
			}
		}
		return 5;
	}

	public Point3D nearest_conner(Point3D player, Point3D p) {
		double close_Conner_min_Final=0;
		double conner = 0;
		Point3D answer  = new Point3D(player);
		Point3D conner_answer = new Point3D(new_b.get(0).getP1());
		Point3D tmp  = new Point3D(player);
		for (int i = 0; i < new_b.size(); i++) {
			while(coord.distance3d(tmp, p) >= 1) {
				if(new_b.get(i).checking(tmp,p)==true){
					conner =coord.distance3d(conner_answer,player);
					close_Conner_min_Final = conner;
					conner = coord.distance3d(new_b.get(i).getP2(),player); 
					if(conner < close_Conner_min_Final){
						close_Conner_min_Final = conner;  
						conner_answer = new_b.get(i).getP2();
					}
					conner = coord.distance3d(new_b.get(i).getP3(),player);
					if(conner < close_Conner_min_Final){
						close_Conner_min_Final = conner; 
						conner_answer = new_b.get(i).getP3();
					}
					conner = coord.distance3d(new_b.get(i).getP4(),player);
					if(conner < close_Conner_min_Final){
						close_Conner_min_Final = conner; 
						conner_answer = new_b.get(i).getP4();
					}
				}
				answer = next_point(tmp, p);
				tmp = answer;
			}
		}
		return conner_answer;
	}

	public boolean check_path (Point3D p1, Point3D p2) {
		for (int i = 0; i < new_b.size(); i++) {
			if(new_b.get(i).checking(p1, p2)==true) {
				return true;
			}
		}
		return false	;
	}

/**
 * getter
 * @return
 */
	public ArrayList<Box> getBoxs() {
		return boxs;
	}
/**
 * setter
 * @param boxs
 */
	public void setBoxs(ArrayList<Box> boxs) {
		this.boxs = boxs;
	}

	/**
	 * help function
	 * @param p1
	 * @param f1
	 * @return
	 */
	public  Point3D next_point(Point3D p1 , Point3D f1) {
		double dt = 10000; 
		double x = p1.x()/dt;
		double y = p1.y()/dt;
		return new Point3D(p1.x()+x*(f1.x()-p1.x()),p1.y()+y*(f1.y()-p1.y()));
	}


}
