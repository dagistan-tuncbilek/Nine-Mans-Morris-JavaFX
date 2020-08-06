package com.dt.morris.gui;

import com.dt.morris.guimenu.GuiMenuBar;

public class SingletonMenuBar {

	public SingletonMenuBar() {
	}
	
	public static GuiMenuBar getMenu() {
		return SingletonHelper.INSTANCE;
	}

	private static class SingletonHelper {
		private static final GuiMenuBar INSTANCE = new GuiMenuBar();
	}

}
