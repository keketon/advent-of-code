package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.*;

@Problem(no = 5)
public class Day5HydrothermalVenture {
    static final String resourcePath = "Day5HydrothermalVenture.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String verticalAndHorizontalOverlaps() throws IOException {
        init();

        List<Line> lines = new ArrayList<>();
        int maxX = 0, maxY = 0;

        String cursor = br.readLine();
        while (cursor != null) {
            Line line = Line.from(cursor);
            if (line.xDir * line.yDir == 0) {
                maxX = Math.max(maxX, line.maxX());
                maxY = Math.max(maxY, line.maxY());
                lines.add(line);
            }
            cursor = br.readLine();
        }

        int[][] overlap = new int[maxY + 10][maxX + 10];

        for (Line line : lines) {
            while (line.hasNext()) {
                line.next().ifPresent(point -> overlap[point.y][point.x]++);
            }
        }

        int ans = 0;

        for (int[] ints : overlap) {
            for (int anInt : ints) {
                if (anInt >= 2) ans++;
            }
        }

        return String.valueOf(ans);
    }

    @Part(no = 2)
    public String allOverlaps() throws IOException {
        init();

        List<Line> lines = new ArrayList<>();
        int maxX = 0, maxY = 0;

        String cursor = br.readLine();
        while (cursor != null) {
            Line line = Line.from(cursor);
            maxX = Math.max(maxX, line.maxX());
            maxY = Math.max(maxY, line.maxY());
            lines.add(line);
            cursor = br.readLine();
        }

        int[][] overlap = new int[maxY + 10][maxX + 10];

        for (Line line : lines) {
            while (line.hasNext()) {
                line.next().ifPresent(point -> overlap[point.y][point.x]++);
            }
        }

        int ans = 0;

        for (int[] ints : overlap) {
            for (int anInt : ints) {
                if (anInt >= 2) ans++;
            }
        }

        return String.valueOf(ans);
    }

    static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point clone() {
            return new Point(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Line {
        final Point p1;
        final Point p2;
        Point now;
        private final int xDir;
        private final int yDir;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;

            now = p1;
            xDir = Integer.compare(p2.x, p1.x);
            yDir = Integer.compare(p2.y, p1.y);
        }

        /**
         * Construct from string format like "12,34 -> 56,78"
         */
        public static Line from(String str) {
            int[][] nums = Arrays.stream(str.split(" -> "))
                    .map(splitStr -> Arrays.stream(splitStr.split(",")).mapToInt(Integer::parseInt).toArray())
                    .toArray(int[][]::new);
            Point p1 = new Point(nums[0][0], nums[0][1]);
            Point p2 = new Point(nums[1][0], nums[1][1]);
            return new Line(p1, p2);
        }

        public boolean hasNext() {
            return now != null;
        }

        public Optional<Point> next() {
            if (now == null) {
                return Optional.empty();
            }

            Point returned = now.clone();
            if (now.equals(p2)) {
                now = null;
            } else {
                now = new Point(now.x + xDir, now.y + yDir);
            }
            return Optional.of(returned);
        }

        public int maxX() {
            return Math.max(p1.x, p2.x);
        }

        public int maxY() {
            return Math.max(p1.y, p2.y);
        }
    }
}
