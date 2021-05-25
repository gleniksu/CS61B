import java.lang.reflect.Array;

public class Palindrome<T>{
    /** Split the word as a letter into Deque. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> AstrDeque = new ArrayDeque<> ();
        for (int i = 0; i < word.length(); i++) {
            AstrDeque.addLast(word.charAt(i));
        }
        return AstrDeque;
    }

    /*public boolean isPalindrome(String word){
        Deque<Character> strDeque= wordToDeque(word);
        for(int i = 0;i < strDeque.size() / 2;) {
            if (strDeque.removeFirst() != strDeque.removeLast()) {
                return false;
            }
        }
    return true;

    }*/


    /*public boolean helper(Deque<Character> wordDeque) {
        //base case
        if (wordDeque.size() == 1 || wordDeque.size() == 0) {
            return true;
        }
        else if (wordDeque.removeFirst() != wordDeque.removeLast()) {
            return false;
        }
        return helper(wordDeque);
    }*/

    private boolean isPalindrome(Deque<Character> strDeque) {
        while (strDeque.size() > 1) {
            return strDeque.removeFirst() == strDeque.removeLast() && isPalindrome(strDeque);
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }



    private boolean isPalindrome(Deque<Character> strDeque, CharacterComparator cc) {
        while (strDeque.size() > 1) {
            return cc.equalChars(strDeque.removeLast(), strDeque.removeFirst()) && isPalindrome(strDeque, cc);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

}
