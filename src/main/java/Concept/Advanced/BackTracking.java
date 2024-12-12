package Concept.Advanced;

import java.util.*;
import java.util.stream.Collectors;

public class BackTracking {

    public static void main(String[] args) {

        int[] keypress9 = {7, 6, 6, 3, 8, 4, 6, 3};
        String[] validWords9 = {"some","time","rome","sometime","so","me"};
        List<List<String>> result9 = NineKeyPhases(keypress9, validWords9);
        for (List<String> l: result9) {
            System.out.println(l);
        }
        // [["rome","time"],["so","me","time"],["some","time"],["sometime"]]


    }

    // Match character by words,
    public static List<List<String>> NineKeyPhases(int[] keypress, String[] validWords) {
        if (validWords == null || validWords.length == 0 || keypress == null || keypress.length == 0) {
            return new ArrayList<>();
        }

        Set<String> validWordsSet = new HashSet<>(List.of(validWords));

        Map<Integer, List<Character>> keysMap = new HashMap<>();
        keysMap.put(2, Arrays.asList('a', 'b', 'c'));
        keysMap.put(3, Arrays.asList('d', 'e', 'f'));
        keysMap.put(4, Arrays.asList('g', 'h', 'i'));
        keysMap.put(5, Arrays.asList('j', 'k', 'l'));
        keysMap.put(6, Arrays.asList('m', 'n', 'o'));
        keysMap.put(7, Arrays.asList('p', 'q', 'r', 's'));
        keysMap.put(8, Arrays.asList('t', 'u', 'v'));
        keysMap.put(9, Arrays.asList('w', 'x', 'y', 'z'));

        List<List<String>> allResults = new ArrayList<>();

        NineKeyPhasesBT(keypress, validWordsSet, keysMap, 0, new ArrayList<>(), allResults);

        return allResults;

    }

    private static void NineKeyPhasesBT(int[] keypress, Set<String> validWordsSet, Map<Integer, List<Character>> keysMap,
                                        int curIndex, List<String> curWords, List<List<String>> allResults) {
        if (curIndex == keypress.length) {
            allResults.add(new ArrayList<>(curWords));
        } else {

        }
    }


//    public static List<String> NineKeyWords(int[] keypress, String[] validWords) {
//        if (validWords == null || validWords.length == 0 || keypress == null || keypress.length == 0) {
//            return new ArrayList<>();
//        }
//
//        // Set<String> validWordsSet = new HashSet<>(List.of(validWords));
//
//        // remove any words that's not the same length as keypress, small optimization
//        Set<String> validWordsSet= Arrays.stream(validWords)
//                .filter(word -> word.length() == keypress.length)
//                .collect(Collectors.toSet());
//
//        Map<Integer, List<Character>> keysMap = new HashMap<>();
//        keysMap.put(2, Arrays.asList('a', 'b', 'c'));
//        keysMap.put(3, Arrays.asList('d', 'e', 'f'));
//        keysMap.put(4, Arrays.asList('g', 'h', 'i'));
//        keysMap.put(5, Arrays.asList('j', 'k', 'l'));
//        keysMap.put(6, Arrays.asList('m', 'n', 'o'));
//        keysMap.put(7, Arrays.asList('p', 'q', 'r', 's'));
//        keysMap.put(8, Arrays.asList('t', 'u', 'v'));
//        keysMap.put(9, Arrays.asList('w', 'x', 'y', 'z'));
//
//        ArrayList<String> allResults = new ArrayList<>();
//
//        // use a stringbuilder here
//        NineKeyWordsBT(keypress, validWordsSet, keysMap, 0, "", allResults);
//
//        return allResults;
//    }
//
//    // NineKeyWordsBT
//    private static void NineKeyWordsBT(int[] keypress, Set<String> validWordsSet, Map<Integer, List<Character>> keysMap,
//                                       int curIndex, String curWord, ArrayList<String> allResults){
//        if (curIndex == keypress.length){
//            if (validWordsSet.contains(curWord)) {
//                allResults.add(curWord);
//            }
//        } else {
//            // list all choices
//            List<Character> choices = keysMap.get(keypress[curIndex]);
//
//            for (Character ch : choices) {
//                // apply choice
//                String tempWord = curWord + ch;
//                // call Backtrack
//                NineKeyWordsBT(keypress, validWordsSet, keysMap, curIndex + 1, tempWord, allResults);
//                // undo choice
//            }
//        }
//    }
}
