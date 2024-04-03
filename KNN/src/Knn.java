import java.util.*;

public class Knn {

    private static double getDistance(DataRecord testRecord, DataRecord trainRecord){
        double distance=0;
        for (int i = 0;i<testRecord.getAttributes().size();i++){
            distance+=Math.pow((testRecord.getAttributes().get(i) - trainRecord.getAttributes().get(i)),2);
        }

        return Math.sqrt(distance);
    }


    public static void algorithm(List<DataRecord> trainList,List<DataRecord> testList,int k,boolean correctAnswerWanted){
        double correctAnswers=0;


        for (DataRecord testRecord : testList){
            String answer="";
            List<CalculatedDistance> distanceList = new ArrayList<>();

            for (DataRecord trainRecord : trainList){
                distanceList.add(new CalculatedDistance(trainRecord,getDistance(testRecord,trainRecord)));
            }

            Collections.sort(distanceList);

            List<String> labelList = new ArrayList<>();
            Set<String> labelSet = new HashSet<>();
            Map<String, Integer> labelMap = new LinkedHashMap<>();

            for (int j = 0; j < k; j++) {
                labelList.add(distanceList.get(j).getTrainRecord().getLabelName());
                labelSet.add(distanceList.get(j).getTrainRecord().getLabelName());
            }


            for (String label :labelSet) {
                labelMap.put(label,Collections.frequency(labelList,label));
            }

            answer = Collections.max(labelMap.entrySet(), Map.Entry.comparingByValue()).getKey();



            if (correctAnswerWanted) {

                if (answer.equals(testRecord.getLabelName())) {
                    correctAnswers++;
                }

                System.out.println("KNN answer: " + answer);
                System.out.println("Correct answer: " + testRecord.getLabelName());
            }
            else {
                System.out.println("KNN answer: " + answer);
            }

        }

        if (correctAnswerWanted){
            System.out.println("==========================================");
            System.out.println("Total accuracy for k=" + k + ": " + (correctAnswers/testList.size())*100 + "%");
            System.out.println("==========================================");
        }

    }



}
