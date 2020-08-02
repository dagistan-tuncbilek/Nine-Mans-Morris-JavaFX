package com.dt.morris.guiboard;

import java.util.List;

import com.dt.morris.board.PieceColor;
import com.dt.morris.gui.AiMove;
import com.dt.morris.gui.AiMoveStatus;
import com.dt.morris.gui.Difficulty;
import com.dt.morris.gui.TurnStatus;
import com.dt.morris.guimenu.GuiMenuBarUtils;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class BoardPane extends Pane {

	private List<PieceColor> pieceColorList;
	private AiMoveStatus guiMoveStatus;
	private TurnStatus turnStatus;
	private Difficulty difficulty = Difficulty.NORMAL;
	private Group circleGroup;
	private boolean isWhiteHuman = true;
	public static int CIRCLE_GROUP_INDEX = 1;
	private int searchDepth = 3;
	private PieceColor deleteThisColor = PieceColor.EMPTY;

	public BoardPane() {
		this.pieceColorList = GuiBoardUtils.getInitialColorList(isWhiteHuman);
		this.guiMoveStatus = AiMoveStatus.DONE;
		this.turnStatus = TurnStatus.WHITE;
		setStyle("-fx-background-color:#cce7e8;");
		getChildren().add(GuiBoardUtils.getLines());
		getChildren().add(GuiBoardUtils.getCircles(this.isWhiteHuman));
		getChildren().add(GuiMenuBarUtils.getDifficultyLabel(difficulty)); // index 2
		getChildren().addAll(GuiMenuBarUtils.getRLabel(), GuiMenuBarUtils.getTextLabel(),
				GuiMenuBarUtils.getHeaderLabel());
		getChildren().add(GuiBoardUtils.getImage());
		getChildren().addAll(GuiMenuBarUtils.moveListArea(), GuiMenuBarUtils.analizeArea());
		GuiBoardUtils.playNewGameMove();
	}

	public void buildNewBord() {
		this.turnStatus = TurnStatus.WHITE;
		getChildren().set(CIRCLE_GROUP_INDEX, GuiBoardUtils.getCircles(isWhiteHuman));
		pieceColorList = GuiBoardUtils.getInitialColorList(isWhiteHuman);
		if (!isWhiteHuman && this.turnStatus == TurnStatus.WHITE) {
			System.out.println("Boarpane Thread");
			AiMove aiMove = new AiMove();
			aiMove.start();
		}
		GuiBoardUtils.playNewGameMove();
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		if (this.difficulty != difficulty) {
			this.difficulty = difficulty;
			getChildren().set(2, GuiMenuBarUtils.getDifficultyLabel(difficulty));
		}
	}

	public Group getCircleGroup() {
		return circleGroup;
	}

	public List<PieceColor> getPieceColorList() {
		return pieceColorList;
	}

	public void setPieceColorList(List<PieceColor> pieceColorList) {
		this.pieceColorList = pieceColorList;
	}

	public AiMoveStatus getGuiMoveStatus() {
		return guiMoveStatus;
	}

	public void setGuiMoveStatus(AiMoveStatus guiMoveStatus) {
		this.guiMoveStatus = guiMoveStatus;
	}

	public TurnStatus getTurnStatus() {
		return turnStatus;
	}

	public void setTurnStatus(TurnStatus turnStatus) {
		this.turnStatus = turnStatus;
	}

	public void changeTurnStatus() {
		this.turnStatus = this.turnStatus == TurnStatus.WHITE ? TurnStatus.BLACK : TurnStatus.WHITE;
	}

	public boolean isWhiteHuman() {
		return isWhiteHuman;
	}

	public void setWhiteHuman(boolean isWhiteHuman) {
		if (this.isWhiteHuman != isWhiteHuman) {
			this.isWhiteHuman = isWhiteHuman;
			this.turnStatus = TurnStatus.WHITE;
			buildNewBord();
		}
	}

	public int getSearchDepth() {
		return searchDepth;
	}

	public void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}

	public PieceColor getDeleteThisColor() {
		return deleteThisColor;
	}

	public void setDeleteThisColor(PieceColor deleteThisColor) {
		this.deleteThisColor = deleteThisColor;
	}
}