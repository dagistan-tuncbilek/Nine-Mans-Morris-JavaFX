package com.dt.morris.guiboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.dt.morris.board.Board;
import com.dt.morris.board.PieceColor;
import com.dt.morris.circle.BlackCircle;
import com.dt.morris.circle.CircleFactory;
import com.dt.morris.circle.WhiteCircle;
import com.dt.morris.gui.AiMoveStatus;
import com.dt.morris.gui.SingletonBoard;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GuiBoardUtils {

	public GuiBoardUtils() {
		throw new UnsupportedOperationException("Do not instantiate this class, use statically.");
	}

	public static List<PieceColor> getInitialColorList(boolean isWhiteHuman) {
		List<PieceColor> colorList = new ArrayList<PieceColor>();
		for (int i = 0; i < 42; i++) {
			if (i < 24) {
				colorList.add(PieceColor.EMPTY);
			} else if (i < 33 && isWhiteHuman) {
				colorList.add(PieceColor.BLACK);
			} else if (i < 33 && !isWhiteHuman) {
				colorList.add(PieceColor.WHITE);
			} else if (isWhiteHuman) {
				colorList.add(PieceColor.WHITE);
			} else {
				colorList.add(PieceColor.BLACK);
			}
		}
		return colorList;
	}

	public static ImageView getImage() {
		Image image = null;
		try {
			image = new Image(new FileInputStream("./assets/BoardCoordinates.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView imageView = new ImageView(image);
		imageView.relocate(0, 450);
		imageView.setFitHeight(250);
		imageView.setFitWidth(250);
		imageView.setPreserveRatio(true);
		return imageView;
	}

	public static Group getCircles(Boolean isWhiteHuman) {
		Group circleGroup = new Group();
		List<int[]> circleCoordinates = Arrays.asList(new int[] { 150, 100 }, new int[] { 375, 100 },
				new int[] { 600, 100 }, new int[] { 225, 175 }, new int[] { 375, 175 }, new int[] { 525, 175 },
				new int[] { 300, 250 }, new int[] { 375, 250 }, new int[] { 450, 250 }, new int[] { 150, 325 },
				new int[] { 225, 325 }, new int[] { 300, 325 }, new int[] { 450, 325 }, new int[] { 525, 325 },
				new int[] { 600, 325 }, new int[] { 300, 400 }, new int[] { 375, 400 }, new int[] { 450, 400 },
				new int[] { 225, 475 }, new int[] { 375, 475 }, new int[] { 525, 475 }, new int[] { 150, 550 },
				new int[] { 375, 550 }, new int[] { 600, 550 });
		int id = 0;
		for (int[] coor : circleCoordinates) {
			circleGroup.getChildren()
					.add(CircleFactory.getCircle(Color.LIGHTGRAY, String.valueOf(id++), coor[0] + 150, coor[1], null));
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				circleGroup.getChildren().add(CircleFactory.getCircle(isWhiteHuman ? Color.BLACK : Color.WHITE,
						String.valueOf(id++), i * 50 + 75, j * 50 + 100, isWhiteHuman));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				circleGroup.getChildren().add(CircleFactory.getCircle(isWhiteHuman ? Color.WHITE : Color.BLACK,
						String.valueOf(id++), i * 50 + 75, j * 50 + 300, isWhiteHuman));
			}
		}
		return circleGroup;
	}

	public static Group getLines() {
		Group lineGroup = new Group();
		List<int[]> lineCoordinates = (Arrays.asList(new int[] { 150, 100, 600, 100 }, new int[] { 225, 175, 525, 175 },
				new int[] { 300, 250, 450, 250 }, new int[] { 150, 325, 300, 325 }, new int[] { 450, 325, 600, 325 },
				new int[] { 300, 400, 450, 400 }, new int[] { 225, 475, 525, 475 }, new int[] { 150, 550, 600, 550 },

				new int[] { 150, 100, 150, 550 }, new int[] { 225, 175, 225, 475 }, new int[] { 300, 250, 300, 400 },
				new int[] { 375, 100, 375, 250 }, new int[] { 375, 400, 375, 550 }, new int[] { 450, 250, 450, 400 },
				new int[] { 525, 175, 525, 475 }, new int[] { 600, 100, 600, 550 }));
		for (int[] coor : lineCoordinates) {
			Line line = new Line();
			line.setStartX(coor[0] + 150);
			line.setStartY(coor[1]);
			line.setEndX(coor[2] + 150);
			line.setEndY(coor[3]);
			line.setStyle("-fx-stroke-width:5;");
			line.toBack();
			line.toBack();
			lineGroup.getChildren().add(line);
		}
		return lineGroup;
	}

	public static String getTextCoordinat(int coordinate) {
		switch (coordinate) {
		case 0:
			return "7a";
		case 1:
			return "7d";
		case 2:
			return "7g";
		case 3:
			return "6b";
		case 4:
			return "6d";
		case 5:
			return "6f";
		case 6:
			return "5c";
		case 7:
			return "5d";
		case 8:
			return "5e";
		case 9:
			return "4a";
		case 10:
			return "4b";
		case 11:
			return "4c";
		case 12:
			return "4e";
		case 13:
			return "4f";
		case 14:
			return "4g";
		case 15:
			return "3c";
		case 16:
			return "3d";
		case 17:
			return "3e";
		case 18:
			return "2b";
		case 19:
			return "2d";
		case 20:
			return "2f";
		case 21:
			return "1a";
		case 22:
			return "1d";
		case 23:
			return "1g";
		default:
			return "R";
		}
	}

	public static PieceColor getPieceColor(Circle c) {
		if (c.getClass() == WhiteCircle.class) {
			return PieceColor.WHITE;

		} else if (c.getClass() == BlackCircle.class) {
			return PieceColor.BLACK;
		} else {
			return PieceColor.EMPTY;
		}
	}

	public static Color getColor(Circle c) {
		if (c.getClass() == WhiteCircle.class) {
			return Color.WHITE;
		} else if (c.getClass() == BlackCircle.class) {
			return Color.BLACK;
		} else {
			return Color.LIGHTGRAY;
		}
	}

	public static boolean canIThisPieceDelete() {

		if (SingletonBoard.getBoard().getDeleteThisColor() == PieceColor.EMPTY) {
			return false;
		}
		if (!SingletonBoard.getBoard().isWhiteHuman()
				&& SingletonBoard.getBoard().getDeleteThisColor() == PieceColor.BLACK) {
			return false;
		}
		if (SingletonBoard.getBoard().isWhiteHuman()
				&& SingletonBoard.getBoard().getDeleteThisColor() == PieceColor.WHITE) {
			return false;
		}
		return true;
	}

	public static void playAudio(String audioName) {
		String musicFile;
		switch (audioName) {
		case "newgame":
			musicFile = "./assets/newGame.wav";
			break;
		case "delete":
			musicFile = "./assets/deleteMove.wav";
			break;
		default:
			musicFile = "./assets/normalMove.wav";
			break;
		}
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		Timeline stopAudioTimeline = new Timeline(new KeyFrame(Duration.millis(450), ev -> {
			mediaPlayer.stop();
		}));
		stopAudioTimeline.play();
	}

	public static boolean isGameEnded() {
		final Board board = new Board(SingletonBoard.getBoard().getPieceColorList(), PieceColor.WHITE);
		if (board.whitePlayer().getPieceCount() < 3 || board.whitePlayer().getLegalMoves().size() == 0) {
			endGameWindow("Black");
			return true;
		} else if (board.blackPlayer().getPieceCount() < 3 || board.blackPlayer().getLegalMoves().size() == 0) {
			endGameWindow("White");
			return true;
		}
		if (board.whitePlayer().getPieceCount() == 3) {
			SingletonBoard.getBoard().setWhiteFlying(true);
		}
		if (board.blackPlayer().getPieceCount() == 3) {
			SingletonBoard.getBoard().setBlackFlying(true);
		}
		return false;
	}

	public static Alert endGameWindow(String player) {
		SingletonBoard.getBoard().setAiMoveStatus(AiMoveStatus.IN_PROCESS);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Nine Man's Morris");
		alert.setHeaderText("Game Finished");
		alert.setContentText("Good game. " + player + " won! Click OK to begin a new game.");
		alert.setOnHidden(evt -> {
			SingletonBoard.getBoard().buildNewBord();
			alert.close();
		});
		alert.show();
		return alert;
	}

	public static boolean checkForStalemate() {
		List<List<PieceColor>> stalameteList = SingletonBoard.getBoard().getWholeGameMoveList().stream()
				.filter(n -> Collections.frequency(SingletonBoard.getBoard().getWholeGameMoveList(), n) > 2)
				.collect(Collectors.toList());
		if (stalameteList.size() > 0) {
			stalameteWindow();
			return true;
		}
		return false;
	}

	public static Alert stalameteWindow() {
		SingletonBoard.getBoard().setAiMoveStatus(AiMoveStatus.IN_PROCESS);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Nine Man's Morris");
		alert.setHeaderText("Stalamete");
		alert.setContentText("Good game. Stalamete! Click OK to begin a new game.");
		alert.setOnHidden(evt -> {
			SingletonBoard.getBoard().buildNewBord();
			alert.close();
		});
		alert.show();
		return alert;
	}
}
