package com.dt.morris.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dt.morris.board.PieceColor;
import com.dt.morris.move.Move;
import com.dt.morris.piece.FlyingStone;


class MiniMaxTest {

	@Test
	void minimaxExecute(){
		MiniMax miniMax = new MiniMax(3);
		List<PieceColor> colorList = Arrays.asList(PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE);
	
		Move expected = miniMax.execute(colorList, PieceColor.WHITE);
		System.out.println(expected.getPiece());
		System.out.println(expected);
		
		assertEquals(expected, new Move(new FlyingStone(colorList, 41, PieceColor.WHITE), 19 , -1));
	}
}
