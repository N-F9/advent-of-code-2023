import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		int sum = 0;

		while(fs.hasNext()) {
			String line = fs.nextLine();

			ArrayList<Integer> nums = new ArrayList<>();

			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);

				if (c >= 48 && c <= 57) {
					nums.add((int) c - 48);
				}
			}

			sum += nums.get(0) * 10 + nums.get(nums.size() - 1);
		}

		System.out.println(sum);

		fs.close();
	}
}
