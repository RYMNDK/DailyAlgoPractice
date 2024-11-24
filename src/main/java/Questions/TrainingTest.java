package Questions;

import java.util.HashMap;
import java.util.List;

import static Questions.Training.*;

public class TrainingTest {
    public static void main(String[] args) {
            // connected map is not based on real world locations
            HashMap<String, List<String>> map = new HashMap<>();
            map.put("Daniel", List.of("Mike", "Sophie", "James", "Tony"));
            map.put("Mike", List.of("Daniel", "James", "Luke"));
            map.put("Tony", List.of("Daniel", "Sophie"));
            map.put("Sophie", List.of("Mike", "Daniel", "Tony", "Eun Ji"));
            map.put("Eun Ji", List.of("Sophie"));
            map.put("James", List.of("Daniel", "Mike"));
            map.put("Luke", List.of("Mike"));

            String[] solution = solution2DFS(map, "James", 20);
            for (String s : solution) {
                System.out.println(s);
            }

    }
}
