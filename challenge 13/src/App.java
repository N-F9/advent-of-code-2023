import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File(args[0]));

		ArrayList<char[]> list = new ArrayList<>();
		ArrayList<Grid> grids = new ArrayList<>();

		while(sc.hasNextLine()) {
			String line = sc.nextLine();

			if(line.equals("")) {
				grids.add(new Grid(list));
				list = new ArrayList<>();
				continue;
			}

			list.add(line.toCharArray());


		}

		grids.add(new Grid(list));
		list = new ArrayList<>();

		System.out.println(grids);
		for (Grid grid : grids) {
			System.out.println(grid.findReflection());
		}

	}

	static class Grid {
		char[][] grid; 

		public Grid(ArrayList<char[]> grid) {
			this.grid = new char[grid.size()][grid.get(0).length];
			for (int i = 0; i < this.grid.length; i++) {
				this.grid[i] = grid.get(i);
			}
		}

		public int findReflection() {

			// vertical

			for (int i = 0; i < grid[0].length - 1; i++) {
				boolean differs = false;
				int n = Math.min((i + 1), (grid[0].length - i - 1));

				System.out.println(i + " " + n );
				
				for (int j = i; j < i + n+1; j++) {
					for (int j2 = 0; j2 < grid.length; j2++) {
						System.out.println("- " + j + " " + this.grid[j2][j] + " " + (n+i-j) + " " + this.grid[j2][n+i-j]);
						if (this.grid[j2][j] != this.grid[j2][n+i-j] && j != n+i-j) {
							differs = true;
						}
					}
				}

				if (!differs) {
					return i + 1;
				}
			}


			// horizontal

			return -1;
		}
	}
}
