package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Problem(no = 8)
public class Day8SevenSegmentSearch {
    static final String resourcePath = "Day8SevenSegmentSearch.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String findUniqueNums() throws IOException {
        init();

        List<String[]> outputs = new ArrayList<>();
        String cursor = br.readLine();
        while (cursor != null) {
            outputs.add(Arrays.copyOfRange(cursor.split(" "), 11, 15));
            cursor = br.readLine();
        }

        List<Integer> target = Arrays.asList(2, 3, 4, 7);

        int ans = (int) outputs.stream()
                .flatMap((Function<String[], Stream<String>>) Arrays::stream)
                .filter(s -> target.contains(s.length()))
                .count();

        return String.valueOf(ans);
    }
}
