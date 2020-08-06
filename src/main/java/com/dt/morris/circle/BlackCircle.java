package com.dt.morris.circle;

import javafx.scene.paint.Color;

public class BlackCircle extends GuiPiece {

	public BlackCircle(String id, double layoutX, double layoutY, boolean hasDragEvent) {
		super(id, layoutX, layoutY, hasDragEvent);
		setColor(Color.BLACK);
		setVisible(true);
		setFill(getColor());
		setStroke(Color.BLACK);
		setViewOrder(-99);
	}
}
