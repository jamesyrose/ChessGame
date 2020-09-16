import java.util.ArrayList;

public class ChessMain {
	private static PieceMoves pieceMoves;
	
    public static void main(String[] args) 
    {
        String turn = "white";
    	Board board = new Board();
    	pieceMoves = new PieceMoves();
       
        board.display();
        
        while (true) {
        	if (!board.kingExists()) {
        		String winner = board.getWinner();
        		Win winWind = new Win();
        		winWind.winFrame(winner);
        		break;
        	}

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
		if (pieceMoves.validMoves().contains(newPiece)) {
    		board.movePieces(currentPiece, newPiece);
    		board.display();
    	} else if (pieceMoves.killMoves().contains(newPiece)) {
    		newPiece.clearPiece();
    		board.movePieces(currentPiece, newPiece);
    		board.display();
    	}
		pieceMoves.killMoves().clear();
		pieceMoves.validMoves().clear();
		board.unpressAll();
		board.display();
    }
    
    private static void highlightMoves(Board board) {
		ChessLabel selectedPiece = board.pressedPiece();
		if (selectedPiece.pieceName().equals("knight")){
			pieceMoves.knight(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("pawn")) {
			pieceMoves.pawn(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("bishop")) {
			pieceMoves.bishop(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("rook")) {
			pieceMoves.rook(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("queen")) {
			pieceMoves.queen(selectedPiece, board);
		} else if (selectedPiece.pieceName().equals("king")) {
			pieceMoves.king(selectedPiece, board);
		}
    }
    
}