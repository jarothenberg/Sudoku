package Sudoku;

import java.util.Scanner;

public class Board {

	static Scanner scan = new Scanner(System.in);

	public static String[][] getSmallGrid(String[][] board, int gridNum) {
		String[][] smallGrid = new String[3][3];
		int rowNum = 0;
		int columnNum = 0;
		if (gridNum > 5) {
			rowNum += 6;
		} else if (gridNum > 2) {
			rowNum += 3;
		}
		if (gridNum == 1 || gridNum == 4 || gridNum == 7) {
			columnNum += 3;
		} else if (gridNum == 2 || gridNum == 5 || gridNum == 8) {
			columnNum += 6;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				smallGrid[i][j] = board[i + rowNum][j + columnNum];
			}
		}
		return smallGrid;
	}

	public static void setSmallGrid(String[][] board, String[][] smallGrid, int gridNum, int row, int col) {
		int rowNum = 0;
		int columnNum = 0;
		if (gridNum > 5) {
			rowNum += 6;
		} else if (gridNum > 2) {
			rowNum += 3;
		}
		if (gridNum == 1 || gridNum == 4 || gridNum == 7) {
			columnNum += 3;
		} else if (gridNum == 2 || gridNum == 5 || gridNum == 8) {
			columnNum += 6;
		}
		board[row + rowNum][col + columnNum] = smallGrid[row][col];
	}

	public static void printGrid(String[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static String[] getRow(String[][] board, int rowNum) {
		String[] row = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			row[i] = board[rowNum][i];
		}
		return row;
	}

	public static void setRow(String[][] board, String[] row, int rowNum) {
		board[rowNum] = row;
	}

	public static String[] getCol(String[][] board, int colNum) {
		String[] col = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			col[i] = board[i][colNum];
		}
		return col;
	}

	public static void setCol(String[][] board, String[] col, int colNum) {
		for (int i = 0; i < board.length; i++) {
			board[i][colNum] = col[i];
		}
	}

	public static void printLine(String[] line) {
		for (int i = 0; i < line.length; i++) {
			System.out.print(line[i]);
		}
		System.out.println();
	}

	public static String[][] makeBoard() {
		String[][] board = new String[9][9];
		String input;
		for (int i = 0; i < 9; i++) {
			System.out.println("Enter known numbers for row " + (i + 1) + ", for unknown numbers, enter 0.");
			input = scan.nextLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = input.charAt(j) + "";
				if (board[i][j].equals("0")) {
					board[i][j] = "(123456789)";
				}
			}
		}
		return board;
	}

	public static void main(String[] args) {
		String[][] board = makeBoard();
		System.out.println("before");
		printGrid(board);

		boolean done = false;
		boolean skip = false;
		String[][] temp = new String[9][9];
		while (done == false) {
			done = false;

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					temp[i][j] = board[i][j];
				}
			}

			BasicStrats.run(board);

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (temp[i][j].equals(board[i][j]) == false) {
						break;
					}
				}
			}
		}

		System.out.println("after");
		printGrid(board);
	}
}
