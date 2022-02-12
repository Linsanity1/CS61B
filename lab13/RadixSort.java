import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {

    private static final int RADIX = 256;
    private static final int PLACEHOLDER = -1;
    private static int maxStringLen;

    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // Done: Implement LSD Sort
        maxStringLen = 0;
        for (String s : asciis) {
            maxStringLen = maxStringLen < s.length() ? s.length() : maxStringLen;
        }

        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);

        for (int i = maxStringLen - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Done: Optional LSD helper method for required LSD radix sort


        int[] counts = new int[RADIX + 1]; // + 1 for placeholder

        for (String s : asciis) {
            if (index > s.length() - 1) {
                counts[PLACEHOLDER + 1] += 1;
            } else {
                counts[s.charAt(index) + 1] += 1;
            }
        }

        int[] starts = new int[RADIX + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            if (index > s.length() - 1) {
                sorted[starts[PLACEHOLDER + 1]] = s;
                starts[PLACEHOLDER + 1] += 1;
            } else {
                sorted[starts[s.charAt(index) + 1]] = s;
                starts[s.charAt(index) + 1] += 1;
            }
        }

        System.arraycopy(sorted, 0, asciis, 0, sorted.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    /** Main method for unit tests. */
    public static void main(String[] args) {
        String[] asciis = new String[3];
        asciis[0] = "SML";
        asciis[1] = "MLS";
        asciis[2] = "LMS";
        System.out.println(Arrays.toString(asciis));
        String[] sorted = sort(asciis);
        System.out.println(Arrays.toString(asciis));
        System.out.println(Arrays.toString(sorted));
    }
}
