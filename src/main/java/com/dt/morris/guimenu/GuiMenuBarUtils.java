package com.dt.morris.guimenu;

import com.dt.morris.gui.Difficulty;
import com.dt.morris.gui.SingletonBoard;
import com.dt.morris.guiboard.Analize;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.FontWeight;

public class GuiMenuBarUtils {

	public static ObservableList<Analize> analizeList = FXCollections.observableArrayList();
	public static SimpleStringProperty moveList = new SimpleStringProperty();
	public static StringBuilder sbForMoveList = new StringBuilder();
	public static int moveCounter = 0;

	public GuiMenuBarUtils() {
		throw new UnsupportedOperationException("Do not instantiate this class, use statically.");
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

	public static Label getDifficultyLabel(Difficulty difficulty) {
		return new GuiLabel("Difficulty: " + difficulty.getName(), 460, 630, FontWeight.BOLD, 14);
	}
	
	public static Label getTableLabel() {
		return new GuiLabel("Analize Results", 970, 370, FontWeight.BOLD, 20);
	}

	public static TextArea moveListArea() {
		TextArea textArea = new TextArea();
		textArea.setLayoutX(800);
		textArea.setLayoutY(100);
		textArea.setPrefWidth(450);
		textArea.setPrefHeight(250);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		SingletonBoard.getBoard();
		textArea.textProperty().bind(moveList);
		return textArea;
	}

	public static TableView<Analize> analizeArea() {
		TableView<Analize> tableView = new TableView<Analize>();

		TableColumn<Analize, String> column1 = new TableColumn<>("Move Number");
		column1.setCellValueFactory(new PropertyValueFactory<>("totalMoves"));
		column1.setPrefWidth(149);
		column1.setStyle( "-fx-alignment: CENTER;");

		TableColumn<Analize, String> column2 = new TableColumn<>("Move");
		column2.setCellValueFactory(new PropertyValueFactory<>("move"));
		column2.setPrefWidth(149);
		column2.setStyle( "-fx-alignment: CENTER;");
		
		TableColumn<Analize, Integer> column3 = new TableColumn<>("Score");
		column3.setCellValueFactory(new PropertyValueFactory<>("score"));
		column3.setPrefWidth(149);
		column3.setStyle( "-fx-alignment: CENTER;");

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		tableView.setLayoutX(800);
		tableView.setLayoutY(400);
		tableView.setPrefWidth(450);
		tableView.setPrefHeight(280);
		tableView.setEditable(false);
		tableView.setItems(analizeList);

		return tableView;
	}

}
