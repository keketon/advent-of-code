package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Problem(no = 9)
public class Day9SmokeBasin {
    static final String resourcePath = "Day9SmokeBasin.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String lowPointsRiskLevelSum() throws IOException {
        init();

        List<List<Integer>> caveHeight = new ArrayList<>();
        String cursor = br.readLine();
        while (cursor != null) {
            caveHeight.add(Arrays.stream(cursor.split("")).map(Integer::parseInt).collect(Collectors.toList()));
            cursor = br.readLine();
        }

        int ans = 0;
        for (int i = 0; i < caveHeight.size(); i++) {
            List<Integer> row = caveHeight.get(i);
            for (int j = 0; j < row.size(); j++) {
                int height = row.get(j);
                int surroundingMinHeight = Integer.MAX_VALUE;

                if (i > 0) {
                    surroundingMinHeight = Math.min(surroundingMinHeight, caveHeight.get(i - 1).get(j));
                }
                if (i < caveHeight.size() - 1) {
                    surroundingMinHeight = Math.min(surroundingMinHeight, caveHeight.get(i + 1).get(j));
                }
                if (j > 0) {
                    surroundingMinHeight = Math.min(surroundingMinHeight, caveHeight.get(i).get(j - 1));
                }
                if (j < caveHeight.get(i).size() - 1) {
                    surroundingMinHeight = Math.min(surroundingMinHeight, caveHeight.get(i).get(j + 1));
                }

                if (height < surroundingMinHeight) ans += height + 1;
            }
        }

        return String.valueOf(ans);
    }
}
