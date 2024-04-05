import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<DataRecord> trainSet = Input.getDataList(args[1]);
        List<DataRecord> testSet = Input.getDataList(args[2]);
        double alpha = Double.parseDouble(args[0]);

        Perceptron perceptron = new Perceptron(alpha,trainSet,testSet);
        perceptron.learn();
        perceptron.classify(testSet,true);

        UI.showInterface(trainSet.get(0).getAttributes().size(),perceptron,testSet);
    }
}
