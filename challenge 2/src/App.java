import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));
		HashMap<String, Integer> map = new HashMap<>();

		map.put("blue", 14);
		map.put("green", 13);
		map.put("red", 12);

		int sum = 0;
		int sumPower = 0;

		while(fs.hasNextLine()) {
			String line = fs.nextLine();

			int game = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.indexOf(": ")));

			line = line.substring(line.indexOf(": ") + 2);
			
			String[] sets = line.split("; ");

			HashMap<String, Integer> power = new HashMap<>();
			power.put("blue", 0);
			power.put("green", 0);
			power.put("red", 0);

			boolean add = true;

			for (int i = 0; i < sets.length; i++) {
				String[] cubes = sets[i].split(", ");
				for (int j = 0; j < cubes.length; j++) {
					String[] cube = cubes[j].split(" ");
					int num = Integer.parseInt(cube[0]); 
					if (num > map.get(cube[1])) {
						add = false;
					}
					power.put(cube[1], Math.max(num, power.get(cube[1])));
				}
			}

			if(add) {
				sum += game;
			}

			sumPower += power.get("blue") * power.get("green") * power.get("red");
		}

		System.out.println("Sum: " + sum);
		System.out.println("Power: " + sumPower);

		fs.close();
	}
}
