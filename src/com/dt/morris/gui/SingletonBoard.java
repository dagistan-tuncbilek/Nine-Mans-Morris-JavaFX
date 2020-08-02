package com.dt.morris.gui;

import com.dt.morris.guiboard.BoardPane;

public class SingletonBoard {

	public SingletonBoard() {
	}
	
	public static BoardPane getBoard() {
		return SingletonHelper.INSTANCE;
	}

	private static class SingletonHelper {
		private static final BoardPane INSTANCE = new BoardPane();
	}

}
