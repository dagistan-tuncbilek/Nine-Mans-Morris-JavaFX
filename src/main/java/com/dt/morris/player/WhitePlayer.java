package com.dt.morris.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dt.morris.board.Board;
import com.dt.morris.board.PieceColor;
import com.dt.morris.engine.MoveTransition;
import com.dt.morris.move.Move;
import com.dt.morris.piece.Piece;

public class WhitePlayer extends Player {
	
	public WhitePlayer(Board board, PieceColor color, List<Piece> gameBoard) {
		super(board, color, gameBoard);
		setLegalMoves(calculateAllLegalMoves());
	}
	
	@Override
	public List<Move> calculateAllLegalMoves() {
		List<Move> moveList = new ArrayList<Move>();
		int counter = 0;
		boolean hasReserveStone = false;
		for (int i=41 ; i>-1 ; i--) {
			if (getGameBoard().get(i).getPieceColor() == PieceColor.WHITE) {
				counter++;
				if (i > 23 && !hasReserveStone ) {
					moveList.addAll(getGameBoard().get(i).getLegalMoveList());
					hasReserveStone = true;
				} 				
				if (i < 24 && !hasReserveStone) {
					moveList.addAll(getGameBoard().get(i).getLegalMoveList());
				}
			}
		}
		setPieceCount(counter);
		return Collections.unmodifiableList(moveList);
	}

	@Override
	public MoveTransition makeMove(Move move) {
		List<PieceColor> pieceColorList = new ArrayList<PieceColor>();
		pieceColorList.addAll(getBoard().getPieceColorList());
		pieceColorList.set(move.getCurrentCoordinate(), PieceColor.EMPTY);
		pieceColorList.set(move.getDestinationCoordinate(), PieceColor.WHITE);
		if (move.getDeletedPieceCoordinate() != -1) {
			pieceColorList.set(move.getDeletedPieceCoordinate(), PieceColor.EMPTY);
		}
		return new MoveTransition (Collections.unmodifiableList(pieceColorList), PieceColor.BLACK);	 
	}

	@Override
	public String toString() {
		return "WhitePlayer";
	}

	@Override
	public Player getOpponent() {
		return this.getBoard().blackPlayer();
	}
}
