package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Problem(no = 7)
public class Day7TheTreacheryOfWhales {
    static final String resourcePath = "Day7TheTreacheryOfWhales.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String minimalFuelConstantBurn() throws IOException {
        init();

        List<Integer> initialPositions =
                Arrays.stream(br.readLine().split(","))
                        .map(Integer::parseInt)
                        .sorted()
                        .collect(Collectors.toList());

        int pivot = initialPositions.get(initialPositions.size() / 2);

        int ans = 0;

        for (Integer position : initialPositions) {
            ans += Math.abs(position - pivot);
        }

        return String.valueOf(ans);
    }
}
