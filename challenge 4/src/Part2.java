import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		int totalcards = 1;

		int[] cards = new int[189];
		int[] points = new int[189];

		for (int i = 0; i < cards.length; i++) {
			cards[i] = 1;
		}

		while(fs.hasNextLine()) {
			String line = fs.nextLine();
			int game = Integer.parseInt(line.substring(0, line.indexOf(": ")).split("\\s+")[1]) - 1;

			line = line.substring(line.indexOf(": ") + 2);

			Scanner ls = new Scanner(line);
			ls.useDelimiter("\\s+");

			HashSet<Integer> winning = new HashSet<>();
			
			while(ls.hasNextInt()) {
				winning.add(ls.nextInt());
			}

			ls.next();

			int pts = 0;

			while(ls.hasNextInt()) {
				int num = ls.nextInt();
				if (winning.contains(num)) {
					pts++;
				}
			}

			points[game] = pts;

			ls.close();

		}
		fs.close();
		
		System.out.println(Arrays.toString(points));

		int curr = 0;


		while(curr < points.length) {
			for(int i = 0; i < cards[curr]; i++) {
				for(int j = 0; j < points[curr]; j++) {
					cards[curr + j + 1]++;
				}
			}
			curr++;			
		}
		System.out.println(Arrays.toString(cards));
		for(int i = 0; i < cards.length; i++) {
			totalcards += cards[i];
		}

		System.out.println(totalcards - 1);

	}
}
