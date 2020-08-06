package com.dt.morris.engine;

import java.util.List;

import com.dt.morris.board.PieceColor;

public class EasyDeleteStone {

	private final List<PieceColor> pieceColorList;
	private final PieceColor colorForDelete;
	private final boolean hasReservePiece;

	public EasyDeleteStone(List<PieceColor> pieceColorList, PieceColor color) {
		this.pieceColorList = pieceColorList;
		this.colorForDelete = color;
		this.hasReservePiece = checkReservePieces();
	}

	public int getDeletedPieceCoordinate() {
		for (int i = 0; i < 24; i++) {
			if (pieceColorList.get(i) == colorForDelete) {
				for (int candCoor : controlMillCandidate(i)) {					
					if (candCoor != -1) {
						if (hasReservePiece) {
							return i;
						}
						for (int candidates : getCandidateCoordinates(candCoor)) {
							if (pieceColorList.get(candidates) == colorForDelete) {
								return i;
							}
						}
					}
				}
			}
		}
		
		int[] criticCooridinates = { 4, 10, 13, 19 };
		for (int candCoor : criticCooridinates) {
			if (pieceColorList.get(candCoor) == colorForDelete && !BoardUtils.controlMill(pieceColorList, candCoor)){
				return candCoor;
			}
		}
		
		int[] lessCriticCoordinates = { 1, 7, 9, 11, 12, 14, 16, 22 };
		for (int candCoor : lessCriticCoordinates) {
			if (pieceColorList.get(candCoor) == colorForDelete && !BoardUtils.controlMill(pieceColorList, candCoor)){
				return candCoor;
			}
		}
		
		int[] otherCoordinates = {0, 2, 3, 5, 6, 8, 10, 15, 17, 18, 20, 21, 23};
		for (int candCoor : otherCoordinates) {
			if (pieceColorList.get(candCoor) == colorForDelete && !BoardUtils.controlMill(pieceColorList, candCoor)){
				return candCoor;
			}
		}
		
		while (true) {
			int random = (int) (Math.random() * 24);
			if (pieceColorList.get(random) == colorForDelete) {
				return random;
			}
		}
	}

	public int[] controlMillCandidate(int stoneCoorinate) {
		int[] candidateCordinates = new int[2];
		switch (stoneCoorinate) {
		case 0:
			candidateCordinates[0] = checkRow(0, 1, 2);
			candidateCordinates[1] = checkRow(0, 9, 21);
			return candidateCordinates;
		case 1:
			candidateCordinates[0] = checkRow(0, 1, 2);
			candidateCordinates[1] = checkRow(1, 4, 7);
			return candidateCordinates;
		case 2:
			candidateCordinates[0] = checkRow(0, 1, 2);
			candidateCordinates[1] = checkRow(2, 14, 23);
			return candidateCordinates;
		case 3:
			candidateCordinates[0] = checkRow(3, 4, 5);
			candidateCordinates[1] = checkRow(3, 10, 18);
			return candidateCordinates;
		case 4:
			candidateCordinates[0] = checkRow(3, 4, 5);
			candidateCordinates[1] = checkRow(4, 1, 7);
			return candidateCordinates;
		case 5:
			candidateCordinates[0] = checkRow(3, 4, 5);
			candidateCordinates[1] = checkRow(5, 13, 20);
			return candidateCordinates;
		case 6:
			candidateCordinates[0] = checkRow(6, 7, 8);
			candidateCordinates[1] = checkRow(6, 11, 15);
			return candidateCordinates;
		case 7:
			candidateCordinates[0] = checkRow(6, 7, 8);
			candidateCordinates[1] = checkRow(7, 4, 1);
			return candidateCordinates;
		case 8:
			candidateCordinates[0] = checkRow(6, 7, 8);
			candidateCordinates[1] = checkRow(8, 12, 17);
			return candidateCordinates;
		case 9:
			candidateCordinates[0] = checkRow(9, 0, 21);
			candidateCordinates[1] = checkRow(9, 10, 11);
			return candidateCordinates;
		case 10:
			candidateCordinates[0] = checkRow(10, 3, 18);
			candidateCordinates[1] = checkRow(9, 10, 11);
			return candidateCordinates;
		case 11:
			candidateCordinates[0] = checkRow(11, 6, 15);
			candidateCordinates[1] = checkRow(9, 10, 11);
			return candidateCordinates;
		case 12:
			candidateCordinates[0] = checkRow(12, 13, 14);
			candidateCordinates[1] = checkRow(12, 8, 17);
			return candidateCordinates;
		case 13:
			candidateCordinates[0] = checkRow(12, 13, 14);
			candidateCordinates[1] = checkRow(13, 5, 20);
			return candidateCordinates;
		case 14:
			candidateCordinates[0] = checkRow(12, 13, 14);
			candidateCordinates[1] = checkRow(14, 2, 23);
			return candidateCordinates;
		case 15:
			candidateCordinates[0] = checkRow(15, 16, 17);
			candidateCordinates[1] = checkRow(15, 11, 6);
			return candidateCordinates;
		case 16:
			candidateCordinates[0] = checkRow(15, 16, 17);
			candidateCordinates[1] = checkRow(16, 19, 22);
			return candidateCordinates;
		case 17:
			candidateCordinates[0] = checkRow(15, 16, 17);
			candidateCordinates[1] = checkRow(17, 12, 8);
			return candidateCordinates;
		case 18:
			candidateCordinates[0] = checkRow(18, 19, 20);
			candidateCordinates[1] = checkRow(18, 10, 3);
			return candidateCordinates;
		case 19:
			candidateCordinates[0] = checkRow(18, 19, 20);
			candidateCordinates[1] = checkRow(19, 16, 22);
			return candidateCordinates;
		case 20:
			candidateCordinates[0] = checkRow(18, 19, 20);
			candidateCordinates[1] = checkRow(20, 13, 5);
			return candidateCordinates;
		case 21:
			candidateCordinates[0] = checkRow(21, 22, 23);
			candidateCordinates[1] = checkRow(21, 9, 0);
			return candidateCordinates;
		case 22:
			candidateCordinates[0] = checkRow(21, 22, 23);
			candidateCordinates[1] = checkRow(22, 19, 16);
			return candidateCordinates;
		case 23:
			candidateCordinates[0] = checkRow(21, 22, 23);				
			candidateCordinates[1] = checkRow(23, 14, 2);
			return candidateCordinates;
		default:
			candidateCordinates[0] = -1;				
			candidateCordinates[1] = -1;
			return candidateCordinates;
		}
		
	}

	public int checkRow(int i, int j, int k) {
		PieceColor colorI = pieceColorList.get(i);
		PieceColor colorJ = pieceColorList.get(j);
		PieceColor colorK = pieceColorList.get(k);
		if (colorI == colorJ && colorK == PieceColor.EMPTY && colorI == colorForDelete) {
			return k;
		} 
		
		if (colorI == colorK && colorJ == PieceColor.EMPTY && colorI == colorForDelete) {
			return j;
		}
		
		if (colorK == colorJ && colorI == PieceColor.EMPTY && colorK == colorForDelete) {
			return i;
		}
		return -1;
	}

	private int[] getCandidateCoordinates(int piecePosition) {
		switch (piecePosition) {
		case 0:
			return new int[] { 1, 9 };
		case 1:
			return new int[] { 0, 2, 4 };
		case 2:
			return new int[] { 1, 14 };
		case 3:
			return new int[] { 4, 10 };
		case 4:
			return new int[] { 1, 3, 5, 7 };
		case 5:
			return new int[] { 4, 13 };
		case 6:
			return new int[] { 7, 11 };
		case 7:
			return new int[] { 4, 6, 8 };
		case 8:
			return new int[] { 7, 12 };
		case 9:
			return new int[] { 0, 10, 21 };
		case 10:
			return new int[] { 3, 9, 11, 18 };
		case 11:
			return new int[] { 6, 10, 15 };
		case 12:
			return new int[] { 8, 13, 17 };
		case 13:
			return new int[] { 5, 12, 14, 20 };
		case 14:
			return new int[] { 2, 13, 23 };
		case 15:
			return new int[] { 11, 16 };
		case 16:
			return new int[] { 15, 17, 19 };
		case 17:
			return new int[] { 12, 16 };
		case 18:
			return new int[] { 10, 19 };
		case 19:
			return new int[] { 16, 18, 20, 22 };
		case 20:
			return new int[] { 13, 19 };
		case 21:
			return new int[] { 9, 22 };
		case 22:
			return new int[] { 19, 21, 23 };
		case 23:
			return new int[] { 14, 22 };
		default:
			throw new RuntimeException("Not a legal move coordinate");
		}
	}
	
	private boolean checkReservePieces() {
		for (int i = 24 ; i<42 ; i++) {
			if (pieceColorList.get(i) == colorForDelete) {
				return true;
			}
		}
		return false;
	}
}
