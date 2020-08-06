package com.dt.morris.piece;

import java.util.List;

import com.dt.morris.board.PieceColor;
import com.dt.morris.move.Move;


public abstract class Piece {
	
	private final int piecePosition;
	private final PieceColor pieceColor;
	private final List<Move> legalMoveList; 
	
	public Piece(List<PieceColor> pieceColorList, int piecePosition, PieceColor color) {
		this.piecePosition = piecePosition;
		this.pieceColor = color;
		this.legalMoveList = calculateLegalMoves(pieceColorList);
	}

	public abstract List<Move> calculateLegalMoves(List<PieceColor> pieceColorList);
	
	public int getPiecePosition() {
		return piecePosition;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public List<Move> getLegalMoveList() {
		return legalMoveList;
	}

	@Override
	public String toString() {
		return "Piece [piecePosition=" + piecePosition + ", pieceColor=" + pieceColor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((legalMoveList == null) ? 0 : legalMoveList.hashCode());
		result = prime * result + ((pieceColor == null) ? 0 : pieceColor.hashCode());
		result = prime * result + piecePosition;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (legalMoveList == null) {
			if (other.legalMoveList != null)
				return false;
		} else if (!legalMoveList.equals(other.legalMoveList))
			return false;
		if (pieceColor != other.pieceColor)
			return false;
		if (piecePosition != other.piecePosition)
			return false;
		return true;
	}
}
