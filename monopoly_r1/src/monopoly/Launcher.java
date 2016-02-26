package monopoly;

/**
 * Gestisce le varie finestre di interfaccia grafica
 * @author Leonardo
 *
 */
public class Launcher {
	
	private static Board board = null;
	private static CreateGame preGame = null;
	private static Game game = null;
	
	/**
	 * Il main, chiama il metodo che crea l'interfaccia e resta in loop infinito
	 */
	public static void main(String[] args){
		createGame();
		
		boolean continua = true;
        while(continua){
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * Crea l'interfaccia di creazione della partita
	 */
	private static void createGame(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
	            	preGame = new CreateGame(2);
	                preGame.createAndShowGUI(); 
                }
        });
	}
	
	/**
	 * Nasconde l'interfaccia di creazione della partita
	 */
	public void nascondiCreate(){
		preGame.nascondiFinestra();
		createBoard();
	}
	
	/**
	 * Chiude l'interfaccia di creazione della partita
	 */
	public void chiudiCreate(){
		preGame.chiudiFinestra();
	}
	
	/**
	 * Crea l'interfaccia di gioco
	 */
	private static void createBoard(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
	            	board = new Board(2);
	                board.createAndShowGUI();
                }
        });
	}
	
	/**
	 * Chiude l'interfaccia di gioco
	 */
	public void chiudiBoard(){
		board.chiudiFinestra();
		preGame.rendiVisibile();
	}
	
	/**
	 * ottiene informazioni
	 */
	public void setGame(Game g){
		Launcher.game = g;
	}
	
	/**
	 * Da infromazioni
	 */
	public Game getGame(){
		return Launcher.game;
	}
	
}
