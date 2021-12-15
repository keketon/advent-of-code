package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Problem(no = 1)
public class Day1SonarSweep {
    @Part(no = 1)
    public String increasedCount() throws IOException {
        String resourcePath = "Day1SonarSweepInput.txt";
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
}
