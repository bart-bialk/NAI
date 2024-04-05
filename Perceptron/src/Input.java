import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public static List<DataRecord> getDataList(String fileName) {
        List<DataRecord> list = new ArrayList<>();
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] vals = line.split(";");
                List<Double> attributesList = new ArrayList<>();
                for (int i = 0; i < vals.length - 1; i++) {
                    attributesList.add(Double.parseDouble(vals[i]));
                }
                list.add(new DataRecord(attributesList, vals[vals.length - 1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}