package com.dt.morris.guimenu;

import com.dt.morris.gui.Difficulty;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GuiMenuBarUtils {
	
	public GuiMenuBarUtils() {
	    throw new UnsupportedOperationException(
	            "Do not instantiate this class, use statically.");
	}
	
	public static Label getHeaderLabel() {
		return new GuiLabel("Nine Man's Morris", 400, 10, FontWeight.EXTRA_BOLD, 40);
	}

	public static Label getTextLabel() {
		return new GuiLabel("Moves", 1000, 70, FontWeight.EXTRA_BOLD, 20);
	}

	public static Label getRLabel() {
		return new GuiLabel("R: Reserve Stone", 800, 350, FontWeight.SEMI_BOLD, 14);
	}
	
	public static Label getDifficultyLabel (Difficulty difficulty) {
		return new GuiLabel("Difficulty: " + difficulty.getName(), 460, 630, FontWeight.BOLD, 14);
	}
	
	public static TextArea moveListArea() {
		TextArea textArea = new TextArea();
		textArea.setLayoutX(800);
		textArea.setLayoutY(100);
		textArea.setPrefWidth(450);
		textArea.setPrefHeight(250);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		return textArea;
	}
	
	public static TextArea analizeArea() {
		TextArea analizeArea = new TextArea();
		analizeArea.setLayoutX(800);
		analizeArea.setLayoutY(400);
		analizeArea.setPrefWidth(450);
		analizeArea.setPrefHeight(280);
		analizeArea.setEditable(false);
		analizeArea.setWrapText(true);
		analizeArea.setFont(Font.font("Times News Roman", FontWeight.SEMI_BOLD, 12));
		return analizeArea;
	}
}
