package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;

@Problem(no = 3)
public class Day3BinaryDiagnostic {
    static final String resourcePath = "Day3BinaryDiagnostic.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String powerConsumption() throws IOException {
        init();

        String str = br.readLine();
        int n = str.length();
        int[] bitCounts = new int[n];
        int inputRow = 0;

        do {
            for (int i = 0; i < n; i++) {
                bitCounts[i] += str.charAt(i) - '0';
            }
            inputRow++;
        } while ((str = br.readLine()) != null);

        int gamma = 0, epsilon = 0;

        for (int i = 0; i < n; i++) {
            gamma *= 2;
            epsilon *= 2;

            if (bitCounts[i] > inputRow / 2) {
                gamma++;
            } else {
                epsilon++;
            }
        }

        int ans = gamma * epsilon;
        return String.valueOf(ans);
    }

    @Part(no = 2)
    public String xxx() {
        return "";
    }
}
