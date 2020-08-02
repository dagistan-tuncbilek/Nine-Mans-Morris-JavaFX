package com.dt.morris.gui;

public enum Difficulty {
	EASY {
		@Override
		public int getLevel() {
			return 1;
		}

		@Override
		public String getName() {
			return "Easy";
		}
	},
	VERY_EASY {
		@Override
		public int getLevel() {
			return 2;
		}

		@Override
		public String getName() {
			return "Very Easy";
		}
	},
	NORMAL {
		@Override
		public int getLevel() {
			return 3;
		}

		@Override
		public String getName() {

			return "Normal";
		}
	},
	HARD {
		@Override
		public int getLevel() {
			return 4;
		}

		@Override
		public String getName() {
			return "Hard";
		}
	},
	VERY_HARD {
		@Override
		public int getLevel() {
			return 5;
		}

		@Override
		public String getName() {
			return "Very Hard";
		}
	};
		
	public abstract int getLevel();
	public abstract String getName();
}
