import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		int sum = 0;

		while(fs.hasNext()) {
			String line = fs.nextLine();

			String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

			for(int i = 0; i < numbers.length; i++) {
				line = line.replace(numbers[i], numbers[i].substring(0, numbers[i].length() / 2) + (i + 1) + numbers[i].substring(numbers[i].length() / 2 + 1));
			}

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