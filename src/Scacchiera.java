import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Scacchiera extends JFrame{
	
	private static final int NUM = 8;
	private Casella[][] griglia;
	
	public Scacchiera(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(NUM*Casella.DIMENSIONE+5, NUM*Casella.DIMENSIONE + Casella.DIMENSIONE);
		setResizable(false);
		setLocation(10,10);
		setVisible(true);
		
		
		try {
			JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("./grafica/scacchiera.png"))));
			background.setLocation(0, 0);
			background.setAlignmentY(TOP_ALIGNMENT);
    		setContentPane(background);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	//pack();
		inizializzaGriglia();
    	setVisible(true);
	}
	
	
	private void inizializzaGriglia() {
		griglia = new Casella[NUM][NUM];
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j] = new Casella(i,j);
				getContentPane().add(griglia[i][j]);
			}
		}
		
	}
	
	private void ricominciaPartita(){
		
	}


	public static void main(String[] argv){
		new Scacchiera();
		System.out.println("Ciao");
	}
	
}
