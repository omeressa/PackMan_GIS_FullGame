//package Game;
//
//import java.util.ArrayList;
//
//public class ShortestPathAlgo {
//
//	ArrayList<Fruit> fruits = new ArrayList<>();
//	ArrayList<Packman> Packmans = new ArrayList<>();
//	Map map = new Map();
//	Compare comp = new Compare(); 
//
//
//	/**
//	 * Constructor
//	 * @param game game
//	 */
//	public ShortestPathAlgo(Game game) {	
//		ArrayList<Fruit> fruits = new ArrayList<Fruit>(game.fruits.size());  
//		for (Fruit fruit : game.fruits) fruits.add(fruit);
//		this.fruits = fruits;
//		this.Packmans = game.packmans;
//	}
//
//	/**
//	 * algorithim for one Packman
//	 * @param p PACKMAN
//	 * @return PATH
//	 */
//	public Path singalPackman(Packman p){
//		ArrayList<Fruit> fruits = this.fruits;
//
//		Packman tempPackman = new Packman(p.getPackman(),p.getSpeed(),p.getRadius());
//		Path distance = distance_Algorithim(tempPackman,fruits);
//		Path path = faster_distance_one_Packman(tempPackman, fruits);
//
//		p.getPath().setFruits(distance.getFruits());	
//		double time1 = distance.totalTime_Path(p);
//		p.getPath().setFruits(path.getFruits());
//		double time2 = path.totalTime_Path(p);
//		if(time1 < time2) {
//			p.getPath().setFruits(distance.getFruits());	
//			p.getPath().setTime(time1);
//			System.out.println(p.getPath().getTime());
//			return p.getPath();
//		}
//		else {
//			p.getPath().setTime(time2);
//			System.out.println(p.getPath().getTime());
//			return p.getPath();
//		}
//	}
//
//
//	/**
//	 * function gives us the closest fruit to the packman
//	 * @param packman packman 
//	 * @param fruits fruits
//	 * @return distance
//	 */
//	public Path faster_distance_one_Packman (Packman packman, ArrayList<Fruit> fruits) {
//		if (fruits.isEmpty()) 
//			return packman.getPath();
//
//		Fruit closeFruit = TheCloserFurit(packman,fruits);
//		packman.getPath().getFruits().add(closeFruit);
//		packman.setPackman(closeFruit.getGps_point());
//		fruits.remove(index(closeFruit, fruits));
//		return faster_distance_one_Packman(packman,fruits);
//	}
//
//	/**
//	 * function gives us the distance between one packman and ea fruit
//	 * @param packman packman
//	 * @param fruits fruits
//	 * @return path
//	 */
//	public Path distance_Algorithim(Packman packman, ArrayList<Fruit> fruits) {
//
//		ArrayList<Double> sorted_paths = new ArrayList<>();
//		Path path = new Path();
//		for (int i = 0; i < fruits.size(); i++) {
//			double tmp = map.distance3D(packman.getPackman(), 
//					fruits.get(i).getGps_point());
//			sorted_paths.add(tmp);
//		}
//		sorted_paths.sort(comp);;
//		double temp;
//		for (int i = 0; i < sorted_paths.size(); i++) {
//			for (int j = 0; j < fruits.size(); j++) {
//				temp = map.distance3D(packman.getPackman(),
//						fruits.get(j).getGps_point());
//				if(temp == sorted_paths.get(i)) {
//					path.getFruits().add(fruits.get(j));
//					break;
//				}
//			}
//		}
//		return path;
//	}
//
//	
///**
// * function to get every packman the best path for him
// * @return path
// */
//	public ArrayList<Packman> multiPackmen (){
//		Path path = new Path();
//		ArrayList<Fruit> fruits = this.fruits;
//		ArrayList<Packman> packmen = this.Packmans;
//		ArrayList<Packman> answer = new ArrayList<>();
//		ArrayList<Packman> temp_answer = new ArrayList<>();
//
//		for (int i = 0; i < packmen.size(); i++) {
//			Packman p = new Packman(packmen.get(i).getPackman(),packmen.get(i).getSpeed(), packmen.get(i).getRadius());
//			answer.add(p);
//		}
//		packmen = multi_Algorithim(packmen,fruits);
//		for (int i = 0; i < packmen.size(); i++) {
//			packmen.get(i).setPackman(answer.get(i).getPackman());
//		}
//		Path temp_path = new Path();
//		Path path2 = new Path();
//		for (int i = 0; i < packmen.size(); i++) {
//			temp_answer.add(new Packman(packmen.get(i)));
//			temp_answer.get(i).getPath().setFruits(packmen.get(i).getPath().getFruits());	
//		}
//		for (int i = 0; i < temp_answer.size(); i++) {
//			temp_path = distance_Algorithim(temp_answer.get(i),temp_answer.get(i).getPath().getFruits());
//		}
//
//		double time = path.totalTime_Path(packmen.get(0));
//		double temp = 0;
//
//		for (int i = 1; i < packmen.size(); i++) {
//			temp = path.totalTime_Path(packmen.get(i));
//			if (temp > time) time = temp;
//		}
//		System.out.println("finishing time: "+time);
//		return packmen;
//	}
//
//
//	private ArrayList<Packman> multi_Algorithim (ArrayList<Packman> packmen , ArrayList<Fruit>fruits) {
//		Path path = new Path();
//		if(fruits.isEmpty()) return packmen;
//
//		int random = (int)(Math.random()*packmen.size());
//		Packman Packman = packmen.get(random);
//		Fruit near_fruit = TheCloserFurit(packmen.get(random),fruits);
//		double best_time = path.time_P_F(packmen.get(random),near_fruit);
//		Packman temp_Packman;
//		Fruit temp_fruit;
//		double temp_time = 0;
//		for (int i = 0; i < packmen.size(); i++) {
//			temp_Packman = packmen.get(i);
//			temp_fruit = TheCloserFurit(packmen.get(i),fruits);
//			temp_time = path.time_P_F(packmen.get(i),temp_fruit);
//
//			if (temp_time < best_time) {
//				Packman = temp_Packman;
//				best_time = temp_time;
//				near_fruit = temp_fruit;
//			}
//		}
//		Packman.getPath().getFruits().add(near_fruit);
//		Packman.setPackman(near_fruit.getGps_point());
//		fruits.remove(index(near_fruit, fruits));
//		return multi_Algorithim(packmen ,fruits);
//	}
//
//
//	double fast_Speed(Packman packman ,ArrayList<Fruit> fruits ) {
//		Path path = new Path();
//		double best_time = path.time_P_F(packman, fruits.get(0));
//		double temp_time = 0;
//
//		for (int i = 1; i < fruits.size(); i++) {
//			temp_time = path.time_P_F(packman, fruits.get(i));
//			if(temp_time <best_time) {
//				best_time = temp_time;
//			}
//		}
//		return best_time;
//	}
//
//	/**
//	 * function to give the nearest fruit of a packman
//	 * @param packman packman
//	 * @param fruits fruits
//	 * @return fruit
//	 */
//	public Fruit TheCloserFurit(Packman packman,ArrayList<Fruit> fruits) {
//		Path path = new Path();
//		double best_time = path.time_P_F(packman,fruits.get(0));
//		Fruit nearest_fruit = fruits.get(0);
//		double temp_time = 0;
//
//		for (int i = 1; i < fruits.size(); i++) {
//			temp_time = path.time_P_F(packman, fruits.get(i));
//			if(temp_time < best_time)	{
//				best_time = temp_time;
//				nearest_fruit = fruits.get(i);
//			}	
//		}
//		return nearest_fruit;
//	}
//
//	/**
//	 * help function
//	 * @param furit fruit
//	 * @param fruits list of fruits
//	 * @return index of fruit
//	 */
//	public int index(Fruit furit , ArrayList<Fruit> fruits) {
//		for (int i = 0; i < fruits.size(); i++) {
//			if(furit.equals(fruits.get(i))) {
//				return i;
//			}
//		}
//		return -1;
//	}
//
//}





package Game;

import java.util.ArrayList;

import Geom.Point3D;


public class ShortestPathAlgo {

	ArrayList<Fruit> fruits = new ArrayList<>(); // Arraylist of fruit
	ArrayList<Packman> Packmans = new ArrayList<>();//Arraylist of Packman 
	Player player = new Player(new Point3D(0,0,0),1,1);
	Map map = new Map();// create a Map object


	/**
	 * Contractor of ShortestPathAlgo Who receives Game Object
	 * @param game Object Game receiv 
	 */
	public ShortestPathAlgo(Game game) {	


		ArrayList<Fruit> clone = new ArrayList<Fruit>(game.fruits.size());  for (Fruit item : game.fruits) clone.add(item);
		this.fruits = clone;	//Create a new fruit for not to overwrite Game data later
		this.Packmans = game.packmans;
		this.player = game.player;
	}

	/**
	 * algorithim for one Packman
	 * @param pack
	 * @return
	 */
	public Path singalPackman(Packman pack){

		ArrayList<Fruit> fruits = new ArrayList<>();
		fruits=pack.getPath().getFruits();

		Packman tmp_Packman = new Packman(pack.getPackman(),pack.getSpeed(),pack.getRadius());
		Path distance = distance_Algorithim(tmp_Packman,fruits);
		Path path = faster_distance_one_Packman(tmp_Packman, fruits);

		double time1 = distance.totalTime_Path(tmp_Packman);
		double time2 = path.totalTime_Path(tmp_Packman);
		if(time1 < time2) {
			distance.setTime(time1);
			return distance;
		}
		else {
			path.setTime(time2);
			return path;
		}
	}

	/**
	 * function gives us the closest fruit to the packman
	 * @param packman
	 * @param fruits
	 * @return
	 */
	public Path faster_distance_one_Packman (Packman packman, ArrayList<Fruit> fruits) {

		if (fruits.isEmpty())
			return packman.getPath();
		Fruit close_F = TheCloserFurit(packman,fruits);  
		packman.getPath().getFruits().add(close_F);
		packman.setPackman(close_F.getGps_point());
		fruits.remove(index(close_F, fruits));
		return faster_distance_one_Packman(packman,fruits);
	}
	/**
	 * function gives us the distance between one packman and ea fruit
	 * @param packman
	 * @param fruits
	 * @return
	 */
	public Path distance_Algorithim(Packman packman, ArrayList<Fruit> fruits) {

		ArrayList<Double> sorted_path = new ArrayList<>();
		Path answer = new Path();

		for (int i = 0; i < fruits.size(); i++) {
			double temp = map.distance3D(packman.getPackman(), fruits.get(i).getGps_point());
			sorted_path.add(temp);
		}
		double tmp;
		for(double distance : sorted_path) {
			for (int j = 0; j < fruits.size(); j++) {
				tmp = map.distance3D(packman.getPackman(), fruits.get(j).getGps_point());
				if(tmp == distance) {
					answer.getFruits().add(fruits.get(j));
					break;
				}
			}
		}
		return answer;
	}

	public ArrayList<Packman> multiPackmen(){

		Path path = new Path();
		ArrayList<Fruit> fruits = this.fruits;
		ArrayList<Packman> answer = new ArrayList<>();
		ArrayList<Packman> packmen = this.Packmans;

		for (int i = 0; i < packmen.size(); i++) {
			Packman p = new Packman(packmen.get(i).getPackman(),packmen.get(i).getSpeed(), packmen.get(i).getRadius());
			answer.add(p);
		}
		packmen = multi_Algorithim(packmen,fruits);
		for (int i = 0; i < packmen.size(); i++)
			packmen.get(i).setPackman(answer.get(i).getPackman());

		for(Packman packman : packmen) {
			Path packman_Path = new Path();
			packman_Path = packman.getPath();
		}
		double max_Path = path.totalTime_Path(packmen.get(0));
		double tmp = 0;
		for (int i = 1; i < packmen.size(); i++) {
			tmp = path.totalTime_Path(packmen.get(i));
			if (tmp > max_Path) {
				max_Path = tmp;
			}
		}
		System.out.println("finishing time: "+max_Path);
		return packmen;
	}



	private ArrayList<Packman> multi_Algorithim (ArrayList<Packman> packmans , ArrayList<Fruit>fruits) {

		Path path = new Path();
		if(fruits.isEmpty()) return packmans;
		Packman packman = packmans.get(0);
		Fruit close_fruit = TheCloserFurit(packmans.get(0),fruits);
		double fast_time = path.time_P_F(packmans.get(0),close_fruit);

		Packman tmp_P;
		Fruit tmp_F;
		double tmp_T = 0;
		for (int i = 0; i < packmans.size(); i++) {
			tmp_P = packmans.get(i);
			tmp_F = TheCloserFurit(packmans.get(i),fruits);
			tmp_T = path.time_P_F(packmans.get(i),tmp_F);

			if (tmp_T < fast_time) {
				packman = tmp_P;
				fast_time = tmp_T;
				close_fruit = tmp_F;
			}
		}

		packman.getPath().getFruits().add(close_fruit);
		packman.setPackman(close_fruit.getGps_point());
		fruits.remove(index(close_fruit, fruits));
		return multi_Algorithim(packmans ,fruits);
	}

	double fast_Speed(Packman packman ,ArrayList<Fruit> fruits ) {
		Path path = new Path();
		double fast_time = path.time_P_F(packman, fruits.get(0));
		double tmp = 0;

		for (int i = 1; i < fruits.size(); i++) {
			tmp = path.time_P_F(packman, fruits.get(i));
			if(tmp <fast_time) 
				fast_time = tmp;
		}
		return fast_time;
	}

	/**
	 * function to give the nearest fruit of a packman
	 * @param packman
	 * @param fruits
	 * @return
	 */
	public Fruit TheCloserFurit(Packman packman,ArrayList<Fruit> fruits) {

		Path path = new Path();
		double fast_time = path.time_P_F(packman,fruits.get(0));
		Fruit close = fruits.get(0);
		double tmp = 0;
		for (int i = 1; i < fruits.size(); i++) {
			tmp = path.time_P_F(packman, fruits.get(i));
			if(tmp < fast_time)	{
				fast_time = tmp;
				close = fruits.get(i);
			}	
		}
		return close;
	}

	/**
	 * helping Function
	 * @param furit
	 * @param fruits
	 * @return
	 */
	public int index(Fruit furit , ArrayList<Fruit> fruits) {
		for (int i = 0; i < fruits.size(); i++) {
			if(furit.equals(fruits.get(i)))
				return i;
		}
		return -1;
	}





}
