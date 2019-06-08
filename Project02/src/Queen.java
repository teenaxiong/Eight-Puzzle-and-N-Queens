
public class Queen {

	private int row, column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	/*
	 * Takes in a Queen and compare it with the current Queen's position
	 * to see if there is a conflict between each other
	 */
	public boolean pathCross(Queen j) {
		
		//see if there's another in the same row
		if(this.row == j.getRow()) {
			return true; 
		}
		
		//see if there's another queen above
		if(this.column == j.getColumn()) {
			return true; 
		}
		
		//see if there's another queen diagonal 
		if(Math.abs(this.row - j.getRow()) == Math.abs(this.column - j.getColumn())) {
			return true; 
		}
		
		//returns false if there's no queen in the same row column, or diagonal
		return false;
		
	}
	
	public void moveQueen(int size) {
		if(this.row != size-1) {
			row++;
		}else row = 0; 
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	
	
}
