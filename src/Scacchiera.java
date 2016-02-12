import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Scacchiera extends JFrame{

	private static final int NUM = 8;
	private Casella[][] griglia;
	ActionListener action;

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

		inizializzaGriglia();
		setVisible(true);

	}


	private void inizializzaGriglia() {
		griglia = new Casella[NUM][NUM];
		griglia[0][0] = new Torre(Colore.NERO, 0,0);
		griglia[0][1] = new Cavallo(Colore.NERO, 0,1);
		griglia[0][2] = new Alfiere(Colore.NERO, 0,2);
		griglia[0][3] = new Regina(Colore.NERO, 0,3);
		griglia[0][4] = new Re(Colore.NERO, 0,4);
		griglia[0][5] = new Alfiere(Colore.NERO, 0,5);
		griglia[0][6] = new Cavallo(Colore.NERO, 0,6);
		griglia[0][7] = new Torre(Colore.NERO, 0,7);
		for(int i = 0; i < NUM; i++){
			griglia[1][i] = new Pedone(Colore.NERO, 1,i);
			//			getContentPane().add(griglia[1][i]);
		}
		for(int i = 2; i < NUM-2; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j] = new Casella(i,j);
				//				getContentPane().add(griglia[i][j]);
			}
		}

		griglia[7][0] = new Torre(Colore.BIANCO, 7,0);
		griglia[7][1] = new Cavallo(Colore.BIANCO, 7,1);
		griglia[7][2] = new Alfiere(Colore.BIANCO, 7,2);
		griglia[7][3] = new Regina(Colore.BIANCO, 7,3);
		griglia[7][4] = new Re(Colore.BIANCO, 7,4);
		griglia[7][5] = new Alfiere(Colore.BIANCO, 7,5);
		griglia[7][6] = new Cavallo(Colore.BIANCO, 7,6);
		griglia[7][7] = new Torre(Colore.BIANCO, 7,7);

		for(int i = 0; i < NUM; i++){
			griglia[6][i] = new Pedone(Colore.BIANCO, 6,i);
			//			getContentPane().add(griglia[6][i]);
		}

		action = new ActionListener() {
			private Pedina pedinaDaMuovere;
			private Casella casellaDiArrivo;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pedinaDaMuovere == null){
					if(e.getSource() instanceof Pedina){
						pedinaDaMuovere = (Pedina)e.getSource();
						System.out.println("Source "+pedinaDaMuovere.riga + " " + pedinaDaMuovere.colonna);
					}
				}else{
					casellaDiArrivo = (Casella)e.getSource();
					int mossa = pedinaDaMuovere
							.mossePossibili(griglia)[casellaDiArrivo.riga][casellaDiArrivo.colonna];
					System.out.println(mossa);
					if(mossa > 0){
						getContentPane().remove(pedinaDaMuovere);
						getContentPane().remove(casellaDiArrivo);
						casellaDiArrivo.removeActionListener(action);

						griglia[casellaDiArrivo.riga][casellaDiArrivo.colonna] = pedinaDaMuovere;
						griglia[pedinaDaMuovere.riga][pedinaDaMuovere.colonna] = new Casella(pedinaDaMuovere.riga, pedinaDaMuovere.colonna);
						
						griglia[pedinaDaMuovere.riga][pedinaDaMuovere.colonna].addActionListener(action);

						getContentPane().add(griglia[pedinaDaMuovere.riga][pedinaDaMuovere.colonna]);

						pedinaDaMuovere.cambiaPosizione(casellaDiArrivo.riga, casellaDiArrivo.colonna);
						getContentPane().add(pedinaDaMuovere);

						Scacchiera.this.repaint();
					}
					pedinaDaMuovere = null;
				}
			}
		};

		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j].addActionListener(action);
				getContentPane().add(griglia[i][j]);
			}
		}
	}
	class GestoreMossa{
		private Pedina daMuovere;

		void muovi(Casella casella){
			if(daMuovere == null){
				if(casella instanceof Pedina)
					daMuovere = (Pedina)casella;
			}else{

			}
		}
	}

	private void ricominciaPartita(){

	}


	public static void main(String[] argv){
		new Scacchiera();
	}




}
