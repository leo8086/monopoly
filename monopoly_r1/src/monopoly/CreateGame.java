package monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * L'interfaccia grafica per creare una partita, chiede il numero di giocatori
 * @author Leonardo
 *
 */
public class CreateGame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NUMBER = "Player number ";
	protected static JFrame frame;
	protected JTextField players;
	protected JButton start;
	protected JLabel textLabel1, textLabel2;
	private Game game;
	
	/**
	 * Costruttore che non istanzia nulla
	 */
	public CreateGame(int i){}
	
	/**
	 * Costruttore, posiziona tutti gli elementi grafici nella finestra
	 */
	public CreateGame(){
		JPanel layout = new JPanel(new GridLayout(0,2,10,10));
		textLabel1 = new JLabel(NUMBER);
		players = new JTextField(10);
		textLabel2 = new JLabel("Insert the players number");
		
		start = new JButton("Start");
        start.setVerticalTextPosition(AbstractButton.CENTER);
        start.setHorizontalTextPosition(AbstractButton.CENTER);
        start.addActionListener(this);
        start.setToolTipText("Click to start the game");
        
        layout.add(textLabel1);
        layout.add(players);
        
        add(layout);
        
        JPanel layout2 = new JPanel(new GridLayout(2,0,10,10));
        layout2.add(textLabel2);
        layout2.add(start);
        
        add(layout2);
        
	}
	
	/**
	 * Crea la finestra per i componenti grafici
	 */
	public void createAndShowGUI() {
        frame = new JFrame("Create Game");
		frame.setPreferredSize(new Dimension(500, 200));
		frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CreateGame contentPane = new CreateGame();
        contentPane.setOpaque(true); 
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	@Override
	/**
	 * Listener della creazione della partita, controlla che l'input del numero dei
	 * giocatori sia corretto.
	 * Se tutto va bene viene aperto il tabellone, altrimenti l'errore viene segnalato
	 * nella textLabel2
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String number = players.getText();
		int playerNumber;
	    number = number.replaceAll("\\s", ""); //cancella gli spazi
	    if (number.length() <= 0){ //controlla per stringhe nulle
		    textLabel2.setText("Inster the players number");
		    textLabel2.setForeground(Color.red);
	    }else if(isInteger(number) == false) { //controlla che sia un numero
	        textLabel2.setText("Insert a valid number");
	        textLabel2.setForeground(Color.red);
	    }else{
	        playerNumber = Integer.parseInt(number);
	        //System.out.println(playerNumber);
	       	game = new Game(playerNumber);
	       	if(game.startGame()==false){
	       		//cioè se il numero di giocatori è troppo alto o troppo basso
	       		textLabel2.setText("Insert a number between 2 and 8");
	       		textLabel2.setForeground(Color.red);
	       	}else{
	       		apriBoard();
	       	}
	    }
	}
	
	/**
	 * controlla che una stringa sia un numero intero
	 * @param s la stringa da testare
	 * @return ritorna true se la stringa contiene un numero, false altrimenti
	 */
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false;
	    } catch(NullPointerException e) {
	        return false;
	    }
	    
	    return true;
	}
	
	/**
	 * apre la finestra di gioco e nasconde questa
	 */
	private void apriBoard(){
		Launcher lau = new Launcher();
		lau.setGame(game);
   		lau.chiudiCreate();
	}

    /**
     * Chiusura finestra
     */
	public void chiudiFinestra(){
		frame.dispose();
	}
	
}
