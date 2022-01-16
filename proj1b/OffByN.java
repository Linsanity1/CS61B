/**
 * OffByN class that implements CharacterComparator interface.
 * @author Luis Lin
 */
public class OffByN implements CharacterComparator {

    private int N;

    /** Constructor */
    public OffByN(int n) {
        N = n;
    }

    /** Returns true if characters are equal by the rules of the implementing class. */
    @Override
    public boolean equalChars(char x, char y) {
        return ((x == y - N) || (x == y + N));
    }

}
