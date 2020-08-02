package com.dt.morris.move;

import com.dt.morris.board.Board;
import com.dt.morris.piece.Piece;

public class Move {

	protected final Piece piece;
	protected final int destinationCoordinate;
	private final int deletedPieceCoordinate;

	public Move(Piece piece, int destinationCoordinate, int deletedPieceCoordinate) {
		this.piece = piece;
		this.destinationCoordinate = destinationCoordinate;
		this.deletedPieceCoordinate = deletedPieceCoordinate;
	}

	public Board execute() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getCurrentCoordinate() {
		return getPiece().getPiecePosition();
	}

	public Piece getPiece() {
		return piece;
	}

	public int getDestinationCoordinate() {
		return destinationCoordinate;
	}
	
	public int getDeletedPieceCoordinate() {
		return deletedPieceCoordinate;
	}

	@Override
	public String toString() {
		return "Move [pieceCoordinate=" + piece.getPiecePosition() + ", destinationCoordinate=" + destinationCoordinate + ", deletedPieceCoordinate="
				+ deletedPieceCoordinate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destinationCoordinate;
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
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
		Move other = (Move) obj;
		if (destinationCoordinate != other.destinationCoordinate)
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (piece.getPiecePosition() != other.piece.getPiecePosition())
			return false;
		return true;
	}

}
