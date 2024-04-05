import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void showInterface(int size, Perceptron perceptron,List<DataRecord> testList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        while (true){
            System.out.println("Do you want learn perceptron again? YES/NO");
            String info = scanner.next();
            if (info.equals("NO")){
                break;
            }
            else if (info.equals("YES")){
                perceptron.learn();
                perceptron.classify(testList,true);
            }

        }

        while (true) {

            System.out.println("Do you want to provide your vector? YES/NO");
            String info = scanner.next();

            if (info.equals("NO")) {
                System.out.println("You' ve just leaved");
                break;
            } else if (info.equals("YES")) {
                System.out.println("Pass your own vector with " + size + " elements with spaces");

                String line = reader.readLine();
                List<DataRecord> recordList = new ArrayList<>();
                List<Double> attributesList = new ArrayList<>();
                String[] vals = line.split(" ");

                for (int i = 0; i < vals.length; i++) {
                    attributesList.add(Double.parseDouble(vals[i]));
                }
                recordList.add(new DataRecord(attributesList));

                perceptron.classify(recordList,false);
            }
        }
    }
}
