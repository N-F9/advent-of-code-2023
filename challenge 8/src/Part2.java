import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Part2 {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));
		char[] directions = fs.nextLine().toCharArray();

		fs.nextLine();

		HashMap<String, Pair> pairs = new HashMap<>();

		while(fs.hasNextLine()) {
			String line = fs.nextLine();

			pairs.put(line.substring(0, 3), new Pair(line.substring(7, 10), line.substring(12, 15)));
		}

		long i = 0;

		ArrayList<String> currs = new ArrayList<>();

		for(String key : pairs.keySet()) {
			if (key.endsWith("A")) {
				currs.add(key);
			}
		}

		while(true) {

			for (int j = 0; j < currs.size(); j++) {
				Pair p = pairs.get(currs.get(j));

				if (directions[(int) (i % directions.length)] == 'L') {
					currs.set(j, p.l);
				} else {
					currs.set(j, p.r);
				}
			}

			// System.out.println(currs + " " + i);

			boolean con = false;

			for (String key : currs) {
				if (!key.endsWith("Z")) {
					i++;
					con = true;
					break;
				}
			}

			if (con) {
				continue;
			}

			break;

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
