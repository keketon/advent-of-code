import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SonarSweepDay1 {
    private static final String resourceFileName = "SonarSweepDay1Input.txt";

    public static void main(String[] args) throws IOException {
        File f = new File(Const.INPUT_DIR + resourceFileName);
        BufferedReader br = new BufferedReader(new FileReader(f));

        String str;
        int prv = Integer.MAX_VALUE;
        int increased = 0;

        while ((str = br.readLine()) != null) {
            int now = Integer.parseInt(str);
            if (now > prv) increased++;
            prv = now;
        }

        System.out.println(increased);
    }
}
