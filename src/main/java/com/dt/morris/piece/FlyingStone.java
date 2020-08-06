package com.dt.morris.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dt.morris.board.PieceColor;
import com.dt.morris.engine.BoardUtils;
import com.dt.morris.engine.EasyDeleteStone;
import com.dt.morris.move.Move;
import com.dt.morris.move.MoveUtils;

public class FlyingStone extends Piece {
		
	public FlyingStone(List<PieceColor> pieceColorList, int piecePosition, PieceColor color) {
		super(pieceColorList, piecePosition, color);
	}

	@Override
	public List<Move> calculateLegalMoves(List<PieceColor> pieceColorList) {
		final List<Move> legalMovesList = new ArrayList<>();
		for (final int currentCandidateTile : MoveUtils.getFlyingStoneCandidateCoordinates()) {
			if (pieceColorList.get(currentCandidateTile) == PieceColor.EMPTY) {
				List<PieceColor> tempPieceColorList = new ArrayList<PieceColor>();
				tempPieceColorList.addAll(pieceColorList);
				tempPieceColorList.set(currentCandidateTile, getPieceColor());
				tempPieceColorList.set(getPiecePosition(), PieceColor.EMPTY);
				int deletedStoneCoordinate = -1;
				if (BoardUtils.controlMill(tempPieceColorList, currentCandidateTile)) {
					EasyDeleteStone easyDeleteStone = new EasyDeleteStone(tempPieceColorList,  BoardUtils.getOpenentColor(getPieceColor()));
					deletedStoneCoordinate = easyDeleteStone.getDeletedPieceCoordinate();
				}
				legalMovesList.add(new Move(this, currentCandidateTile, deletedStoneCoordinate));
			}
		}
		return Collections.unmodifiableList(legalMovesList);
	}



}
