import java.io.File;
import java.util.*;

public class Part2 {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		String[] l = fs.nextLine().split(" ");

		fs.nextLine();
		fs.nextLine();
			
		ArrayList<Range>[] ranges = new ArrayList[7];
		for(int p = 0; p < ranges.length; p++) {
			ranges[p] = new ArrayList<>();
		}
		int k = 0;

		while(fs.hasNextLine()) {

			while(fs.hasNextLong()) {
				ranges[k].add(new Range(fs.nextLong(), fs.nextLong(), fs.nextLong()));
			}

			if (fs.hasNextLine()) {
				fs.nextLine();
			}

			k++;

			if (fs.hasNextLine()) {
				fs.nextLine();
				fs.nextLine();
			} else {
				break;
			}
		}

		long min = Long.MAX_VALUE;

		for(int i = 1; i < l.length; i+=2) {
			for(int j = 0; j < Long.parseLong(l[i+1]); j++) {
				long t = Long.parseLong(l[i]) + j;
				boolean changed = false;
				for(ArrayList<Range> range : ranges) {
					for(Range r : range) {
						long map = r.map(t);
						if(map != -1 && !changed) {
							t = map;
							changed = true;
						}
					}
					changed = false;
				}
				if (min > t) {
					min = t;
				}
			}
		}

		System.out.println(min);

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
				return s - source + destination;
			}
			return -1;
		}
	}
}
