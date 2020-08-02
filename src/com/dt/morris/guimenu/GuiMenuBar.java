package com.dt.morris.guimenu;

import com.dt.morris.gui.Difficulty;
import com.dt.morris.gui.SingletonBoard;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public final class GuiMenuBar extends MenuBar {

	public GuiMenuBar() {
		
		Menu menu = new Menu("Menu");
		menu.getItems().addAll(new GuiMenuItem("New Game", newGameEvent), new GuiMenuItem("Exit", exitEvent));

		Menu colorMenu = new Menu("Player Color");
		colorMenu.getItems().addAll(new GuiMenuItem("White", colorEventWhite),
				new GuiMenuItem("Black", colorEventBlack));

		Menu difficultyMenu = new Menu("Difficulty");
		difficultyMenu.getItems().addAll(new GuiMenuItem("Very Easy", easyDifficulty),
				new GuiMenuItem("Easy", veryEasyDifficulty), new GuiMenuItem("Normal", normalDifficulty),
				new GuiMenuItem("Hard", hardDifficulty), new GuiMenuItem("Very Hard", veryHardDifficulty));

		getMenus().addAll(menu, colorMenu, difficultyMenu);

	}

	EventHandler<ActionEvent> exitEvent = (e -> Platform.exit());
	
	EventHandler<ActionEvent> newGameEvent = (e -> SingletonBoard.getBoard().buildNewBord());

	EventHandler<ActionEvent> colorEventWhite = (e -> SingletonBoard.getBoard().setWhiteHuman(true));
	
	EventHandler<ActionEvent> colorEventBlack = (e -> SingletonBoard.getBoard().setWhiteHuman(false));

	EventHandler<ActionEvent> easyDifficulty = (e -> SingletonBoard.getBoard().setDifficulty(Difficulty.VERY_EASY));
	
	EventHandler<ActionEvent> veryEasyDifficulty = (e -> SingletonBoard.getBoard().setDifficulty(Difficulty.EASY));
	
	EventHandler<ActionEvent> normalDifficulty = (e -> SingletonBoard.getBoard().setDifficulty(Difficulty.NORMAL));
	
	EventHandler<ActionEvent> hardDifficulty = (e -> SingletonBoard.getBoard().setDifficulty(Difficulty.HARD));
	
	EventHandler<ActionEvent> veryHardDifficulty = (e -> SingletonBoard.getBoard().setDifficulty(Difficulty.VERY_HARD));
}
