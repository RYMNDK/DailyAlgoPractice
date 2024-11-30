package Questions;

import java.util.*;
import java.util.stream.Collectors;

public class Training {

    // Q1
    // Given a HashMap of connected cities get all city within a certain degree
    // - Do not return own city name
    // solution2(
    //  {Toronto -> [Mississauga], Mississauga -> [Toronto, Oakville]},
    //  "Toronto", 2) => "Oakville", no Toronto because Toronto is caller
    // - no duplicates


    // get all people degree of 2
    //    public static String solution1(HashMap<String, List<Spring>> ConnectedCities) {
    //
    //    }

    // recursive find (dfs)
    // - Time O(N^D) Space O(N)
    // In worst case if all the cities are connected with each other
    public static String[] solution2DFS(HashMap<String, List<String>> connectedCities, String city, int degree){
        Set<String> visited = new HashSet<>();
        Set<String> result = new HashSet<>();
        // solution2DFSHelper(connectedCities, city, degree, city);
        result.remove(city);
        return result.toArray(new String[result.size()]);
    }

    // (HashMap<String, List<String>> connectedCities, String city, int degree,
    //                              Set<String> visited, Set<String> result)
    public static String[] solution2DFSHelper(HashMap<String, List<String>> ConnectedCities, String city, int degree,
                                              Set<String> visited, Set<String> result) {
        return new String[0];
    }

    public static String[] solution2DFSHelperOld(HashMap<String, List<String>> ConnectedCities, String city, int degree, String caller) {
        // add in the valid connected cities
        String[] validCities = ConnectedCities.get(city).stream().filter(name -> !caller.equals(name)).toArray(String[]::new);
        if (degree == 1) {
            return validCities;
        } else {
            ArrayList<String> allConnectedCities = new ArrayList<>();
            for (String nextCity: validCities) {
                // Collections.addAll(allConnectedCities, solution2DFSHelper(ConnectedCities, nextCity, degree - 1, caller));
            }
            // remove duplicates
            return allConnectedCities.stream().distinct().toArray(String[]::new);
        }
    }

    // Chatgpt recommend BFS
    // Why BFS is Better in This Case
    //   Avoids Redundant Computations: BFS ensures each node is visited at most once, preventing the exponential growth of paths that occurs in the recursive DFS approach.
    //   Level-by-Level Exploration: BFS explores nodes in order of their distance from the starting node, making it straightforward to limit the search to a specific degree.
    //   Linear Time Complexity: The time complexity of BFS is linear with respect to the number of nodes and edges in the graph that are within the specified degree.
    public static String[] solution2BFS(HashMap<String, List<String>> ConnectedCities, String city, int degree){
        return new String[0];
    }

    // Given two people's names, return the distance between them in friend connections, their Kevin Bacon number?

    // Q2
    // Given an array of cake sizes and number of attendees, what is the largest piece of cake we can give each person.
    // We want to give each person one whole piece of cake, not two that add up to the given size.
    public static int largestCakeForEveryone(int[] cakes, int participants) {
        return participants;
    }

}
