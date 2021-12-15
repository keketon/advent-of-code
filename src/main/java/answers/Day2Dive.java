package answers;

import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.*;

@Problem(no = 2)
public class Day2Dive {
    static final String resourcePath = "Day2Dive.txt";
    private final File f = new File(Const.INPUT_DIR + resourcePath);
    private BufferedReader br;

    private void init() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
    }

    @Part(no = 1)
    public String finalPositionProduct() throws IOException, NoSuchMethodException {
        init();

        String str;
        int horizontal = 0, depth = 0;

        while ((str = br.readLine()) != null) {
            String[] command = str.split(" ");
            String direction = command[0];
            int dist = Integer.parseInt(command[1]);

            switch (direction) {
                case "forward":
                    horizontal += dist;
                    break;
                case "down":
                    depth += dist;
                    break;
                case "up":
                    depth -= dist;
                    break;
                default:
                    throw new NoSuchMethodException("No such input direction, " + direction);
            }
        }

        int ans = horizontal * depth;

        return String.valueOf(ans);
    }

    @Part(no = 2)
    public String finalPositionProductWithAim() throws IOException, NoSuchMethodException {
        init();

        String str;
        int horizontal = 0, depth = 0, aim = 0;

        while ((str = br.readLine()) != null) {
            String[] command = str.split(" ");
            String direction = command[0];
            int dist = Integer.parseInt(command[1]);

            switch (direction) {
                case "forward":
                    horizontal += dist;
                    depth += aim * dist;
                    break;
                case "down":
                    aim += dist;
                    break;
                case "up":
                    aim -= dist;
                    break;
                default:
                    throw new NoSuchMethodException("No such input direction, " + direction);
            }
        }

        int ans = horizontal * depth;

        return String.valueOf(ans);
    }
}
