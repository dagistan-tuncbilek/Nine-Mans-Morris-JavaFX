package com.dt.morris.engine;

import java.util.List;

import com.dt.morris.board.Board;
import com.dt.morris.board.PieceColor;
import com.dt.morris.move.Move;
import com.dt.morris.move.MoveFactory;

public final class MiniMax {

	private final BoardEvaluator evaluator;
	private final int searchDepth;
	private long boardsEvaluated;
	private long executionTime;

	public MiniMax(final int searchDepth) {
		this.evaluator = BoardEvaluator.get();
		this.boardsEvaluated = 0;
		this.searchDepth = searchDepth;
	}

	@Override
	public String toString() {
		return "Engine";
	}

	public long getNumBoardsEvaluated() {
		return this.boardsEvaluated;
	}

	public Move execute(List<PieceColor> pieceColorList, PieceColor playerColor) {
		Board board = new Board(pieceColorList, playerColor);
		final long startTime = System.currentTimeMillis();
		Move bestMove = MoveFactory.getNullMove();
		int highestSeenValue = Integer.MIN_VALUE;
		int lowestSeenValue = Integer.MAX_VALUE;
		int currentValue;
		final int intelligentSearchDepth = BoardUtils.getIntelligentSearchDepth(board, this.searchDepth, playerColor);
		System.out.println(board.currentPlayer() + " THINKING with depth = " + intelligentSearchDepth);
		List<Move> legalMoves = board.currentPlayer().getLegalMoves();
		int moveCounter = 1;
		final int numMoves = legalMoves.size();
		for (final Move move : legalMoves) {
			final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
			currentValue = board.currentPlayer().getColor().isWhite()
					? min(moveTransition.getBoard(), intelligentSearchDepth - 1)
					: max(moveTransition.getBoard(), intelligentSearchDepth - 1);
//			System.out.println("\t" + toString() + " analyzing move (" + moveCounter + "/" + numMoves + ") " + move
//					+ " scores " + currentValue);

//			BoardViever.engineTextArea.append("\t" + toString() + " analyzing move" + "\t" + "(" + moveCounter + " / "
//					+ numMoves + ") " + "\t\t" + GuiUtils.setCoordinateToText(move.getCurrentCoordinate()) + "-"
//					+ GuiUtils.setCoordinateToText(move.getDestinationCoordinate()) + "    " + "\t" + " Score" + "\t:  "
//					+ currentValue + "\n");
//				analysList.add(new int[] {currentValue, move.getCurrentCoordinate(), move.getDestinationCoordinate()});				
			if (board.currentPlayer().getColor().isWhite() && currentValue >= highestSeenValue) {
				highestSeenValue = currentValue;
				bestMove = move;
			} else if (board.currentPlayer().getColor().isBlack() && currentValue <= lowestSeenValue) {
				lowestSeenValue = currentValue;
				bestMove = move;
			}
			moveCounter++;
		}

		this.executionTime = System.currentTimeMillis() - startTime;
		System.out.printf("%s SELECTS %s [#boards = %d, time taken = %d ms]\n", board.currentPlayer(), bestMove,
				this.boardsEvaluated, this.executionTime,
				(1000 * ((double) this.boardsEvaluated / this.executionTime)));
//		String str = "\t" + board.currentPlayer() + " selects \t" + GuiUtils.setCoordinateToText(bestMove.getCurrentCoordinate()) + "-"
//				+ GuiUtils.setCoordinateToText(bestMove.getDestinationCoordinate()) + "\t    Evaluated Boards: "
//				+ this.boardsEvaluated + "\t Time : " + this.executionTime + " ms" + "\n" + "\n";
//		BoardViever.engineTextArea.insert(0, str);
		if (this.boardsEvaluated == Integer.MIN_VALUE || this.boardsEvaluated == Integer.MAX_VALUE) {
			System.out.println("somethings wrong with the # of boards evaluated!");
		}
//		saveTextArea(playerColor);
		return bestMove;
	}

//	private void saveTextArea(StoneColor playerColor) {
//		Collections.sort(analysList, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] i, int[] j) {
//				if (playerColor == StoneColor.BLACK) {
//					return i[0] - j[0];
//				} else {
//					return j[0] - i[0];
//				}
//			}		
//		});			
//				
//		for(int[] i : analysList) {
//			BoardViever.engineTextArea.append("Move :" + GuiUtils.setCoordinateToText(i[1]) + "-" + GuiUtils.setCoordinateToText(i[2]) +
//					"\t" + "Analyze result : " + i[0] + "\n");
//		}
//	}

	private int min(final Board board, final int depth) {
		if (depth == 0) {
			this.boardsEvaluated++;
			return this.evaluator.evaluate(board, depth);
		}
		if (isEndGameScenario(board)) {
			return this.evaluator.evaluate(board, depth);
		}
		int lowestSeenValue = Integer.MAX_VALUE;
		for (final Move move : board.currentPlayer().getLegalMoves()) {
			final MoveTransition moveTransition = board.currentPlayer().makeMove(move);

			final int currentValue = max(moveTransition.getBoard(), depth - 1);
			if (currentValue <= lowestSeenValue) {
				lowestSeenValue = currentValue;
			}
		}
		return lowestSeenValue;
	}

	private int max(final Board board, final int depth) {
		if (depth == 0) {
			this.boardsEvaluated++;
			return this.evaluator.evaluate(board, depth);
		}
		if (isEndGameScenario(board)) {
			return this.evaluator.evaluate(board, depth);
		}
		int highestSeenValue = Integer.MIN_VALUE;
		for (final Move move : board.currentPlayer().getLegalMoves()) {
			final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
			final int currentValue = min(moveTransition.getBoard(), depth - 1);
			if (currentValue >= highestSeenValue) {
				highestSeenValue = currentValue;
			}
		}
		return highestSeenValue;
	}

	private static boolean isEndGameScenario(final Board board) {
		return board.currentPlayer().getPieceCount() < 3 || board.currentPlayer().getOpponent().getPieceCount() < 3;
	}

}
