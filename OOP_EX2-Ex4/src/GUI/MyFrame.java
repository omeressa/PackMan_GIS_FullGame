package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Coords.MyCoords;
import Game.Fruit;
import Game.Game;
import Game.Ghost;
import Game.Packman;
import GIS.Path;
import Game.Player;
import Game.Box;
import Game.Map;
import Game.Algo;


import Geom.Point3D;
import Robot.Play;





public class MyFrame extends JFrame implements MouseListener , KeyListener {

    public boolean click = false,one_playing=false,verefie_player=false;
	int isGamer=0;
	double num;

	public BufferedImage image;
	public BufferedImage packman_image;
	public BufferedImage fruits_image;
	public BufferedImage ghost_image;
	public BufferedImage box_image;
	public BufferedImage player_image;
	Image Image;
	public  Graphics graph;

	double radius = 1;
	int speed = 1;

	public  ArrayList<Packman> packmans = new ArrayList<>();
	public  ArrayList<Fruit> fruits = new ArrayList<>();
	public  ArrayList<Box> boxs = new ArrayList<>();
	public  ArrayList<Ghost> ghosts = new ArrayList<>();
	public ArrayList<Packman> tmp_arr=new ArrayList<>();
	public Game game=new Game(packmans, fruits,boxs,ghosts);
	public Map map = new Map();
	Path nearest_packman;
	private MyCoords coord = new MyCoords();

	public Play play;
	MenuBar menu = new MenuBar();

	Game tmp_game=new Game(packmans, fruits,boxs,ghosts);

	public MyFrame() {
		initGUI();		
		this.addMouseListener(this); 
		this.addKeyListener(this);
	}


	private void initGUI() {
		try {	
			image = ImageIO.read(new File(map.getPic())); } 
		catch (IOException e) { e.printStackTrace();	}	
		try {
			packman_image = ImageIO.read(new File("packman.png")); 
		}
		catch (IOException e) { e.printStackTrace();
		}
		try {	
			fruits_image = ImageIO.read(new File("fruit.jpg"));
		}
		catch (IOException e) { e.printStackTrace();
		}
		try {
			box_image = ImageIO.read(new File("box.jpg")); 
		}
		catch (IOException e) { e.printStackTrace();	
		}
		try {
			ghost_image = ImageIO.read(new File("ghost.png"));
		} 
		catch (IOException e) { e.printStackTrace();
		}
		try {
			player_image = ImageIO.read(new File("Player.jpg")); 
		}
		catch (IOException e) { e.printStackTrace();	
		}


		//adding menu options//
		
		Menu file_menu = new Menu("File"); 
		menu.add(file_menu);
		Menu opject_menu = new Menu("Game Opject"); 
		menu.add(opject_menu);
		Menu importing_menu=new Menu ("Import");
		menu.add(importing_menu);
		Menu more = new Menu("other");
		menu.add(more);


		MenuItem run_manual = new MenuItem("Run Manual");
		MenuItem run_auto= new MenuItem("Run Automatic");
		MenuItem resset = new MenuItem("Resset");
		file_menu.add(run_manual);
		file_menu.add(run_auto);
		file_menu.add(resset);

		MenuItem Player_manual = new MenuItem("Player (Manual)");	
		MenuItem read_csv_file = new MenuItem("read Csv file");
		opject_menu.add(Player_manual);
		importing_menu.add(read_csv_file);

		MenuItem exit = new MenuItem("Exit");	
		more.add(exit);

		this.setMenuBar(menu);



		//run1 action 
		run_manual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play.setIDs(342685898, 311326490);
				if (verefie_player==true) {
					if(game.player != null) {
						play.getBoard();
						isGamer = 4;
						click = true;
						play.start();
						Thread thread = new Thread(){
							ArrayList<String> board_data = play.getBoard();
							public void run(){ 
								while(play.isRuning()){ 
									try {
										sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();	
									}
									play.rotate(num);
									board_data = play.getBoard();
									String info = play.getStatistics();
									System.out.println(info);
									try {
										game.Create_Game(board_data);
									} 
									catch (IOException e1) {
										e1.printStackTrace();
									}
									repaint();
								}
							}
						};
						thread.start();
					}
				}
				else 
					JOptionPane.showMessageDialog(null,"EROR: first you need to enter a player ");
			}
		});

		//run2 action
		run_auto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verefie_player==true) 
					JOptionPane.showMessageDialog(null,"player will get random gps point ");
				play.setIDs(342685898, 311326490);
				click = false;
				int ran = (int)(Math.random()*game.fruits.size());
				System.out.println(game.fruits.get(ran).getGps_point().x());
				Point3D temp_point_locat=map.Pixel2Gps(game.fruits.get(ran).getGps_point().x(), game.fruits.get(ran).getGps_point().y());
				play.setInitLocation(temp_point_locat.x(),temp_point_locat.y());
				if(game.player != null) {
					play.getBoard();
					isGamer = 4;
					play.start();
					Thread thread = new Thread(){
						ArrayList<String> board_data = play.getBoard();
						public void run(){ 
							while(play.isRuning()){ 
								try {
									sleep(100);
								}
								catch (InterruptedException e) {
									e.printStackTrace();
								}
								play.rotate(num);
								board_data = play.getBoard();
								String info = play.getStatistics();
								System.out.println(info);
								try {
									tmp_game.Create_Game(board_data);
									Point3D covertedfromPixel2 = map.Pixel2Gps(game.player.getPlayer_point().x(), game.player.getPlayer_point().y());
									Point3D covertedfromPixel3 = map.Pixel2Gps(tmp_game.player.getPlayer_point().x(), tmp_game.player.getPlayer_point().y());
									Algo algo = new Algo(tmp_game);
									tmp_game.player.setPlayer_point(covertedfromPixel3);
									game.player.setPlayer_point(covertedfromPixel2);
									double theDir = algo.update_Game(tmp_game.player , num);
									num = theDir;
								} 
								catch (IOException e) {
									e.printStackTrace();	
								}
								try {
									game.Create_Game(board_data);
								} 
								catch (IOException e1) {
									e1.printStackTrace();
								}
								repaint();						
							}
						}
					};
					thread.start();
				}
			}
		});


		//resset action
		resset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				one_playing=false;
				radius = 1;
				speed = 1;
				new MenuBar();
				new Map();
				packmans = new ArrayList<>();
				fruits = new ArrayList<>();
				boxs = new ArrayList<>();
				ghosts = new ArrayList<>();
				game=new Game(packmans, fruits,boxs,ghosts);
				isGamer=0;
				tmp_arr=new ArrayList<>();
				verefie_player=false;
				nearest_packman=null;
				repaint();
			}
		});

		//player add action
		Player_manual.addActionListener  (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 2;
			}
		});

		//csv file read action
		read_csv_file.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setDialogTitle("choose file");
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				fileChooser.addChoosableFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().getPath())	;
					play = new Play(fileChooser.getSelectedFile().getPath());
					try {
						game = new Game(play);
						one_playing=true;
					}
					catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					isGamer = 4;
					game.setFile(fileChooser.getSelectedFile().getPath());
					repaint();
				}
			}
		});


		//exit action
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}


	public void paint(Graphics g) {
		if(graph==null){
			Image = createImage(5000,5000);
			graph = Image.getGraphics();
		}
		graph.drawImage(image, 8,50, this.getWidth()-17, this.getHeight()-60,null);
		double x1 = 0;
		double y1 = 0 ;
		double x2 = 0;
		double y2 = 0 ;
		if (isGamer!=0) {
			if(game.fruits.size() > 0) {
				for (int j=0; j<game.boxs.size(); j++) {
					x1=(game.boxs.get(j).getP1().x()*getWidth());
					y1=(game.boxs.get(j).getP1().y()*getHeight());
					x2=(game.boxs.get(j).getP2().x()*getWidth());
					y2=(game.boxs.get(j).getP2().y()*getHeight());	
					double width = x2-x1;
					double height = y2-y1;
					graph.drawImage(box_image, (int)x1,(int) y1,(int)width, (int)height, null);
				}
				for (int i=0; i<game.fruits.size(); i++){
					x1=(int)(game.fruits.get(i).getGps_point().x()*getWidth());
					y1=(int)(game.fruits.get(i).getGps_point().y()*getHeight());	
					graph.drawImage(fruits_image, (int)x1, (int)y1,20, 20, null);
				}
			}
			for (int j=0; j<game.packmans.size(); j++) {
				x1=(game.packmans.get(j).getPackman().x()*getWidth());
				y1=(game.packmans.get(j).getPackman().y()*getHeight());	
				graph.drawImage(packman_image, (int)x1,(int) y1,20, 20, null);
			}
			for (int j=0; j<game.Ghosts.size(); j++) {
				x1=(game.Ghosts.get(j).getPoint().x()*getWidth());
				y1=(game.Ghosts.get(j).getPoint().y()*getHeight());	
				graph.drawImage(ghost_image, (int)x1,(int) y1,20, 20, null);
			}
			if(game.player!=null){
				x1=(game.player.getPlayer_point().x()*getWidth());
				y1=(game.player.getPlayer_point().y()*getHeight());	
				graph.setColor(Color.cyan);
				graph.fillOval((int)x1,(int) y1, 10, 10);
			}
		}
		g.drawImage(Image, 0, 0, this);
	}


	@Override
//	public void mouseClicked(MouseEvent arg) {
//		double x_temp=arg.getX();
//		x_temp=x_temp/getWidth();
//		double y_temp=arg.getY();
//		y_temp=y_temp/getHeight();
//		Point3D point_return=new Point3D(x_temp, y_temp, 0);
//		Point3D covertedfromPixel = map.Pixel2Gps(x_temp, y_temp);
//		if(click == true) {
//			Point3D playerConert = map.Pixel2Gps(game.player.getPlayer_point().x(), game.player.getPlayer_point().y());
//			double finalnum = coord.myDir(covertedfromPixel,playerConert);
//			System.out.println(finalnum);
//			num = finalnum;
//			play.rotate(num);
//		}
//		if (isGamer==(1)){	
//			game.fruits.add(new Fruit(point_return,1));
//			System.out.println("Fruit "+covertedfromPixel.toString());
//			repaint();
//		}else if (isGamer==(-1)) {
//			game.packmans.add(new Packman(point_return, radius, speed));
//			System.out.println("Packman "+covertedfromPixel.toString());
//			repaint();
//		}else if(isGamer==3) {
//			game.Ghosts.add(new Ghost(point_return, radius, speed));
//			System.out.println("Ghost "+covertedfromPixel.toString());
//			repaint();
//		}else if(isGamer==2) {
//			game.player=new Player(point_return, speed,radius);
//			System.out.println("Player "+covertedfromPixel.toString());
//			if(one_playing==true)
//			{play.setInitLocation(covertedfromPixel.x(), covertedfromPixel.y());
//			verefie_player=true;
//			repaint();
//			}
//		}
//	}       

    public void mouseClicked(MouseEvent e)
    {
        Point3D clickLocation = new Point3D(e.getX(), e.getY(), 0);
        Point3D p = pointBeforeResize(clickLocation);
        Point3D inGPS = map.Pixel2Gps(p.x(),p.y());
        double angle = game.getPlayer().angelToMove(inGPS);
    }
	
    public Point3D pointBeforeResize(Point3D p)
    {
        int paneW = this.getWidth();
        int paneH = this.getHeight();
        int x = (int) (p.x() * 900 / paneW);
        int y = (int) (p.y() * 600 / paneH);
        return new Point3D(x, y, 0);
    }
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}




}