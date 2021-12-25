package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Problem(no = 6)
public class Day6Lanternfish {
    static final String resourcePath = "Day6Lanternfish.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String lanternfishGrouth80Days() throws IOException {
        init();

        return lanternfishGrouth(80);
    }

    @Part(no = 2)
    public String lanternfishGrouth256Days() throws IOException {
        init();

        return lanternfishGrouth(256);
    }

    public String lanternfishGrouth(int maxDays) throws IOException {
        init();

        List<Integer> initialCounters = Arrays.stream(br.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        long[] counter = new long[10];

        for (int cnt : initialCounters) {
            counter[cnt]++;
        }

        for (int i = 0; i < maxDays; i++) {
            counter[7] += counter[0];
            counter[9] += counter[0];
            for (int j = 0; j < 9; j++) {
                counter[j] = counter[j + 1];
            }
            counter[9] = 0L;
        }

        long ans = Arrays.stream(counter).sum();

        return String.valueOf(ans);
    }

}
