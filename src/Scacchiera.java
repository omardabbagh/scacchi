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
		setSize(NUM*Casella.DIMENSIONE+5, NUM*Casella.DIMENSIONE + Casella.DIMENSIONE/2+4);
		setResizable(false);
		setLocation(10,10);
		setVisible(true);
		
		
		try {
			ImageIcon immagine = new ImageIcon(ImageIO.read(new File("./grafica/scacchiera.png")));
			JLabel sfondo = new JLabel(immagine);
			sfondo.setVisible(true);
			setContentPane(sfondo);
			
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	//pack();
		inizializzaGriglia();
    	setVisible(true);
	}
	
	
	private void inizializzaGriglia() {
		griglia = new Casella[NUM][NUM];
		griglia[0][0] = new Torre(Colore.NERO, 0,0);
		griglia[0][1] = new Cavallo(Colore.NERO, 1,0);
		griglia[0][2] = new Alfiere(Colore.NERO, 2,0);
		griglia[0][3] = new Regina(Colore.NERO, 3,0);
		griglia[0][4] = new Re(Colore.NERO, 4,0);
		griglia[0][5] = new Alfiere(Colore.NERO, 5,0);
		griglia[0][6] = new Cavallo(Colore.NERO, 6,0);
		griglia[0][7] = new Torre(Colore.NERO, 7,0);
		for(int i = 0; i < NUM; i++){
			griglia[1][i] = new Pedone(Colore.NERO, i,1);
//			getContentPane().add(griglia[1][i]);
		}
		for(int i = 2; i < NUM-2; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j] = new Casella(j,i);
//				getContentPane().add(griglia[i][j]);
			}
		}
		
		griglia[7][0] = new Torre(Colore.BIANCO, 0,7);
		griglia[7][1] = new Cavallo(Colore.BIANCO, 1,7);
		griglia[7][2] = new Alfiere(Colore.BIANCO, 2,7);
		griglia[7][3] = new Regina(Colore.BIANCO, 3,7);
		griglia[7][4] = new Re(Colore.BIANCO, 4,7);
		griglia[7][5] = new Alfiere(Colore.BIANCO, 5,7);
		griglia[7][6] = new Cavallo(Colore.BIANCO, 6,7);
		griglia[7][7] = new Torre(Colore.BIANCO, 7,7);
		
		for(int i = 0; i < NUM; i++){
			griglia[6][i] = new Pedone(Colore.BIANCO, i,6);
//			getContentPane().add(griglia[6][i]);
		}
		

		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
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
