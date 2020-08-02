package com.dt.morris.engine;

import java.util.List;

import com.dt.morris.board.Board;
import com.dt.morris.board.PieceColor;

public class MoveTransition {
	
	Board board;
	List<PieceColor> pieceColorList;
	PieceColor currentPlayerColor;
	
	public MoveTransition(List<PieceColor> pieceColorList, PieceColor playerColor) {
		this.pieceColorList = pieceColorList;
		this.currentPlayerColor = playerColor;
		this.board = new Board(pieceColorList, playerColor);	
	}

	public Board getBoard() {
		return board;
	}


	public PieceColor getCurrentPlayerColor() {
		return currentPlayerColor;
	}

	@Override
	public String toString() {
		return "MoveTransition [currentPlayerColor=" + currentPlayerColor +"]";
	}
}
