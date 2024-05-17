import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Input {

    public static List<List<String>> getDataList(String fileName) {
        List<List<String>> list = new ArrayList<>();
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String line;

            while ((line = reader.readLine()) != null) {
                list.add(new ArrayList<>(Arrays.asList(line.split(","))));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
