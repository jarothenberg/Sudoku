package Sudoku;

public class BasicStrats {

	public static void run(String[][] board) {
		BasicStrats.gettingStarted(board);
		BasicStrats.singles(board);
		BasicStrats.nakedPairs(board);
		BasicStrats.nakedTriples(board);
		BasicStrats.nakedQuads(board);
	}

	public static void gettingStarted(String[][] board) {
		String[] row = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			row = Board.getRow(board, i);
			for (int j = 0; j < row.length; j++) {
				for (int k = 0; k < row.length; k++) {
					if (row[j].length() == 1 && j != k && row[k].length() != 1
							&& row[k].contains(row[j].charAt(0) + "")) {
						row[k] = row[k].replace(row[j].charAt(0) + "", "");
					}
					if (row[k].length() == 3) {
						row[k] = row[k].charAt(1) + "";
					}
					board[i][k] = row[k];
				}
			}
		}
		String[] col = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			col = Board.getCol(board, i);
			for (int j = 0; j < col.length; j++) {
				for (int k = 0; k < col.length; k++) {
					if (col[j].length() == 1 && j != k && col[k].length() != 1
							&& col[k].contains(col[j].charAt(0) + "")) {
						col[k] = col[k].replace(col[j].charAt(0) + "", "");
					}
					if (col[k].length() == 3) {
						col[k] = col[k].charAt(1) + "";
					}
					board[k][i] = col[k];
				}
			}
		}
		String[][] smallGrid = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			smallGrid = Board.getSmallGrid(board, i);
			for (int j = 0; j < smallGrid.length; j++) {
				for (int k = 0; k < smallGrid.length; k++) {
					for (int p = 0; p < smallGrid.length; p++) {
						for (int d = 0; d < smallGrid.length; d++) {
							if (smallGrid[j][k].length() == 1 && (j != p || k != d) && smallGrid[p][d].length() != 1
									&& smallGrid[p][d].contains(smallGrid[j][k].charAt(0) + "")) {
								smallGrid[p][d] = smallGrid[p][d].replace(smallGrid[j][k].charAt(0) + "", "");
							}
							if (smallGrid[p][d].length() == 3) {
								smallGrid[p][d] = smallGrid[p][d].charAt(1) + "";
							}
							Board.setSmallGrid(board, smallGrid, i, p, d);
						}
					}
				}
			}
		}
	}

	public static void singles(String[][] board) {
		String[] row = new String[board.length];
		String str = "";
		for (int i = 0; i < board.length; i++) {
			row = Board.getRow(board, i);
			str = "";
			for (int j = 0; j < row.length; j++) {
				str += row[j];
			}
			for (int j = 0; j < str.length(); j++) {
				for (int k = 1; k < 10; k++) {
					char c = Integer.toString(k).toCharArray()[0];
					if (str.chars().filter(ch -> ch == c).count() == 1) {
						for (int p = 0; p < row.length; p++) {
							if (row[p].contains(c + "") && row[p].length() > 1) {
								row[p] = c + "";
								break;
							}
							board[i][p] = row[p];
						}
					}
				}
			}
		}
		String[] col = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			col = Board.getCol(board, i);
			str = "";
			for (int j = 0; j < col.length; j++) {
				str += col[j];
			}
			for (int j = 0; j < str.length(); j++) {
				for (int k = 1; k < 10; k++) {
					char c = Integer.toString(k).toCharArray()[0];
					if (str.chars().filter(ch -> ch == c).count() == 1) {
						for (int p = 0; p < col.length; p++) {
							if (col[p].contains(c + "") && col[p].length() > 1) {
								col[p] = c + "";
								break;
							}
							board[p][i] = col[p];
						}
					}
				}
			}
		}
		String[][] smallGrid = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			smallGrid = Board.getSmallGrid(board, i);
			str = "";
			for (int j = 0; j < smallGrid.length; j++) {
				for (int k = 0; k < smallGrid.length; k++) {
					str += smallGrid[j][k];
				}
			}
			for (int j = 0; j < str.length(); j++) {
				for (int k = 1; k < 10; k++) {
					char c = Integer.toString(k).toCharArray()[0];
					if (str.chars().filter(ch -> ch == c).count() == 1) {
						for (int p = 0; p < smallGrid.length; p++) {
							for (int d = 0; d < smallGrid.length; d++) {
								if (smallGrid[p][d].contains(c + "") && smallGrid[p][d].length() > 1) {
									smallGrid[p][d] = c + "";
									break;
								}
								Board.setSmallGrid(board, smallGrid, i, p, d);
							}
						}
					}
				}
			}
		}
	}

	public static void nakedPairs(String[][] board) {
		String[] row = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			row = Board.getRow(board, i);
			for (int j = 0; j < row.length; j++) {
				for (int k = 0; k < row.length; k++) {
					if (row[j].length() == 4 && row[k].equals(row[j]) && j != k) {
						for (int p = 0; p < row.length; p++) {
							if (row[p].length() > 1 && row[p].equals(row[j]) == false) {
								row[p] = row[p].replace(row[j].toCharArray()[1] + "", "");
								row[p] = row[p].replace(row[j].toCharArray()[2] + "", "");
							}
							board[i][p] = row[p];
						}
					}
				}
			}
		}
		String[] col = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			col = Board.getCol(board, i);
			for (int j = 0; j < col.length; j++) {
				for (int k = 0; k < col.length; k++) {
					if (col[j].length() == 4 && col[k].equals(col[j]) && j != k) {
						for (int p = 0; p < col.length; p++) {
							if (col[p].length() > 1 && col[p].equals(col[j]) == false) {
								col[p] = col[p].replace(col[j].toCharArray()[1] + "", "");
								col[p] = col[p].replace(col[j].toCharArray()[2] + "", "");
							}
							board[p][i] = col[p];
						}
					}
				}
			}
		}
		String[][] smallGrid = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			smallGrid = Board.getSmallGrid(board, i);
			for (int j = 0; j < smallGrid.length; j++) {
				for (int k = 0; k < smallGrid.length; k++) {
					for (int p = 0; p < smallGrid.length; p++) {
						for (int d = 0; d < smallGrid.length; d++) {
							if (smallGrid[j][k].length() == 4 && smallGrid[p][d].equals(smallGrid[j][k])
									&& (j != p || k != d)) {
								for (int b = 0; b < smallGrid.length; b++) {
									for (int c = 0; c < smallGrid.length; c++) {
										if (smallGrid[b][c].length() > 1
												&& smallGrid[b][c].equals(smallGrid[j][k]) == false) {
											smallGrid[b][c] = smallGrid[b][c]
													.replace(smallGrid[j][k].toCharArray()[1] + "", "");
											smallGrid[b][c] = smallGrid[b][c]
													.replace(smallGrid[j][k].toCharArray()[2] + "", "");
										}
										Board.setSmallGrid(board, smallGrid, i, b, c);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static void nakedTriples(String[][] board) {
		String[] row = new String[board.length];
		String[] nums = new String[3];
		String str = "";
		String strTemp = "";
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			row = Board.getRow(board, i);
			for (int j = 0; j < row.length; j++) {
				if (row[j].length() == 5 || row[j].length() == 4) {
					nums[0] = row[j].toString();
					for (int k = j; k < row.length; k++) {
						if (k != j && (row[k].length() == 5 || row[k].length() == 4)) {
							nums[1] = row[k].toString();
							for (int p = k; p < row.length; p++) {
								if (p != k && (row[p].length() == 5 || row[p].length() == 4)) {
									nums[2] = row[p].toString();
									for (int d = 0; d < nums.length; d++) {
										str += nums[d];
									}
									for (int d = 0; d < str.length(); d++) {
										if (strTemp.contains(str.toCharArray()[d] + "") == false) {
											strTemp += str.toCharArray()[d];
											count++;
										}
									}
									str = "";
									if (count == 5) {
										strTemp = strTemp.replace("(", "");
										strTemp = strTemp.replace(")", "");
										for (int d = 0; d < row.length; d++) {
											if (d != j && d != k && d != p) {
												row[d] = row[d].replace(strTemp.toCharArray()[0] + "", "");
												row[d] = row[d].replace(strTemp.toCharArray()[1] + "", "");
												row[d] = row[d].replace(strTemp.toCharArray()[2] + "", "");
											}
											board[i][d] = row[d];
										}
									}
									strTemp = "";
									count = 0;
								}
							}
						}
					}
				}
			}
		}
		String[] col = new String[board.length];
		for (int i = 0; i < board.length; i++) {
			col = Board.getCol(board, i);
			for (int j = 0; j < col.length; j++) {
				if (col[j].length() == 5 || col[j].length() == 4) {
					nums[0] = col[j].toString();
					for (int k = j; k < col.length; k++) {
						if (k != j && (col[k].length() == 5 || col[k].length() == 4)) {
							nums[1] = col[k].toString();
							for (int p = k; p < col.length; p++) {
								if (p != k && (col[p].length() == 5 || col[p].length() == 4)) {
									nums[2] = col[p].toString();
									for (int d = 0; d < nums.length; d++) {
										str += nums[d];
									}
									for (int d = 0; d < str.length(); d++) {
										if (strTemp.contains(str.toCharArray()[d] + "") == false) {
											strTemp += str.toCharArray()[d];
											count++;
										}
									}
									str = "";
									if (count == 5) {
										strTemp = strTemp.replace("(", "");
										strTemp = strTemp.replace(")", "");
										for (int d = 0; d < col.length; d++) {
											if (d != j && d != k && d != p) {
												col[d] = col[d].replace(strTemp.toCharArray()[0] + "", "");
												col[d] = col[d].replace(strTemp.toCharArray()[1] + "", "");
												col[d] = col[d].replace(strTemp.toCharArray()[2] + "", "");
											}
											board[d][i] = col[d];
										}
									}
									strTemp = "";
									count = 0;
								}
							}
						}
					}
				}
			}
		}
		String[][] smallGrid = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			smallGrid = Board.getSmallGrid(board, i);
			for (int j = 0; j < smallGrid.length; j++) {
				for (int k = 0; k < smallGrid.length; k++) {
					if (smallGrid[j][k].length() == 5 || smallGrid[j][k].length() == 4) {
						nums[0] = smallGrid[j][k].toString();
						for (int p = 0; p < smallGrid.length; p++) {
							for (int d = 0; d < smallGrid.length; d++) {
								if ((j != p || k != d)
										&& (smallGrid[p][d].length() == 5 || smallGrid[p][d].length() == 4)) {
									nums[1] = smallGrid[p][d].toString();
									for (int c = 0; c < smallGrid.length; c++) {
										for (int b = 0; b < smallGrid.length; b++) {
											if ((p != c || d != b) && (c != j || b != k)
													&& (smallGrid[c][b].length() == 5
															|| smallGrid[c][b].length() == 4)) {
												nums[2] = smallGrid[c][b].toString();

												for (int e = 0; e < nums.length; e++) {
													str += nums[e];
												}
												for (int e = 0; e < str.length(); e++) {
													if (strTemp.contains(str.toCharArray()[e] + "") == false) {
														strTemp += str.toCharArray()[e];
														count++;
													}
												}
												str = "";
												if (count == 5) {
													strTemp = strTemp.replace("(", "");
													strTemp = strTemp.replace(")", "");
													for (int e = 0; e < smallGrid.length; e++) {
														for (int f = 0; f < smallGrid.length; f++) {
															if ((e != j || f != k) && (e != p || f != d)
																	&& (e != c || f != b)) {
																if (i == 3) {
																	i += 0;
																}
																smallGrid[e][f] = smallGrid[e][f]
																		.replace(strTemp.toCharArray()[0] + "", "");
																smallGrid[e][f] = smallGrid[e][f]
																		.replace(strTemp.toCharArray()[1] + "", "");
																smallGrid[e][f] = smallGrid[e][f]
																		.replace(strTemp.toCharArray()[2] + "", "");
															}
															Board.setSmallGrid(board, smallGrid, i, e, f);
														}
													}
												}
												strTemp = "";
												count = 0;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static void nakedQuads(String[][] board) {
		String[] nums = new String[4];
		String str = "";
		String strTemp = "";
		int count = 0;
		String[][] smallGrid = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			smallGrid = Board.getSmallGrid(board, i);
			for (int j = 0; j < smallGrid.length; j++) {
				for (int k = 0; k < smallGrid.length; k++) {
					if (smallGrid[j][k].length() == 6 || smallGrid[j][k].length() == 5
							|| smallGrid[j][k].length() == 4) {
						nums[0] = smallGrid[j][k].toString();
						for (int p = 0; p < smallGrid.length; p++) {
							for (int d = 0; d < smallGrid.length; d++) {
								if ((p != j || d != k) && (smallGrid[p][d].length() == 6
										|| smallGrid[p][d].length() == 5 || smallGrid[p][d].length() == 4)) {
									nums[1] = smallGrid[p][d].toString();
									for (int c = 0; c < smallGrid.length; c++) {
										for (int b = 0; b < smallGrid.length; b++) {
											if ((c != j || b != k) && (c != p || b != d)
													&& (smallGrid[c][b].length() == 6 || smallGrid[c][b].length() == 5
															|| smallGrid[c][b].length() == 4)) {
												nums[2] = smallGrid[c][b].toString();

												for (int x = 0; x < smallGrid.length; x++) {
													for (int y = 0; y < smallGrid.length; y++) {
														if ((x != j || y != k) && (x != p || y != d)
																&& (x != c || y != b)
																&& (smallGrid[x][y].length() == 6
																		|| smallGrid[x][y].length() == 5
																		|| smallGrid[x][y].length() == 4)) {
															nums[3] = smallGrid[x][y].toString();

															for (int e = 0; e < nums.length; e++) {
																str += nums[e];
															}
															for (int e = 0; e < str.length(); e++) {
																if (strTemp
																		.contains(str.toCharArray()[e] + "") == false) {
																	strTemp += str.toCharArray()[e];
																	count++;
																}
															}
															str = "";
															if (count == 6) {
																strTemp = strTemp.replace("(", "");
																strTemp = strTemp.replace(")", "");
																for (int e = 0; e < smallGrid.length; e++) {
																	for (int f = 0; f < smallGrid.length; f++) {
																		if ((e != j || f != k) && (e != p || f != d)
																				&& (e != c || f != b)
																				&& (e != x || f != y)) {
																			if (i == 3 && (e == 2 && f == 2)) {
																				i += 0;
																			}
																			smallGrid[e][f] = smallGrid[e][f].replace(
																					strTemp.toCharArray()[0] + "", "");
																			smallGrid[e][f] = smallGrid[e][f].replace(
																					strTemp.toCharArray()[1] + "", "");
																			smallGrid[e][f] = smallGrid[e][f].replace(
																					strTemp.toCharArray()[2] + "", "");
																			smallGrid[e][f] = smallGrid[e][f].replace(
																					strTemp.toCharArray()[3] + "", "");
																		}
																		Board.setSmallGrid(board, smallGrid, i, e, f);
																	}
																}
															}
															strTemp = "";
															count = 0;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static void hiddenPairs(String[][] board) {
		
	}

	public static void hiddenTripples(String[][] board) {

	}
	
	public static void hiddenQuads(String[][] board) {
		
	}

	public static void pointingPairs(String[][] board) {

	}

	public static void reduction(String[][] board) {

	}
}
