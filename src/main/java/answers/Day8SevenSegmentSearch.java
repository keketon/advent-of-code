package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;
import java.util.*;
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

    private static final char[][] SAMPLE = {
            {'a', 'b', 'c', 'e', 'f', 'g'},
            {'c', 'f'},
            {'a', 'c', 'd', 'e', 'g'},
            {'a', 'c', 'd', 'f', 'g'},
            {'b', 'c', 'd', 'f'},
            {'a', 'b', 'd', 'f', 'g'},
            {'a', 'b', 'd', 'e', 'f', 'g'},
            {'a', 'c', 'f'},
            {'a', 'b', 'c', 'd', 'e', 'f', 'g'},
            {'a', 'b', 'c', 'd', 'f', 'g'},
    };

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

    @Part(no = 2)
    public String detectHiddenNums() throws IOException {
        init();

        String cursor = br.readLine();
        int ans = 0;
        while (cursor != null) {
            String[] hint = Arrays.copyOfRange(cursor.split(" "), 0, 10);
            String[] hidden = Arrays.copyOfRange(cursor.split(" "), 11, 15);
            ans += solveHidden(hint, hidden);
            cursor = br.readLine();
        }

        return String.valueOf(ans);
    }

    private int solveHidden(String[] hint, String[] hidden) {
        char[][] hintca = toSortedCharArray(hint);
        char[][] hiddenca = toSortedCharArray(hidden);

        // hidden to sample
        Map<Character, Character> transform = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        Map<Integer, Character> sampleCount = new HashMap<>();
        sampleCount.put(6, 'b');
        sampleCount.put(4, 'e');
        sampleCount.put(9, 'f');

        for (char[] chars : hintca) {
            for (char aChar : chars) {
                count.put(aChar, count.getOrDefault(aChar, 0) + 1);
            }
        }

        char[] one = Arrays.stream(hintca).filter(ca -> ca.length == 2).findFirst().get();
        char[] seven = Arrays.stream(hintca).filter(ca -> ca.length == 3).findFirst().get();
        transform.put(diff(seven, one).get(0), 'a');

        for (Map.Entry<Character, Integer> charIntEntry : count.entrySet()) {
            char hiddenChar = charIntEntry.getKey();
            int cnt = charIntEntry.getValue();
            if (cnt == 8 && !transform.containsKey(hiddenChar)) {
                // 'a' is already found
                transform.put(hiddenChar, 'c');
            } else if (cnt == 7) {
                char[] four = Arrays.stream(hintca).filter(ca -> ca.length == 4).findFirst().get();
                boolean contained = false;
                for (char c : four) {
                    if (c == hiddenChar) {
                        contained = true;
                        break;
                    }
                }

                transform.put(hiddenChar, contained ? 'd' : 'g');
            } else if (sampleCount.containsKey(cnt)) {
                transform.put(hiddenChar, sampleCount.get(cnt));
            }
        }

        // calc hidden number
        int hiddenNum = 0;
        for (char[] chars : hiddenca) {
            char[] transformed = Arrays.copyOf(chars, chars.length);
            for (int i = 0; i < transformed.length; i++) {
                transformed[i] = transform.get(transformed[i]);
            }
            Arrays.sort(transformed);

            hiddenNum *= 10;
            for (int i = 0; i < SAMPLE.length; i++) {
                if (isSame(transformed, SAMPLE[i])) {
                    hiddenNum += i;
                    break;
                }
            }
        }

        return hiddenNum;
    }

    private char[][] toSortedCharArray(String[] strings) {
        return Arrays.stream(strings).map(str -> {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            return ca;
        }).toArray(char[][]::new);
    }

    private List<Character> diff(char[] master, char[] subBy) {
        Set<Character> subBySet = new HashSet<>();
        for (char c : subBy) {
            subBySet.add(c);
        }
        List<Character> res = new ArrayList<>();
        for (char c : master) {
            if (!subBySet.contains(c)) res.add(c);
        }

        return res;
    }

    private boolean isSame(char[] ca1, char[] ca2) {
        if (ca1.length != ca2.length) return false;
        for (int i = 0; i < ca1.length; i++) {
            if (ca1[i] != ca2[i]) return false;
        }

        return true;
    }
}
