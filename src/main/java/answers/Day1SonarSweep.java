package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

@Problem(no = 1)
public class Day1SonarSweep {
    @Part(no = 1)
    public String increasedCount() throws IOException {
        String resourcePath = "Day1SonarSweep.txt";
        File f = new File(Const.INPUT_DIR + resourcePath);
        BufferedReader br = new BufferedReader(new FileReader(f));

        String str;
        int prv = Integer.MAX_VALUE;
        int increased = 0;

        while ((str = br.readLine()) != null) {
            int now = Integer.parseInt(str);
            if (now > prv) increased++;
            prv = now;
        }

        return String.valueOf(increased);
    }

    /**
     * No need to possess sum of window.
     * What we only need id to compare a newcomer and a graduate of window.
     */
    @Part(no = 2)
    public String increasedCountWithRange() throws IOException {
        String resourcePath = "Day1SonarSweep.txt";
        File f = new File(Const.INPUT_DIR + resourcePath);
        BufferedReader br = new BufferedReader(new FileReader(f));

        int range = 3;
        String str;
        int increased = 0;
        Queue<Integer> que = new ArrayDeque<>();

        for (int i = 0; i < range; i++) {
            que.add(Integer.parseInt(br.readLine()));
        }

        while ((str = br.readLine()) != null) {
            int now = Integer.parseInt(str);
            if (now > que.poll()) increased++;
            que.offer(now);
        }

        return String.valueOf(increased);
    }
}
