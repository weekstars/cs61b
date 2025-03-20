public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private boolean isPalindromehelper(Deque<Character> d){
        if (d.size() <= 1){
            return true;
        }
        else{
            char f = d.removeFirst();
            char l = d.removeLast();
            return f == l && isPalindromehelper(d);
        }
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return isPalindromehelper(d);         
    }

    private boolean isPalindromehelper(Deque<Character> d, CharacterComparator cc){
        if (d.size() <= 1){
            return true;
        }
        else{
            char f = d.removeFirst();
            char l = d.removeLast();
            return cc.equalChars(f, l) && isPalindromehelper(d, cc);
        }
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        return isPalindromehelper(d, cc);  
    }
}
