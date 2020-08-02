package com.dt.morris.piece;

import java.util.List;

import com.dt.morris.board.PieceColor;
import com.dt.morris.move.Move;

public class EmptyTile extends Piece {

	public EmptyTile(int piecePosition) {
		super(null, piecePosition, PieceColor.EMPTY);
	}

	@Override
	public List<Move> calculateLegalMoves(List<PieceColor> pieceColorList) {
		return null;
	}

}
