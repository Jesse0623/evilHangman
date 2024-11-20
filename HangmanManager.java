import java.util.*;

public class HangmanManager {
   private int length;
   private int max;
   private Set<Character> guesses;
   private Set<String> words;
   
   public HangmanManager(Collection<String> dictionary, int length, int max) {
       this.length = length;
       this.max = max;
       this.guesses = new TreeSet<>();
       this.words = new TreeSet<>();
   
       for (String word : dictionary) {
           if (word.length() == length) {
               words.add(word);  
           } 
       }
   
       if (length < 1 || max < 0) {
           throw new IllegalArgumentException("Invalid input.");
       }
   }
   
   public Set<String> words() {
      return words;
   }
       
   public int guessesLeft() {
      int guessL = max - guesses.size();
      return guessL;
   }
   
   public Set<Character> guesses() {
      return guesses;
   }
   
   public String pattern() {
      Iterator<String> itr = words.iterator();
      
      if (words.isEmpty()) {
          throw new IllegalStateException("The set of words is empty.");
      }
      
      String s = "";
      for (char letter : itr.next().toCharArray()) {
         if (guesses.contains(letter)) {
             s += letter;
          } else {
             s += "-";
          }
      }       
      return s;
   } 
   
   public int record(char guess) {
      if (guessesLeft() < 1 || words.isEmpty()) {
        throw new IllegalStateException("No guesses left or the list of words is empty");
      }
      
      if (guesses.contains(guess)) {
         throw new IllegalArgumentException("You have already guessed this guess");
      }
      
      int occurrences = 0;
      List<String> wordList = new LinkedList<>(words);
      
      for (String word : wordList) {
         int count = 0;
         for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
               count++;
            }
         }
         occurrences += count;
        
         if (count == 0) {
            words.remove(word);
         }
      }
    
      guesses.add(guess);
    
      return occurrences;
  }
}
