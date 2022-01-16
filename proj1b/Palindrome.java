public class Palindrome {
    /** Converts String to Deque */
    public Deque<Character> wordToDeque(String word) {
        /* Deque Deq = new LinkedListDeque(); // Using LinkedListDeque */
        Deque Deq = new ArrayDeque(); // Using ArrayDeque
        for (int i = 0; i < word.length(); i++) {
            Deq.addLast(word.charAt(i));
        }
        return Deq;
    }

    /** Examines whether the word is palindrome */
    public boolean isPalindrome(String word) {
        /** Not using Deque
        if (word.length() <= 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) == word.charAt(word.length() - 1 - i)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
         */

        /** Using Deque data structure
        Deque Deq = wordToDeque(word);
        if (Deq.size() <= 1) {
            return true;
        }
        for (int i = 0; i < Deq.size() / 2; i++) {
            if (Deq.removeFirst() == Deq.removeLast()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
         */

        /** Using Deque data structure and recursion */
        Deque Deq = wordToDeque(word);
        return isPalindromeHelper(Deq);
    }

    private boolean isPalindromeHelper (Deque Deq) {
        if (Deq.size() <= 1) {
            return true;
        }
        if (Deq.removeFirst() != Deq.removeLast()) {
            return false;
        }
        return isPalindromeHelper(Deq);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
