package com.dt.morris.gui;

import com.dt.morris.board.PieceColor;
import com.dt.morris.circle.CircleFactory;
import com.dt.morris.engine.MiniMax;
import com.dt.morris.guiboard.BoardPane;
import com.dt.morris.guiboard.GuiBoardUtils;
import com.dt.morris.move.Move;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class AiMove extends Thread {

	public void run() {
		SingletonBoard.getBoard().setAiMoveStatus(AiMoveStatus.IN_PROCESS);
		MiniMax miniMax = new MiniMax(SingletonBoard.getBoard().getSearchDepth());
		Move move = miniMax.execute(SingletonBoard.getBoard().getPieceColorList(),
				SingletonBoard.getBoard().isWhiteHuman() ? PieceColor.BLACK : PieceColor.WHITE);

		Group group = (Group) SingletonBoard.getBoard().getChildren().get(SingletonBoard.getBoard().getCIRCLE_GROUP_INDEX());

		Circle currentCircle = (Circle) group.getChildren().get(move.getCurrentCoordinate());
		Circle targetCircle = (Circle) group.getChildren().get(move.getDestinationCoordinate());
		double currentLayoutX = currentCircle.getLayoutX();
		double currentLayoutY = currentCircle.getLayoutY();
		double targetLayoutX = targetCircle.getLayoutX();
		double targetLayoutY = targetCircle.getLayoutY();

		TranslateTransition transition = new TranslateTransition(Duration.millis(1000));
		transition.setNode(currentCircle);
		transition.setToX(targetLayoutX - currentLayoutX);
		transition.setToY(targetLayoutY - currentLayoutY);
		transition.play();

		Timeline soundTimeline = new Timeline(new KeyFrame(Duration.millis(900), ev -> {
			if (move.getDeletedPieceCoordinate() == -1) {
				GuiBoardUtils.playAudio("normal");
			} else {
				GuiBoardUtils.playAudio("delete");
			}
		}));
		soundTimeline.play();

		Timeline aimationTimeline = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
			group.getChildren().set(move.getCurrentCoordinate(),
					CircleFactory.getCircle(GuiBoardUtils.getColor(targetCircle),
							String.valueOf(move.getCurrentCoordinate()), currentLayoutX, currentLayoutY, null));

			group.getChildren().set(move.getDestinationCoordinate(),
					CircleFactory.getCircle(GuiBoardUtils.getColor(currentCircle),
							String.valueOf(move.getDestinationCoordinate()), targetLayoutX, targetLayoutY,
							!SingletonBoard.getBoard().isWhiteHuman()));

			SingletonBoard.getBoard().getPieceColorList().set(move.getDestinationCoordinate(),
					SingletonBoard.getBoard().getPieceColorList().get(move.getCurrentCoordinate()));
			SingletonBoard.getBoard().getPieceColorList().set(move.getCurrentCoordinate(), PieceColor.EMPTY);

			SingletonBoard.getBoard().changeTurnStatus();
			SingletonBoard.getBoard().setAiMoveStatus(AiMoveStatus.DONE);
		}));
		aimationTimeline.play();

		if (move.getDeletedPieceCoordinate() > -1) {
			Timeline deletionTimeline = new Timeline(new KeyFrame(Duration.millis(1400), ev -> {
				Circle deletedCircle = (Circle) group.getChildren().get(move.getDeletedPieceCoordinate());
				
				group.getChildren().set(move.getDeletedPieceCoordinate(), CircleFactory.getCircle(Color.LIGHTGRAY,
						deletedCircle.getId(), deletedCircle.getLayoutX(), deletedCircle.getLayoutY(), null));
				SingletonBoard.getBoard().getPieceColorList().set(move.getDeletedPieceCoordinate(), PieceColor.EMPTY);
				GuiBoardUtils.isGameEnded();
			}));
			deletionTimeline.play();
		}
	}
}