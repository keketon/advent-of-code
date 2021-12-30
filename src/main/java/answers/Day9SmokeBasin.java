package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.*;
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

    @Part(no = 2)
    public String islandNum() throws IOException {
        init();

        int wall = 9;
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        List<List<Integer>> caveHeight = new ArrayList<>();
        String cursor = br.readLine();
        while (cursor != null) {
            caveHeight.add(Arrays.stream(cursor.split("")).map(Integer::parseInt).collect(Collectors.toList()));
            cursor = br.readLine();
        }

        int r = caveHeight.size();
        int c = caveHeight.get(0).size();

        List<Integer> islandNums = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (caveHeight.get(i).get(j) == wall) continue;

                int size = 0;
                Queue<Coordinate> que = new ArrayDeque<>();
                que.add(new Coordinate(i, j));

                while (!que.isEmpty()) {
                    Coordinate coordinate = que.poll();
                    if (caveHeight.get(coordinate.r).get(coordinate.c) == wall) continue;

                    for (int k = 0; k < dr.length; k++) {
                        int nr = coordinate.r + dr[k];
                        int nc = coordinate.c + dc[k];

                        if (nr >= 0 && nr < r && nc >= 0 && nc < c && caveHeight.get(nr).get(nc) != wall) {
                            que.add(new Coordinate(nr, nc));
                        }
                    }

                    caveHeight.get(coordinate.r).set(coordinate.c, wall);
                    size++;
                }

                islandNums.add(size);
            }
        }

        int ans = islandNums.stream().sorted(Comparator.reverseOrder()).limit(3).reduce((acc, now) -> acc * now).get();

        return String.valueOf(ans);
    }

    private static class Coordinate {
        int r;
        int c;

        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
