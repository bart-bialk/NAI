import java.util.*;

public class Perceptron {
    private List<Double> weightList;
    private double theta;
    private final String perLanguage;


    public Perceptron(String perLanguage, int attributesSize){
        this.weightList = new ArrayList<>();
        for (int i=0; i<attributesSize;i++) {
            //weightList.add(Math.random() * 2 - 1);
            weightList.add(1.0);
        }
        //this.theta = Math.random()*2-1;
        this.theta = 1;
        this.perLanguage = perLanguage;
    }

    public void learn(DataRecord record, double alpha) {
        int y = compute(record.getAttributes()) >= 0 ? 1 : 0;
        int d = record.getLanguage().equals(perLanguage) ? 1 : 0;

        List<Double> weightsPrime = new ArrayList<>(weightList);
        if (y != d){
            for (int i = 0; i < record.getAttributes().size(); i++) {
                weightsPrime.set(i, weightList.get(i) + record.getAttributes().get(i) * (d - y) * alpha);
            }
            weightList = weightsPrime;
            theta = theta - (d - y) * alpha;
        }
    }

    public void normalizeWeights(){
        double sumWeights = 0;
        List<Double> normalizedWeights = new ArrayList<>();

        for (double weight : weightList){
            sumWeights += Math.pow(weight,2) ;
        }
        double length = Math.sqrt(sumWeights);

        for (int i=0; i<weightList.size();i++){
            normalizedWeights.add(weightList.get(i)/length);
        }

        theta = theta/length;
        this.weightList = normalizedWeights;
    }

    public double compute(List<Double> attributes){
        double s = 0;
        for (int i=0;i<attributes.size();i++){
            s += attributes.get(i) * weightList.get(i);
        }
        return s-theta;
    }

    public String getPerLanguage(){
        return perLanguage;
    }

}
