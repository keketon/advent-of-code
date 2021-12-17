package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

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
    public String lifeSupportRating() throws IOException {
        init();

        List<String> co2 = new LinkedList<>();
        List<String> o2 = new LinkedList<>();
        String str;
        while ((str = br.readLine()) != null) {
            co2.add(str);
            o2.add(str);
        }

        int n = co2.get(0).length();

        for (int i = 0; i < n; i++) {
            List<String> bitStands = new LinkedList<>();
            List<String> bitNotStands = new LinkedList<>();

            int sum = 0;

            for (String bit : co2) {
                if (bit.charAt(i) == '1') {
                    sum++;
                    bitStands.add(bit);
                } else {
                    bitNotStands.add(bit);
                }
            }

            co2 = 2 * sum >= co2.size() ? bitStands : bitNotStands;
            if (co2.size() == 1) break;
        }

        for (int i = 0; i < n; i++) {
            List<String> bitStands = new LinkedList<>();
            List<String> bitNotStands = new LinkedList<>();

            int sum = 0;

            for (String bit : o2) {
                if (bit.charAt(i) == '1') {
                    sum++;
                    bitStands.add(bit);
                } else {
                    bitNotStands.add(bit);
                }
            }

            o2 = 2 * sum < o2.size() ? bitStands : bitNotStands;
            if (o2.size() == 1) break;
        }

        int co2Res = 0, o2Res = 0;

        for (int i = 0; i < n; i++) {
            co2Res *= 2;
            o2Res *= 2;

            co2Res += co2.get(0).charAt(i) - '0';
            o2Res += o2.get(0).charAt(i) - '0';
        }

        int ans = co2Res * o2Res;

        return String.valueOf(ans);
    }
}
