package com.dt.morris.move;

public enum MoveStatus {
	DONE {
		@Override
		public boolean isDone() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isGameOver() {
			// TODO Auto-generated method stub
			return false;
		}

	},
	GAMEOVER {
		@Override
		public boolean isDone() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean isGameOver() {
			// TODO Auto-generated method stub
			return true;
		}

	};
	
	public abstract boolean isDone();
	public abstract boolean isGameOver();
	
}
