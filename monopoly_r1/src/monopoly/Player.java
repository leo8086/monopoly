package monopoly;

public class Player {
	
	private String playerName;
	private int order;
	private int position;
	private int round;
	
	public Player(String name){
		playerName = name;
		position = 0;
		round = 0;
	}
	
	/**
	 * @param o il valore da assegnare alla variabile order, indica l'ordine del giocatore
	 */
	public void setOrder(int o){
		order = o;
	}
	
	/**
	 * @return ritorna l'ordine del giocatore
	 */
	public int getOrder(){
		return order;
	}
	
	/**
	 * @return ritorna la posizione del giocatore
	 */
	public int getPosition(){
		return position;
	}
	
	/**
	 * @return ritorna il numero di round giocati
	 */
	public int getRound(){
		return round;
	}
	
	/**
	 * @return ritorna il nome del giocatore
	 */
	public String getName(){
		return playerName;
	}
	
	/**
	 * muove il giocatore
	 * @param number il numero di caselle che il giocatore deve muoversi
	 * @return ritorna la posizione del giocatore dopo la mossa
	 */
	public int playerMovement(int number){
		round++;
		position = position + number;
		if(position > 39){
			position = position - 40;
		}
		return position;
	}
	
}
