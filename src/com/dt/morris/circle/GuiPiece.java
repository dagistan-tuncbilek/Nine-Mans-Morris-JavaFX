package com.dt.morris.circle;

import com.dt.morris.board.PieceColor;
import com.dt.morris.engine.BoardUtils;
import com.dt.morris.gui.AiMove;
import com.dt.morris.gui.AiMoveStatus;
import com.dt.morris.gui.SingletonBoard;
import com.dt.morris.guiboard.GuiBoardUtils;
import com.dt.morris.move.MoveUtils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
			if (hasDragEvent && SingletonBoard.getBoard().getGuiMoveStatus() == AiMoveStatus.DONE) {
				int[] candidatePoints = MoveUtils.getStoneCandidateCoordinates(Integer.parseInt(getId()));
				Group group = (Group) SingletonBoard.getBoard().getChildren().get(1);
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

						boolean isMill = BoardUtils.controlMill(SingletonBoard.getBoard().getPieceColorList(),
								targetCoord);

						Timeline soundTimeline = new Timeline(new KeyFrame(Duration.millis(1), ev -> {
							if (isMill) {
								GuiBoardUtils.playDeleteMove();
							} else {
								GuiBoardUtils.playNormalMove();
							}
						}));
						soundTimeline.play();

						if (isMill) {
							SingletonBoard.getBoard().setDeleteThisColor(
									getColor() == Color.WHITE ? PieceColor.BLACK : PieceColor.WHITE);
						} else {
							SingletonBoard.getBoard().changeTurnStatus();
							AiMove aiMove = new AiMove();
							aiMove.start();
						}
					}
				}
				relocate(this.oldX - 20, this.oldY - 20);
			}
		});

		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
			if (SingletonBoard.getBoard().getGuiMoveStatus() == AiMoveStatus.DONE) {
				if (SingletonBoard.getBoard().getDeleteThisColor() == PieceColor.EMPTY) {
					if (hasDragEvent && SingletonBoard.getBoard().getGuiMoveStatus() != AiMoveStatus.IN_PROCESS) {
						int[] candidatePoints = MoveUtils.getStoneCandidateCoordinates(Integer.parseInt(getId()));
						for (int i : candidatePoints) {
							Group g = (Group) SingletonBoard.getBoard().getChildren().get(1);
							Circle c = (Circle) g.getChildren().get(i);
							if (c.getClass() == CandidatePoint.class) {
								c.setVisible(true);
							}
						}
					}
				} else if (GuiBoardUtils.canIThisPieceDelete()) {
					Group group = (Group) SingletonBoard.getBoard().getChildren().get(1);
					int index = group.getChildren().indexOf(((Circle) e.getSource()));
					group.getChildren().set(index,
							CircleFactory.getCircle(Color.LIGHTGRAY, ((Circle) e.getSource()).getId(),
									((Circle) e.getSource()).getLayoutX(), ((Circle) e.getSource()).getLayoutY(),
									null));
					SingletonBoard.getBoard().getPieceColorList().set(index, PieceColor.EMPTY);
					SingletonBoard.getBoard().setDeleteThisColor(PieceColor.EMPTY);
					SingletonBoard.getBoard().changeTurnStatus();
					GuiBoardUtils.isGameEnded();
					AiMove aiMove = new AiMove();
					aiMove.start();
				}
			}
		});

		setOnMouseDragged(e -> {
			if (hasDragEvent && SingletonBoard.getBoard().getGuiMoveStatus() == AiMoveStatus.DONE) {
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
