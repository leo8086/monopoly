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
	
	/**
	 * Costruttore della classe definisce tutti gli elementi grafici
	 */
	public Board(){
		boxes = new JLabel[170];
		vertical = new JPanel[11];
		horizontal = new JPanel[11];
		Dimension d = new Dimension();
		d.setSize(70, 70);
		//vertical = new JPanel(new GridLayout(2,0,0,0));
		//horizontal = new JPanel(new GridLayout(0,2,0,0));
		int scorr = 0;
		int verticalCounter = 0;
		int horizontalCounter = 0;
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
	    		vertical[verticalCounter] = new JPanel(new GridLayout(0,2,0,0));
	    		vertical[verticalCounter].setMaximumSize(d);
	    		vertical[verticalCounter].setPreferredSize(d);
	    		vertical[verticalCounter].setMinimumSize(d);
	    		vertical[verticalCounter].add(boxes[scorr]);
	    		scorr++;
	    		boxes[scorr] = addBox(2);
	    		vertical[verticalCounter].add(boxes[scorr]);
	    		boxesColumns.add(vertical[verticalCounter]);
	    		verticalCounter++;
	    	}
	    	
	    	//caselle con proprietà (orizzontali)
	    	else if(numero == 1||numero == 3||numero == 4||numero == 6||numero == 7
	    			||numero == 9||numero == 111||numero == 112||numero == 114||numero == 117
	    			||numero == 119){
	    		boxes[scorr] = addBox(5); //caselle 2,4,5,7,8,10,112,113,115,118,120
	    		horizontal[horizontalCounter] = new JPanel(new GridLayout(2,0,0,0));
	    		horizontal[horizontalCounter].setMaximumSize(d);
	    		horizontal[horizontalCounter].setPreferredSize(d);
	    		horizontal[horizontalCounter].setMinimumSize(d);
	    		horizontal[horizontalCounter].add(boxes[scorr]);
	    		scorr++;
	    		boxes[scorr] = addBox(2);
	    		if(horizontalCounter < 6){
	    			boxes[scorr].setOpaque(true);
	    			boxes[scorr].setBackground(Color.red);
	    		}
	    		horizontal[horizontalCounter].add(boxes[scorr]);
	    		boxesColumns.add(horizontal[horizontalCounter]);
	    		
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
		frame.setPreferredSize(new Dimension(850, 850));
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
