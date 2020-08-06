package com.dt.morris.player;

import java.util.List;

import com.dt.morris.board.Board;
import com.dt.morris.board.PieceColor;
import com.dt.morris.engine.MoveTransition;
import com.dt.morris.move.Move;
import com.dt.morris.piece.Piece;

public abstract class Player {

	private Board board;
	private PieceColor color;
	private List<Piece> gameBoard;
	private List<Move> legalMoves;
	private int pieceCount;
	
	public Player(Board board, PieceColor color, List<Piece> gameBoard) {
		this.board = board;
		this.color = color;
		this.gameBoard = gameBoard;
	}
	
	public abstract List<Move> calculateAllLegalMoves();
	
	public abstract MoveTransition makeMove (Move move);
	
	public abstract Player getOpponent();
	
	public PieceColor getColor() {
		return color;
	}

	public List<Piece> getGameBoard() {
		return gameBoard;
	}
	
	public Board getBoard() {
		return board;
	}

	public List<Move> getLegalMoves() {
		return legalMoves;
	}

	public void setLegalMoves(List<Move> allLegalMoves) {
		this.legalMoves = allLegalMoves;
	}

	public int getPieceCount() {
		return pieceCount;
	}

	public void setPieceCount(int pieceCount) {
		this.pieceCount = pieceCount;
	}
}
