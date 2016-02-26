package monopoly;

/**
 * Gestisce le varie finestre di interfaccia grafica
 * @author Leonardo
 *
 */
public class Launcher {
	
	private static Board board = null;
	
	/**
	 * Il main, chiama il metodo che crea l'interfaccia e resta in loop infinito
	 */
	public static void main(String[] args){
		createBoard();
		
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
	 * Crea l'interfaccia
	 */
	private static void createBoard(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
	            	board = new Board();
	                board.createAndShowGUI(); 
                }
        });
	}
}
