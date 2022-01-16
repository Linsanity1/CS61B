import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        assertFalse(palindrome.isPalindrome("Assa"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("A"));
        assertFalse(palindrome.isPalindrome("Yo, banana boy"));
        assertTrue(palindrome.isPalindrome("Yo b anana b oY"));
    }

    /** Test isPalindrome with specified interface CharacterComparator cc */
    static OffByOne offByOne = new OffByOne();
    OffByN offBy5 = new OffByN(5);
    public void testPalindromeCC() {
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("ling", offBy5));
        assertTrue(palindrome.isPalindrome("A", offBy5));
    }

}
