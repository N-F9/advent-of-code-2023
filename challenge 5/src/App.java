import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));
		ArrayList<Long> list = new ArrayList<>();

		String[] l = fs.nextLine().split(" ");

		for(int i = 1; i < l.length; i++) {
			list.add(Long.parseLong(l[i]));
		}
		boolean[] changed = new boolean[list.size()];

		fs.nextLine();
		fs.nextLine();

		while(fs.hasNextLine()) {
			ArrayList<Range> ranges = new ArrayList<>();

			while(fs.hasNextLong()) {
				ranges.add(new Range(fs.nextLong(), fs.nextLong(), fs.nextLong()));
			}

			if (fs.hasNextLine()) {
			fs.nextLine();}

			System.out.println(ranges);

			for(Range range : ranges) {
				for(int i = 0; i < list.size(); i++) {
					long map = range.map(list.get(i));
					System.out.println("" + list.get(i));
					if(map != -1 && !changed[i]) {
						list.set(i, map);
					System.out.println("-->" + list.get(i));
						changed[i] = true;
					}
				}
			}

			changed = new boolean[list.size()];

			if (fs.hasNextLine()) {
				fs.nextLine();
				fs.nextLine();
			} else {
				break;
			}
		}

		System.out.println(list);
		int min = 0;
		for(int i = 1; i < list.size(); i++) {
			if (list.get(min) > list.get(i)) {
				min = i;
			}
		}

		System.out.println(list.get(min));

		fs.close();
	}

	private static class Range {
		long source;
		long destination;
		long size;

		public Range(long destination, long source, long size) {
			this.source = source;
			this.destination = destination;
			this.size = size;
		}

		// -1 means source == destination
		public long map(long s) {
			if (s >= source && s < source + size) {
				System.out.println(s + " " + source + " " + size + " " + destination);
				return s - source + destination;
			}
			return -1;
		}
	}
}
