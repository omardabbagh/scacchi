import java.awt.Color;
import javax.swing.JButton;

public class Casella extends JButton{// dvrebbe essere abstract
	
	public static final int DIMENSIONE = 50;
	public int x;
	public int y;
	
	public Casella(int x, int y){
		super();
		setBounds(x * DIMENSIONE, y * DIMENSIONE, DIMENSIONE, DIMENSIONE);
		this.x = x;
		this.y = y;
		this.setBackground(Color.BLUE);
		setOpaque(false);
		//setContentAreaFilled(false);
		//setBorderPainted(false);
	}
}
