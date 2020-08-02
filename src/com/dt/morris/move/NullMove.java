package com.dt.morris.move;

import com.dt.morris.board.Board;

class NullMove extends Move {

	public NullMove() {
		super(null, -1, -1);
	}

	@Override
	public int getCurrentCoordinate() {
		return -1;
	}

	@Override
	public int getDestinationCoordinate() {
		return -1;
	}

	@Override
	public Board execute() {
		throw new RuntimeException("cannot execute null move!");
	}

	@Override
	public String toString() {
		return "Null Move";
	}
}
