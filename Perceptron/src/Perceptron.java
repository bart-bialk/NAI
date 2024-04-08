import java.util.*;

public class Perceptron {

    private List<DataRecord> trainList;
    private List<DataRecord> testList;
    private List<Double> weightList;
    private double alpha;
    private double theta;


    public Perceptron(double alpha,List<DataRecord> trainList,List<DataRecord> testList){
        this.alpha= alpha;
        this.trainList = trainList;
        this.testList=testList;
        this.weightList=fillWeights();
        this.theta = (Math.random()*10)-5;

    }

    private Map<String,Integer> getValuesMap(List<DataRecord> list){
        Map<String,Integer> valuesMap = new HashMap<>();
        for (DataRecord record : list){
            valuesMap.put(record.getLabelName(),null);
        }

        int value = 0;
        for (Map.Entry<String,Integer> entry : valuesMap.entrySet()){
            entry.setValue(value++);
        }

        return valuesMap;
    }

    private List<Double> fillWeights(){
        Random random = new Random();
        List<Double> weights=new ArrayList<>();

        for (int i=0; i<trainList.get(0).getAttributes().size();i++){
            weights.add(random.nextDouble(-5,5));
        }
        return weights;
    }


    public void learn(){
        Map<String,Integer> valuesMap = getValuesMap(trainList);
        Collections.shuffle(trainList,new Random());

            for (DataRecord record : trainList) {
                int y = compute(record);
                int dy = valuesMap.get(record.getLabelName()) - y;

                for (int j = 0; j < weightList.size(); j++) {
                    weightList.set(j, (weightList.get(j) + record.getAttributes().get(j) * dy * alpha));
                }

                theta -= dy * alpha;
            }
    }


    public void classify(List<DataRecord> list,boolean count) {
        Map<String, Integer> classifies = getValuesMap(trainList);
        double correctAnswers = 0;
        double correctRecordZero=0;
        double correctRecordOne=0;

        for (DataRecord record : list) {
            int k = compute(record);
            String label="";
            for (Map.Entry<String,Integer> entry: classifies.entrySet()){
                if (entry.getValue()==k){
                    label=entry.getKey();
                }
            }
            System.out.println("Your label is: " + label);

            if (count) {
                if (k == classifies.get(record.getLabelName())) {
                    correctAnswers++;
                    if (classifies.get(record.getLabelName()) == 0){
                        correctRecordZero++;
                    }
                    else{
                        correctRecordOne++;
                    }
                }

            }
        }

        if (count) {
            double accuracyAll = (correctAnswers / list.size()) * 100;
            double totalZero=0;
            double totalOne=0;
            String flowerZero="";
            String flowerOne="";

            for (DataRecord record : testList){
                if (classifies.get(record.getLabelName()) == 0){
                    flowerZero= record.getLabelName();
                    totalZero++;
                }
                else if(classifies.get(record.getLabelName()) == 1){
                    flowerOne=record.getLabelName();
                    totalOne++;
                }
            }

            double accuracyZero = (correctRecordZero / totalZero) * 100;
            double accuracyOne = (correctRecordOne / totalOne) * 100;
            System.out.println("Total accuracy: " + accuracyAll + "%");
            System.out.println("Accuracy for " + flowerZero + ": " + accuracyZero + "%");
            System.out.println("Accuracy for " + flowerOne + ": " + accuracyOne + "%");
        }
    }

    public int compute(DataRecord record){
        double s = 0;
        for (int i=0;i<record.getAttributes().size();i++){
            s += record.getAttributes().get(i) * weightList.get(i);
        }
        return (s-theta)>=0 ? 1:0;
    }


}
