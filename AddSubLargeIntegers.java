package AddingLargeIntegers;

/*
Implementation of addition and subtraction function of two large integers.
@author G25
 */
import java.util.List;
import java.util.ListIterator;

public class AddSubLargeIntegers {
	/**
	 * Helper function that returns the next element of an iterator if present,
	 * false otherwise
	 * 
	 * @param iterator
	 *            : the iterator whos elements are to be inspected
	 * @return element if there exists a next element in iterator, and null
	 *         otherwise
	 */
	static Integer next(ListIterator<Integer> iterator) {
		if (iterator.hasNext())
			return iterator.next();

		else
			return null;
	}

	/**
	 * Procedure to calcuate difference of two large integers. Stores the
	 * difference of List x and List y in List z
	 *
	 * @param x
	 *            : Integer List - the minuend - the numbers are stored from
	 *            right to left
	 * @param y
	 *            : Integer List - the subtrahend - the numbers are stored from
	 *            right to left Pre-condition : Always integer in List X is
	 *            greated than or equal to List Y. Each index of list will hold
	 *            only one digit Else result is arbitary
	 * @param z
	 *            : Output, i.e the difference
	 * @param b
	 *            : base notation to be used for subtraction. Pre-condition :
	 *            Validated input must be given. Else result is arbitary
	 * 
	 */
	public static void sub(List<Integer> x, List<Integer> y, List<Integer> z, int b) {
		if (x == null || y == null) {
			System.out.println("One or more input is empty");
			return;
		}

		int xSize = x.size();
		int ySize = y.size();

		// Logic to append leading zeros if List Y is smaller than List X
		if (ySize < xSize) {
			while (ySize < xSize) {
				y.add(0);
				ySize++;
			}
		}
		ListIterator<Integer> aIterator = x.listIterator();
		ListIterator<Integer> bIterator = y.listIterator();

		Integer aTemp = next(aIterator);// Holds List x element
		Integer bTemp = next(bIterator);// Holds List y element

		int borrow = 0; // variable to store the borrow for assiting the
		// subtraction
		int sub = 0; // temporary variable to store the result of difference
		// between two integers
		while (aTemp != null && bTemp != null) {

			/*
			 * When element pointed by List x iterator is greater than or equal
			 * to y iterator, then borrow is not required.
			 */
			if (aTemp >= bTemp) {
				borrow = 0;
			}
			/*
			 * In other case where Y is greater than X, we borrow one from the
			 * left, borrow is set to 1, and base is added to the element
			 * pointed by x iterator
			 */
			else {
				aTemp = aTemp + b;
				borrow = 1;
			}
			// subraction is performed and stored in a temporary variable - sub.
			sub = aTemp - bTemp;
			z.add(sub); // difference added to the output.
			aTemp = next(aIterator); // we move to the next digit of List x

			// Check if the value pointed by x iterator is not null, then
			// subtract borrow from the temp.
			// borrow will be 0 in case if X>=Y in above steps, 1 otherwise. If
			// x is null then set aTemp
			// to zero.
			if (aTemp != null)
				aTemp = aTemp - borrow;
			else
				aTemp = 0;
			// If bTemp is not null, then move to the next digit of List y
			if (bTemp != null)
				bTemp = next(bIterator);

		}
		// When List y is exhausted, we add the remaining elements in List x to
		// the output
		while (aTemp != null) {
			z.add(aTemp);
			aTemp = next(aIterator);
		}

		trim(z);// this function trims the leading zeroes in the output.

	}

	/**
	 * Procedure to trim the leading zeroes in a list Return the list after
	 * removing the leading zeroes(non significant zeros) if any present.
	 * 
	 * @param a
	 *            : Integer List. removes all non significant zeros in the list,
	 *            else returns the list unchanged
	 */
	static void trim(List<Integer> a) {
		int i = a.size() - 1;

		while (a.get(i).intValue() == 0 && i != 0) {
			a.remove(i);
			i--;
		}

	}

	/**
	 * Procedure to calculate sum of two large integers. Stores the sum of List
	 * x and List y into List z
	 *
	 * @param x
	 *            : Integer List - the 1st summand - the numbers are stored from
	 *            right to left
	 * @param y
	 *            : Integer List - the 2nd summand - the numbers are stored from
	 *            right to left Pre-condition : Each index of list will hold
	 *            only one digit Else result is arbitary
	 * @param z
	 *            : Output, i.e the sum of the two integers in lists x and y
	 * @param b
	 *            : base notation to be used for addition. Pre-condition :
	 *            Validated input must be given. Else result is arbitary
	 * 
	 */
	public static void add(List<Integer> x, List<Integer> y, List<Integer> z, int b) {

		if (x == null || y == null) {
			System.out.println("One or more input is empty");
			return;
		}
		ListIterator<Integer> aIterator = x.listIterator();
		ListIterator<Integer> bIterator = y.listIterator();

		Integer aTemp = next(aIterator);// Holds List x element
		Integer bTemp = next(bIterator);// Holds List y element

		int carry = 0; // holds the digit to carry forward to the next left
		// digit
		int sum = 0; // temporary variable to store the sum of two digits

		while (aTemp != null && bTemp != null) {
			// Calculating the sum of element pointed by x iterator and y
			// iterator and carry
			sum = aTemp + bTemp + carry;
			// When sum exceeds the base value, we take the carry by division of
			// sum by base, else
			// carry is set to 0 in other cases
			if (sum > b - 1) {
				carry = sum / b;
			} else {
				carry = 0;

			}
			// The remainder is stored in the output as sum of two digits
			sum = sum % b;
			z.add(sum);
			aTemp = next(aIterator);
			bTemp = next(bIterator);
		}

		// When list y is exhausted, elements of list x is copied to the output
		// along with the carry
		while (aTemp != null) {
			sum = aTemp + carry;
			carry = 0;
			z.add(sum);
			aTemp = next(aIterator);
		}
		// When list x is exhausted, elements of list y is copied to the output
		// along with the carry
		while (bTemp != null) {
			sum = bTemp + carry;
			carry = 0;
			z.add(sum);
			bTemp = next(bIterator);
		}
		// Adding any addition carry that is overflowing
		if (carry != 0)
			z.add(carry);

	}

}
