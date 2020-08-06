package com.dt.morris.move;

import com.dt.morris.board.Board;

public class MoveFactory {

	private static final Move NULL_MOVE = new NullMove();

	private MoveFactory() {
		throw new RuntimeException("Not instantiatable!");
	}

	public static Move getNullMove() {
		return NULL_MOVE;
	}

	public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
		for (final Move move : board.getAllLegalMoves()) {
			if (move.getCurrentCoordinate() == currentCoordinate
					&& move.getDestinationCoordinate() == destinationCoordinate) {
				return move;
			}
		}
		return NULL_MOVE;
	}
}
