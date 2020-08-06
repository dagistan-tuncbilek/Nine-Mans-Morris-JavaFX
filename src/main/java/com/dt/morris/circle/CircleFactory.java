package com.dt.morris.circle;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CircleFactory {

	public static GuiPiece getCircle(Paint color, String id, double layoutX, double layoutY, Boolean isWhiteHuman) {

		if (Color.WHITE == color) {
			if (isWhiteHuman) {
				return new WhiteCircle(id, layoutX, layoutY, true);
			} else {
				return new WhiteCircle(id, layoutX, layoutY, false);
			}			
		} else if (Color.BLACK == color) {
			if (isWhiteHuman) {
				return new BlackCircle(id, layoutX, layoutY, false);
			} else {
				return new BlackCircle(id, layoutX, layoutY, true);
			}
		} else if (Color.LIGHTGRAY == color) {
			return new CandidatePoint(id, layoutX, layoutY, false);
		}
		return null;
	}
}
