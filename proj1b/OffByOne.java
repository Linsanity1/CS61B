/**
 * OffByOne class that implements CharacterComparator interface.
 * @author Luis Lin
 */
public class OffByOne implements CharacterComparator {

    /** Returns true if characters are equal by the rules of the implementing class. */
    @Override
    public boolean equalChars(char x, char y) {
        return ((x == y - 1) || (x == y + 1));
    }

}
