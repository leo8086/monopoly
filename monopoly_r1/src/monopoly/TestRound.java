package monopoly;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing della classe Round
 * @author Leonardo
 *
 */
public class TestRound {
	
	private Game game;

	@Test
	/**
	 * controllo che in una partita vengano giocati esattamente 20 round
	 * e che ogni giocatore abbia giocato esattamente 20 round
	 */
	public void roundsNumber() {
		boolean controlloTest = true;
		game = new Game(3);
		if(game.startGame() == false)
			controlloTest = false;
		else{
			while(game.isOver()==false){
				game.playerTurn();
			}
			if(game.roundsPlayed() != 20){ //controllo se i round giocati sono stati 20
				controlloTest = false;
			}else{ //controllo che i round giocati di singoli giocatori siano stati 20
				for(int i = 0; i < game.getPlayers().length; i++){
					if(game.getPlayers()[i].getRound() != 20){
						controlloTest = false;
						break;
					}
				}
			}
		}
		assertTrue(controlloTest);
	}
	
	@Test
	/**
	 * controllo che durante una partita completa l'ordine dei giocatori rimane sempre lo stesso
	 */
	public void playersOrder(){
		boolean controlloTest = true;
		game = new Game(2);
		if(game.startGame() == false)
			controlloTest = false;
		else{
			Player initialOrder[] = game.getPlayers();
			while(game.isOver()==false && controlloTest == true){
				for(int i =0; i< 2; i++){
					if(game.currentPlayer().equals(initialOrder[i])){
						//se il giocatore corrente è uguale al corrispondente nel vettore dell'ordine iniziale
						game.playerTurn();
					}else{
						controlloTest = false;
						break;
					}
				}
			}
		}
		assertTrue(controlloTest);
	}

}
