package com.dt.morris.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dt.morris.move.Move;
import com.dt.morris.piece.EmptyTile;
import com.dt.morris.piece.FlyingStone;
import com.dt.morris.piece.Piece;
import com.dt.morris.piece.Stone;
import com.dt.morris.player.BlackPlayer;
import com.dt.morris.player.Player;
import com.dt.morris.player.WhitePlayer;

public class Board {

	private final List<PieceColor> pieceColorList;
	private final List<Piece> gameBoard;
	private PieceColor currentPlayer;
	Player whitePlayer;
	Player blackPlayer;

	public Board (List<PieceColor> pieceColorList, PieceColor playerColor) {
		this.pieceColorList = pieceColorList;
		this.gameBoard = createGameBoard();
		this.currentPlayer = playerColor;
		whitePlayer = new WhitePlayer(this, PieceColor.WHITE, this.gameBoard);
		blackPlayer = new BlackPlayer (this, PieceColor.BLACK, gameBoard);
	}

	private List<Piece> createGameBoard() {
		List<Piece> pieceList = new ArrayList<Piece>();
		boolean isWhiteFlying = checkIsPlayerFlying(PieceColor.WHITE);
		boolean isBlackFlying = checkIsPlayerFlying(PieceColor.BLACK);
		for (int i=0 ; i<42 ; i++) {
			switch (pieceColorList.get(i)) {
			case WHITE:
				if (isWhiteFlying || i > 23) {
					pieceList.add(new FlyingStone(pieceColorList, i, PieceColor.WHITE));
				} else {
					pieceList.add(new Stone(pieceColorList, i, PieceColor.WHITE));
				}
				break;
				
			case BLACK:
				if (isBlackFlying || i > 23) {
					pieceList.add(new FlyingStone(pieceColorList, i, PieceColor.BLACK));
				} else {
					pieceList.add(new Stone(pieceColorList, i, PieceColor.BLACK));
				}
				break;
			case EMPTY:
				pieceList.add(new EmptyTile(i));
			default:
				break;
			}
		}
		return Collections.unmodifiableList(pieceList);
	}

	public List<Piece> getGameBoard() {
		return gameBoard;
	}
	
	public boolean checkIsPlayerFlying(PieceColor playerColor) {
		int count = 0;
		for (PieceColor color : pieceColorList) {
			if (color == playerColor) {
				count += 1;
				if (count > 3)
					return false;
			}
		}
		return true;
	}

	public List<Move> getAllLegalMoves() {
		if (currentPlayer == PieceColor.WHITE) {
			return whitePlayer.getLegalMoves();
		} else {
			return blackPlayer.getLegalMoves();
		}
	}

	public Player currentPlayer() {
		return currentPlayer == PieceColor.WHITE ? whitePlayer : blackPlayer;
	}

	public List<PieceColor> getPieceColorList() {
		return pieceColorList;
	}

	public PieceColor getCurrentPlayerColor() {
		return currentPlayer;
	}

	public Player whitePlayer() {
		return this.whitePlayer;
	}

	public Player blackPlayer() {
		return blackPlayer;
	}
}
