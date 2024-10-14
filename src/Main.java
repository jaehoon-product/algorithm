import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int [][] board = new int[][]{{1, 20300104, 100, 80},{2, 20300804, 847, 37},{3, 20300401, 10, 8}};
        int [] moves = new int[]{1,5,3,5,1,2,1,4};
        System.out.println("answer " + s.solution_35(board, "date", 20300501, "remain"));
    }
}