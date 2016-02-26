package monopoly;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test del movimento del giocatore, classe Player
 * @author Leonardo
 *
 */
public class TestPlayerMovement {
	
	private Player player = new Player("Horse");
	private Player player2 = new Player("Car");
	
	@Test
	/**
	 * un player nella posizione 0 che tira 7 finisce nella posizione 7
	 */
	public void simpleMove() {
		boolean controlloTest;
		int posizione = player.playerMovement(7);
		if(posizione != 7)
			controlloTest = false;
		else
			controlloTest = true;
		assertTrue(controlloTest);
	}
	
	@Test
	/**
	 * un player in posizione 39 che tira 6 finisce nella posizione 5
	 */
	public void passaDalVia() {
		boolean controlloTest;
		player2.playerMovement(39); //lo posiziono nella casella 39
		int posizione = player2.playerMovement(6);
		if(posizione != 5)
			controlloTest = false;
		else
			controlloTest = true;
		assertTrue(controlloTest);
	}

}
