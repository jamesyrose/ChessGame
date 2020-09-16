import java.awt.Color;
import javax.swing.JButton; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ChessLabel extends JButton {

    Font font     = new Font("Ariel", Font.PLAIN, 48);
    Color bgLight = new Color(222, 184, 135);
    Color bgDark  = new Color(139, 69, 19);
    private int x; 
    private int y;
    private boolean pressed;
    private String name;
    private String pieceName;
    private String color; 

    ChessLabel(String s)
    {
        super(s);
        this.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		selectionButtonPressed();
	  		} 
    	} 
        );
        this.pressed = false;
        this.name = s;
        this.initializePiece();
    }
    
    void clearPiece() {
    	setText(" ");
        this.pressed = false;
        this.name = " ";
        this.initializePiece();
    }
    
    void setQueen() {
    	String key = "\u2655";
    	if (this.color.equals("black")) {
    		key = "\u265B";
    	}
    	setText(key);
        this.pressed = false;
        this.name = key;
        this.initializePiece();
    }
    
    void set(int idx, int row) {
		setFont(font);
		setOpaque(true);
		setBackground((idx+row)%2 == 0 ? bgDark : bgLight);
		setHorizontalAlignment( SwingConstants.CENTER );
		this.x = idx;
		this.y = row;
    }
    
    public void selectionButtonPressed() {
    	if (this.isPressed()) {
    		this.font = new Font("Ariel", Font.PLAIN, 48);
        	this.set(this.x, this.y);
        	this.pressed = false;
    	}else {
    		this.font = new Font("Ariel", Font.BOLD, 64);
        	this.set(this.x, this.y);
        	this.pressed = true;
    	}
    	
    }
    
    public boolean isPressed() {
    	return pressed;
    }
    
    public void initializePiece() {
    	String piece = "";
    	String color = "white";
    	switch (name) {
    		case "\u2654":
    			piece = "king";
    			break;
    		case "\u2655":
    			piece = "queen";
    			break;
      		case "\u2656": 
    			piece = "rook";
    			break;
    		case "\u2657":
    			piece = "bishop";
    			break;
    		case "\u2658":
    			piece = "knight";
    			break;
    		case "\u2659":
    			piece = "pawn";
    			break;
    		case "\u265A":
    			piece = "king";
    			color = "black";
    			break;
    		case "\u265B":
    			piece = "queen";
    			color = "black";
    			break;
    		case "\u265C":
    			piece = "rook";
    			color = "black";
    			break;
    		case "\u265D":
    			piece = "bishop";
    			color = "black";
    			break;
    		case "\u265E":
    			piece = "knight";
    			color = "black";
    			break;
    		case "\u265F":
    			piece = "pawn";
    			color = "black";
    			break;
    		default: 
    			piece = "blank";
    			color = "none";
    	}
    	this.pieceName = piece;
    	this.color = color;
    }
    
    public String pieceName() {
    	return this.pieceName;
    }
    
    public String pieceColor() {
    	return this.color;
    }

    public void changeBackgroundColor() {
    	setBackground(Color.BLUE);
    }
    
    public int getxPos() {
    	return x;
    }
    
    public int getyPos() {
    	return y;
    }

}