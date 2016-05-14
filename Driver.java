package AddingLargeIntegers;
/** Driver program for Addition and Subtraction of two large integers
 * The driver program prompts for entering the two integers for calculation.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	//Stores the given string input in reverse order into the integer List
	static void input(List<Integer> a, String s) {
		int n = s.length();
		while (n > 0) {
			a.add(Integer.parseInt(String.valueOf(s.charAt(n - 1))));
			n--;
		}

	}

	public static void main(String[] args) throws IOException {

		List input1 = new ArrayList();
		List input2 = new ArrayList();
		List<Integer> c = new ArrayList();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1 - Add , 2 - Subtract");
		Integer choice = Integer.parseInt(br.readLine());
		System.out.println("Enter the first integer :");
		String digit1 = br.readLine();

		input(input1, digit1);
		System.out.println("Enter the second integer :");
		String digit2 = br.readLine();
		input(input2, digit2);
		System.out.println("Enter the base :");
		Integer base = Integer.parseInt(br.readLine());

		List<Integer> a = new ArrayList<Integer>(input1);
		List<Integer> b = new ArrayList<Integer>(input2);

		switch (choice) {
		case 1:
			AddSubLargeIntegers.add(a, b, c, base);
			break;
		case 2:
			AddSubLargeIntegers.sub(a, b, c, base);
			break;
		}

		for (int i = c.size() - 1; i >= 0; i--) {
			System.out.print(c.get(i));
		}
	}
}
