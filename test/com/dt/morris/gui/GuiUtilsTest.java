package com.dt.morris.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dt.morris.board.PieceColor;
import com.dt.morris.guimenu.GuiMenuBarUtils;

class GuiUtilsTest {

	private List<PieceColor> pieceColors1;
	private List<PieceColor> pieceColors2;
	
	@BeforeEach
	void init() {
		pieceColors1 = Arrays.asList(PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE);

		pieceColors2 = Arrays.asList(PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY, PieceColor.EMPTY,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE,
				PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.WHITE, PieceColor.BLACK,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK,
				PieceColor.BLACK, PieceColor.BLACK, PieceColor.BLACK);
	}

//	@Test
//	@DisplayName("Colorlist ititilaize when white begins")
//	void getInitialColorListWhite() throws NoSuchMethodException, SecurityException, IllegalAccessException,
//			IllegalArgumentException, InvocationTargetException {
//
//		Method method = GuiUtils.class.getDeclaredMethod("getInitialColorList", PieceColor.class);
//		method.setAccessible(true);
//		GuiUtils guiUtils = new GuiUtils();
//		@SuppressWarnings("unchecked")
//		List<PieceColor> expected = (List<PieceColor>) method.invoke(guiUtils, PieceColor.WHITE);
//		assertEquals(expected, pieceColors1);
//	}
//	
//	@Test
//	@DisplayName("Colorlist ititilaize when black begins")
//	void getInitialColorListBlack() throws NoSuchMethodException, SecurityException, IllegalAccessException,
//			IllegalArgumentException, InvocationTargetException {
//
//		Method method = GuiUtils.class.getDeclaredMethod("getInitialColorList", PieceColor.class);
//		method.setAccessible(true);
//		GuiUtils guiUtils = new GuiUtils();
//		@SuppressWarnings("unchecked")
//		List<PieceColor> expected = (List<PieceColor>) method.invoke(guiUtils, PieceColor.BLACK);
//
//		assertEquals(expected, pieceColors2);
//	}
}
