import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		fs.next();

		ArrayList<Race> races = new ArrayList<>();

		while(fs.hasNextInt()) {
			races.add(new Race(fs.nextInt()));
		}

		fs.next();

		for (int i = 0; i < races.size(); i++) {
			races.get(i).distance = fs.nextInt();
		}

		int num = 1;

		for (Race race : races) {
			num *= race.numWin();
		}

		System.out.println(num);

		Race big = new Race(0);

		for (Race race : races) {
			big.time = Long.parseLong(big.time + "" + race.time);
			big.distance = Long.parseLong(big.distance + "" + race.distance);
		}

		System.out.println(big);

		System.out.println(big.numWin());
	}

	public static class Race {
		long time;
		long distance;

		public Race(long time) {
			this.time = time;
		}
		
		public int numWin() {
			int wins = 0; 

			for(long i = 0; i < time; i++) {
				long d = i * (time - i);

				if (d > distance) {
					wins++;
				}
			}

			return wins;
		}

		@Override
		public String toString() {
			return time + " " + distance;
		}
	}
}
