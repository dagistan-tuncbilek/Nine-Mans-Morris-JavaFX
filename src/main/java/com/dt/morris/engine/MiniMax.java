package com.dt.morris.engine;

import java.util.List;

import com.dt.morris.board.Board;
import com.dt.morris.board.PieceColor;
import com.dt.morris.gui.SingletonBoard;
import com.dt.morris.guiboard.Analize;
import com.dt.morris.guiboard.GuiBoardUtils;
import com.dt.morris.guimenu.GuiMenuBarUtils;
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
		// For test case
		try {
			GuiMenuBarUtils.analizeList.clear();
		}catch (ExceptionInInitializerError e) {
			System.out.println("It must be test case");
		}

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
			try {
				addObservableList(moveCounter, moveCounter, numMoves, GuiBoardUtils.getTextCoordinat(move.getCurrentCoordinate()),
						GuiBoardUtils.getTextCoordinat(move.getDestinationCoordinate()), currentValue, board);
			} catch (Exception e) {
				System.out.println("It must be test case");
			}

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

		// For test case
		try {
			appendToStringBuilder(bestMove.getCurrentCoordinate(), bestMove.getDestinationCoordinate(),
					bestMove.getDeletedPieceCoordinate());
			GuiMenuBarUtils.moveList.set(GuiMenuBarUtils.sbForMoveList.toString());
		} catch (ExceptionInInitializerError e) {
			System.out.println("It must be test case");
		}

		

		if (this.boardsEvaluated == Integer.MIN_VALUE || this.boardsEvaluated == Integer.MAX_VALUE) {
			System.out.println("somethings wrong with the # of boards evaluated!");
		}
		return bestMove;
	}

	private void addObservableList(int i, int moveCounter, int numMoves, String firstMove, String secondMove, int score, Board board) {
		GuiMenuBarUtils.analizeList.add(new Analize(i + "/" + numMoves, firstMove + "-" + secondMove, score));
		if (board.currentPlayer().getColor() == PieceColor.WHITE) {
			GuiMenuBarUtils.analizeList.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
		} else {
			GuiMenuBarUtils.analizeList.sort((a, b) -> Integer.compare(a.getScore(), b.getScore()));
		}
		
	}

	private void appendToStringBuilder(int currentCoordinate, int destinationCoordinate, int deletedPieceCoordinate) {
		if (!SingletonBoard.getBoard().isWhiteHuman()) {
			GuiMenuBarUtils.sbForMoveList.append(++GuiMenuBarUtils.moveCounter + ". ");
		}
		GuiMenuBarUtils.sbForMoveList.append(GuiBoardUtils.getTextCoordinat(currentCoordinate) + "-");
		GuiMenuBarUtils.sbForMoveList.append(GuiBoardUtils.getTextCoordinat(destinationCoordinate) + "  ");
		if (deletedPieceCoordinate > -1) {
			GuiMenuBarUtils.sbForMoveList
					.append("(Mill)->" + GuiBoardUtils.getTextCoordinat(deletedPieceCoordinate) + "x  ");
		}
	}

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
