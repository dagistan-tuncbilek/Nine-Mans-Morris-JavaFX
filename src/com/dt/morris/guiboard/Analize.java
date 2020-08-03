package com.dt.morris.guiboard;

public class Analize {

	String totalMoves;
	String move;
	int score;

	public Analize(String totalMoves, String move, int score) {
		this.totalMoves = totalMoves;
		this.move = move;
		this.score = score;
	}

	public String getTotalMoves() {
		return totalMoves;
	}

	public String getMove() {
		return move;
	}


	public int getScore() {
		return score;
	}
}
