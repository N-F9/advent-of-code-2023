import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));
		char[] directions = fs.nextLine().toCharArray();

		fs.nextLine();

		HashMap<String, Pair> pairs = new HashMap<>();

		while(fs.hasNextLine()) {
			String line = fs.nextLine();

			pairs.put(line.substring(0, 3), new Pair(line.substring(7, 10), line.substring(12, 15)));
		}

		int i = 0;

		String curr = "AAA";

		while(true) {

			Pair p = pairs.get(curr);

			if (directions[i % directions.length] == 'L') {
				curr = p.l;
			} else {
				curr = p.r;
			}

			if (curr.equals("ZZZ")) {
				break;
			}

			i++;
		}

		System.out.println(i + 1);
	}

	static class Pair {
		String l;
		String r;

		public Pair(String l, String r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public String toString() {
			return l + " " + r;
		}
	}
}
