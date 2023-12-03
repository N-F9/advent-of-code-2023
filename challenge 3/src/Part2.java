import java.io.File;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception {
		int[][] board = new int[140][140];

		Scanner fs = new Scanner(new File(args[0]));

		int j = 0;
		while(fs.hasNextLine()) {
			String line = fs.nextLine();

			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if(c >= 48 && c <= 57)
					board[j][i] = (int) c - 48;
				else {
					if (c == '.') {
						board[j][i] = 11;
					} else if (c == '*') {
						board[j][i] = 13;
					} else {
						board[j][i] = 12;
					}
				}
			}

			j++;
		}

		int sum = 0;

		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				if(board[i][k] == 13) {
				// 	int num = board[i][k];
				// 	int l = k + 1;
				// 	while(l < board[i].length && board[i][l] >= 0 && board[i][l] <= 9) {
				// 		num = num * 10 + board[i][l++];
				// 	}

				// 	if (hasSymbol(board, i, k)) {
				// 		sum += num;
				// 	}

				// 	k = l;

					int num1 = -1;
					int num2 = -1;

					int[] rows = {i-1, i, i+1, i-1, i+1, i-1, i, i+1};
					int[] cols = {k-1, k-1, k-1, k, k, k+1, k+1, k+1};

					for (int l = 0; l < rows.length; l++) {
						for (int l2 = 0; l2 < cols.length; l2++) {
							if(valid(board, rows[l], cols[l2]) && board[rows[l]][cols[l2]] >= 0 && board[rows[l]][cols[l2]] <= 9) {
								if (num1 == -1) {
									num1 = getNum(board, rows[l], cols[l2]);
								} else if (num2 == -1) {
									num2 = getNum(board, rows[l], cols[l2]);
								}
							}
						}
					}

					if (num1 > 0 && num2 > 0) {
						System.out.println(num1 + " " + num2 + " " + i + " " + k);
						sum += num1 * num2;
					}
				}
			}
		}

		System.out.println(sum);
	}

	public static int getNum(int[][] board, int r, int c) {
		while(valid(board, r, c-1) && board[r][c-1] >= 0 && board[r][c-1] <= 9) {
			c--;
		}
		System.out.println(r + " " + c);
		int num = 0;
		while(valid(board, r, c) && board[r][c] >= 0 && board[r][c] <= 9) {
			num = num * 10 + board[r][c];
			board[r][c] = 11;
			c++;
		}
		return num;

	}

	public static boolean valid(int[][] board, int r, int c) {
		return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
	}
}
