import java.util.ArrayList;

public class test {
	private static ArrayList<ChessLabel> validMoves;
	private static ArrayList<ChessLabel> killMoves;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        validMoves = new ArrayList<>();
        killMoves = new ArrayList<>();
        String turn = "white";
    	Board board = new Board();

       
        board.display();
        
        while (true) {
        	if (board.buttonPressed()) {
        		ChessLabel currPiece = board.pressedPiece();
        		if (currPiece.pieceColor().equals(turn)) {
            		highlightMoves(board);
            		validMoveOrChange(board, currPiece);
            		if (turn.equals("black")) {
            			turn = "white";
            		} else if (turn.equals("white")) {
            			turn = "black";
            		}
        		} else { 
        			board.unpressAll();
        			board.display();
        		}
        	}
        }
    }
    
    private static void validMoveOrChange(Board board, ChessLabel currentPiece) {
    	ChessLabel newPiece = null;
    	while (true) {
    		if (board.newPiecePressed(currentPiece)) {
    			newPiece = board.newPressedPiece(currentPiece);
    			break;
    		}
    	}
		if (validMoves.contains(newPiece)) {
    		board.movePieces(currentPiece, newPiece);
    		board.display();
    	} else if (killMoves.contains(newPiece)) {
    		newPiece.clearPiece();
    		board.movePieces(currentPiece, newPiece);
    		board.display();
    	}
		killMoves.clear();
		validMoves.clear();
		board.unpressAll();
		board.display();
    }
    
    private static void highlightMoves(Board board) {
		ChessLabel selectedPiece = board.pressedPiece();
		if (selectedPiece.pieceName().equals("knight")){
			knight(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("pawn")) {
			pawn(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("bishop")) {
			bishop(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("rook")) {
			rook(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("queen")) {
			queen(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("king")) {
			king(selectedPiece, board);
		}

    }
    
    private static void pawn(ChessLabel piece, Board board) {
    	int x = piece.getxPos();
		int y = piece.getyPos();
		boolean canGo = true;

		if (piece.pieceColor().equals("black")) {
			int maxMove = 1;
			if (y == 6) {
				maxMove = 2;
			}
			for (int i=1; i <= maxMove ; i++) {
				ChessLabel newPiece = board.pieceAt(x,  y - i);
				if (newPiece.pieceName().equals("blank") && canGo) { 
					newPiece.changeBackgroundColor();
					validMoves.add(newPiece);
				}else {
					canGo = false;
				}
			}
			// Kill moves (diagonals)
			if (x < 7) {
				ChessLabel newPiece = board.pieceAt(x + 1,  y - 1);
				if (newPiece.pieceName().equals("white")) { 
					newPiece.changeBackgroundColor();
					killMoves.add(newPiece);
				}
			}

			if (x > 0) {
				ChessLabel newPiece = board.pieceAt(x - 1,  y - 1);
				if (newPiece.pieceName().equals("white")) { 
					newPiece.changeBackgroundColor();
					killMoves.add(newPiece);
				}
			}

		}else if (piece.pieceColor().equals("white")) {
			canGo = true;
			int maxMove = 1;
			if (y == 1) {
				maxMove = 2;
			}
			for (int i=1; i <= maxMove ; i++) {
				ChessLabel newPiece = board.pieceAt(x,  y + i);
				if (newPiece.pieceName().equals("blank") && canGo) { 
					newPiece.changeBackgroundColor();
					validMoves.add(newPiece);
				}else {
					canGo = false;
				}
			}
			canGo = true;
			// Kill moves (diagonals)
			if (x < 7) {
				ChessLabel newPiece = board.pieceAt(x + 1,  y + 1);
				if (newPiece.pieceName().equals("black")) { 
					newPiece.changeBackgroundColor();
					killMoves.add(newPiece);
				}
			}

			if (x > 0) {
				ChessLabel newPiece = board.pieceAt(x - 1,  y + 1);
				if (newPiece.pieceName().equals("black")) { 
					newPiece.changeBackgroundColor();
					killMoves.add(newPiece);
				}
			}
		}
    }

    private static void knight(ChessLabel piece, Board board) {
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

    private static void bishop(ChessLabel piece, Board board){
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
    
    private static void rook(ChessLabel piece, Board board){
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
    
    private static void queen(ChessLabel piece, Board board){
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
    
    private static void king(ChessLabel piece, Board board) {
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