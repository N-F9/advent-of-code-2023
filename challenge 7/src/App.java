import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner fs = new Scanner(new File(args[0]));

		ArrayList<Hand> list = new ArrayList<>();

		while(fs.hasNextLine()) {
			list.add(new Hand(fs.next(), fs.nextInt()));
		}

		Collections.sort(list, new HandComparator());

		int sum = 0;
		for (int index = 0; index < list.size(); index++) {
			sum += (index + 1) * list.get(index).win;
		}

		System.out.println(sum);
	}

	public static class Hand {
		static char[] Cards = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
		// rank: Cards.length - i - 1
		char[] hand;
		int win;

		public Hand(String hand, int win) {
			this.win = win;
			this.hand = hand.toCharArray();
		}

		public String toString() {
			return Arrays.toString(hand) + " " + win;
		}

		public int type() {
			HashMap<Character, Integer> map = new HashMap<>();

			for (char c : hand) {
				if (map.get(c) == null) {
					map.put(c, 1);
				} else {
					map.put(c, map.get(c) + 1);
				}
			}

			// 7 Five of a kind, where all five cards have the same label: AAAAA

			// 6 Four of a kind, where four cards have the same label and one card has a different label: AA8AA
			// 5 Full house, where three cards have the same label, and the remaining two cards share a different label: 23332

			// 4 Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
			// 3 Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432

			// 2 One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4

			// 1 High card, where all cards' labels are distinct: 23456
			Integer[] ints = map.values().toArray(new Integer[0]);

			if(map.size() == 1) {
				return 7;
			} else if(map.size() == 2) {				
				int max = Math.max(ints[0], ints[1]);

				if (max == 4) {
					return 6;
				} else {
					return 5;
				}
			} else if (map.size() == 3) {
				int max = ints[0];

				for (int i = 1; i < ints.length; i++) {
					max = Math.max(max, ints[i]);
				}

				if (max == 3) {
					return 4;
				} else {
					return 3;
				}
			} else if (map.size() == 4) {
				return 2;
			} else if (map.size() == 5) {
				return 1;
			}

			return -1;
		}

	}

	static class HandComparator implements Comparator<Hand> {
    public int compare(Hand o1, Hand o2) {
			int diff = o1.type() - o2.type();
			if (diff != 0) {
				return diff;
			} else {
				for (int i = 0; i < o1.hand.length; i++) {
					int j = search(Hand.Cards, o1.hand[i]);
					int k = search(Hand.Cards, o2.hand[i]);

					if (j == k) continue;

					return k - j;
				}

				return 0;

			}
    }
}

	static int search(char arr[], char x)
    {
        for (int i = 0; i < arr.length; i++) {
            // Return the index of the element if the element
            // is found
            if (arr[i] == x)
                return i;
        }
 
        // return -1 if the element is not found
        return -1;
    }
}
