import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<List<String>> trainSet = Input.getDataList(args[0]);
        List<List<String>> testSet = Input.getDataList(args[1]);

        BayesClassifier.classify(trainSet, testSet);
        boolean running = true;
        while(running){
            System.out.println("Do you want pass your own vector? YES/NO");
            String resp = scanner.next();
            if (resp.equals("NO")){
                System.out.println("You' ve just leaved");
                running = false;
            }
            else if(resp.equals("YES")){
                System.out.println("Pass your own values with , :");
                String[] splitValues = scanner.next().split(",");
                List<List<String>> newTestSet = new ArrayList<>();
                List<String> newRecords = new ArrayList<>(Arrays.asList(splitValues));
                newTestSet.add(newRecords);
                BayesClassifier.classify(trainSet,newTestSet);
            }
        }

    }


}
