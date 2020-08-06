package com.dt.morris.circle;

import javafx.scene.paint.Color;

public class CandidatePoint extends GuiPiece {

	public CandidatePoint(String id, double layoutX, double layoutY, boolean hasDragEvent) {
		super(id, layoutX, layoutY, hasDragEvent);
		setColor(Color.LIGHTGRAY);
		setVisible(false);
		setFill(Color.rgb(0, 0, 0, 0.15));
		setStroke(getColor());
		setViewOrder(-11);

		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
	}
}
