package com.dt.morris.guiboard;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dt.morris.board.PieceColor;
import com.dt.morris.gui.SingletonBoard;



class GuiBoardUtilsTest {

	List<PieceColor> pieceColorList1;

	@BeforeEach
	void initilaize() {
		pieceColorList1 = Arrays.asList(PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE);
	}

	
	@Test
	void getInitialColorList() {
		
		assertEquals(GuiBoardUtils.getInitialColorList(true), pieceColorList1);
	}

}
