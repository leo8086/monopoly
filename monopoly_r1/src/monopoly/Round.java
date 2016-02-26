package monopoly;

import java.util.Random;

public class Round {
	
	private int rounds;
	private int roundsElapsed;
	
	/**
	 * @param numb il numero di turni dopo i quali la partita termina
	 */
	public Round(int numb){
		rounds = numb;
		roundsElapsed = 0;
	}
	
	/**
	 * Tira due dadi e muove il giocatore
	 * @param player il giocatore che sta tirando i dadi
	 * @return la posizione risultante del giocatore che ha tirato i dadi
	 */
	public int playerRoll(Player player){
		Random rando = new Random();
		int dice1 = rando.nextInt(6)+1;
		int dice2 = rando.nextInt(6)+1;
		int roll = dice1+dice2;
		System.out.print("Player "+player.getName()+" rolled "+roll+" - ");
		return player.playerMovement(roll);
	}
	
	/**
	 * @return il numero di round
	 */
	public int getRounds(){
		return roundsElapsed;
	}
	
	/**
	 * incrementa il numero di round passati dall'inizio
	 * @return ritorna true se il gioco continua, ritorna false se il gioco è finito
	 */
	public boolean roundElapse(){
		roundsElapsed++;
		if(roundsElapsed == rounds)
			return false;
		else
			return true;
	}
}
