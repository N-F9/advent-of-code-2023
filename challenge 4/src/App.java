import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		int points = 0;

		while(fs.hasNextLine()) {
			String line = fs.nextLine();

			line = line.substring(line.indexOf(": ") + 2);

			Scanner ls = new Scanner(line);
			ls.useDelimiter("\\s+");

			HashSet<Integer> winning = new HashSet<>();
			
			while(ls.hasNextInt()) {
				winning.add(ls.nextInt());
			}

			System.out.println(winning);

			ls.next();

			int pts = 0;

			while(ls.hasNextInt()) {
				int num = ls.nextInt();
					System.out.print(num + " ");
				if (winning.contains(num)) {
					pts++;
				}
			}


			System.out.println(pts + " " + Math.pow(2, pts - 1));

			if (pts > 0) {
				points += (int) Math.pow(2, pts - 1);
			}

			ls.close();

		}

		System.out.println("Points: " + points);

		fs.close();
	}
}
