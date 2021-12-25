package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Problem(no = 4)
public class Day4GiantSquid {
    static final String resourcePath = "Day4GiantSquid.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private static final int BINGO_LINE = 5;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String firstWinnerScore() throws IOException {
        init();

        List<Integer> draws = Arrays.stream(br.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<BingoSheet> sheets = new ArrayList<>();

        String cursor = br.readLine();
        while (cursor != null) {
            List<String> strs = new ArrayList<>(5);
            for (int i = 0; i < BINGO_LINE; i++) {
                strs.add(br.readLine());
            }
            sheets.add(new BingoSheet(strs));
            cursor = br.readLine();
        }

        for (int draw : draws) {
            for (BingoSheet sheet : sheets) {
                boolean hasMarkedLine = sheet.markDraw(draw);
                if (hasMarkedLine) {
                    return String.valueOf(sheet.calcScore(draw));
                }
            }
        }

        int ans = 0;
        return String.valueOf(ans);
    }

    static class BingoSheet {
        int[][] sheet;
        boolean[][] marked;
        static int LINE = BINGO_LINE;

        BingoSheet(List<String> strs) {
            sheet = new int[BINGO_LINE][BINGO_LINE];
            sheet = strs.stream()
                    .map(str -> Arrays.stream(
                            str.strip().split("\\s+")
                    ).mapToInt(Integer::parseInt).toArray())
                    .toArray(int[][]::new);

            marked = new boolean[BINGO_LINE][BINGO_LINE];
        }

        /**
         * @return true if this sheet becomes win state
         */
        public boolean markDraw(int draw) {
            for (int i = 0; i < LINE; i++) {
                for (int j = 0; j < LINE; j++) {
                    if (sheet[i][j] == draw) {
                        marked[i][j] = true;
                        // Assume numbers in sheet is distinct.
                        return hasMarkedLine(i, j);
                    }
                }
            }

            return false;
        }

        private boolean hasMarkedLine(int row, int col) {
            boolean rowMarked = true;
            for (int i = 0; i < LINE; i++) {
                if (!marked[row][i]) {
                    rowMarked = false;
                    break;
                }
            }

            boolean colMarked = true;
            for (int i = 0; i < LINE; i++) {
                if (!marked[i][col]) {
                    colMarked = false;
                    break;
                }
            }

            return rowMarked || colMarked;
        }

        private int calcScore(int lastMarked) {
            int score = 0;
            for (int i = 0; i < LINE; i++) {
                for (int j = 0; j < LINE; j++) {
                    if (!marked[i][j]) {
                        score += sheet[i][j];
                    }
                }
            }

            return score * lastMarked;
        }
    }

}
