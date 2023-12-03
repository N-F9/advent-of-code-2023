import java.io.File;
import java.util.Scanner;

public class App {
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
				if(board[i][k] >= 0 && board[i][k] <= 9) {
					int num = board[i][k];
					int l = k + 1;
					while(l < board[i].length && board[i][l] >= 0 && board[i][l] <= 9) {
						num = num * 10 + board[i][l++];
						// k++;
					}

					if (hasSymbol(board, i, k)) {
						sum += num;
					}

					System.out.println(k);

					k = l;
					System.out.println(num + " " + k);
				}
			}
		}

		System.out.println(sum);
	}

	public static boolean hasSymbol(int[][] board, int r, int c) {

		if (valid(board, r+1, c-1) && board[r+1][c-1] == 12) {
			return true;
		}

		if (valid(board, r+1, c) && board[r+1][c] == 12) {
			return true;
		}

		if (valid(board, r+1, c+1) && board[r+1][c+1] == 12) {
			return true;
		}

		if (valid(board, r-1, c-1) && board[r-1][c-1] == 12) {
			return true;
		}

		if (valid(board, r-1, c) && board[r-1][c] == 12) {
			return true;
		}

		if (valid(board, r-1, c+1) && board[r-1][c+1] == 12) {
			return true;
		}

		if (valid(board, r, c+1) && board[r][c+1] == 12) {
			return true;
		}

		if (valid(board, r, c-1) && board[r][c-1] == 12) {
			return true;
		}

		if (valid(board, r, c+1) && board[r][c+1] >= 0 && board[r][c+1] <= 9) {
			return hasSymbol(board, r, c + 1);
		} else {
			return false;
		}
	}

	public static boolean valid(int[][] board, int r, int c) {
		return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
	}
}
