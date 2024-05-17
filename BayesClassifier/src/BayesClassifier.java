import java.util.*;

public class BayesClassifier {

    public static void classify(List<List<String>> trainSet, List<List<String>> testSet) {
        Set<String> labelSet = new HashSet<>();
        for (List<String> record : trainSet) {
            labelSet.add(record.get(record.size() - 1));
        }

        for (List<String> testRecord : testSet) {
            String outLabel="";
            Map<String, Double> labelsMap = new HashMap<>();

            for (String y : labelSet) {
                double countY = 0, countAllY = 0, result = 1;

                //Y values
                for (List<String> record : trainSet) {
                    if (record.get(record.size() - 1).equals(y)) {
                        countY++;
                    }
                    countAllY++;
                }
                result *= (countY / countAllY);

                //X vector values
                for (int i = 0; i < testRecord.size(); i++) {
                    double countX = 0;

                    for (List<String> record : trainSet) {
                        if (record.get(i).equals(testRecord.get(i)) && record.get(record.size() - 1).equals(y)) {
                            countX++;
                        }
                    }

                    if (countX==0){
                        Set<String> diffXSet = new HashSet<>();
                        for (List<String> record : trainSet){
                           diffXSet.add(record.get(i));
                        }
                        result *= ((countX+1) / (countY + diffXSet.size()));
                    }
                    else {
                        result *= (countX / countY);
                    }
                }

                labelsMap.put(y,result);
            }
            double maxResult = Collections.max(labelsMap.values());
            for (Map.Entry<String,Double> entry : labelsMap.entrySet()){
                if (entry.getValue()==maxResult){
                    outLabel = entry.getKey();
                }
            }
            System.out.println("For input: " + testRecord + " your decision is: " + outLabel);
        }

    }


}
