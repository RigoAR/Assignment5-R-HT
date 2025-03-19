public class WordPattern {
    public static boolean wordPattern(String pattern, char delimiter, String s)
    {
        String[] words = s.split("\\" + delimiter);

        if (pattern.length() != words.length) {
            return false;
        }

        java.util.Map<Character, String> charToWord = new java.util.HashMap<>();
        java.util.Map<String, Character> wordToChar = new java.util.HashMap<>();


        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                charToWord.put(c, word);
            }

            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != c) {
                    return false;
                }
            } else {
                wordToChar.put(word, c);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Example 1: " + wordPattern("abba", '?', "dog?cat?cat?dog"));  // true
        System.out.println("Example 2: " + wordPattern("abba", '|', "apple|banana|grape|apple"));  // false
        System.out.println("Example 3: " + wordPattern("aaaa", ',', "dog,cat,cat,dog"));  // false
        System.out.println("Example 4: " + wordPattern("aaaa", ' ', "ice cream taco day"));  // false
        System.out.println("Example 5: " + wordPattern("adxp", ' ', "ice cream taco day"));  // true

        // Edge cases with weird special characters
        System.out.println("Edge case 1: " + wordPattern("a", '.', "single"));  // true
        System.out.println("Edge case 2: " + wordPattern("abc", '-', "a-b-c"));  // true
        System.out.println("Edge case 3: " + wordPattern("aaa", '_', "same_same_same"));  // true
    }
}