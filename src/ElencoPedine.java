import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ElencoPedine extends JFrame{
	private ImageIcon reginaBianco;
	private ImageIcon alfiereBianco;
	private ImageIcon torreBianco;
	private ImageIcon cavalloBianco;

	private ImageIcon reginaNero;
	private ImageIcon alfiereNero;
	private ImageIcon torreNero;
	private ImageIcon cavalloNero;

	private JButton alfiere = new JButton();
	private JButton cavallo = new JButton();
	private JButton regina = new JButton();
	private JButton torre = new JButton();

	ElencoPedine(final Colore colore, final Scacchiera scacchiera, final Pedina p){
		super("Scegli pedina");
		try {
			reginaBianco = new ImageIcon(ImageIO.read(new File("./grafica/regina_b.png")));
			alfiereBianco = new ImageIcon(ImageIO.read(new File("./grafica/alfiere_b.png")));
			torreBianco = new ImageIcon(ImageIO.read(new File("./grafica/torre_b.png")));
			cavalloBianco = new ImageIcon(ImageIO.read(new File("./grafica/cavallo_b.png")));

			reginaNero = new ImageIcon(ImageIO.read(new File("./grafica/regina_n.png")));
			alfiereNero = new ImageIcon(ImageIO.read(new File("./grafica/alfiere_n.png")));
			torreNero = new ImageIcon(ImageIO.read(new File("./grafica/torre_n.png")));
			cavalloNero = new ImageIcon(ImageIO.read(new File("./grafica/cavallo_n.png")));
		} catch (IOException ex) {
		}
		
		if (colore.equals(Colore.NERO)) {
			alfiere.setIcon(alfiereNero);
			cavallo.setIcon(cavalloNero);
			regina.setIcon(reginaNero);
			torre.setIcon(torreNero);
		} else {
			alfiere.setIcon(alfiereBianco);
			cavallo.setIcon(cavalloBianco);
			regina.setIcon(reginaBianco);
			torre.setIcon(torreBianco);
		}
		
		alfiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scacchiera.sostituisciPedina(p,(Pedina) (new Alfiere(colore, p.riga, p.colonna)));
				dispose();
			}
		});
		cavallo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scacchiera.sostituisciPedina(p,(Pedina) new Cavallo(colore, p.riga, p.colonna));
				dispose();
			}
		});
		regina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scacchiera.sostituisciPedina(p,(Pedina) new Regina(colore, p.riga, p.colonna));
				dispose();
			}
		});
		torre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scacchiera.sostituisciPedina(p, (Pedina) new Torre(colore, p.riga, p.colonna));
				dispose();
			}
		});
		JPanel pannello = new JPanel();
		pannello.setLayout(new GridLayout(4,1));
		pannello.add(alfiere);
		pannello.add(cavallo);
		pannello.add(regina);
		pannello.add(torre);
		add(pannello);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

}
