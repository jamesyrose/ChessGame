import java.util.ArrayList;

public class PieceMoves {
	private static ArrayList<ChessLabel> validMoves;
	private static ArrayList<ChessLabel> killMoves;
	
	public PieceMoves() {
        validMoves = new ArrayList<>();
        killMoves = new ArrayList<>();
	}
	
	public ArrayList<ChessLabel> validMoves(){
		return validMoves;
	}
	
	public  ArrayList<ChessLabel> killMoves(){
		return killMoves;
	}
	
    public static void pawn(ChessLabel piece, Board board) {
    	int x = piece.getxPos();
		int y = piece.getyPos();
		boolean canGo = true;
    	String pieceColor = piece.pieceColor();
		int[] directions = {1, 0};
		if (y == 6 || y == 1) {
			directions[1] = 2;
		}
		
    	canGo = true;
		for(int move: directions) {
			x = piece.getxPos();
        	y = piece.getyPos();

        	if (pieceColor.equals("black")) {
        		move *= -1;
        	}
    		y += move;
    		if (x < 8 && x > -1 && y < 8 && y > -1) {
    			ChessLabel newPiece = board.pieceAt(x, y);
    			if (newPiece.pieceName().equals("blank") && canGo){
					newPiece.changeBackgroundColor();
    				validMoves.add(newPiece);
    			}else { 
    				canGo = false;
    			}
    		}else {
    			canGo = false;
    		}
    	}
		int[][] moves = {{1,1}, {-1, 1}};
		for (int[] move: moves) {
			x = piece.getxPos();
        	y = piece.getyPos();
        	int dx = move[0];
        	int dy = move[1];
        	if (pieceColor.equals("black")) {
        		dy *= -1;
        	}
        	x += dx;
    		y += dy;
    		if (x < 8 && x > -1 && y < 8 && y > -1) {
    			ChessLabel newPiece = board.pieceAt(x, y);
    			if (!newPiece.pieceName().equals("blank") && 
    					!newPiece.pieceColor().equals(pieceColor)){
					newPiece.changeBackgroundColor();
    				killMoves.add(newPiece);
    			}
    		}
		}
		
    }

    public static void knight(ChessLabel piece, Board board) {
    	// knights can move 2x1
    	int x = piece.getxPos();
		int y = piece.getyPos();
		int[][] moves = {{2, 1},{1, 2},{-1, 2}, {1, -2}, {-2, 1},{-1, -2}, {-2,-1}};
		if (piece.pieceColor().equals("white")) {
			for (int[] move: moves) {
				int dx = move[0];
				int dy = move[1];
				int newx = dx + x;
				int newy = dy + y; 
				if (newx > -1 && newx < 8 && newy > -1 && newy < 8) {
					System.out.println(newx  + "x " + newy);
					ChessLabel newPiece = board.pieceAt(newx, newy);
					if (newPiece.pieceName().equals("blank")) {
						newPiece.changeBackgroundColor();
						validMoves.add(newPiece);
					}else if (!newPiece.pieceName().equals("blank") && 
							newPiece.pieceColor().equals("black")
							){
						newPiece.changeBackgroundColor();
						killMoves.add(newPiece);
					}
				}
			}
		}else { 
			for (int[] move: moves) {
				int dx = move[0];
				int dy = move[1];
				int newx = dx + x;
				int newy = dy + y; 
				if (newx > -1 && newx < 8 && newy > -1 && newy < 8) {
					System.out.println(newx  + "x " + newy);
					ChessLabel newPiece = board.pieceAt(newx, newy);
					if (newPiece.pieceName().equals("blank")) {
						newPiece.changeBackgroundColor();
						validMoves.add(newPiece);
					}else if (!newPiece.pieceName().equals("blank") && 
							newPiece.pieceColor().equals("white")
							){
						newPiece.changeBackgroundColor();
						killMoves.add(newPiece);
					}
				}
			}
		}

		
    }

    public static void bishop(ChessLabel piece, Board board){
    	int x;
    	int y;
    	boolean canGo; 
    	int[][] direction = {{1,1}, {-1, -1}, {-1, 1}, {1,-1}};
    	
    	for (int[] pair: direction) {
        	x = piece.getxPos();
        	y = piece.getyPos();
        	String pieceColor = piece.pieceColor();
        	canGo = true; 
        	while (canGo) {
        		x += pair[0];
        		y += pair[1];
        		if (x < 8 && x > -1 && y < 8 && y > -1) {
        			ChessLabel newPiece = board.pieceAt(x, y);
        			if (newPiece.pieceName().equals("blank")){
    					newPiece.changeBackgroundColor();
        				validMoves.add(newPiece);
        			} else if (!newPiece.pieceColor().equals(pieceColor) && 
        					!newPiece.pieceName().equals("blank")) {
    					newPiece.changeBackgroundColor();
        				killMoves.add(newPiece);
        				canGo = false;
        			} else { 
        				canGo = false;
        			}
        		}else {
        			canGo = false;
        		}
        	}
    	}

    }
    
    public static void rook(ChessLabel piece, Board board){
    	int x;
    	int y;
    	boolean canGo; 
    	int[][] direction = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    	
    	for (int[] pair: direction) {
        	x = piece.getxPos();
        	y = piece.getyPos();
        	String pieceColor = piece.pieceColor();
        	canGo = true; 
        	while (canGo) {
        		x += pair[0];
        		y += pair[1];
        		if (x < 8 && x > -1 && y < 8 && y > -1) {
        			ChessLabel newPiece = board.pieceAt(x, y);
        			if (newPiece.pieceName().equals("blank")){
    					newPiece.changeBackgroundColor();
        				validMoves.add(newPiece);
        			} else if (!newPiece.pieceColor().equals(pieceColor) && 
        					!newPiece.pieceName().equals("blank")) {
    					newPiece.changeBackgroundColor();
        				killMoves.add(newPiece);
        				canGo = false;
        			} else { 
        				canGo = false;
        			}
        		}else {
        			canGo = false;
        		}
        	}
    	}

    }
    
    public static void queen(ChessLabel piece, Board board){
    	int x;
    	int y;
    	boolean canGo; 
    	int[][] direction = {{1,1}, {-1, -1}, {-1, 1}, {1,-1},
    			{1,0}, {-1, 0}, {0, 1}, {0, -1}};  // basically a rook and a bishop
    	
    	for (int[] pair: direction) {
        	x = piece.getxPos();
        	y = piece.getyPos();
        	String pieceColor = piece.pieceColor();
        	canGo = true; 
        	while (canGo) {
        		x += pair[0];
        		y += pair[1];
        		if (x < 8 && x > -1 && y < 8 && y > -1) {
        			ChessLabel newPiece = board.pieceAt(x, y);
        			if (newPiece.pieceName().equals("blank")){
    					newPiece.changeBackgroundColor();
        				validMoves.add(newPiece);
        			} else if (!newPiece.pieceColor().equals(pieceColor) && 
        					!newPiece.pieceName().equals("blank")) {
    					newPiece.changeBackgroundColor();
        				killMoves.add(newPiece);
        				canGo = false;
        			} else { 
        				canGo = false;
        			}
        		}else {
        			canGo = false;
        		}
        	}
    	}

    }
    
    public static void king(ChessLabel piece, Board board) {
    	int x;
    	int y;
    	int[][] direction = {{1,1}, {-1, -1}, {-1, 1}, {1,-1},
    			{1,0}, {-1, 0}, {0, 1}, {0, -1}};  // basically a rook and a bishop
    	
    	for (int[] pair: direction) {
        	x = piece.getxPos();
        	y = piece.getyPos();
        	String pieceColor = piece.pieceColor();
    		x += pair[0];
    		y += pair[1];
    		if (x < 8 && x > -1 && y < 8 && y > -1) {
    			ChessLabel newPiece = board.pieceAt(x, y);
    			if (newPiece.pieceName().equals("blank")){
					newPiece.changeBackgroundColor();
    				validMoves.add(newPiece);
    			} else if (!newPiece.pieceColor().equals(pieceColor) && 
    					!newPiece.pieceName().equals("blank")) {
					newPiece.changeBackgroundColor();
    				killMoves.add(newPiece);
    			}
    		}
    	}
    }

}
