package monopoly;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Grafica del tabellone di monopoly
 * @author Leonardo
 *
 */
public class Board extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static JFrame frame;
	protected JLabel[] boxes;
	protected JPanel[] vertical;
	protected JPanel[] horizontal;
	protected JLabel[] legenda;
	protected Color[] colorsH;
	protected Color[] colorsV;
	protected JLabel playerTurn, playerRoll;
	protected JButton roll;
	private BoardTokens[] tokens;
	private Game g;
	
	/**
	 * Costruttore che non istanzia nulla
	 */
	public Board(int i){}
	
	/**
	 * Costruttore della classe definisce tutti gli elementi grafici del tabellone
	 */
	public Board(){
		getGame();
		boxes = new JLabel[170];
		vertical = new JPanel[11];
		horizontal = new JPanel[11];
		colorsV = new Color[]{Color.orange, Color.green, Color.orange, Color.green, Color.orange, 
				Color.green, Color.magenta, Color.magenta, Color.blue, Color.magenta, Color.blue};
		colorsH = new Color[]{Color.red,Color.red,Color.red,Color.yellow,Color.yellow,Color.yellow,
				Color.cyan,Color.cyan, Color.cyan, Color.darkGray, Color.darkGray, Color.darkGray};
		Dimension d = new Dimension();
		d.setSize(70, 70);
		
		int scorr = 0;
		int verticalCounter = 0;
		int horizontalCounter = 0;
		int colorCounterH = 0;
		int colorCounterV = 0;
		JPanel boxesColumns = new JPanel(new GridLayout(11,11,0,0));
		int numero;
	    for(numero = 0; numero<121; numero++){ //costruisce la tabella di gioco
	    	//caselle senza proprietà
	    	if(numero == 0||numero == 2||numero == 5||numero == 8||numero == 10||numero == 33
	    			||numero == 43||numero == 55||numero == 65||numero == 76||numero == 88
	    			||numero == 98||numero == 110||numero == 113||numero == 115||numero == 116
	    			||numero == 118||numero == 120){
	    		boxes[scorr] = addBox(0); // caselle 1,3,6,9,11,34,44,56,66,77,89,99,111,114,116,117,119,121
	    		boxesColumns.add(boxes[scorr]);
	    		
	    	}
	    	
	    	//caselle con proprietà (verticali)
	    	else if(numero == 11||numero == 21||numero == 22||numero == 32||numero == 44||numero == 54
	    			||numero == 66||numero == 77||numero == 87||numero == 99||numero == 109){
	    		boxes[scorr] = addBox(4); //caselle 12,22,23,33,45,55,67,78,88,100,110
	    		if(verticalCounter == 1||verticalCounter == 3||verticalCounter == 5||
	    				verticalCounter == 8||verticalCounter == 10){
	    			boxes[scorr].setOpaque(true);
	    			boxes[scorr].setBackground(colorsV[colorCounterV]);
	    		}
	    		vertical[verticalCounter] = new JPanel(new GridLayout(0,2,0,0));
	    		vertical[verticalCounter].setMaximumSize(d);
	    		vertical[verticalCounter].setPreferredSize(d);
	    		vertical[verticalCounter].setMinimumSize(d);
	    		vertical[verticalCounter].add(boxes[scorr]);
	    		scorr++;
	    		boxes[scorr] = addBox(2);
	    		if(verticalCounter == 0||verticalCounter == 2||verticalCounter == 4||
	    				verticalCounter == 6||verticalCounter == 7||verticalCounter == 9){
	    			boxes[scorr].setOpaque(true);
	    			boxes[scorr].setBackground(colorsV[colorCounterV]);
	    		}
	    		vertical[verticalCounter].add(boxes[scorr]);
	    		boxesColumns.add(vertical[verticalCounter]);
	    		colorCounterV++;
	    		verticalCounter++;
	    	}
	    	
	    	//caselle con proprietà (orizzontali)
	    	else if(numero == 1||numero == 3||numero == 4||numero == 6||numero == 7
	    			||numero == 9||numero == 111||numero == 112||numero == 114||numero == 117
	    			||numero == 119){
	    		boxes[scorr] = addBox(5); //caselle 2,4,5,7,8,10,112,113,115,118,120
	    		if(horizontalCounter > 5){
	    			boxes[scorr].setOpaque(true);
	    			boxes[scorr].setBackground(colorsH[colorCounterH]);
	    		}
	    		horizontal[horizontalCounter] = new JPanel(new GridLayout(2,0,0,0));
	    		horizontal[horizontalCounter].setMaximumSize(d);
	    		horizontal[horizontalCounter].setPreferredSize(d);
	    		horizontal[horizontalCounter].setMinimumSize(d);
	    		horizontal[horizontalCounter].add(boxes[scorr]);
	    		scorr++;
	    		boxes[scorr] = addBox(2);
	    		if(horizontalCounter < 6){
	    			boxes[scorr].setOpaque(true);
	    			boxes[scorr].setBackground(colorsH[colorCounterH]);
	    		}
	    		horizontal[horizontalCounter].add(boxes[scorr]);
	    		boxesColumns.add(horizontal[horizontalCounter]);
	    		colorCounterH++;
	    		horizontalCounter++;
	    	}
	    	
	    	//vuota
	    	else{
	    		boxes[scorr] = addBox(1);
	    		boxesColumns.add(boxes[scorr]);
	    	}
	    	scorr++;
	    }
	    add(boxesColumns);
	    
	    Color playerColor[] = new Color[]{Color.blue, Color.green, Color.orange, Color.red,
        		Color.yellow, Color.magenta, Color.pink, Color.black};
	    
	    JPanel rightBox = null;
	    
	    //inserisco i token dei giocatori
	    if(g!=null){
	    	System.out.println("ee" +g.getPlayers().length);
	    	tokens = new BoardTokens[g.getPlayers().length];
			for(int i = 0; i < g.getPlayers().length;i++){
				tokens[i] = new BoardTokens(5+i*6,10,142,i);
				boxes[142].add(tokens[i]); //il box 142 è il VIA
			}
			
			Dimension d2 = new Dimension();
			d2.setSize(20, 20);
			int colorCounter = 0;
			rightBox = new JPanel(new GridLayout(g.getPlayers().length,2,5,5));
			legenda = new JLabel[g.getPlayers().length*2];
			for(int j = 0; j < legenda.length; j++){
				if(j==0||j%2==0){
					System.out.print(""+j);
					legenda[j] = new JLabel("");
					legenda[j].setMaximumSize(d2);
					legenda[j].setPreferredSize(d2);
					legenda[j].setMinimumSize(d2);
					legenda[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					legenda[j].setOpaque(true);
					legenda[j].setBackground(playerColor[colorCounter]);
					colorCounter++;
					rightBox.add(legenda[j]);
				}else{
					legenda[j] = new JLabel("Player: "+ colorCounter +" "+g.getPlayers()[colorCounter-1].getName());
					legenda[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					legenda[j].setOpaque(true);
					rightBox.add(legenda[j]);
				}
			}
	    }
	    
	    add(rightBox);
	    
	    JPanel diceBox = new JPanel(new GridLayout(3,0,5,5));
	    roll = new JButton("Roll");
        roll.setVerticalTextPosition(AbstractButton.CENTER);
        roll.setHorizontalTextPosition(AbstractButton.CENTER);
        roll.addActionListener(this);
        roll.setToolTipText("Roll the dices");
        
        playerTurn = new JLabel("It's the player: "+g.currentPlayer().getName()+" turn");
        playerRoll = new JLabel("");
	    
        diceBox.add(playerTurn);
        diceBox.add(roll);
        diceBox.add(playerRoll);
        add(diceBox);
	    
        //per mostrare il numero di ogni box
        /*for(int i = 0; i < 170; i++){
	    	if(boxes[i]!=null){
	    		boxes[i].setText(""+i);
	    	}
	    		
	    }*/
	}
	
	/**
	 * Assegna una dimensione specifica ad una JLabel
	 * @param i 0 per 70,70 con bordo; 1 per 70,70; 2 per 20,70 con bordo; 3 per 20,70 con bordo; 4 per 50,70 con bordo; 5 per 70,50 con bordo;
	 * @return ritorna la JLabel con la dimensione e l'eventuale bordo voluti
	 */
	private JLabel addBox(int i){
		JLabel j = new JLabel("");
		Dimension d = new Dimension();
		int w = 0, h = 0;
		
		if(i == 0){ //casella normale
			w = 70;
			h = 70;
			j.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}else if(i == 1){ //casella senza bordo
			w = 70;
			h = 70;
		}else if(i == 2){ //proprietà verticale
			w = 20;
			h = 70;
			j.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}else if(i == 3){ //proprietà orizzontale
			w = 20;
			h = 70;
			j.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}else if(i == 4){ //casella ridotta in spessore
			w = 50;
			h = 70;
			j.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}else if(i == 5){ //casella ridotta in altezza
			w = 70;
			h = 50;
			j.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
		d.setSize(w, h);
		j.setMaximumSize(d);
		j.setPreferredSize(d);
		j.setMinimumSize(d);
		return j;
	}
	
	/**
	 * Crea la finestra per i componenti grafici
	 */
	public void createAndShowGUI() {
        frame = new JFrame("Monopoly Game");
		frame.setPreferredSize(new Dimension(1250, 850));
		frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent) {
				disposaFrame();
		    }
		});
        Board contentPane = new Board();
        contentPane.setOpaque(true); 
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	@Override
	/**
	 * Listener del bottone ROLL, tira i dadi per il player corrente, scrive il risultato del
	 * tiro nella JLabel3, muove il player corrente nella posizione risultante,
	 * si disabilita quando il gioco è finito
	 */
	public void actionPerformed(ActionEvent arg0) {
		int indexToMove = 0;
		int playerPosition = 0;
		int previousPosition = g.currentPlayer().getPosition();
		String previousName = g.currentPlayer().getName();
		for(int i = 0; i<g.getPlayers().length; i++){
			if(g.getPlayers()[i].equals(g.currentPlayer())){
				indexToMove = i;
				break;
			}
		}
		int diceRoll = g.playerTurn();
		playerPosition = diceRoll;
		diceRoll = diceRoll - previousPosition;
		if(diceRoll<0){
			diceRoll = (diceRoll+40);
		}
		
		//System.out.println("roll: "+diceRoll+" position: "+playerPosition+"  index: " + indexToMove);
		
		//ridisegno il token a seconda di dove si trova
		if(0<=playerPosition && playerPosition<11){ //dalla posizione 0 alla 10
			//cioè box 142, 141, 139, 138, 136, 135, 134, 132, 131, 129, 127
			int[] box = new int[]{142, 141, 139, 138, 136, 135, 134, 132, 131, 129, 127};
			boxes[tokens[indexToMove].getBox()].setBackground(Color.red);;
			boxes[tokens[indexToMove].getBox()].setBackground(null);;
			tokens[indexToMove].update(5+indexToMove*6,10,box[playerPosition]);
			boxes[box[playerPosition]].add(tokens[indexToMove]);
		}else if(11<=playerPosition && playerPosition<20){ //dalla posizione 11 alla 19
			//cioè box 114, 103, 90, 78, 67, 54, 43, 30, 17
			int[] box = new int[]{114, 103, 90, 78, 67, 54, 43, 30, 17};
			boxes[tokens[indexToMove].getBox()].setBackground(Color.red);;
			boxes[tokens[indexToMove].getBox()].setBackground(null);;
			tokens[indexToMove].update(10,5+indexToMove*6,box[playerPosition-11]);
			boxes[box[playerPosition-11]].add(tokens[indexToMove]);
		}else if(20<=playerPosition && playerPosition<31){ //dalla posizione 20 alla 30
			//cioè box 0, 1, 3, 4, 6, 8, 9, 11, 13, 14, 16
			int[] box = new int[]{0, 1, 3, 4, 6, 8, 9, 11, 13, 14, 16};
			boxes[tokens[indexToMove].getBox()].setBackground(Color.red);;
			boxes[tokens[indexToMove].getBox()].setBackground(null);;
			tokens[indexToMove].update(5+indexToMove*6,10,box[playerPosition-20]);
			boxes[box[playerPosition-20]].add(tokens[indexToMove]);
		}else if(31<=playerPosition && playerPosition<40){ //dalla posizione 31 alla 39
			//cioè box 29, 42, 53, 66, 77, 89, 102, 113, 126
			int[] box = new int[]{29, 42, 53, 66, 77, 89, 102, 113, 126};
			boxes[tokens[indexToMove].getBox()].setBackground(Color.red);;
			boxes[tokens[indexToMove].getBox()].setBackground(null);;
			tokens[indexToMove].update(10,5+indexToMove*6,box[playerPosition-31]);
			boxes[box[playerPosition-31]].add(tokens[indexToMove]);
		}
		
		playerRoll.setText("The player: "+ previousName +" rolled: " +diceRoll);
		playerTurn.setText("It's the player: "+g.currentPlayer().getName()+" turn");
		
		if(g.isOver()==true){
			rollDisable();
			playerTurn.setText("The game has ended!!");
			playerTurn.setForeground(Color.red);
		}
		frame.validate();
	}
	
	/**
	 * disabilita il bottone roll
	 */
	private void rollDisable(){
		roll.setEnabled(false);
	}
	
	/**
	 * Quando la finestra viene chiusa riapro la finestra di creazione del giocoo
	 */
	private void disposaFrame(){
		Launcher lau = new Launcher();
    	lau.chiudiBoard();
	}
	
	/**
	 * Chiude la finestra
	 */
    public void chiudiFinestra(){
    	frame.dispose();
    }
    
    /**
     * ottiene informazioni
     */
    private void getGame(){
    	Launcher lau = new Launcher();
    	g = lau.getGame();
    }
    
    
    /**
     * Classe di comodo per creare i token dei giocatori
     * @author Leonardo
     *
     */
    private class BoardTokens extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int xPosition;
		private int yPosition;
		private int boxValue;
        private Color colors[] = new Color[]{Color.blue, Color.green, Color.orange, Color.red,
        		Color.yellow, Color.magenta, Color.pink, Color.black};
        private Color color;
        
        /**
         * @param x la posizione x del token
         * @param y la posizione y del token
         * @param box il numero del box nel quale si trova il token
         * @param playerN il numero del giocatore
         */
        public BoardTokens(int x, int y,int box, int playerN) {
            xPosition = x;
            yPosition = y;
            boxValue = box;
            this.setOpaque(false);
            this.setEnabled(true);
            this.setBounds(xPosition, yPosition, 20, 20);
            color = colors[playerN];
        }

        @Override
        /**
         * Disegna il token
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
        }

        /**
         * sposta il token
         * @param x dove spostare il token sulle x
         * @param y dove spostare il token sulle y
         */
        private void update(int x, int y, int box) {
        	xPosition = x;
        	yPosition = y;
        	boxValue = box;
        	this.setBounds(xPosition, yPosition, 20, 20);
            repaint();
        }
        
        public int getBox(){
        	return boxValue;
        }
    }
	
}
