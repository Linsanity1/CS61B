import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    OffByN offBy5 = new OffByN(5);

    // Your tests go here.

    @Test
    public void testEqualChars() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('A', 'F'));
        assertFalse(offBy5.equalChars('A', 'A'));
    }

    @Test
    public void testIsPanlidrome() {
        assertFalse(offBy5.isPalindrome("assa"));
        assertTrue(offBy5.isPalindrome("ling"));
        assertTrue(offBy5.isPalindrome("A"));
    }

}
