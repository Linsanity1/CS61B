public class Palindrome {
    /** Converts String to Deque */
    public Deque<Character> wordToDeque(String word) {
        /* Deque deq = new LinkedListDeque(); // Using LinkedListDeque */
        Deque deq = new ArrayDeque(); // Using ArrayDeque
        for (int i = 0; i < word.length(); i++) {
            deq.addLast(word.charAt(i));
        }
        return deq;
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
        Deque deq = wordToDeque(word);
        if (deq.size() <= 1) {
            return true;
        }
        for (int i = 0; i < Deq.size() / 2; i++) {
            if (deq.removeFirst() == deq.removeLast()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
         */

        /** Using Deque data structure and recursion */
        Deque deq = wordToDeque(word);
        return isPalindromeHelper(deq);
    }

    private boolean isPalindromeHelper(Deque deq) {
        if (deq.size() <= 1) {
            return true;
        }
        if (deq.removeFirst() != deq.removeLast()) {
            return false;
        }
        return isPalindromeHelper(deq);
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
