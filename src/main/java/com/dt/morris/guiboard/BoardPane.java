package com.dt.morris.guiboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dt.morris.board.PieceColor;
import com.dt.morris.gui.AiMove;
import com.dt.morris.gui.AiMoveStatus;
import com.dt.morris.gui.Difficulty;
import com.dt.morris.gui.TurnStatus;
import com.dt.morris.guimenu.GuiMenuBarUtils;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BoardPane extends Pane {

	private List<PieceColor> pieceColorList;
	private AiMoveStatus aiMoveStatus;
	private TurnStatus turnStatus;
	private Difficulty difficulty = Difficulty.NORMAL;
	private int CIRCLE_GROUP_INDEX;
	private PieceColor deleteThisColor = PieceColor.EMPTY;
	private int selectedPiece = -1;
	private boolean isWhiteHuman = true;
	private boolean isWhiteFlying = false;
	private boolean isBlackFlying = false;
	private List<List<PieceColor>> wholeGameMoveList;

	public BoardPane() {
		this.pieceColorList = GuiBoardUtils.getInitialColorList(isWhiteHuman);
		wholeGameMoveList = new ArrayList<List<PieceColor>>();
		wholeGameMoveList.add(new ArrayList<>(pieceColorList));
		this.aiMoveStatus = AiMoveStatus.DONE;
		this.turnStatus = TurnStatus.WHITE;
		setStyle("-fx-background-color:#cce7e8;");
		getChildren().add(GuiBoardUtils.getLines());
		Group circleGroup = GuiBoardUtils.getCircles(this.isWhiteHuman);
		getChildren().add(circleGroup);
		CIRCLE_GROUP_INDEX = getChildren().indexOf(circleGroup);
		getChildren().add(GuiMenuBarUtils.getDifficultyLabel(difficulty)); // index 2
		getChildren().addAll(GuiMenuBarUtils.getRLabel(), GuiMenuBarUtils.getTextLabel(),
				GuiMenuBarUtils.getHeaderLabel());
		getChildren().add(GuiBoardUtils.getImage());
		getChildren().addAll(GuiMenuBarUtils.moveListArea(), GuiMenuBarUtils.analizeArea()); // index 8
		getChildren().add(GuiMenuBarUtils.getTableLabel());

		new MediaPlayer(new Media(new File("./assets/start.wav").toURI().toString())).play();

	}

	public void buildNewBord() {
		this.turnStatus = TurnStatus.WHITE;
		getChildren().set(CIRCLE_GROUP_INDEX, GuiBoardUtils.getCircles(isWhiteHuman));
		pieceColorList = GuiBoardUtils.getInitialColorList(isWhiteHuman);
		wholeGameMoveList.clear();
		wholeGameMoveList.add(new ArrayList<>(pieceColorList));
		setAiMoveStatus(AiMoveStatus.DONE);
		isWhiteFlying = false;
		isWhiteFlying = false;
		GuiMenuBarUtils.moveCounter = 0;
		deleteThisColor = PieceColor.EMPTY;
		if (!isWhiteHuman && this.turnStatus == TurnStatus.WHITE) {
			AiMove aiMove = new AiMove();
			aiMove.start();
		}
		GuiBoardUtils.playAudio("newgame");
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

	public List<PieceColor> getPieceColorList() {
		return pieceColorList;
	}

	public void setPieceColorList(List<PieceColor> pieceColorList) {
		this.pieceColorList = pieceColorList;
	}

	public AiMoveStatus getAiMoveStatus() {
		return this.aiMoveStatus;
	}

	public void setAiMoveStatus(AiMoveStatus aiMoveStatus) {
		this.aiMoveStatus = aiMoveStatus;
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

	public PieceColor getDeleteThisColor() {
		return deleteThisColor;
	}

	public void setDeleteThisColor(PieceColor deleteThisColor) {
		this.deleteThisColor = deleteThisColor;
	}

	public boolean isWhiteFlying() {
		return isWhiteFlying;
	}

	public void setWhiteFlying(boolean isWhiteFlying) {
		this.isWhiteFlying = isWhiteFlying;
	}

	public boolean isBlackFlying() {
		return isBlackFlying;
	}

	public void setBlackFlying(boolean isBlackFlying) {
		this.isBlackFlying = isBlackFlying;
	}

	public int getCIRCLE_GROUP_INDEX() {
		return CIRCLE_GROUP_INDEX;
	}

	public int getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(int selectedPiece) {
		this.selectedPiece = selectedPiece;
	}

	public List<List<PieceColor>> getWholeGameMoveList() {
		return wholeGameMoveList;
	}

	public void setWholeGameMoveList(List<List<PieceColor>> wholeGameMoveList) {
		this.wholeGameMoveList = wholeGameMoveList;
	}
}