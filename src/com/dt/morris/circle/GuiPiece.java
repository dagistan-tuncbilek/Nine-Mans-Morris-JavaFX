package com.dt.morris.circle;

import com.dt.morris.board.PieceColor;
import com.dt.morris.engine.BoardUtils;
import com.dt.morris.gui.AiMove;
import com.dt.morris.gui.AiMoveStatus;
import com.dt.morris.gui.SingletonBoard;
import com.dt.morris.guiboard.GuiBoardUtils;
import com.dt.morris.guimenu.GuiMenuBarUtils;
import com.dt.morris.move.MoveUtils;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class GuiPiece extends Circle {

	protected double mouseX, mouseY;
	protected double oldX, oldY;
	private Color color;

	public GuiPiece(String id, double layoutX, double layoutY, boolean hasDragEvent) {
		setId(id);
		relocate(layoutX, layoutY);
		setRadius(20);
		setStroke(Color.BLACK);
		this.oldX = getLayoutX();
		this.oldY = getLayoutY();

		setOnMouseReleased(e -> {
			if (hasDragEvent && SingletonBoard.getBoard().getAiMoveStatus() == AiMoveStatus.DONE) {
				int[] candidatePoints;
				if ((getColor() == Color.WHITE && SingletonBoard.getBoard().isWhiteFlying())
						|| (getColor() == Color.BLACK && SingletonBoard.getBoard().isBlackFlying())) {
					candidatePoints = MoveUtils.getFlyingStoneCandidateCoordinates();
				} else {
					candidatePoints = MoveUtils.getStoneCandidateCoordinates(Integer.parseInt(getId()));
				}
				Group group = (Group) SingletonBoard.getBoard().getChildren()
						.get(SingletonBoard.getBoard().getCIRCLE_GROUP_INDEX());
				for (int targetCoord : candidatePoints) {
					Circle targetCircle = (Circle) group.getChildren().get(targetCoord);
					if (targetCircle.getClass() == CandidatePoint.class) {
						targetCircle.setVisible(false);
					}
					if ((e.getSceneX() - targetCircle.getLayoutX()) - 20 < 25
							&& (e.getSceneX() - targetCircle.getLayoutX()) > -25
							&& (e.getSceneY() - targetCircle.getLayoutY() - 20) - 25 < 20
							&& (e.getSceneY() - targetCircle.getLayoutY() - 20) > -25) {

						int currentCoord = group.getChildren().indexOf(this);
						SingletonBoard.getBoard().getPieceColorList().set(targetCoord,
								SingletonBoard.getBoard().getPieceColorList().get(currentCoord));
						SingletonBoard.getBoard().getPieceColorList().set(currentCoord, PieceColor.EMPTY);

						group.getChildren().set(targetCoord,
								CircleFactory.getCircle(getColor(), targetCircle.getId(), targetCircle.getLayoutX(),
										targetCircle.getLayoutY(), SingletonBoard.getBoard().isWhiteHuman()));
						group.getChildren().set(currentCoord,
								CircleFactory.getCircle(Color.LIGHTGRAY, getId(), this.oldX, this.oldY, null));
						
						if (SingletonBoard.getBoard().isWhiteHuman()) {
							GuiMenuBarUtils.sbForMoveList.append(++GuiMenuBarUtils.moveCounter + ". ");	
						}
						
						GuiMenuBarUtils.sbForMoveList.append(GuiBoardUtils.getTextCoordinat(currentCoord) + "-");
						GuiMenuBarUtils.sbForMoveList.append(GuiBoardUtils.getTextCoordinat(targetCoord) + "  ");
						GuiMenuBarUtils.moveList.set(GuiMenuBarUtils.sbForMoveList.toString());
						boolean isMill = BoardUtils.controlMill(SingletonBoard.getBoard().getPieceColorList(),
								targetCoord);

						if (isMill) {
							GuiBoardUtils.playAudio("delete");
						} else {
							GuiBoardUtils.playAudio("normal");
						}
						
						if (isMill) {
							SingletonBoard.getBoard().setDeleteThisColor(
									getColor() == Color.WHITE ? PieceColor.BLACK : PieceColor.WHITE);
						} else {
							SingletonBoard.getBoard().changeTurnStatus();
							if (!GuiBoardUtils.isGameEnded()) {
								AiMove aiMove = new AiMove();
								aiMove.start();
							}
						}
					}
				}
				relocate(this.oldX - 20, this.oldY - 20);
			}
		});

		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
			if (SingletonBoard.getBoard().getAiMoveStatus() == AiMoveStatus.DONE && !GuiBoardUtils.isGameEnded()) {
				if (SingletonBoard.getBoard().getDeleteThisColor() == PieceColor.EMPTY) {
					if (hasDragEvent && SingletonBoard.getBoard().getAiMoveStatus() != AiMoveStatus.IN_PROCESS) {

						int[] candidatePoints;
						if ((getColor() == Color.WHITE && SingletonBoard.getBoard().isWhiteFlying())
								|| (getColor() == Color.BLACK && SingletonBoard.getBoard().isBlackFlying())) {
							candidatePoints = MoveUtils.getFlyingStoneCandidateCoordinates();
						} else {
							candidatePoints = MoveUtils.getStoneCandidateCoordinates(Integer.parseInt(getId()));
						}
						for (int i : candidatePoints) {
							Group g = (Group) SingletonBoard.getBoard().getChildren()
									.get(SingletonBoard.getBoard().getCIRCLE_GROUP_INDEX());
							Circle c = (Circle) g.getChildren().get(i);
							if (c.getClass() == CandidatePoint.class) {
								c.setVisible(true);
							}
						}
					}
				} else if (GuiBoardUtils.canIThisPieceDelete()) {
					Group group = (Group) SingletonBoard.getBoard().getChildren()
							.get(SingletonBoard.getBoard().getCIRCLE_GROUP_INDEX());
					int deletedPieceCoordinate = group.getChildren().indexOf(((Circle) e.getSource()));
					group.getChildren().set(deletedPieceCoordinate,
							CircleFactory.getCircle(Color.LIGHTGRAY, ((Circle) e.getSource()).getId(),
									((Circle) e.getSource()).getLayoutX(), ((Circle) e.getSource()).getLayoutY(),
									null));
					SingletonBoard.getBoard().getPieceColorList().set(deletedPieceCoordinate, PieceColor.EMPTY);
					SingletonBoard.getBoard().setDeleteThisColor(PieceColor.EMPTY);
					SingletonBoard.getBoard().changeTurnStatus();
					GuiMenuBarUtils.sbForMoveList.append("(Mill)->" +  GuiBoardUtils.getTextCoordinat(deletedPieceCoordinate) + "x  ");		
					GuiMenuBarUtils.moveList.set(GuiMenuBarUtils.sbForMoveList.toString());
					if (SingletonBoard.getBoard().getAiMoveStatus() == AiMoveStatus.DONE && !GuiBoardUtils.isGameEnded()) {
						AiMove aiMove = new AiMove();
						aiMove.start();
					}
				}
			}
		});

		setOnMouseDragged(e -> {
			if (hasDragEvent && SingletonBoard.getBoard().getAiMoveStatus() == AiMoveStatus.DONE) {
				relocate(e.getSceneX() - mouseX + oldX - 20, e.getSceneY() - mouseY + oldY - 20);
			}
		});
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
