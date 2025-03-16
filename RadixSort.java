import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadixSort {

    public static String[] radixSort(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int maxLength = 0;
        for (String s : arr) {
            maxLength = Math.max(maxLength, s.length());
        }

        String[] result = Arrays.copyOf(arr, arr.length);

        for (int pos = maxLength - 1; pos >= 0; pos--) {
            result = countingSort(result, pos);
        }

        return result;
    }

    private static String[] countingSort(String[] arr, int pos)
    {
        Map<Character, List<String>> buckets = new HashMap<>();
        List<String> shortStrings = new ArrayList<>();

        for (String s : arr) {
            if (pos >= s.length())
            {
                shortStrings.add(s);
            } else {
                char c = s.charAt(pos);
                buckets.putIfAbsent(c, new ArrayList<>());
                buckets.get(c).add(s);
            }
        }

        List<Character> sortedChars = new ArrayList<>(buckets.keySet());
        sortedChars.sort(null);

        String[] sorted = new String[arr.length];
        int index = 0;

        for (String s : shortStrings) {
            sorted[index++] = s;
        }

        for (char c : sortedChars) {
            for (String s : buckets.get(c)) {
                sorted[index++] = s;
            }
        }

        return sorted;
    }


    public static String[] radixSortWithCase(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int maxLength = 0;
        for (String s : arr) {
            maxLength = Math.max(maxLength, s.length());
        }

        String[] result = Arrays.copyOf(arr, arr.length);

        for (int pos = maxLength - 1; pos >= 0; pos--) {
            result = countingSortWithCase(result, pos);
        }

        return result;
    }


    private static String[] countingSortWithCase(String[] arr, int pos) {
        Map<Character, List<String>> buckets = new HashMap<>();
        List<String> shortStrings = new ArrayList<>();

        for (String s : arr) {
            if (pos >= s.length()) {
                shortStrings.add(s);
            } else {
                char c = s.charAt(pos);
                buckets.putIfAbsent(c, new ArrayList<>());
                buckets.get(c).add(s);
            }
        }


        List<Character> sortedChars = new ArrayList<>(buckets.keySet());
        sortedChars.sort((c1, c2) -> {
            if (Character.isUpperCase(c1) && Character.isUpperCase(c2) || Character.isLowerCase(c1) && Character.isLowerCase(c2))
            {
                return Character.compare(c1, c2);
            }
            if (Character.isLowerCase(c1) && Character.isUpperCase(c2)) {
                return -1;
            }
            return 1;
        });

        String[] sorted = new String[arr.length];
        int index = 0;

        for (String s : shortStrings) {
            sorted[index++] = s;
        }

        for (char c : sortedChars) {
            for (String s : buckets.get(c)) {
                sorted[index++] = s;
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        String[] input = {
                "google", "gojo", "amazingly", "jogo", "luna", "pup",
                "solas", "solo", "pupperino", "amaterasu", "amazon",
                "puppy", "hydra", "amazonia", "vueltiao"
        };

        System.out.println("Original array:");
        System.out.println(Arrays.toString(input));

        String[] sorted = radixSort(input);

        System.out.println("\nSorted array:");
        System.out.println(Arrays.toString(sorted));

        System.out.println("\nSample Output:");
        System.out.println(String.join(", ", sorted));

        // Extra credit
        String[] mixedCaseInput = {
                "Google", "gojo", "Amazingly", "jogo", "Luna", "pup",
                "Solas", "solo", "Pupperino", "amaterasu", "Amazon",
                "puppy", "Hydra", "amazonia", "vueltiao"
        };

        System.out.println("\nOriginal array with mixed case:");
        System.out.println(Arrays.toString(mixedCaseInput));

        String[] sortedWithCase = radixSortWithCase(mixedCaseInput);

        System.out.println("\nSorted array with uppercase precedence:");
        System.out.println(Arrays.toString(sortedWithCase));

        System.out.println("\nExtra Credit Output:");
        System.out.println(String.join(", ", sortedWithCase));
    }
}
