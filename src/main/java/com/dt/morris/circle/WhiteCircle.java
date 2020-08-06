package com.dt.morris.circle;

import javafx.scene.paint.Color;

public class WhiteCircle extends GuiPiece {

	public WhiteCircle(String id, double layoutX, double layoutY, boolean hasDragEvent) {
		super(id, layoutX, layoutY, hasDragEvent);
		setColor(Color.WHITE);
		setVisible(true);
		setFill(getColor());
		setStroke(Color.BLACK);
		setViewOrder(-99);
	}
}
