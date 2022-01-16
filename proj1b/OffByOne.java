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

    /** Examines whether word is off-by-one palindrome. */
    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!equalChars(word.charAt(i), word.charAt(word.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

}
