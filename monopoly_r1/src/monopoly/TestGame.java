package monopoly;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGame {
	
	private Game game;
	
	@Test
	/**
	 * Cerco di creare una partita con più di 8 giocatori 
	 */
	public void tooManyPlayers() {
		boolean controlloTest;
		game = new Game(9);
		controlloTest = game.startGame();
		assertFalse(controlloTest);
	}
	
	@Test
	/**
	 * Cerco di creare una partita con meno di 2 giocatori 
	 */
	public void tooFewPlayers() {
		boolean controlloTest;
		game = new Game(1);
		controlloTest = game.startGame();
		assertFalse(controlloTest);
	}
	
	@Test
	/**
	 * Verifico che alla creazione di 100 partite l'ordine dei 2 giocatori partecipanti
	 * sia diverso
	 */
	public void veryfyShuffle() {
		boolean controlloTest = false;
		int order = 0;
		Player one = null;
		Player two = null;
		for(int i = 0; i<100; i++){
			game = new Game(2);
			game.startGame();
			Player p[] = game.getPlayers();
			if(i==0){
				one = p[0];
			}else{
				two = p[1];
				if(one.equals(two)){
					//l'ordine non è cambiato, continuo il test
				}else{
					//l'ordine è cambiato, il test è concluso
					controlloTest = true;
					break;
				}
			}
		}
		assertTrue(controlloTest);
	}

}
