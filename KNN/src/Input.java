import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

    public static List<DataRecord> getDataList(String fileName){
        List<DataRecord> list =  new ArrayList<>();
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            String line;
            while ((line = reader.readLine()) != null){
                String[] vals = line.split(";");
                List<Double> attributesList = new ArrayList<>();
                for (int i=0;i<vals.length-1;i++){
                    attributesList.add(Double.parseDouble(vals[i]));
                }

                list.add(new DataRecord(attributesList,vals[vals.length-1]));
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return list;
    }


    public static void terminal(int k, List<DataRecord> trainList) throws IOException{
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.println("Do you want to provide your vector? YES/NO");
            String info = scanner.next();

            if (info.equals("NO")) {
                System.out.println("You' ve just leaved");
                break;
            }
            else if(info.equals("YES")){
                System.out.println("Pass your own vector with " + trainList.get(0).getAttributes().size() + " elements with spaces");

                String line = reader.readLine();
                List<DataRecord> recordList = new ArrayList<>();
                List<Double> attributesList = new ArrayList<>();
                String[] vals = line.split(" ");

                for (int i=0;i<vals.length;i++){
                    attributesList.add(Double.parseDouble(vals[i]));
                }
                recordList.add(new DataRecord(attributesList));

                System.out.println("Pass k value");
                k=scanner.nextInt();

                Knn.algorithm(trainList,recordList,k,false);
            }
        }
    }

}

