package com.dt.morris.engine;

import com.dt.morris.board.Board;
import com.dt.morris.player.Player;

public final class BoardEvaluator {

	private final static int MOBILITY_MULTIPLIER = 2;
	private static final BoardEvaluator INSTANCE = new BoardEvaluator();

	private BoardEvaluator() {
	}

	public static BoardEvaluator get() {
		return INSTANCE;
	}

	public int evaluate(final Board board, final int depth) {
		return score(board.whitePlayer(), depth) - score(board.blackPlayer(), depth);
	}

	private static int score(final Player player, final int depth) {
		return mobility(player) + pieceEvaluations(player) + depthBonus(depth) + gameOver(player)
				+ strategicCoordinates(player);
	}

	private static int strategicCoordinates(Player player) {
		int bonus = 0;
		int[] criticCooridinates = {4, 10, 13, 19};
		for (int coordinate : criticCooridinates) {
			if (player.getBoard().getPieceColorList().get(coordinate) == player.getColor()) {
				bonus += 3;
			}
		}
		int[] lessCriticCoordinates = { 1, 7, 9, 11, 12, 14, 16, 22 };
		for (int coordinate : lessCriticCoordinates) {
			if (player.getBoard().getPieceColorList().get(coordinate) == player.getColor()) {
				bonus += 1;
			}
		}
		return bonus;
	}

	private static int gameOver(Player player) {
		if (player.getPieceCount() < 3)
			return -1000;
		if (player.getOpponent().getPieceCount() < 3)
			return 1000;
		return 0;
	}

	private static int pieceEvaluations(final Player player) {
		return player.getPieceCount() * 100;
	}

	private static int mobility(final Player player) {
		if (player.getOpponent().getLegalMoves().size()==0) {
			return 300;
		} else if(player.getLegalMoves().size() == 0) {
			return -1000;
		} else {
			return MOBILITY_MULTIPLIER * (player.getLegalMoves().size() - player.getOpponent().getLegalMoves().size()*2);
		}	
	}

	private static int depthBonus(final int depth) {
		return depth == 0 ? 1 : 3 * depth;
	}
}
