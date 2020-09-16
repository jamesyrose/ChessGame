import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;


public class Board extends JFrame {


   //Initialize arrays to hold panels and images of the board

    private static ChessLabel[] labels = new ChessLabel[] {

    // white
    new ChessLabel("\u2656"), new ChessLabel("\u2658"), new ChessLabel("\u2657"), 
    new ChessLabel("\u2655"), new ChessLabel("\u2654"), new ChessLabel("\u2657"), 
    new ChessLabel("\u2658"), new ChessLabel("\u2656"), new ChessLabel("\u2659"), 
    new ChessLabel("\u2659"), new ChessLabel("\u2659"), new ChessLabel("\u2659"),
    new ChessLabel("\u2659"), new ChessLabel("\u2659"), new ChessLabel("\u2659"), 
    new  ChessLabel("\u2659"), 
    // empty
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "), 
    new ChessLabel(" "), new ChessLabel(" "),
    // black
    new ChessLabel("\u265F"), new ChessLabel("\u265F"), new ChessLabel("\u265F"), 
    new ChessLabel("\u265F"), new ChessLabel("\u265F"), new ChessLabel("\u265F"), 
    new ChessLabel("\u265F"), new ChessLabel("\u265F"), new ChessLabel("\u265C"), 
    new ChessLabel("\u265E"), new ChessLabel("\u265D"), new ChessLabel("\u265B"), 
    new ChessLabel("\u265A"), new ChessLabel("\u265D"), new ChessLabel("\u265E"), 
    new ChessLabel("\u265C")
    };

    public Board(){
    } // Board()

    void display()
    {
        setTitle("Chess board with unicode images");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(8, 8);

        contentPane.setLayout(gridLayout);

        int row = -1;
        for (int i = 0; i < labels.length; i++) 
        {
            if(i % 8 == 0) row ++; // increment row number
            
            labels[i].set(i - row * 8, row);
            contentPane.add(labels[i]);
        } // i

        setSize(900, 900);
        setLocationRelativeTo(null);
        setVisible(true);
     } // display()

    public static boolean buttonPressed() {
    	for(ChessLabel label: labels) {
    		if (label.isPressed()){
    			return true;
    		}
    	}
    	return false;
    }
    
    public ChessLabel pressedPiece() {
    	for(ChessLabel label: labels) {
    		if (label.isPressed()){
    			return label;
    		}
    	}
    	return null;
    }
    
    public static boolean newPiecePressed(ChessLabel pressedLabel) {
    	for(ChessLabel label: labels) {
    		if (!label.equals(pressedLabel) && label.isPressed()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public ChessLabel newPressedPiece(ChessLabel pressedLabel) {
    	for(ChessLabel label: labels) {
    		if (!label.equals(pressedLabel) && label.isPressed()){
    			return label;
    		}
    	}
    	return null;
    }
    
    public ChessLabel pieceAt(int x, int y) {
    	for (ChessLabel label: labels) {
    		int idx = label.getxPos();
    		int row = label.getyPos();
    		if (idx == x && row == y) {
    			return label;
    		}
    	}
    	return null;
    }
    
    public static void unpressAll() {
    	for (ChessLabel label: labels) {
    		if(label.isPressed()) {
    			label.selectionButtonPressed();
    		}
    	}
    }
    
    public static void movePieces(ChessLabel firstPiece, ChessLabel secondPiece) {
    	int firstIdx = firstPiece.getxPos() + firstPiece.getyPos() * 8;
    	int secondIdx = secondPiece.getxPos() + secondPiece.getyPos() * 8;
    	labels[firstIdx] = secondPiece;
    	labels[firstIdx].set(firstPiece.getxPos(), firstPiece.getyPos());
    	labels[secondIdx] = firstPiece;
    	labels[secondIdx].set(secondPiece.getxPos(), secondPiece.getyPos());
    	unpressAll();
    }
} // class Board