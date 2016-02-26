package monopoly;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static JFrame frame;
	protected JLabel[] boxes;
	protected JPanel[] vertical;
	protected JPanel[] horizontal;
	protected Color[] colorsH;
	protected Color[] colorsV;
	
	/**
	 * Costruttore della classe definisce tutti gli elementi grafici del tabellone
	 */
	public Board(){
		boxes = new JLabel[170];
		vertical = new JPanel[11];
		horizontal = new JPanel[11];
		colorsV = new Color[]{Color.orange, Color.green, Color.orange, Color.green, Color.orange, 
				Color.green, Color.magenta, Color.magenta, Color.blue, Color.magenta, Color.blue};
		colorsH = new Color[]{Color.red,Color.red,Color.red,Color.yellow,Color.yellow,Color.yellow,
				Color.cyan,Color.cyan, Color.cyan, Color.darkGray, Color.darkGray, Color.darkGray};
		Dimension d = new Dimension();
		d.setSize(70, 70);
		JPanel lateralBox = new JPanel(new GridLayout(11,0,0,0));
		for(int indice = 0; indice<11; indice++){
			JLabel j = new JLabel("");
			j.setMaximumSize(d);
			j.setPreferredSize(d);
			j.setMinimumSize(d);
			lateralBox.add(j);
		}
		add(lateralBox);
		
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
		frame.setPreferredSize(new Dimension(950, 850));
		frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board contentPane = new Board();
        contentPane.setOpaque(true); 
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
