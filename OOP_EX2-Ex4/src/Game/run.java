package Game;

import javax.swing.JFrame;

import GUI.MyFrame;



public class run {
	
	private static void run() {
		// TODO Auto-generated method stub
		MyFrame window = new MyFrame();
		window.setVisible(true);	
		window.setSize(900,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		run();
	}
}