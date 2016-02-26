package monopoly;

import java.util.Random;

/**
 * Classe principale la cui istanza rappresenta una partita. Contiene tutte le informazioni
 * relative alla partita che si sta giocando: giocatori partecipanti, numero di round,
 * giocatore corrente, ordine dei giocatori, numero di giocatori.
 * I giocatori hanno dei nomi predefiniti dato che le pedine del monopoly sono sempre le stesse
 * @author Leonardo
 *
 */
public class Game {
	
	private int nPlayers;
	private Player players[];
	private Round round;
	private int orderPointer;
	private boolean gameEnded;
	private String names[] = new String[]{"Horse","Car","Cat","Racecar","Battleship",
											"Top hat","Thimble","Dog"};
	/**
	 * Costruttore
	 * @param n il numero di giocatori partecipanti
	 */
	public Game(int n){
		nPlayers = n;
		round = new Round(20);
		orderPointer = 0;
		gameEnded = false;
	}
	
	/**
	 * Crea la partita, effettua i controlli, mescola il vettore dei giocatori e assegna l'ordine
	 * @return true se l'operazione è andata a buon fine, false altrimenti
	 */
	public boolean startGame(){
		if(playersCheck()==false){
			return false;
		}
		players = shufflePlayers();
		return true;
	}
	
	/**
	 * Controlla che non ci siano più o meno giocatori del dovuto e assegna i nomi
	 * predefiniti ai giocatori 
	 */
	private boolean playersCheck(){
		if(nPlayers<2){
			System.out.println("il numero minimo di giocatori è 2");
			return false;
		}else if(nPlayers>8){
			System.out.println("il numero massimo di giocatori è 8");
			return false;
		}
		
		players = new Player[nPlayers];
		for(int num = 0; num < nPlayers; num++){
			players[num] = new Player(names[num]);
		}
		return true;
	}
	
	/**
	 * Mischia il vettore contenente i giocatori e ne assegna l'ordine
	 * @return ritorna il vettore mescolato
	 */
	private Player[] shufflePlayers(){
		Random rando = new Random();
		for (int i = players.length - 1; i > 0; i--){
			int indice = rando.nextInt(i + 1);
			Player p = players[indice];
			players[indice] = players[i];
			players[i] = p;
		}
		for (int a = 0; a < players.length; a++){
			players[a].setOrder(a);
		}
		return players;
	}
	
	/**
	 * il round di un giocatore
	 */
	public int playerTurn(){
		int position = round.playerRoll(players[orderPointer]);
		System.out.println("Player "+players[orderPointer].getName()+" in position "+position);
		orderPointer++;
		if(orderPointer > players.length -1){ //tutti i player hanno giocato il turno
			orderPointer = 0;
			if(round.roundElapse()==false){
				//il gioco termina
				gameEnded = true;
				System.out.println("gioco terminato");
			}
		}
		return position;
	}
	
	/**
	 * @return ritorna il vettore dei giocatori
	 */
	public Player[] getPlayers(){
		return players;
	}
	
	/**
	 * @return ritorna true se la partita è terminata
	 */
	public boolean isOver(){
		return gameEnded;
	}
	
	/**
	 * @return ritorna i round giocati
	 */
	public int roundsPlayed(){
		return round.getRounds();
	}
	
	/**
	 * @return il giocatore corrente
	 */
	public Player currentPlayer(){
		return players[orderPointer];
	}
	
}
