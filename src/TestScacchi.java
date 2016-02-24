import org.junit.Assert;
import org.junit.Test;

/**
 * Classe per il test della logica del gioco
 * 
 * @author Omar Dabbagh
 *
 */

public class TestScacchi{

	Scacchiera s=new Scacchiera(true);


	@Test
	public void testMossePossibiliPedone() {
		Pedone p=(Pedone) s.griglia[3][0];
		int[][] mosse=p.mossePossibili(s.griglia);
		Assert.assertTrue(mosse[2][0]==1 && mosse[2][1]==2);
	}

	@Test
	public void testMossePossibiliAlfiere() {
		Alfiere a=(Alfiere) s.griglia[5][6];
		int mosse[][]=a.mossePossibili(s.griglia);
		Assert.assertTrue(mosse[6][5]==1);
		Assert.assertTrue(mosse[6][7]==1);
		for(int i=0;i<5;i++){
			Assert.assertTrue(mosse[i][i+1]==1);
		}
		Assert.assertTrue(mosse[4][7]==2);
	}

	@Test
	public void testMossePossibiliCavallo() {
		Cavallo t=(Cavallo) s.griglia[5][0];
		int mosse[][]=t.mossePossibili(s.griglia);
		Assert.assertTrue(mosse[3][1]==1);
		Assert.assertTrue(mosse[7][1]==1);
	}


	@Test
	public void testMossePossibiliTorre() {
		Torre t=(Torre) s.griglia[4][7];
		int mosse[][]=t.mossePossibili(s.griglia);
		for(int i=0;i<3;i++){
			Assert.assertTrue(mosse[i][7]==1);
		}
		for(int i=5;i<7;i++){
			Assert.assertTrue(mosse[i][7]==1);
		}
		for(int i=4;i<6;i++){
			Assert.assertTrue(mosse[4][i]==1);
		}
	}


	@Test
	public void testMossePossibiliRegina() {
		Regina t=(Regina) s.griglia[4][3];
		int mosse[][]=t.mossePossibili(s.griglia);
		for(int i=1;i<7;i++){
			if(i!=4)
				Assert.assertTrue(mosse[i][3]==1);
		}
		for(int i=0;i<7;i++){
			if(i!=3)
				Assert.assertTrue(mosse[4][i]==1);
		}
		Assert.assertFalse(mosse[4][7]==1);
	}
	
	@Test
	public void testMossePossibiliRe() {
		Re t=(Re) s.griglia[0][3];
		int mosse[][]=t.mossePossibili(s.griglia);
		Assert.assertTrue(mosse[0][2]==1);
		Assert.assertTrue(mosse[0][4]==1);
		Assert.assertTrue(mosse[1][2]==1);
		Assert.assertTrue(mosse[1][3]==1);
		Assert.assertTrue(mosse[1][4]==1);
	}

	@Test
	public void testScacco() {
		Scacchiera scacchiera = new Scacchiera(true);

		Assert.assertTrue(scacchiera.inScacco() == Scacchiera.SCACCO_BIANCO);	//scacco a re bianco

		scacchiera.mossa((Pedina)scacchiera.griglia[7][3], scacchiera.griglia[7][4]);
		Assert.assertTrue(scacchiera.inScacco() == 0);	//nessuno scacco

		scacchiera.mossa((Pedina)scacchiera.griglia[0][3], scacchiera.griglia[1][3]);
		scacchiera.mossa((Pedina)scacchiera.griglia[5][6], scacchiera.griglia[6][5]);
		scacchiera.mossa((Pedina)scacchiera.griglia[1][3], scacchiera.griglia[2][3]);
		scacchiera.mossa((Pedina)scacchiera.griglia[6][5], scacchiera.griglia[5][6]);
		Assert.assertTrue(scacchiera.inScacco() == Scacchiera.SCACCO_NERO);	//scacco a re nero
	}


	@Test
	public void testScaccoMatto() {
		Scacchiera scacchiera = new Scacchiera(true);

		scacchiera.mossa((Pedina)scacchiera.griglia[7][3], scacchiera.griglia[6][2]);
		scacchiera.mossa((Pedina)scacchiera.griglia[4][7], scacchiera.griglia[6][7]);
		scacchiera.mossa((Pedina)scacchiera.griglia[6][2], scacchiera.griglia[5][1]);
		scacchiera.mossa((Pedina)scacchiera.griglia[6][7], scacchiera.griglia[6][1]);
		Assert.assertTrue(scacchiera.inScaccoMatto());
	}


}
