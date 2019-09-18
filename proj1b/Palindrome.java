public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));//charAt() can get each item in a string
        }
        return deque;
    }

    public boolean isPalindrome(String word, int start){
        /**
        Deque<Character> deque = wordToDeque(word);
        for(int i = 0; i < word.length(); i++){
            if(!deque.removeLast().equals(word.charAt(i))){
                return false;
            }
        }
        return true;
         */
        Deque<Character> deque = wordToDeque(word);
        if(deque.removeLast().equals(word.charAt(start))){
            isPalindrome(word, start + 1);
        }else {
            return false;
        }
        return true;
    }

}

