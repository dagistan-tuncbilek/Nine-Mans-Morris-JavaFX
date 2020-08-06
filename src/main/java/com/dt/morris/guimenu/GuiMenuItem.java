package com.dt.morris.guimenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;


public class GuiMenuItem extends MenuItem {

	public GuiMenuItem(String itemName, EventHandler<ActionEvent> e) {
		setText(itemName);
		setOnAction(e);
	}
}
