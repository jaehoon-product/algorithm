import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int [][] board = new int[][]{{1, 20300104, 100, 80},{2, 20300804, 847, 37},{3, 20300401, 10, 8}};
        int [] moves = new int[]{1,5,3,5,1,2,1,4};

        String today = "2022.05.19";
        String[] terms = new String[] {"A 6", "B 12", "C 3"};
        String[] privacies = new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" };
        String[] words = new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        System.out.println("answer " + s.solution_45(3,words));
    }
}