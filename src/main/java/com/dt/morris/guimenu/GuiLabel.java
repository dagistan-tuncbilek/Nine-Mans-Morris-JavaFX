package com.dt.morris.guimenu;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GuiLabel extends Label{
	
	public GuiLabel(String text, double layoutX, double layoutY, FontWeight fw, int punto) {
		setText(text);
		setLayoutX(layoutX);
		setLayoutY(layoutY);
		setTextFill(Color.BLACK);
		setFont(Font.font("Times News Roman", fw, punto));
	}
}
